package com.cloudsell.product.service;

import com.cloudsell.product.common.DecreaseStockInput;
import com.cloudsell.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/3/14 16:54
 */
public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 根据商品id列表查询对应的商品
     * @param productIdList
     * @return
     */
    List<ProductInfo> findByProductIdList(List<String> productIdList);

    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
