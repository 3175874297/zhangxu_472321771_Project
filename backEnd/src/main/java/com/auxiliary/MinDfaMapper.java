package com.auxiliary;

import java.util.Arrays;
import java.util.List;

public class MinDfaMapper {
    private List<Integer> k;    // 状态集

    private char[] letters;     // 字母表

    private String[][] f;       // 转换函数

    private int s;              // 唯一初态

    private List<Integer> z;    // 终态集

    public List<Integer> getK() {
        return k;
    }

    public void setK(List<Integer> k) {
        this.k = k;
    }

    public char[] getLetters() {
        return letters;
    }

    public void setLetters(char[] letters) {
        this.letters = letters;
    }

    public String[][] getF() {
        return f;
    }

    public void setF(String[][] f) {
        this.f = f;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public List<Integer> getZ() {
        return z;
    }

    public void setZ(List<Integer> z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "MinDfaMapper{" +
                "k=" + k +
                ", letters=" + Arrays.toString(letters) +
                ", f=" + Arrays.toString(f) +
                ", s=" + s +
                ", z=" + z +
                '}';
    }
}
