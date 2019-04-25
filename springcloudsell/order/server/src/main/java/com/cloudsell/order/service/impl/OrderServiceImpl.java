package com.cloudsell.order.service.impl;

import com.cloudsell.order.dataobject.OrderDetail;
import com.cloudsell.order.dataobject.OrderMaster;
import com.cloudsell.order.dto.OrderDTO;
import com.cloudsell.order.enums.OrderStatusEnum;
import com.cloudsell.order.enums.PayStatusEnum;
import com.cloudsell.order.enums.ResultEnum;
import com.cloudsell.order.exception.OrderException;
import com.cloudsell.order.repository.OrderDetailRepository;
import com.cloudsell.order.repository.OrderMasterRepository;
import com.cloudsell.order.service.OrderService;
import com.cloudsell.order.util.KeyUtil;
import com.cloudsell.product.client.ProductClient;
import com.cloudsell.product.common.DecreaseStockInput;
import com.cloudsell.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Haiyu
 * @date 2018/10/17 9:44
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 下单的那一刻就会产生一条订单号
        String orderId = KeyUtil.genUniqueKey();
        // 总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        // 1.查询商品（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
//            // 2.计算一件商品的总价，遍历所有商品累加得到订单总价
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    // 3.写入订单详情（order_detail）
                    // 注意一定要先拷贝属性，再设置，不然拷贝时会将set的值覆盖
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        // 4.减库存
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);
        // 5.写入订单数据（order_master）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        // 属性拷贝后，将默认值覆盖了，所有要重新设置
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO finish(String  orderId) {
        // 1. 查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        // 2. 判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (!OrderStatusEnum.NEW.getCode().equals(orderMaster.getOrderStatus())) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        // 3. 查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }


}
