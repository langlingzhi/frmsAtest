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
public class FrmsEnv implements Serializable {

    private Integer id;
    private String productname;
    private String envtest;
    private String envpro;
    private String protocol;
    private String status;

    @Override
    public String toString() {
        return "FrmsEnv{" +
                "id=" + id +
                ", productname='" + productname + '\'' +
                ", envtest='" + envtest + '\'' +
                ", envpro='" + envpro + '\'' +
                ", protocol='" + protocol + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
