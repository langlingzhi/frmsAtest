package com.nuanshui.frms.test.command;

import lombok.Getter;

public enum DataSourceType {
    cs("cs", "从库"),
    app("app", "从库"),
    read("read", "从库"),
    write("write", "主库");

    @Getter
    private String type;
    @Getter
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
