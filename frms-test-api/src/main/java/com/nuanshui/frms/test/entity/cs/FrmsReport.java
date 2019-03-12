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
@AllArgsConstructor
@NoArgsConstructor
public class FrmsReport implements Serializable {
    private Integer id;
    private String reportId;
    private String testName;
    private String testPass;
    private String testFail;
    private String testSkip;
    private String testAll;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date beginTime;
    private String totalTime;
    private String testResult;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String create_time;
}
