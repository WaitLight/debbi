package org.dl.debbi.user.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Account {

    @Id
    public long id;

    @Column(unique = true)
    public String principal;

    public String certificate;

    public Date created;
    public Date deleted;
}