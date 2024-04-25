package com.service;

import com.auxiliary.Producter;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LLAnalysisService {
//    Analysis
    public String[] analysis(Map<Producter, Set<Character>> select, String str);
//    public boolean isVN(Character c);
//    public boolean isVT(Character c);

//  F_F_S
    public Map<Character, Set<Character>> firstV(Set<Producter> gs);
    public Map<String, Set<Character>> first(Set<Producter> gs);
    public Map<Character, Set<Character>> follow(Set<Producter> gs);
    public Map<Producter, Set<Character>> select(Set<Producter> gs);
    public List<Set<Character>> can_2_empty(Set<Producter> gs);

//    LL1
    public boolean isLL1(Set<Producter> gs);
    public Set<Producter> producterWithVN(Character vn, Set<Producter> gs);


    public void extractCommonFactor(Set<Producter> gs);
    public void removeRecursin(Set<Producter> gs);
    public char[] getUnUsedCase(Set<Producter> gs);
    public void transGs(Set<Producter> gs);
    public void removeUnReachable(Set<Producter> gs);
    public void removeFromGs(Set<Producter> gs, Character left, String right);
    public void removeFromGs(Set<Producter> gs, Character left);
    public void addToGs(Set<Producter> gs, Character newVN, String s, String commonFactor);
}
