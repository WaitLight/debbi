package org.dl.debbi.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.dl.debbi.common.error.DebbiException;

import java.io.Serializable;

@Data
public final class Response implements Serializable {
    public boolean succ;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object data;
    /*-------错误信息-------*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String err;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String hash;

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
        return response;
    }

    public static Response err(DebbiException e, String hash) {
        Response response = err(e);
        response.hash = hash;
        return response;
    }
}
