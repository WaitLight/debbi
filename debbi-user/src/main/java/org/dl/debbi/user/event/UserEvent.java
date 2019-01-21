package org.dl.debbi.user.event;

import org.dl.debbi.common.event.Event;
import org.dl.debbi.user.account.domain.Account;

public class UserEvent implements Event<Account> {

    @Override
    public Account getSource() {
        return null;
    }
}
