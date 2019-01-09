package org.dl.debbi.user.account.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

import static org.dl.debbi.user.account.utils.BlackCharUtil.assertNotContentBlackChar;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Accessors(chain = true)
public class Account {

    @Id
    private Long id;

    @Column(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    private long created;
    private long updated;
    private Long deleted;

    public static Account signUp(String username, String password) {
        long now = System.currentTimeMillis();
        return new Account()
                .setUsername(username)
                .setPassword(password)
                .setCreated(now)
                .setUpdated(now);
    }

    public Account setUsername(String username) {
        assertNotContentBlackChar(username);
        this.username = username;
        return this;
    }

    public boolean isDeleted() {
        return null == deleted;
    }
}