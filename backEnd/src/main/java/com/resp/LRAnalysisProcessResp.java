package com.resp;

public class LRAnalysisProcessResp {
    private String[] process;
    private Boolean judge;   //判断是否是表达式

    public String[] getProcess() {
        return process;
    }

    public void setProcess(String[] process) {
        this.process = process;
    }

    public Boolean getJudge() {
        return judge;
    }

    public void setJudge(Boolean judge) {
        this.judge = judge;
    }
}
