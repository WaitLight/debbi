package org.dl.debbi.user.account.domain;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Accessors(chain = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "register")
public class Account {

    @Id
    private Long id;

    @Column(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    private Instant created;
    private Instant updated;
    private Instant deleted;

    public boolean isDeleted() {
        return null == deleted;
    }
}