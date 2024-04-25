package com.req;

public class DFAReq {
    private String k;    // 状态集

    private String letters;     // 字母表

    private String f;       // 转换函数

    private String s;              // 唯一初态

    private String z;    // 终态集

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "DFAReq{" +
                "k='" + k + '\'' +
                ", letters='" + letters + '\'' +
                ", f='" + f + '\'' +
                ", s='" + s + '\'' +
                ", z='" + z + '\'' +
                '}';
    }
}