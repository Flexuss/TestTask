package com.ivanovdmitry;

import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Dmitry on 20.09.2017.
 */

public class Response {

    private String result;

    public Response(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
