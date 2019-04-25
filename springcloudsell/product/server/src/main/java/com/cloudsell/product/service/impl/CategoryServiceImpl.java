package com.cloudsell.product.service.impl;

import com.cloudsell.product.dataobject.ProductCategory;
import com.cloudsell.product.repository.ProductCategoryRepository;
import com.cloudsell.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Haiyu
 * @date 2019/3/14 16:59
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
