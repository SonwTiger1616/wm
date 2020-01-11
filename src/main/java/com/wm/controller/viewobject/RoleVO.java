package com.wm.controller.viewobject;

public class RoleVO {
    private Integer id;
    private String name;
    private boolean ifAdmin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIfAdmin() {
        return ifAdmin;
    }

    public void setIfAdmin(boolean ifAdmin) {
        this.ifAdmin = ifAdmin;
    }
}
