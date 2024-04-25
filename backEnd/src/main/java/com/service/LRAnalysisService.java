package com.service;

import com.resp.LRPremiseResp;

import java.util.Set;

public interface LRAnalysisService {
    public void Grammar(String[] ip, String S);
//    public void calculationCLOSURE(Set<Production> I);
//    public void calculationDFA();
    public LRPremiseResp out();
//    public boolean createLRTable();
    public String[] contains(String st);
}
