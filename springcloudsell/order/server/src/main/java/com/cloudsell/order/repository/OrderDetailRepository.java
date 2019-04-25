package com.cloudsell.order.repository;

import com.cloudsell.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Haiyu
 * @date 2018/10/16 13:07
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**
     * 根据订单号查询订单详情，一个订单对应多个订单详情
     *
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
