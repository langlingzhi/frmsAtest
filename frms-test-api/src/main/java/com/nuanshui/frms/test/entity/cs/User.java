package com.nuanshui.frms.test.entity.cs;

public class User {
    private long id;
    private String username;
    private String password;
    private String loginIdentification;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User(long id, String username, String password, String loginIdentification) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.loginIdentification = loginIdentification;
    }
}
