package org.dl.debbi.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.dl.debbi.common.error.DebbiException;
import org.dl.debbi.common.error.Error;
import org.dl.debbi.common.error.Status;

import java.io.Serializable;

@Data
public final class Response implements Serializable {
    public int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Detail detail;

    private Response() {
    }


    public static Response success(Object data) {
        Response response = new Response();
        response.status = Status.OK.code();
        response.data = data;
        return response;
    }

    public static Response fail(Error error) {
        Response response = new Response();
        response.status = error.status().code();
        response.message = error.status().name();
        response.detail = new Detail(error.code(), error.name());
        return response;
    }

    static final class Detail {
        int code;
        String message;

        Detail(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}