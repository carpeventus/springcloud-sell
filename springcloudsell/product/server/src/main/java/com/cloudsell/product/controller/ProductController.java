package com.cloudsell.product.controller;

import com.cloudsell.product.common.DecreaseStockInput;
import com.cloudsell.product.dataobject.ProductCategory;
import com.cloudsell.product.dataobject.ProductInfo;
import com.cloudsell.product.service.CategoryService;
import com.cloudsell.product.service.ProductService;
import com.cloudsell.product.util.ResultVOUtil;
import com.cloudsell.product.vo.ProductInfoVO;
import com.cloudsell.product.vo.ProductVO;
import com.cloudsell.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Haiyu
 * @date 2019/3/14 15:02
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    CategoryService categoryService;

    /**
     * 查询商品列表
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list() {
        // 1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 2. 获取类目的type列表
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        // 3. 查询类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                // 商品的类目和当前类目一样，才把该商品展示在当前类目下
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    // 只会拷贝匹配的属性（属性名相同）
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 根据商品id列表查询所有对应的商品（创建订单所需）
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productService.findByProductIdList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList) {
        productService.decreaseStock(cartDTOList);
    }
}
