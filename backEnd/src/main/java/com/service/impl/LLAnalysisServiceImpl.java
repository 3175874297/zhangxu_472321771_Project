package com.service.impl;

import com.auxiliary.PredictTable;
import com.auxiliary.Producter;
import com.service.LLAnalysisService;
import com.utils.GSBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LLAnalysisServiceImpl implements LLAnalysisService {
    @Override
    public String[] analysis(Map<Producter, Set<Character>> select, String str){
        Map<Character, Map<Character, Producter>> table = new PredictTable().makePredictTable(select);
        Stack<Character> stack = new Stack<>();
        stack.push('#');
        stack.push('S');
        int index = 0;
        int indd=0;
        String[] pro=new String[200];
        Stack<Character> stackTemp = new Stack<>();

        for (int i = 0; i < pro.length; i++) {
            pro[i]="";
        }

        while (true){
            Character X = stack.pop();
            Character Y = str.charAt(index);
            if(X == '#' && Y == '#'){
                System.out.println("接受！！！自顶向下分析成功 >_<!!!");
                break;
            }
            if(isVN(X)){
                String tmp = new PredictTable().findTable(table, X, Y);
                if(tmp.equals("ERROR")){
                    System.out.println("推导失败！自顶向下分析失败 >_<");
                    break;
                }else{
                    System.out.println("【推导】" + X + "->" + (tmp.equals("") ? "ε" : tmp));
                    if(tmp.equals("")){
                        System.out.println("输出当前的状况");
                        pro[indd]+=X+"->"+"ε"+" ";
                        pro[indd]+="ε ";
                        while (!stack.empty()){
                            Character chhh;
                            chhh=stack.pop();
                            stackTemp.push(chhh);
                            pro[indd]+=String.valueOf(chhh);

                        }
                        while (!stackTemp.empty()){
                            Character chh;
                            chh=stackTemp.pop();
                            stack.push(chh);
                        }
                        indd++;
                    }else
                    pro[indd]+=X+"->"+(tmp.equals("") ? "ε" : tmp)+" ";
                    for(int i = tmp.length() - 1; i >= 0; i--){
                        stack.push(tmp.charAt(i));
                    }
                }
            }else{
                if(X == Y){
                    System.out.println("【匹配】" + Y);
                    pro[indd]+=Y+" ";
                    while (!stack.empty()){
                        Character chhh;
                        chhh=stack.pop();
                        stackTemp.push(chhh);
//                        if(chhh=='#')
//                            continue;
                        pro[indd]+=String.valueOf(chhh);

                    }
                    while (!stackTemp.empty()){
                        Character chh;
                        chh=stackTemp.pop();
                        stack.push(chh);
                    }
                    indd++;
                    index++;
                }else{
                    System.out.println("匹配失败！自顶向下分析失败 >_<");
                    break;
                }
            }
        }
        return pro;
    }


    /**
     * 求firstV集————非终结符VN和终结符VT的firstV集，为之后求推导式右侧字符串的first集作准备
     * @param gs    文法GS（推导式Producter的Set集合）
     * @return      非终结符VN和终结符VT的firstV集
     */
    @Override
    public Map<Character, Set<Character>> firstV(Set<Producter> gs) {
        Map<Character, Set<Character>> firstV = new HashMap<>();
        // 将所有的VN和VT先初始化到HashMap当中
        for(Character vn : GSBuilder.getVN(gs)){
            firstV.put(vn, new HashSet<>());
        }
        for(Character vt : GSBuilder.getVT(gs)){
            firstV.put(vt, new HashSet<>());
            firstV.get(vt).add(vt);
            // 对于终结符VT，其firstV就是自身（e.g. FirstV(a) = {a}）
            // 而对于非终结符VN，求其firstV需要扫描（下面就是）
        }

        // 调用了can_2_empty()方法————返回emptyOK和emptyNO————如果当前字符属于emptyNO或者是终结符，就没有继续找firstV的必要了
        List<Set<Character>> res = can_2_empty(gs);
        Set<Character> emptyOK = res.get(0);
        Set<Character> emptyNO = res.get(1);

        // 不断尝试更新firstV，当其不再变化时停止循环
        while (true){
            int beforeCnt = countCell(firstV);
            for(Producter producter : gs){
                String str = producter.getRight();
                Set<Character> tempSet = new HashSet<>();
                // index不断后移，扫描生成式右侧的字符串
                // 一个小技巧：如果这个index能够移动到串的最后(str.length())，就可以把ε加到该VN的firstV中
                int index = 0;
                while(index < str.length()){
                    char c = str.charAt(index);
                    tempSet.addAll(firstV.get(c));
                    tempSet.remove('ε');
                    if(emptyNO.contains(c) || isVT(c)){
                        break;
                    }
                    index++;
                }
                if(index == str.length()){
                    tempSet.add('ε');
                }
                firstV.get(producter.getLeft()).addAll(tempSet);
            }
            if(countCell(firstV) == beforeCnt){
                break;
            }
        }
        return firstV;
    }

    /**
     * 求first集————推导式右侧字符串的first集
     * @param gs    文法GS（推导式Producter的Set集合）
     * @return      推导式右侧字符串的first集
     */
    @Override
    public Map<String, Set<Character>> first(Set<Producter> gs){
        Map<String, Set<Character>> first = new HashMap<>();
        Map<Character, Set<Character>> firstV = firstV(gs);
        // 这里仍旧用到了emptyOK和emptyNO，因为需要扫描，我们需要判断index是否还可以后移
        List<Set<Character>> res = can_2_empty(gs);
        Set<Character> emptyOK = res.get(0);
        Set<Character> emptyNO = res.get(1);

        // 我们已经求出每个文法符号(VN+VT)的firstVN了，在求字符串的first的时候是完全不需要while循环的
        for(Producter producter : gs){
            first.put(producter.getRight(), new HashSet<>());
            String str = producter.getRight();
            if(str.length() == 1){
                first.get(str).add(str.charAt(0));
            }
            int index = 0;
            while (index < str.length()){
                char c = str.charAt(index);
                first.get(str).addAll(firstV.get(c));
                first.get(str).remove('ε');
                if(emptyNO.contains(c) || isVT(c)){
                    break;
                }
                index++;
            }
            if(index == str.length()){
                first.get(str).add('ε');
            }
        }
        return first;
    }


    /**
     * 返回follow集
     * 这里有必要说一下实现的思路。以「S -> ABCD」为例
     * 用一个index扫描生成式右侧字符串，当index指向一个非终结符VN时，开启以下算法(假设当前指向B)：
     *      1. 将其后方的firstV加到当前的follow当中；如果后方可推出ε，则用一个jndex继续向后扫直到扫到不可推出ε的字符为止
     *      2. 如果这个时候jndex == str.length()，说明这一路(包括结束)都可以推出ε，因此这时把左侧的VN的follow加到当前的follow当中
     * 另外两点，要有这种意识：
     *      1. 第一步啥都别干，赶紧直接把把{#}加到开始符号S的follow里
     *      2. 随符集follow中是不可能出现ε的，因此每次向follow加first时，都要把ε去掉，即加入"非空串元素"
     *      3. while循环结束的条件依旧是follow集不再变化
     */
    @Override
    public Map<Character, Set<Character>> follow(Set<Producter> gs){
        Map<Character, Set<Character>> follow = new HashMap<>();
        for(Character c : GSBuilder.getVN(gs)){
            follow.put(c, new HashSet<>());
        }
        // 第一步就是把{#}加到开始符号S的follow里
        follow.get('S').add('#');
        // 这里仍旧用到了emptyOK和emptyNO
        List<Set<Character>> res = can_2_empty(gs);
        Set<Character> emptyOK = res.get(0);
        Set<Character> emptyNO = res.get(1);

        Map<Character, Set<Character>> firstV = firstV(gs);

        while (true){
            int beforeCnt = countCell(follow);
            for(Producter producter : gs){
                String str = producter.getRight();
                int index = 0;
                while (index < str.length()){
                    char c = str.charAt(index);
                    if(isVN(c)){
                        int jndex = index + 1;  // 幽默一下
                        while (jndex < str.length()){
                            char nextC = str.charAt(jndex);
                            follow.get(c).addAll(firstV.get(nextC));
                            follow.get(c).remove('ε');
                            if(emptyNO.contains(nextC) || isVT(nextC)){
                                break;
                            }
                            jndex++;
                        }
                        if(jndex == str.length()){
                            follow.get(c).addAll(follow.get(producter.getLeft()));
                        }
                    }
                    index++;
                }
            }
            if(countCell(follow) == beforeCnt){
                break;
            }
        }
        return follow;
    }


    /**
     * 返回select集————这可能是整个文法分析中最简单的一步
     * 以「S->ABC」为例：
     *      1.如果first(ABC)中含有ε，Select(S->ABC) = First(ABC) - {ε} + Follow(S)
     *      2.如果first(ABC)中不含有ε，Select(S->ABC) = First(ABC)
     */
    @Override
    public Map<Producter, Set<Character>> select(Set<Producter> gs){
        // 之前求的first集和follow集，终于全都派上了用场！
        Map<Producter, Set<Character>> select = new HashMap<>();
        Map<String, Set<Character>> first = first(gs);
        Map<Character, Set<Character>> follow = follow(gs);

        for(Producter producter : gs){
            select.put(producter, new HashSet<>());
            if(!first.get(producter.getRight()).contains('ε')){
                select.get(producter).addAll(first.get(producter.getRight()));
            }else{
                select.get(producter).addAll(first.get(producter.getRight()));
                select.get(producter).remove('ε');
                select.get(producter).addAll(follow.get(producter.getLeft()));
            }
        }

        return select;
    }






    /**
     * 返回emptyOK + emptyNO
     * 即对非终结符号VN分为了两类：最终可以推出空串的 + 最终也无法推导出空串的（这尤为关键，是整个自上而下分析的基础，一步错步步错）
     */
    @Override
    public List<Set<Character>> can_2_empty(Set<Producter> gs){
        Set<Character> emptyOK = new HashSet<>();                       // 最终可以推导出空串
        Set<Character> emptyNO = new HashSet<>();                       // 最终不可推导出空串
        Set<Producter> gs1 = gs;                                        // GS文法格式1
        Map<Character, List<String>> gs2 = GSBuilder.gsHelper(gs);      // GS文法格式2

        // 第一次扫描
        // 对于某个终结符A，如果生成式右侧为ε，即直接可以推导出空串，将该生成式左侧加入emptyOK集合
        for(Producter producter : gs){
            if(producter.getRight().equals("ε")) {
                emptyOK.add(producter.getLeft());
            }
        }

        // 第二次扫描
        // 对于某个终结符A，如果以此为左侧的生成式，右侧全都包括至少一个终结符a，则将其加入emptyNO集合
        for(Character c : gs2.keySet()){
            if(emptyOK.contains(c)){
                continue;
            }
            boolean flag = true;
            for(String s : gs2.get(c)){
                if(!hasVT(s)){
                    flag = false;
                    break;
                }
            }
            if (flag){
                emptyNO.add(c);
            }
        }

        // 循环扫描（1次扫描尝试加入emptyOK + 1次扫描尝试加入emptyNO）
        // 当这两个集合都不再变化时，STOP
        while (true){
            // 记录一下两个集合每次循环之前的大小，循环结束后比较的时候要用————决定是否还需要下一次循环
            int emptyOK_num = emptyOK.size();
            int emptyNO_num = emptyNO.size();

            // 循环中的第一次扫描，当右侧全为非终结符(ABC)并且它们都在emptyOK之中时，将生成式左侧加入emptyOK集合
            for (Producter producter : gs){
                if(emptyOK.contains(producter.getLeft()) || emptyNO.contains(producter.getLeft())){
                    continue;
                }
                if(!hasVT(producter.getRight())){
                    boolean flag = true;
                    for(char i : producter.getRight().toCharArray()){
                        if(!emptyOK.contains(i)){
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        emptyOK.add(producter.getLeft());
                    }
                }
            }

            // 循环中的第二次扫描，对于某个非终结符A，以其为左侧的所有生成式的右侧，都至少包括一个终结符b 或 一个在emptyNO中的非终结符B，则将A加入emptyNO集合
            for(Character c : gs2.keySet()){
                if(emptyOK.contains(c) || emptyNO.contains(c)){
                    continue;
                }
                boolean flag = true;
                for(String s : gs2.get(c)){
                    if(!hasVT(s)){
                        flag = false;
                        for(char i : s.toCharArray()){
                            if(emptyNO.contains(i)){
                                flag = true;
                            }
                        }
                    }
                }
                if (flag){
                    emptyNO.add(c);
                }
            }

            // 当这两个集合都不再变化时，跳出循环
            if(emptyOK_num == emptyOK.size() && emptyNO_num == emptyNO.size()){
                break;
            }
        }
        // 将千辛万苦得到的两个集合（emptyOK + emptyNO）作为返回值返回
        List<Set<Character>> res = new ArrayList<>();
        res.add(emptyOK);
        res.add(emptyNO);
        return res;
    }






    // 是否是非终结符VN？
    // 我们认为，大写字母即为非终结符(S, A, B, C, D)
    // 跟严谨的写法是，遍历文法GS的所有生成式Producter，所有生成式左边的字母即为VN，其余为VT
    public boolean isVN(Character c){
        return Character.isUpperCase(c);
    }

    // 是否为终结符VT？
    // 我们认为，小写字母即为终结符(a, b, c, d, e)
    // 注意两点：一，这不严谨；二，「ε」被Java认为是小写字母，但它不是终结符————如果忽略这个细节，后面你将会付出惨痛的代价
    public boolean isVT(Character c){
        String loli = "()+*";
        return (Character.isLowerCase(c) || loli.indexOf(c) >= 0) && !c.equals('ε');
    }

    // 一个字符串是否包含非终结符？
    // 技巧：取反————这个字符串不包含非终结符————这个字符串是纯终结符串
    private boolean hasVN(String s){
        for(char c : s.toCharArray()){
            if(isVN(c)){
                return true;
            }
        }
        return false;
    }

    // 同理
    private boolean hasVT(String s){
        for(char c : s.toCharArray()){
            if(isVT(c)){
                return true;
            }
        }
        return false;
    }

    // 将HashMap的value中字符(Character)的个数作为了该数据结构的标志————如果该计数值不变，则认为该数据结构没变
    private int countCell(Map<Character, Set<Character>> map){
        int cnt = 0;
        for(Set<Character> value : map.values()){
            cnt += value.size();
        }
        return cnt;
    }




    // 判断是否为LL(1)文法————根据First集，Follow集->Select集
    @Override
    public boolean isLL1(Set<Producter> gs){
        Map<Producter, Set<Character>> select = select(gs);
        Set<Character> VN = GSBuilder.getVN(gs);

        for(Character vn : VN){
            Set<Producter> producterSet = producterWithVN(vn, gs);
            Set<Character> tmp = new HashSet<>();
            int cnt = 0;
            for(Producter producter : producterSet){
                cnt += select.get(producter).size();
                tmp.addAll(select.get(producter));
            }
            if(cnt != tmp.size()){
                return false;
            }
        }
        return true;
    }

    @Override
    public Set<Producter> producterWithVN(Character vn, Set<Producter> gs){
        Set<Producter> producterSet = new HashSet<>();
        for (Producter producter : gs){
            if(producter.getLeft() == vn){
                producterSet.add(producter);
            }
        }
        return producterSet;
    }






    /**
     *【提取公因子】直接在文法gs的基础上进行等价的修改
     */
    @Override
    public void extractCommonFactor(Set<Producter> gs){
        // 提取公因子之前，先消除隐式的情况
        transGs(gs);
        Map<Character, List<String>> gs2 = GSBuilder.gsHelper(gs);
        Set<Character> VN = GSBuilder.getVN(gs);
        // 维护了一个给新的VN命名的候选数组
        int k = 0;
        char[] unUsedCase = getUnUsedCase(gs);

        for(Character vn : VN){
            // 对于每个非终结符(产生式的左侧)，都尝试消除左递归
            List<String> list = gs2.get(vn);
            // 不是所有的文法，都能在有限步骤内提取出所有的左公因子。换句话说，明明算法正确，但仍有可能死循环————因此设置一个阀值
            int cnt = 10;
            boolean flag = true;
            while (flag && cnt > 0){
                cnt--;
                flag = false;
                // 循环实在是太深了(封装太少的恶果)，这里使用了一个flag+here标签实现跳出多层循环后，再次重新循环
                here: for(int i = 0; i < list.size(); i++){
                    String curI = list.get(i);
                    while (curI.length() > 0){
                        for(int j = i + 1; j < list.size(); j++){
                            String curJ = list.get(j);
                            if(curJ.indexOf(curI) != 0){
                                continue;
                            }else{
                                // 出现公共前缀
                                String s1 = list.get(i);
                                String s2 = list.get(j);
                                String commonFactor = curI;
                                char newVN = unUsedCase[k++];
                                String s3 = commonFactor + newVN;
                                list.remove(s1);
                                list.remove(s2);
                                list.add(s3);
                                removeFromGs(gs, vn, s1);
                                removeFromGs(gs, vn, s2);
                                gs.add(new Producter(vn + "->" + s3));
                                gs.add(new Producter(newVN + "->" + (s1.length() == commonFactor.length() ? "ε" : s1.replaceFirst(commonFactor, ""))));
                                gs.add(new Producter(newVN + "->" + (s2.length() == commonFactor.length() ? "ε" : s2.replaceFirst(commonFactor, ""))));
                                flag = true;
                                break here;
                            }
                        }
                        curI = curI.substring(0, curI.length() - 1);
                    }
                }
            }
        }
        // 提取公因子之后，删掉不可达的产生式
        removeUnReachable(gs);
    }


    /**
     *【消除左递归】直接在文法gs的基础上进行等价的修改
     */
    @Override
    public void removeRecursin(Set<Producter> gs){
        Set<Character> VN = GSBuilder.getVN(gs);
        Map<Character, List<String>> gs2 = GSBuilder.gsHelper(gs);
        int k = 0;
        char[] unUsedCase = getUnUsedCase(gs);
        // 消除隐式
        transGs(gs);

        for(Character vn : VN){
            List<String> list = gs2.get(vn);
            List<String> alpha = new ArrayList<>();
            List<String> beta = new ArrayList<>();
            for(String str : list){
                if(str.charAt(0) == vn){
                    alpha.add(str.substring(1));
                }else{
                    if(str.equals("ε")){
                        str = "";
                    }
                    beta.add(str);
                }
            }
            if(alpha.size() > 0){
                removeFromGs(gs, vn);
                char newVN = unUsedCase[k++];
                for(String s : beta){
                    gs.add(new Producter(vn + "->" + s + newVN));
                }
                for(String s : alpha){
                    gs.add(new Producter(newVN + "->" + s + newVN));
                }
                gs.add(new Producter(newVN + "->" + "ε"));
            }
        }
        // 同样，删掉不可到达的生成式
        removeUnReachable(gs);
    }





//    Try2LL1
    // 简单的功能性方法————比如目前已有的VN有SABC，这个方法会返回除了这几个之外的所有大写字母，作为候选
    @Override
    public char[] getUnUsedCase(Set<Producter> gs){
        Set<Character> set = new HashSet<>();
        Set<Character> VN = GSBuilder.getVN(gs);
        for(int i = 0; i < 26; i++){
            set.add((char)('A' + i));
        }
        set.removeAll(VN);
        char[] res = new char[set.size()];
        int k = 0;
        for(char c : set){
            res[k++] = c;
        }
        return res;
    }


    /**
     * 消除隐式
     * 在「提取公因子」「消除左递归」之前进行隐式的消除————如果产生式的右部以非终结符(ABC)开头，那么公因子有可能是隐式的；递归同理
     */
    @Override
    public void transGs(Set<Producter> gs){
        Set<Character> VN = GSBuilder.getVN(gs);
        boolean flag = true;
        while (flag){
            flag = false;
            Set<Producter> trash = new HashSet<>();
            Set<Producter> wait = new HashSet<>();
            for(Producter producter : gs){
                Character c = producter.getRight().charAt(0);
                // 这里多讨论了一下：比如 A->Aa|b，这就属于左递归的范畴。
                // 如果执迷于消除全部产生式的右部第一个VN，会死循环的————这就是「左递归」的陷阱，也是之后的工作
                if(VN.contains(c) && c != producter.getLeft()){
                    flag = true;
                    Map<Character, List<String>> gs2 = GSBuilder.gsHelper(gs);
                    List<String> arr = gs2.get(c);
                    trash.add(producter);
                    for(String s : arr){
                        // 你不能用一个递归的式子去进行首字母的替换
                        if(s.charAt(0) == c){
                            continue;
                        }
                        Character newLeft = producter.getLeft();
                        String newRight = producter.getRight().replaceFirst(String.valueOf(c), s);
                        wait.add(new Producter(newLeft + "->" + newRight));
                    }
                }
            }
            gs.removeAll(trash);
            gs.addAll(wait);
        }
    }
    /**
     * 删除不可达
     * 在「提取公因子」「消除左递归」之后进行不可达生成式的删除————有些生成式可能会变得不可达，完全可以删掉
     */
    @Override
    public void removeUnReachable(Set<Producter> gs){
        Set<Character> reachableVN = new HashSet<>();
        Set<Character> unreachableVN = GSBuilder.getVN(gs);
        for(Producter producter : gs){
            for(char vn : unreachableVN){
                if(producter.getRight().indexOf(vn) >= 0){
                    reachableVN.add(vn);
                }
            }
        }
        // 排除"可达到"的非终结符VN，以及S（S作为起始符号，必然是"可到达"的）
        unreachableVN.removeAll(reachableVN);
        unreachableVN.remove('S');
        Set<Producter> trash = new HashSet<>();
        for(Producter producter : gs){
            if(unreachableVN.contains(producter.getLeft())){
                trash.add(producter);
            }
        }
        gs.removeAll(trash);
    }



    // 封装了一个简单的功能：从文法中删除某些产生式
    // 的是使上面的代码美观清晰
    @Override
    public void removeFromGs(Set<Producter> gs, Character left, String right){
        Set<Producter> trash = new HashSet<>();
        for(Producter producter : gs){
            if(producter.getLeft() == left && producter.getRight().equals(right)){
                trash.add(producter);
            }
        }
        gs.removeAll(trash);
    }
    @Override
    public void removeFromGs(Set<Producter> gs, Character left){
        Set<Producter> trash = new HashSet<>();
        for(Producter producter : gs){
            if(producter.getLeft() == left){
                trash.add(producter);
            }
        }
        gs.removeAll(trash);
    }

    // 封装了一个简单的功能：向文法中添加某些产生式
    // 的是使上面的代码美观清晰
    @Override
    public void addToGs(Set<Producter> gs, Character newVN, String s, String commonFactor){
        String newRight = s.replaceFirst(commonFactor, "");
        if(newRight.equals("")){
            newRight = "ε";
        }
        gs.add(new Producter(newVN + "->" + newRight));
    }
}
