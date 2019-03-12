package com.nuanshui.frms.test.command.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FrmsTest {
    private String reqType;
    private String method;
    private String path;
    private String body;
    private ResponseBody response;
}
