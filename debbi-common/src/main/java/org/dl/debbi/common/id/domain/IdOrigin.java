package org.dl.debbi.common.id.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class IdOrigin {
    @Id
    private int process;
    private int business;
    private long originTimeMillis;
}