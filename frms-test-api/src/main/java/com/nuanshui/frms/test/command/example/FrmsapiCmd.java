package com.nuanshui.frms.test.command.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FrmsapiCmd implements Serializable {
    private String apiName;
    private String status;
    private Integer productId;

    @Override
    public String toString() {
        return "FrmsapiCmd{" +
                "name='" + apiName + '\'' +
                ", status='" + status + '\'' +
                ", productid=" + productId +
                '}';
    }
}
