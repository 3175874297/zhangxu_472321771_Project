package com.resp;

import java.util.Arrays;
import java.util.List;

public class NfaDfaResp {
    private List<Integer> k;    // 状态集

    private char[] letters;     // 字母表

    private String[] f;       // 转换函数

    private int s;              // 唯一初态

    private List<Integer> z;    // 终态集

    private String[] Queue;     //状态进出队列的变化

    private String[][] state;    //出队列

    private String[] change;    //新状态是由那几个老状态合成

    private int index;

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

    public String[] getF() {
        return f;
    }

    public void setF(String[] f) {
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

    public String[] getQueue() {
        return Queue;
    }

    public void setQueue(String[] queue) {
        Queue = queue;
    }

    public String[][] getState() {
        return state;
    }

    public void setState(String[][] state) {
        this.state = state;
    }

    public String[] getChange() {
        return change;
    }

    public void setChange(String[] change) {
        this.change = change;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "NfaDfaResp{" +
                "k=" + k +
                ", letters=" + Arrays.toString(letters) +
                ", s=" + s +
                ", z=" + z +
                ", index=" + index +
                '}';
    }
}
