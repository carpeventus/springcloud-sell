package com.cloudsell.product.client;


import com.cloudsell.product.common.DecreaseStockInput;
import com.cloudsell.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 定义要访问的接口，应用名为product的/msg地址
 * @author Haiyu
 * @date 2019/3/17 16:37
 */
//@FeignClient(name = "product",fallback = ProductClientFallback.class)
@FeignClient(name = "product")
public interface ProductClient {
    // 这里的RequestMapping并不是作为服务端的地址映射
    // 而是表明当前应用要去访问product应用的/msg地址
    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList);

}

