package org.dl.debbi.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户授权信息
 *
 * @author Dean
 * @version 0.0.1
 */
@Setter
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
public class Authority implements Serializable {

    @Transient
    private static final long serialVersionUID = -7699003675236861824L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(255) COMMENT '用户名'")
    private String username;

    @Column(nullable = false, columnDefinition = "varchar(255) COMMENT '密码'")
    private String password;
}
