package com.nuanshui.frms.test.entity.cs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FrmsCase implements Serializable{

    private Integer id;
    private String caseid;
    private Integer apiid;
    private String casename;
    private String requestparam;
    private String relstatus;
    private String relresponse;
    private String expstatus;
    private String expresponse;
    private String verifyobj;
    private String spendtime;
    private String verifyresult;
    private String log;
    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;


}
