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
public class FrmsVparam implements Serializable {
    private Integer id;
    private Integer caseid;
    private String paramKey;
    private String paramType;
    private String pramValue;
}
