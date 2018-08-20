package org.dl.debbi.common.error.domain;

import lombok.Data;

import java.util.List;

@Data
public class ErrorLog {
    public String message;
    public List<String> stackInfo;
    public long date;

    public ErrorLog(String message, List<String> stackInfo, long date) {
        this.message = message;
        this.stackInfo = stackInfo;
        this.date = date;
    }
}
