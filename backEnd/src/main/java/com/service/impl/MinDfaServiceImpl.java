package com.service.impl;

import com.auxiliary.Group;
import com.auxiliary.MinDfaMapper;
import com.resp.MinDfaResp;
import com.service.MinDfaService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *【DFA的最小化】
 * 核心思路：
 *  1. 定义一个Group类，作为「分组」。
 *     Group有两个属性：groupID作为唯一标识；StateSet为该分组包含的状态集
 *  2. separate()方法的作用是，根据某个字母(letter)对分组集合(groupSet)进行彻底分裂
 *  3. 对于字母表中的每个字母，进行separate分裂
 *
 * 下面的论述有利于理解这个算法：
 *  1. HashMap做映射，是该算法的一个关键点。
 *     对于某个字母，一个分组(group)的所有状态(state)根据这个字母，用HashMap记录它们分别会被映射到哪个分组里，据此分裂。
 *     举个例子：{group1,[0, 1]}, {group2,[2, 3]}    (key是组，value是转化后指向该组的所有状态)
 *     ⬆ 0,1状态会转化到1组，2,3状态会转化到2组，据此，旧组分裂为了两个新组，然后删掉旧组，新组入队BFS
 *     ⬆️ 如果这个哈希表的size==1，说明所有状态只能转化到一个组中，那么它们是等价的，不用删掉旧组，该组直接进入finalGroupSet最终分组
 *   2. groupID的作用是什么？为什么还要专门维护它？
 *      唯一标识。从1中看出，过程中不断进行着"删掉旧组，生成新组"的行为。维护这个ID主要是为了HashMap做映射
 *
 */

@Service
public class MinDfaServiceImpl implements MinDfaService {
    private int cnt = 0;

    private MinDfaResp minDFA=new MinDfaResp();

    private String[] sss = new String[200];

    private int index = 0;

    @Override
    public MinDfaResp minDFA(MinDfaMapper dfa){
        List<Integer> K = dfa.getK();
        List<Integer> Z = dfa.getZ();
        String[][] f = dfa.getF();
        for (int i = 0; i < f.length; i++) {
            System.out.print(i);
            for (int j = 0; j < f[i].length; j++) {
                System.out.print(f[i][j]+" ");
            }
            System.out.println("+++++");
        }
        char[] letters = dfa.getLetters();

        int kLength=K.size();
        K.removeAll(Z);         // 全部状态集K - 终态集Z = 非终态集
        Group groupx = new Group(cnt++, new HashSet<>(K));
        Group groupy = new Group(cnt++, new HashSet<>(Z));
        Set<Group> finalGroupSet = new HashSet<>();             // 最终分组
        Set<Group> curGroupSet = new HashSet<>();               // 此时的分组
        finalGroupSet.add(groupx);
        finalGroupSet.add(groupy);


        for(char letter : letters){                                 // 对于每个字母
            sss[index] = "开始对非终态集"+groupx.stateSet+"进行move运算";
            index++;
            curGroupSet = finalGroupSet;                            // 【最终分组】不断沦为【此时分组】
            finalGroupSet = separate(curGroupSet, letter, f);       // 【此时分组】又分裂成新的【最终分组】
        }                                                           // 所有字母都用了一次后，成为名副其实的【最终分组】

        // 打印最终分组(一组中的状态等价)
        int fLength=0;
        for(Group group : finalGroupSet){
            fLength++;
            System.out.print(group.groupID);
            System.out.print(group.stateSet);
            System.out.println();
        }
        String[][] ff=new String[fLength+1][fLength+1];
        for (int i = 0; i <=fLength; i++) {
            for (int j = 0; j <=fLength; j++) {
                ff[i][j]="";
            }
        }

        Set<String> ss=new HashSet<>();
        for (Group group:finalGroupSet) {
            for (Integer i:group.stateSet) {
                for (int j = 0; j < kLength; j++) {
                    if(f[i][j]!=""){
                        for (Group group1:finalGroupSet) {
                            if (group1.stateSet.contains(j)){
                                System.out.println(group.groupID+"----------"+group1.groupID);
                                ff[group.groupID][group1.groupID]+=f[i][j];
                                ss.add(group.groupID+"->"+f[i][j]+"->"+group1.groupID);
                            }
                        }
                    }
                }
            }
            System.out.println("----------");
        }

//        状态转换赋值和新集合赋值
        String[] state=new String[fLength];
        String newSet=new String();
        int i=0;
        for (Group group : finalGroupSet) {
            state[i++]="新集合"+group.groupID+"由原集合"+group.stateSet+"转化";
            newSet+=group.groupID;
        }

        minDFA.setNewSet(newSet);
        minDFA.setState(state);
//        转变函数赋值
        String[] func=new String[ss.size()];
        int indd=0;
        for (String ssss:ss) {
            func[indd++]=ssss;
        }
        minDFA.setFunc(func);
//        改变赋值，过程
        String[] process=new String[index];
        for (int j = 0; j < index; j++) {
            process[j]=sss[j];
        }
        minDFA.setProcess(process);
//        终态集赋值
        String end="";
        Set<String> set = new HashSet<>();
        for (Group group:finalGroupSet) {
            for (Integer l:group.stateSet) {
                if(Z.contains(l)) {
                    set.add(String.valueOf(group.groupID));
                    System.out.println(end);
                }
            }
        }
        for (String s:set) {
            end+=s;
            System.out.println(end);
        }
        index=0;
        cnt=0;
        minDFA.setEnd(end);
        minDFA.setEnd(end);
        return minDFA;
    }

    private Set<Group> separate(Set<Group> groupSet, char letter, String[][] f){
        Set<Group> finalGroupSet = new HashSet<>();
        Set<Group> curGroupSet = groupSet;
        Queue<Group> queue = new LinkedList<>();
        for(Group group : groupSet){
            queue.add(group);
        }
        while (!queue.isEmpty()){
            Group oldGroup = queue.poll();
            sss[index]=oldGroup.stateSet+"弹出队列";
            index++;
            Map<Group, List<Integer>> map = new HashMap<>();  //根据指向的组，对状态Integer进行分类
            for(Integer state : oldGroup.stateSet){
                Group stateNextBelong = beLong(state, letter, f, curGroupSet);
                if(!map.containsKey(stateNextBelong)){
                    map.put(stateNextBelong, new ArrayList<>());
                }
                map.get(stateNextBelong).add(state);
            }
            if (map.size() == 1){   // 如果这些状态映射到了一个状态集(Group)中，则为最终分组
                sss[index]=oldGroup.stateSet+"经过move("+letter+")运算之后所生成的状态集都属于老状态集的子集("+oldGroup.stateSet+"进入最终状态集)";
                index++;
                finalGroupSet.add(oldGroup);
            }else{                  // 如果这些状态映射到了多个状态集(Group)中，则删除原先分组，创建多个新分组，并将新分组入队
                curGroupSet.remove(oldGroup);
                for(List<Integer> list : map.values()){
                    Group newGroup = new Group(cnt++, new HashSet<>(list));
                    curGroupSet.add(newGroup);
                    sss[index]=newGroup.stateSet+"经过move("+letter+")运算之后生成新的状态集("+newGroup.stateSet+")加入队列";
                    index++;
                    queue.add(newGroup);
                }
            }
        }
        String ssss="";
        for(Group group : groupSet){
            ssss+=String.valueOf(group.stateSet)+" ";
        }
        sss[index]=ssss+"已经分区运算完毕";
        index++;
        ssss="";
        for(Group group : finalGroupSet){
            ssss+=String.valueOf(group.stateSet)+" ";
        }
        sss[index]="生成的新的状态集是"+ssss;
        index++;
        return finalGroupSet;
    }

    /**
     * move方法: 返回唯一后继状态（-1表示没有后继状态）
     */
    private int move(int state, char letter, String[][] f){

        for(int nextState = 0; nextState < f.length; nextState++){
            for(char c : f[state][nextState].toCharArray()){

                if(c == letter){
                    return nextState;
                }
            }
        }
        return -1;
    }

    /**
     *
     * beLong方法: 某状态(state)经过字母(letter)一次转化(move)后，所属于的当前分组(group)
     */
    private Group beLong(int state, char letter, String[][] f, Set<Group> groupSet){
        int newState = move(state, letter, f);
        for(Group group : groupSet){
            if(group.stateSet.contains(newState)){
                return group;
            }
        }
        return null;
    }
}

/**
 * 注：
 * 1.本代码仅仅实现了DFA的状态的分组，即找出等价状态。但等价状态的合并由读者自行实现（这并不困难）
 * 2.考举个例子，当根据a分裂一次后，接着根据b分裂，那么，是否根据a又可以再分裂一次呢？这是本代码不严谨的地方，需要再加一个"可分裂检查"的逻辑
 */