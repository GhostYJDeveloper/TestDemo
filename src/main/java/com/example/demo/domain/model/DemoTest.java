package com.example.demo.domain.model;

public class DemoTest {
    private Long id;
    private String chinesename;

    public DemoTest(){

    }

    public DemoTest(Long id,String chinesename){
        this.id=id;
        this.chinesename=chinesename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getchinesename() {
        return chinesename;
    }

    public void setchinesename(String chinesename) {
        this.chinesename = chinesename;
    }
}
