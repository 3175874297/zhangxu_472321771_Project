package com.controller;

import com.req.LRContentReq;
import com.req.LRExpressionReq;
import com.resp.LRAnalysisProcessResp;
import com.resp.LRPremiseResp;
import com.service.LRAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

@RestController
@RequestMapping("/LR")
public class LRController {

    private static final char Njump = 'ε';

    @Autowired
    private LRAnalysisService lrAnalysisService;

    @PostMapping("LRContent")
    public LRPremiseResp LRAnalysisContent(@RequestBody LRContentReq lrContentReq){
        LRPremiseResp lrPremiseResp = new LRPremiseResp();
        String S="E";//开始符
//        String P[]={"E->aA|bB","A->cA|d","B->cB|d"};//规则集
//        String P[]={"E->aA|bBC","C->"+Njump,"A->cA|d","B->cB|d"};//规则集
//        System.out.print("请输入规则集：");
//        Scanner scanner = new Scanner(System.in);
//        String n=scanner.nextLine();
        String[] strings=lrContentReq.getContent().split(" ");
        lrAnalysisService.Grammar(strings,S);
        lrPremiseResp = lrAnalysisService.out();
        return lrPremiseResp;
    }
    @PostMapping("LRExpression")
    public LRAnalysisProcessResp LRAnalysisProcess(@RequestBody LRExpressionReq lrExpressionReq){
        LRAnalysisProcessResp lrAnalysisProcessResp = new LRAnalysisProcessResp();
        String[] pro=lrAnalysisService.contains(lrExpressionReq.getExpression());
        String[] strings=new String[pro.length-1];
        String ss=pro[pro.length-1];
        System.out.println("Boolean"+ss);
        if(ss.equals("false")){
            lrAnalysisProcessResp.setJudge(false);
        }
        if(ss.equals("true")){
            lrAnalysisProcessResp.setJudge(true);
        }
        for (int i = 0; i < pro.length-1; i++) {
            strings[i]=pro[i];
        }
        for (String s:strings) {
            System.out.println(s);
        }
        lrAnalysisProcessResp.setProcess(strings);
        return lrAnalysisProcessResp;
    }
}
