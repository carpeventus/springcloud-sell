package com.cloudsell.order.repository;

import com.cloudsell.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Haiyu
 * @date 2018/10/16 13:06
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
