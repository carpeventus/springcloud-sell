package com.cloudsell.product.service.impl;

import com.cloudsell.product.common.DecreaseStockInput;
import com.cloudsell.product.common.ProductInfoOutput;
import com.cloudsell.product.dataobject.ProductInfo;
import com.cloudsell.product.enums.ProductStatusEnum;
import com.cloudsell.product.enums.ResultEnum;
import com.cloudsell.product.exception.ProductException;
import com.cloudsell.product.repository.ProductInfoRepository;
import com.cloudsell.product.service.ProductService;
import com.cloudsell.product.util.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Haiyu
 * @date 2019/3/14 16:55
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findByProductIdList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(cartDTOList);
        List<ProductInfoOutput> productInfoOutputs = productInfoList.stream().map(e -> {
            ProductInfoOutput p = new ProductInfoOutput();
            BeanUtils.copyProperties(e, p);
            return p;
        }).collect(Collectors.toList());
        // 扣库存后发送MQ消息
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputs));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> cartDTOList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);

            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

            productInfoList.add(productInfo);

        }
        return productInfoList;
    }
}
