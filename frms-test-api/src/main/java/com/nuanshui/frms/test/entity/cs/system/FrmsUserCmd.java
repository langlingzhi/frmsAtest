package com.nuanshui.frms.test.entity.cs.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FrmsUserCmd implements Serializable {
    private Integer id;
    private String no;
    private String name;
    private String password;
    private Integer status;
    private String NewPwd;
    private String OldPwd;

    @Override
    public String toString() {
        return "SysUserCmd{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", NewPwd=" + NewPwd +
                ", OldPwd='" + OldPwd + '\'' +
                '}';
    }
}
