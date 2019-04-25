package com.cloudsell.order.service;

import com.cloudsell.order.dto.OrderDTO;

/**
 * @author Haiyu
 * @date 2018/10/17 9:39
 */
public interface OrderService {
    /** 创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单（只能卖家操作）
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
