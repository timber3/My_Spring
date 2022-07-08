package com.timber.timberspring.domain;

public class Member {
    private Long id;    // 시스템에 의해 저장되는 아이디 ( 숫자로 구분하는 아이디 )
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
