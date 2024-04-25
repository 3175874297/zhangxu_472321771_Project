package com.resp;

public class LLContentResp {
    private String[] first;        //旧的first集
    private String[] follow;       //旧的Follow集
    private String[] select;       //旧的Select集
    private boolean judge;         //是否是LL1文法
    private String[] newFirst;     //新的First集
    private String[] newFollow;    //新的Follow集
    private String[] newSelect;    //新的Select集

    public String[] getFirst() {
        return first;
    }

    public void setFirst(String[] first) {
        this.first = first;
    }

    public String[] getFollow() {
        return follow;
    }

    public void setFollow(String[] follow) {
        this.follow = follow;
    }

    public String[] getSelect() {
        return select;
    }

    public void setSelect(String[] select) {
        this.select = select;
    }

    public boolean isJudge() {
        return judge;
    }

    public void setJudge(boolean judge) {
        this.judge = judge;
    }

    public String[] getNewFirst() {
        return newFirst;
    }

    public void setNewFirst(String[] newFirst) {
        this.newFirst = newFirst;
    }

    public String[] getNewFollow() {
        return newFollow;
    }

    public void setNewFollow(String[] newFollow) {
        this.newFollow = newFollow;
    }

    public String[] getNewSelect() {
        return newSelect;
    }

    public void setNewSelect(String[] newSelect) {
        this.newSelect = newSelect;
    }
}
