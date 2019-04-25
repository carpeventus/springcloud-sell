package com.cloudsell.product.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Haiyu
 * @date 2018/10/15 16:29
 */
@Data
@Entity
@DynamicInsert
public class ProductInfo implements Serializable {

    @Id
    private String productId;

    /** 商品名字 */
    private String productName;

    /** 商品价格 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private Integer productStock;

    /** 商品描述 */
    private String productDescription;

    /** 商品小图 */
    private String productIcon;

    /** 商品状态，0上架、1下架 */
    private Integer productStatus;

    /** 商品类目 */
    private Integer categoryType;

    /** 创建日期 */

    private Date createTime;
    /** 更新日期 */
    private Date updateTime;
}
