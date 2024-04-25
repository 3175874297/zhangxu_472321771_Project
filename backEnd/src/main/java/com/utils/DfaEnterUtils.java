package com.utils;

import com.auxiliary.MinDfaMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DfaEnterUtils {

    private int n;

    private MinDfaMapper minDfaMapper;

    // 输入状态集
    public List<Integer> enterK(String ss){
        List<Integer> list = new ArrayList<>();
        String[] strings = ss.split("");
        for(String s : strings){
            list.add(Integer.parseInt(s));
        }
        n = list.size();
        return list;
    }

    // 输入字母表
    public char[] enterLetters(String ss){
        char[] letter=ss.toCharArray();
        return ss.toCharArray();
//        dfaMapper.setLetters(letters);
    }

    // 输入转换函数
    public String[][] enterF(String ss){
        String[] strings=ss.split(" ");
        String[][] f = new String[n][n];
        for(String[] arr : f){
            Arrays.fill(arr, "");
        }
        String line = "";
        for (int i = 0; i < strings.length; i++) {
            String[] arr = strings[i].split("");
            for (int j = 2; j < arr.length; j++) {
                f[Integer.parseInt(arr[0])][Integer.parseInt(arr[j])] += arr[1];
            }
        }
        return f;
//        dfaMapper.setF(f);
    }

    // 输入初态集
    public int enterS(String ss){
        return Integer.parseInt(ss);
//        dfaMapper.setS(Integer.parseInt(ss));
    }

    // 输入终态集
    public List<Integer> enterZ(String ss){
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        String[] strings = ss.split("");
        for (String s : strings){
            list.add(Integer.parseInt(s));
        }
        return list;
//        dfaMapper.setZ(list);
    }

//    public MinDfaMapper input(){
////        return dfaMapper;
//    }
}
