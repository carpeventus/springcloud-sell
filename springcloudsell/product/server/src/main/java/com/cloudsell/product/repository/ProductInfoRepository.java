package com.cloudsell.product.repository;

import com.cloudsell.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Haiyu
 * @date 2018/10/15 16:56
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    /**
     * 按照商品的状态查询
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
    List<ProductInfo> findByProductIdIn(List<String> productIdList);

}
