package org.dl.debbi.common.event;

import java.io.Serializable;

public interface Event<S> extends Serializable {
    S getSource();
}