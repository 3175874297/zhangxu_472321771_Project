package com.utils;

import com.auxiliary.NfaMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class NfaEnterUtils {
    /**
     * 状态集的个数（转换函数f也要用到）
     * 这个n被所有nfa类共享————为了防止出错，enterF一定要写在enterK的后面，才能保证初始化f时使用正确的n
     */
    private static int  n;
    // 输入状态集
    public  void enterK(NfaMapper nfa, String ss) throws IOException {
        List<Integer> list = new ArrayList<>();
        String[] strings = ss.split("");
        for(String s : strings){
            list.add(Integer.parseInt(s));
        }
        n = list.size();
        nfa.setK(list);
    }

    // 输入字母表
    public void enterLetters(NfaMapper nfa,char[] ss) throws IOException {
//        for (int i = 0; i < ss.length; i++) {
//            System.out.println(ss[i]);
//        }
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        char[] letters = br.readLine().toCharArray();
        nfa.setLetters(ss);
    }

    // 输入转换函数
    public void enterF(NfaMapper nfa,String ss) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings=ss.split(" ");
        String[][] f = new String[n][n];
        for(String[] arr : f){
            Arrays.fill(arr, "");
        }
        for(int i = 0; i < f.length; i++){
            f[i][i] = "ε";
        }
        String line = "";
        for (int i = 0; i < strings.length; i++) {
            String[] arr = strings[i].split("");
            for (int j = 2; j < arr.length; j++) {
                f[Integer.parseInt(arr[0])][Integer.parseInt(arr[j])] += arr[1];
            }
        }
        nfa.setF(f);
    }

    // 输入初态集
    public void enterS(NfaMapper nfa,String ss) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        String[] strings = ss.split("");
        for(String s : strings){
            list.add(Integer.parseInt(s));
        }
        nfa.setS(list);
    }

    // 输入终态集
    public void enterZ(NfaMapper nfa,String ss) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        String[] strings = ss.split("");
        for (String s : strings){
            list.add(Integer.parseInt(s));
        }
        nfa.setZ(list);
    }
}
