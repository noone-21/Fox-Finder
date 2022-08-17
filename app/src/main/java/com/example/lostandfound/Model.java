package com.example.lostandfound;

public class Model {
    private String header;
    private String desc;
    private String name;
    private String phoneNum;
    private byte[] imgName;

    public Model(String header,String name, String desc, String phoneNum, byte[] imgName) {
        this.header = header;
        this.desc = desc;
        this.name = name;
        this.phoneNum = phoneNum;
        this.imgName = imgName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImgName() {
        return imgName;
    }

    public void setImgName(byte[] imgName) {
        this.imgName = imgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
