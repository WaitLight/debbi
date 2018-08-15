package org.dl.debbi.common.vo;

import org.dl.debbi.common.error.DebbiException;

import java.io.Serializable;

public final class Response implements Serializable {
    public boolean succ;
    public Object data;
    /*-------错误信息-------*/
    public String err;
    public int code;
    public int hash;

    private Response() {
    }


    public static Response succ(Object data) {
        Response response = new Response();
        response.succ = true;
        response.data = data;
        return response;
    }

    public static Response err(DebbiException e) {
        Response response = new Response();
        response.succ = false;
        response.err = e.getErr();
        response.code = e.getCode();
        response.data = e.getData();
        response.hash = e.getHash();
        return response;
    }
}
