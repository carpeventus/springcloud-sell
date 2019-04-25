package com.cloudsell.product.service;

import com.cloudsell.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/3/14 16:58
 */
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
