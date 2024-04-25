package com.service.impl;

import com.auxiliary.NfaMapper;
import com.resp.NfaDfaResp;
import com.service.NfaDfaService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NfaDfaServiceImpl implements NfaDfaService {

    /**
     * definite:【子集法】将NFA确定化为DFA
     * @param nfa 输入nfa
     * @return    返回dfa
     *
     * 伪代码逻辑：
     *  DFA状集合C(注：C的每个成员又都是NFA的状态state的集合)
     *  Queue 临时队列queue;
     *  NFA的初态集S(K0)的闭包入队;
     *  while(队不空):
     *      取出当前状态I;
     *      for 每个输入字母 in letters:
     *             nextI = ε-closure(move(I, letter));
     *             if(C 不包含 nextI) :
     *                  则nextI入队
     *
     *  最终的C即为确定化生成的DFA的状态集
     */

    private int index1 = 0;
    private int index11=0;

    private String[] Queue = new String[200];                                   //记录进队列出队列的状态
    private String[][] state = new String[20][200];
    private String[] change = new String[200];                                  //记录新旧状态的变化
    @Override
    public NfaDfaResp definite(NfaMapper nfa) {

        int initial=0;
        List<Integer> K0 = nfa.getS();
        char[] letters = nfa.getLetters();
        String[][] f = nfa.getF();
        List<Integer> Z = nfa.getZ();

        NfaDfaResp dfa = new NfaDfaResp();
        List<Integer> K = new ArrayList<>();             // DFA状态集合(已重命名)
        int k = 0;                                       // 用于DFA状态集的重命名
        List<String[]> listF = new ArrayList<>();        // 用于暂存转换函数f
        List<Integer> listZ = new ArrayList<>();         // 用于暂存终态集Z
        StringBuilder sb = new StringBuilder();          // 用于输出整个子集法的过程

        Set<List<Integer>> set = new HashSet<>();               // 状态集临时集合
        Queue<List<Integer>> queue = new LinkedList<>();        // 状态集临时队列
        Map<List<Integer>, Integer> map = new HashMap<>();      //「状态集」向「命名」的映射

        List<Integer> closure_K = closure(K0, f);
        K.add(k);
        map.put(closure_K, k++);
        state[index1][index11] = String.valueOf(K0)+"进入了闭包运算 ";
        change[index1]="由"+String.valueOf(K0)+"状态集生成新的状态集"+String.valueOf(closure_K);
        set.add(closure_K);
        queue.add(closure_K);
        for (int i = 0; i < Queue.length; i++)
            Queue[i]="";
        index1++;
        Queue[index1] = "状态集"+String.valueOf(closure_K).replaceAll(" ","")+"进队列 ";
        while(!queue.isEmpty()){
            List<Integer> I = queue.poll();
            Queue[index1] = Queue[index1]+"状态集"+String.valueOf(I).replaceAll(" ","")+"出队列 ";
            for(char letter : letters){
                List<Integer> nextI = closure(move(I, letter, f), f);
                Queue[index1]=Queue[index1]+"原状态集"+String.valueOf(I).replaceAll(" ","")+"进入move("+letter+")运算，随后进入闭包运算 ";
                if(nextI.isEmpty()) continue;
                if(!containsI(set, nextI)){
                    change[index1]="由"+String.valueOf(nextI).replace(" ","")+"状态集生成新的状态集"+String.valueOf(k);
                    nextI.sort(Comparator.comparing(Integer::intValue));
                    map.put(nextI, k);
                    K.add(k);
                    if(!Collections.disjoint(nextI, Z)){
                        listZ.add(k);
                    }
                    set.add(nextI);
                    index1++;
                    Queue[index1] = "新状态集"+String.valueOf(nextI).replace(" ","")+"进队列 ";
                    index11=0;
                    queue.add(nextI);
                    k++;
                }
                listF.add(new String[]{String.valueOf(map.get(I)), String.valueOf(letter), String.valueOf(map.get(nextI))});
                sb.append(map.get(I)).append(letter).append(map.get(nextI)).append('\n');
            }
        }
        System.out.println("重命名后：");
        System.out.println(sb.toString());

        // 下面是为了构造出DFA对象
        int len = K.size();
        String[][] f2 = new String[len][len];
        for(String[] tmp : f2){
            Arrays.fill(tmp, "");
        }
        for(String[] arr : listF){
            f2[Integer.parseInt(arr[0])][Integer.parseInt(arr[2])] += arr[1];
        }
        String[] ss=new String[200];
        int num=0;
        for (int i = 0; i < f2.length; i++) {
            String strings = new String();
            for (int j = 0; j < f2[i].length; j++) {
                if(f2[i][j]!=""){
                    strings=i+"->"+f2[i][j]+"->"+j;
                    ss[num++]=strings;
                }
            }
        }
        String[] ffinal=new String[num];
        for (int i = 0; i < num; i++) {
            ffinal[i]=ss[i];
        }
        dfa.setF(ffinal);
        dfa.setK(K);
        dfa.setLetters(letters);
//        dfa.setF(f2);
        dfa.setS(0);
        dfa.setZ(listZ);
        dfa.setQueue(Queue);
        dfa.setState(state);
        dfa.setChange(change);
        dfa.setIndex(index1);
        index1=0;
        return dfa;
    }

    /**
     * ε-closure闭包运算：某个状态集经过任意多个ε，得到当前的真正状态集
     * @param I 当前状态集
     * @return 当前真正的状态集(closureI)
     */
    private List<Integer> closure(List<Integer> I, String[][] f){
        // 经过任意多个ε，因此BFS
        List<Integer> closureI  = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i : I){
            state[index1][index11]="状态"+String.valueOf(i)+"进入了小队列 ";
            index11++;
            queue.add(i);
            while (!queue.isEmpty()){
                int n = queue.poll();
                state[index1][index11]="状态"+String.valueOf(n)+"出了小队列 ";
                index11++;
                for(int iNext = 0; iNext < f.length; iNext++){
                    for(char c : f[n][iNext].toCharArray()){
                        if(c == 'ε' && !closureI.contains(iNext)){
                            closureI.add(iNext);
//                            state[index1][index11]=String.valueOf(iNext)+"进入了闭包运算 ";
                            index11++;
                            if(n != iNext){
                                state[index1][index11]="状态"+String.valueOf(i)+"进入了小队列 ";
                                index11++;
                                queue.add(iNext);
                            }
                        }
                    }
                }
            }
        }
        return closureI;
    }
    /**
     * move方法：当前状态集通过某个字母可以转化到的下一状态集（一步转换，没有进行ε-闭包运算）
     * @param I 当前的状态集
     * @return 返回下一状态集
     */
    private List<Integer> move(List<Integer> I, char letter, String[][] f){
        List<Integer> nextI = new ArrayList<>();
        for(int i : I){
            for(int iNext = 0; iNext < f.length; iNext++){
                for(char c : f[i][iNext].toCharArray()){
                    if(c == letter && !nextI.contains(iNext)) {
                        nextI.add(iNext);
                    }
                }
            }
        }
        return nextI;
    }

    /**
     * 判断一个Set<List>是否包含某个List
     * @param set
     * @param list
     */
    private boolean containsI(Set<List<Integer>> set, List<Integer> list){
        for(List<Integer> l : set){
            if(listEquals(l, list)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两个List集合是否相等（不考虑顺序）
     * @param list1
     * @param list2
     */
    private boolean listEquals(List<Integer> list1, List<Integer> list2){
        list1.sort(Comparator.comparing(Integer::intValue));
        list2.sort(Comparator.comparing(Integer::intValue));
        return list1.toString().equals(list2.toString());
    }

}
