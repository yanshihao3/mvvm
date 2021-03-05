package com.zq.network.model;

import java.io.Serializable;

/**
 *
 */
public class LzyResponse<T> implements Serializable {

    public int code;
    public String message;
    public T data;

    @Override
    public String toString() {
        return "LzyResponse{\n" +//
                "\tcode=" + code + "\n" +//
                "\tmsg='" + message + "\'\n" +//
                "\tdata=" + data + "\n" +//
                '}';
    }
}
