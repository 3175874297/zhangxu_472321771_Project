package com.controller;

import com.auxiliary.Producter;
import com.req.LLContentReq;
import com.req.LLExpressionReq;
import com.resp.LLContentResp;
import com.resp.LLExperssionResp;
import com.service.LLAnalysisService;
import com.utils.GSBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/LL")
public class LLController {

    @Autowired
    private LLAnalysisService ll;

    private Set<Producter> ggs;


    @PostMapping("LLanalysis")
    public LLContentResp LLAnalysis(@RequestBody LLContentReq LL){
        System.out.println(LL.getContent());
        System.out.println("==========================================");

        System.out.println("请输入文法GS（一行一行的输入生成式，end表示结束）：");
        Set<Producter> gs = GSBuilder.build(LL);
//      处理输入的字符串
        Map<String,Set<String>> map2 = new HashMap<>();
        Set<String> ss=new HashSet<>();
        String[] s;
        Map<Character,Set<Character>> mapFirst = new HashMap<>();
        Set<String> set2 = new HashSet<>();

        for (Producter strings:gs) {
            s=strings.getProducter().split("->");
            System.out.println(s[0]+"--->"+s[1]);
            ss.add(s[0]);
            if(!map2.containsKey(s[0]))
                map2.put(s[0],new HashSet<>());
            map2.get(s[0]).add(s[1]);
        }
//        处理输入的字符串
        LLContentResp llContentResp = new LLContentResp();
        System.out.println("------------------------------------------");
        System.out.println("首符集First是：");
        Map<String, Set<Character>> first = ll.first(gs);

        for(String str : first.keySet()){
            System.out.print(str);
            String Fei=new String();
            for (String value:map2.keySet()) {
                if(map2.get(value).contains(str)){
                    Fei=value;
                }
            }
            if(!mapFirst.containsKey(Fei.charAt(0)))
                mapFirst.put(Fei.charAt(0),new HashSet<>());
            for (char ch :first.get(str)) {
                if(!Character.isUpperCase(ch))
                    mapFirst.get(Fei.charAt(0)).add(ch);
            }
            System.out.print(" : ");
            System.out.println(first.get(str));
        }
//        旧First集赋值
        Set<Character> characters = mapFirst.keySet();
        String[] oldFirst=new String[characters.size()];
        int index=0;
        for (Character ch:characters){
            oldFirst[index]=ch+" {";
            for (Character ch1:mapFirst.get(ch)) {
                oldFirst[index]+=ch1+",";
            }
            oldFirst[index]=oldFirst[index].substring(0,oldFirst[index].length()-1);
            oldFirst[index]+="}";
            index++;
        }
        llContentResp.setFirst(oldFirst);


        System.out.println("------------------------------------------");
        System.out.println("随符集Follow是：");
        Map<Character, Set<Character>> follow = ll.follow(gs);
        String[] oldFollow=new String[follow.keySet().size()];
        for (int i = 0; i < follow.keySet().size(); i++) {
            oldFollow[i]="";
        }
        int indexOldFollow=0;

        for(Character key : follow.keySet()){
            System.out.print(key);
            System.out.print(" : ");
            oldFollow[indexOldFollow]+=key+" {";
            for (Character ch:follow.get(key)) {
                oldFollow[indexOldFollow]+=ch+",";
            }
            oldFollow[indexOldFollow]=oldFollow[indexOldFollow].substring(0,oldFollow[indexOldFollow].length()-1);
            oldFollow[indexOldFollow]+="}";
            System.out.println(follow.get(key));
            indexOldFollow++;
        }
        llContentResp.setFollow(oldFollow);

        System.out.println("------------------------------------------");
        System.out.println("Select集是：");
        Map<Producter, Set<Character>> select = ll.select(gs);
        String[] oldSelect=new String[select.keySet().size()];
        for (int i = 0; i < select.keySet().size(); i++) {
            oldSelect[i]="";
        }
        int indexOldSelect = 0;
        for(Producter producter : select.keySet()){
            oldSelect[indexOldSelect]=producter+" {";
            System.out.print(producter);
            System.out.print(" : ");
            for (Character ch:select.get(producter)) {
                oldSelect[indexOldSelect]+=ch+",";
            }
            oldSelect[indexOldSelect]=oldSelect[indexOldSelect].substring(0,oldSelect[indexOldSelect].length()-1);
            oldSelect[indexOldSelect]+="}";
            indexOldSelect++;
            System.out.println(select.get(producter));
        }
        llContentResp.setSelect(oldSelect);

        System.out.println("------------------------------------------");
        System.out.println("是否是LL(1)文法呢？：");
        Boolean judge=ll.isLL1(gs);
        System.out.println(judge);

        llContentResp.setJudge(judge);

        System.out.println();

        System.out.println("------------------------------------------");
        System.out.println("提取公因子...");
         ll.extractCommonFactor(gs);

        System.out.println("------------------------------------------");
        System.out.println("消除左递归...");
        ll.removeRecursin(gs);
//        接受处理输入的字符串
        for (Producter strings:gs) {
            s=strings.getProducter().split("->");
            System.out.println(s[0]+"--->"+s[1]);
            ss.add(s[0]);
            if(!map2.containsKey(s[0]))
                map2.put(s[0],new HashSet<>());
            map2.get(s[0]).add(s[1]);
        }
//        接受处理输入的字符串
        System.out.println("------------------------------------------");
        System.out.println("处理后，首符集First是：");
        first = ll.first(gs);
        for(String str : first.keySet()){
            System.out.print(str);
            String Fei=new String();
            for (String value:map2.keySet()) {
                if(map2.get(value).contains(str)){
                    Fei=value;
                }
            }
            if(!mapFirst.containsKey(Fei.charAt(0)))
                mapFirst.put(Fei.charAt(0),new HashSet<>());
            for (char ch :first.get(str)) {
                if(!Character.isUpperCase(ch))
                    mapFirst.get(Fei.charAt(0)).add(ch);
            }
            System.out.print(" : ");
            System.out.println(first.get(str));
            System.out.println(Fei.charAt(0)+"输出"+mapFirst.get(Fei.charAt(0)));
        }

        Set<Character> character = mapFirst.keySet();
        String[] newFirst=new String[characters.size()];
        int indexNew=0;
        for (Character ch:character){
            newFirst[indexNew]=ch+" {";
            for (Character ch1:mapFirst.get(ch)) {
                newFirst[indexNew]+=ch1+",";
            }
            newFirst[indexNew]=newFirst[indexNew].substring(0,newFirst[indexNew].length()-1);
            newFirst[indexNew]+="}";
            indexNew++;
        }
        llContentResp.setNewFirst(newFirst);

        System.out.println("------------------------------------------");
        System.out.println("处理后，随符集Follow是：");
        follow = ll.follow(gs);
        String[] newFollow = new String[follow.keySet().size()];
        int indexNewFollow=0;
        for(Character key : follow.keySet()){
            newFollow[indexNewFollow]=key+" {";
            System.out.print(key);
            System.out.print(" : ");

            System.out.println(follow.get(key));
            for (Character ch:follow.get(key)) {
                newFollow[indexNewFollow]+=ch+",";
            }
            newFollow[indexNewFollow] = newFollow[indexNewFollow].substring(0,newFollow[indexNewFollow].length()-1);
            newFollow[indexNewFollow]+="}";
            indexNewFollow++;
        }
        llContentResp.setNewFollow(newFollow);

        System.out.println("------------------------------------------");
        System.out.println("处理后，Select集是：");
        select = ll.select(gs);
        String[] newSelect=new String[select.keySet().size()];
        for (int i = 0; i < select.keySet().size(); i++) {
            newSelect[i]="";
        }
        int indexNewSelect=0;
        for(Producter producter : select.keySet()){
            System.out.print(producter);
            newSelect[indexNewSelect]+=producter+" {";
            System.out.print(" : ");

            System.out.println(select.get(producter));
            for (Character ch:select.get(producter)) {
                newSelect[indexNewSelect]+=ch+",";
            }
            newSelect[indexNewSelect]=newSelect[indexNewSelect].substring(0,newSelect[indexNewSelect].length()-1);
            newSelect[indexNewSelect]+="}";
            indexNewSelect++;
        }
        llContentResp.setNewSelect(newSelect);

        System.out.println("------------------------------------------");
        System.out.println("处理后，是否是LL(1)文法呢？：");
        System.out.println(ll.isLL1(gs));


        System.out.println("==========================================");
        System.out.println("请输入要检查的串：");
        ggs=gs;
        return llContentResp;
    }

    @PostMapping("LLExpression")
    public LLExperssionResp LLExpersion(@RequestBody LLExpressionReq llExpressionReq){
        System.out.println("前端内容"+llExpressionReq.getExpression());
        LLExperssionResp llExperssionResp = new LLExperssionResp();
        System.out.println("------------------------------------------");
        for (Producter str:ggs) {
            System.out.println(str.toString());
        }
        String[] pro=ll.analysis(ll.select(ggs), llExpressionReq.getExpression() + "#");

        int i=0;
        for (i = 0; i < pro.length; i++) {
            if (pro[i]==""){
                break;
            }
        }
        String[] process=new String[i];
        for (int j = 0; j < i; j++) {
            process[j]=pro[j];
        }
        llExperssionResp.setResult(process);
        return llExperssionResp;
    }
}
