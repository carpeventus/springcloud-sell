package com.cloudsell.user.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Haiyu
 * @date 2019/4/19 15:24
 */
@Data
@Entity
public class UserInfo {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;

}
