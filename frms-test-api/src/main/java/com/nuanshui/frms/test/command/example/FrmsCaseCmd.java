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
public class FrmsCaseCmd implements Serializable{
    private Integer apiid;
    private String casename;
    private Integer status;

    @Override
    public String toString() {
        return "FrmsCaseCmd{" +
                "apiid=" + apiid +
                ", casename='" + casename + '\'' +
                '}';
    }
}
