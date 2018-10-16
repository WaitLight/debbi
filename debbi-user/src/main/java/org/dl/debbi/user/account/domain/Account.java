package org.dl.debbi.user.account.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "register")
public class Account {

    @Id
    public long id;

    @Column(unique = true)
    @NonNull
    public String username;
    @NonNull
    public String password;

    public Date created;
    public Date deleted;

    public boolean isDeleted() {
        return deleted == null;
    }
}