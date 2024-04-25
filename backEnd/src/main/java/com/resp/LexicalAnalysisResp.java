package com.resp;

public class LexicalAnalysisResp {
    private String[] symbol;
    private String[] dfa;
    private String[] error;
    private String[] handle;
    private String[] type;
    private int ind;

    public String[] getSymbol() {
        return symbol;
    }

    public void setSymbol(String[] symbol) {
        this.symbol = symbol;
    }

    public String[] getDfa() {
        return dfa;
    }

    public void setDfa(String[] dfa) {
        this.dfa = dfa;
    }

    public String[] getError() {
        return error;
    }

    public void setError(String[] error) {
        this.error = error;
    }

    public String[] getHandle() {
        return handle;
    }

    public void setHandle(String[] handle) {
        this.handle = handle;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }
//    @Override
//    public String toString() {
//        return "LexicalAnalysisResp{" +
//                "symbol=" + Arrays.toString(symbol) +
//                ", dfa=" + Arrays.toString(dfa) +
//                ", error=" + Arrays.toString(error) +
//                ", handle=" + Arrays.toString(handle) +
//                ", type=" + Arrays.toString(type) +
//                '}';
//    }
}
