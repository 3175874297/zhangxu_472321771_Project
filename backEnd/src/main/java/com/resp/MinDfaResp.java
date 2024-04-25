package com.resp;

import java.util.Arrays;

public class MinDfaResp {
    private String newSet;               //新状态

    private String[] func;              //状态转换函数

    private String[] process;           //过程

    private String[] state;             //状态转换

    private String end;                  //终态集

    public String getNewSet() {
        return newSet;
    }

    public void setNewSet(String newSet) {
        this.newSet = newSet;
    }

    public String[] getFunc() {
        return func;
    }

    public void setFunc(String[] func) {
        this.func = func;
    }

    public String[] getProcess() {
        return process;
    }

    public void setProcess(String[] process) {
        this.process = process;
    }

    public String[] getState() {
        return state;
    }

    public void setState(String[] state) {
        this.state = state;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "MinDfaResp{" +
                "newSet='" + newSet + '\'' +
                ", func=" + Arrays.toString(func) +
                ", process=" + Arrays.toString(process) +
                ", state=" + Arrays.toString(state) +
                ", end='" + end + '\'' +
                '}';
    }
}

