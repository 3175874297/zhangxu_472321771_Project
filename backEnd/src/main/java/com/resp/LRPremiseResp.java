package com.resp;

public class LRPremiseResp {
    private String vn;                  //非终结符
    private String vt;                  //终结符
    private String expand;              //拓广文法
    private String start;               //开始符
    private String DFAFunc;             //DFA状态转换函数
    private String tableHeader;         //LR分析表表头
    private String[] tableContent;    //LR分析表内容

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getVt() {
        return vt;
    }

    public void setVt(String vt) {
        this.vt = vt;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDFAFunc() {
        return DFAFunc;
    }

    public void setDFAFunc(String DFAFunc) {
        this.DFAFunc = DFAFunc;
    }

    public String getTableHeader() {
        return tableHeader;
    }

    public void setTableHeader(String tableHeader) {
        this.tableHeader = tableHeader;
    }

    public String[] getTableContent() {
        return tableContent;
    }

    public void setTableContent(String[] tableContent) {
        this.tableContent = tableContent;
    }
}
