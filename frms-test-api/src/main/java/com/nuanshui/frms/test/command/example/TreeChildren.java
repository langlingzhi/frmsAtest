package com.nuanshui.frms.test.command.example;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
public class TreeChildren implements Serializable{
    private String id;//节点ID
    private String title;//节点名称
    private boolean isLast;
    private String parentId;//父节点ID
    private String level;//层级
    private String checkArr;

    public boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(boolean last) {
        isLast = last;
    }
}
