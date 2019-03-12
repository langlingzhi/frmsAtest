package com.nuanshui.frms.test.entity.cs;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class FrmsCaseList implements Serializable {
    private Integer id;
    private String caseid;
    private String casename;
    private String apiName;
    private String requestparam;
    private String expstatus;
    private String verifyobj;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;
    private String verifyresult;
}
