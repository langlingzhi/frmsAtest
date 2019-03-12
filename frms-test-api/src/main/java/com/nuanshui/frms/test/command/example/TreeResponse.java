package com.nuanshui.frms.test.command.example;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TreeResponse implements Serializable {
    private String id;//节点ID
    private String title;//节点名称
    private boolean isLast;
    private String parentId;//父节点ID
    private String level;//层级
    private String basicData;//表示用户自定义需要存储在树节点中的数据
    private String checkArr;//复选框列表
    private String iconClass;//自定义图标class
    private String children;//子节点列表


    public boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(boolean last) {
        isLast = last;
    }
}
