package com.cloudsell.order.controller;

import com.cloudsell.order.converter.OrderForm2OrderDTO;
import com.cloudsell.order.dto.OrderDTO;
import com.cloudsell.order.enums.ResultEnum;
import com.cloudsell.order.exception.OrderException;
import com.cloudsell.order.form.OrderForm;
import com.cloudsell.order.service.OrderService;
import com.cloudsell.order.util.ResultVOUtil;
import com.cloudsell.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Haiyu
 * @date 2018/10/19 8:43
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 1. 参数校验
     * 2. 查询商品信息（调用商品服务）
     * 3. 计算总价
     * 4. 扣库存（调用商品服务）
     * 5. 订单入库
     * @return
     */
    @PostMapping("/create")
    @Transactional
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new OrderException(ResultEnum.ORDER_PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        // OrderFor -> OrderDTO
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new OrderException(ResultEnum.ORDER_CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    @PostMapping("/finish")
    @Transactional
    public ResultVO finish(@RequestParam("orderId") String orderId) {
        return ResultVOUtil.success(orderService.finish(orderId));
    }
}
