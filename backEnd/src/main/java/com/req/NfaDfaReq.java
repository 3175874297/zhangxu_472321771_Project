package com.req;

import java.util.Arrays;

public class NfaDfaReq {
    private String k;    // 状态集

    private char[] letters;     // 字母表1

    private String f;       // 转换函数1

    private String s;    // 初态集1

    private String z;    // 终态集1

    private int n;              //规定数组的长度

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public char[] getLetters() {
        return letters;
    }

    public void setLetters(char[] letters) {
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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "NfaDfaReq{" +
                "k='" + k + '\'' +
                ", letters=" + Arrays.toString(letters) +
                ", f='" + f + '\'' +
                ", s='" + s + '\'' +
                ", z='" + z + '\'' +
                ", n=" + n +
                '}';
    }
}
