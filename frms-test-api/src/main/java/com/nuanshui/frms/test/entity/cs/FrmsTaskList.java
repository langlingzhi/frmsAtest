package com.nuanshui.frms.test.entity.cs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FrmsTaskList implements Serializable{
    private Integer id;
    private String taskname;
    private Integer productid;
    private String productname;
    private String env;
    private String envname;
    private String report;
    private String jobtime;
    private String email;
    private String creater;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_time;

}
