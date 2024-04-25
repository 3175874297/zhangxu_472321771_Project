package com.req;

public class LexicalAnalysisReq {
    private String program;

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "LexicalAnalysisReq{" +
                "program='" + program + '\'' +
                '}';
    }
}
