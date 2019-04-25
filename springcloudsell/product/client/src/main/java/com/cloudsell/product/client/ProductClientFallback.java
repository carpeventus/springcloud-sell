package com.cloudsell.product.client;

import com.cloudsell.product.common.DecreaseStockInput;
import com.cloudsell.product.common.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/4/21 23:30
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String productMsg() {
        return "服务降级";
    }

    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {

    }
}
