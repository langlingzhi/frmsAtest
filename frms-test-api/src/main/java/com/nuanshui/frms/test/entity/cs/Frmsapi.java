package com.nuanshui.frms.test.entity.cs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Frmsapi implements Serializable {

    private Integer id;
    private String apiName;
    private Integer productId;
    private Integer status;
    private String reqType;
    private String method;
    private String path;
    private String apiVersion;
    private String writer;
    private String discribe;
    private String paramType;
    private String body;
    private String susResponse;
    private String falResponse;
    private String create_time;
    private String update_time;

    @Override
    public String toString() {
        return "Frmsapi{" +
                "id=" + id +
                ", apiName='" + apiName + '\'' +
                ", productId=" + productId +
                ", status=" + status +
                ", reqType='" + reqType + '\'' +
                ", method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                ", writer='" + writer + '\'' +
                ", discribe='" + discribe + '\'' +
                ", paramType='" + paramType + '\'' +
                ", body='" + body + '\'' +
                ", susResponse='" + susResponse + '\'' +
                ", falResponse='" + falResponse + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}