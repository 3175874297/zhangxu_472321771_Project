package com.service.impl;



import com.auxiliary.KeyData;
import com.resp.LexicalAnalysisResp;
import com.service.LexicalAnalysisService;
import org.springframework.stereotype.Service;

//单符号3，双符号4，标识符1，关键字0，数字2

@Service
public class LexicalAnalysisServiceImpl implements LexicalAnalysisService {

    //    保留字的个数
    KeyData keyData = new KeyData();
    //    程序中的字符串
    char[][] chars = new char[300][200];
    //    程序中的行数
    int line;
    //    程序中的总行数
    int totalLine;
    //    每一行中的字符位置
    int i=0;
    //    判断读出的字符是保留字还是ID
    char[] chWords =new char[20];

    //    拼接字符的位置
    int j = 0;
    //    程序中的当前字符
    char cWords;
    //    判断数字中一共有几个点
    int x=2;
    //词法分析的返回结果
    LexicalAnalysisResp analysisResp = new LexicalAnalysisResp();
    String[] symbol=new String[300];
    String[] dfa =new String[300];
    String[] error=new String[300];
    String[] handle = new String[300];
    String[] type =new String[300];
    int index=0;

    //    读出所有的字符
    @Override
    public void Filter(String sc){
        int len = 0;
        String s[] = sc.split("\n");
        String s1=new String();
        for (int k = 0; k < s.length; k++) {
            s1 = s[k];
            s1=s1+"\n";
            chars[totalLine] = s1.toCharArray();
            totalLine++;
        }
    }
    @Override
    public LexicalAnalysisResp invoke(){
        for (int k = 0; k<=totalLine ; k++) {
            for (char chc:chars[k]) {
                if(chc=='\0')
                    break;
                words();
                k=line;
                System.out.println("----------"+line);
                System.out.println("-============="+i);
                i++;
            }
        }
        String[] s1=new String[index];
        String[] s2=new String[index];
        String[] s3=new String[index];
        String[] s4=new String[index];
        String[] s5=new String[index];
        for (int k = 0; k < chars.length; k++) {
            for (int l = 0; l < chars[k].length; l++) {
                chars[k][l]='\0';
            }
        }
        for (int i = 0; i <index; i++) {
            s1[i]=reDefine(type[i]);
            s2[i]=reDefine(symbol[i]);
            s3[i]=handle[i];
            s4[i]=dfa[i];
            s5[i]=error[i];
        }
        for (int k = 0; k < s3.length; k++) {
            if(s3[k]==null)
                s3[k]="无需特殊处理";
        }
        for (int k = 0; k < s3.length; k++) {
            if(s5[k]==null)
                s5[k]="解析"+reDefine(symbol[k])+"无异常";
        }
        analysisResp.setType(s1);
        analysisResp.setSymbol(s2);
        analysisResp.setHandle(s3);
        analysisResp.setError(s5);
        analysisResp.setDfa(s4);
        analysisResp.setInd(index);
        totalLine=0;
        index=0;
        line=0;
        i=0;
        j=0;
        x=2;
        return analysisResp;
    }

    public String reDefine(String str){
        char[] sss=str.toCharArray();
        String strings=new String();
        for (int k = 0; k < sss.length; k++) {
            if(sss[k]!='\0'){
                strings+=sss[k];
            }else {
                break;
            }
        }
        return strings;
    }

    //    截取有效长度
    public int valid(){
        int k;
        for (k = 0; k < chWords.length; k++) {
            if (chWords[k]=='\0'){
                break;
            }
        }
        return k;
    }
    //    保留字的识别
    public int preseve(){
        String ccss;
        int k;
        for (String ss:keyData.strings) {
            k = valid();
            ccss = String.valueOf(chWords).substring(0,k);
            if(ccss.equals(ss)){
                return 1;
            }
        }
        return 0;
    }
    //打印出相应的值
    public void printfToken(String str){
        symbol[index]=str;
        type[index]="运算符"+str;
        if(str.length()>1){
            dfa[index]="4";
        }else
            dfa[index]="3";
        index++;
//        System.out.println("("+str+",-)");
    }
    public void printfToken2(char chc){
        symbol[index]=String.valueOf(chc);
        if(chc==';'){
            type[index]="界符";
            dfa[index]="3";
        }else if (chc!='('||chc!=')'||chc!='['||chc!=']'||chc!=','||chc!='\''){
            type[index]="运算符";
            dfa[index]="3";
        }else {
            type[index]="界符";
            dfa[index]="3";
        }
        index++;
//        System.out.println("("+chc+",-)");
    }


    //  如果遇到换行就行数加一
    public boolean change(){
        if(chars[line][i]=='\n'||chars[line][i]==';'){
            return true;
        }
        return false;
    }

    //    识别各种数字
    public boolean digit(){
        if(Character.isDigit(chars[line][i])){
            return true;
        }else {
            return false;
        }
    }
    //    判断小数点
    public boolean dot(){
        if (chars[line][i]=='.'){
            return true;
        }
        return false;
    }

    //    识别出大写或者是小写的字母
    public boolean character(){
        if (Character.isUpperCase(chars[line][i])||Character.isLowerCase(chars[line][i])){
            return true;
        }else {
            return false;
        }
    }

    //    字符串赋值
    public void fu(int numberJudge){
        if(chWords[0]=='\0')
            return;
        if (numberJudge==1){
            for (int k = 0; k < chWords.length; k++) {
                keyData.id[keyData.idnum][k]=chWords[k];
            }
        }else if (numberJudge==2){
            for (int k = 0; k < chWords.length; k++) {
                keyData.numch[keyData.number][k]=chWords[k];
            }
        }else if(numberJudge==3){
            for (int k = 0; k < chWords.length; k++) {
                keyData.prese[keyData.presenum][k]=chWords[k];
            }
        }
        clear();

    }
    //输出相应的错误
    public void Error(String error){
        System.out.println("出现的错误是："+error);
    }

    //  清空字符数组
    public void clear(){
        for (int k = 0; k < chWords.length; k++) {
            chWords[k]='\0';
        }
    }
    //    函数的主体，多个有限自动机的结合
    public void words(){
        keyData.ch = chars[line][i];
        System.out.println(chars[line][i]);
        if(character()||keyData.ch=='_'||keyData.ch=='$'){
            do {
                chWords[j] = chars[line][i];
                i++;j++;
            }while(character()||digit()||keyData.ch=='_'||keyData.ch=='$');
//            if (!change())
//                i--;
            if(change()&&chars[line][i]==';'){
                line++;
                i=-1;
                printfToken2(';');
            }
            j=0;
            if(preseve()==1&&chWords[0]!='\0'){
                symbol[index] = String.valueOf(chWords);
                fu(3);
                type[index]="关键字";
                dfa[index]="0";
                index++;
//                System.out.println("("+keyData.presenum+",-)");
                keyData.presenum++;
            }else{
                for (int k = 0; k < keyData.idnum; k++) {
                    if (String.valueOf(chWords).equals(String.valueOf(keyData.id[k]))){
                        symbol[index] = String.valueOf(chWords)+"("+k+")";
                        clear();
                        type[index]="标识符";
                        dfa[index] = "1";
                        index++;
//                        System.out.println("("+k+",id)");
                        break;
                    }
                }
                if(chWords[0]!='\0'){
                   symbol[index]=String.valueOf(chWords)+"("+keyData.idnum+")";
                    type[index]="标识符";
                    dfa[index] = "1";
                    index++;
                }
                fu(1);
                j=0;
//                System.out.println("("+keyData.idnum+",id)");
                if(chWords[0]!='\0')
                    keyData.idnum++;
            }

        }else if(Character.isDigit(chars[line][i])){
            int d=0;
            do{
                chWords[j]= chars[line][i];
                j++;
                i++;
                if (change()){
                    break;
                }
                if(dot()){
                    d++;
                    if (d==2){
                        Error("小数点过多");
                        error[index]="小数点过多";
                        handle[index]="直接跳过";
                        d=0;
                        break;
                    }else {
                        continue;
                    }
                }
            }while (digit()||dot());
            if (String.valueOf(chWords).contains(".")){
                type[index]="浮点型数字"+String.valueOf(chWords);

            }else {
                type[index]="整型数字"+String.valueOf(chWords);
            }
            symbol[index]=String.valueOf(chWords);
            dfa[index]="2";
            index++;
            fu(2);
            i--;
            j=0;
            keyData.number++;

        }else if(keyData.ch=='+'){
            if(chars[line][i+1]=='+'){
                printfToken("++");
//                System.out.println("(++"+",-)");
                i=i+1;
            }else if (chars[line][i+1]=='='){
                printfToken("+=");
//                System.out.println("(+="+",-)");
                i=i+1;
            }else {
                printfToken2('+');
//                System.out.println("(+"+",-)");
            }
            change();
        }else if(keyData.ch=='-'){
            if(chars[line][i+1]=='-'){
                printfToken("--");
//                System.out.println("(--"+",-)");
                i=i+1;
            }else if (chars[line][i+1]=='='){
                printfToken("-=");
//                System.out.println("(-="+",-)");
                i=i+1;
            }else {
                printfToken2('-');
//                System.out.println("(-"+",-)");
            }
            change();
        }else if(keyData.ch=='*'){
            if(chars[line][i+1]=='='){
                printfToken("*=");
//                System.out.println("(*="+",-)");
                i=i+1;
            }else {
                printfToken2('*');
//                System.out.println("(*,-)");
            }
        }else if(keyData.ch=='/'){
            if (chars[line][i+1]=='/'){
                line++;
                i=-1;
            }else if (chars[line][i+1]=='*'){
                boolean jud=true;
                int o,k;
                if(chars[line][i+2]=='\n'){
                    line++;
                    i=-2;
                }
                for (k = line; k < totalLine; k++) {
                    for (o=i+2;o<chars[k].length;o++) {
                        char chc=chars[k][o];
                        if(chc=='*'){
                            if (chars[k][o+1]=='/'){
                                if(o+2<chars[k].length&&chars[k][o+2]!='\n'){
                                    i=o+2;
                                    line=k+1;
                                    jud=false;
                                    System.out.println(chars[line][i]+"*-*-"+chars[line][i]);
                                }else {
                                    line=k+1;
                                    i=-1;
                                    jud=false;
                                }
                                break;
                            }
                        }
                    }
                    if (!jud)
                        break;
                }
            }else if (chars[line][i+1]=='='){
                i++;
                printfToken("/=");
//                System.out.println("(/=,-)");
            }
        }else if(keyData.ch=='&'){
            if (chars[line][i+1]=='&'){
                i=i+1;
                printfToken("&&");
//                System.out.println("(&&,-)");
            }else {
                printfToken2('&');
//                System.out.println("(&,-)");
            }
        }else if(keyData.ch=='^'){
            printfToken2('^');
//            System.out.println("(^,-)");
        }else if(keyData.ch=='%'){
            if(chars[line][i+1]=='='){
                i=i+1;
                printfToken("%=");
//                System.out.println("(%=,-)");
            }else{
                printfToken2('%');
//                System.out.println("(%,-)");
            }
        }else if(keyData.ch=='#'){
            printfToken2('#');
//            System.out.println("(#,-)");
        }else if (keyData.ch=='!'){
            if (chars[line][i+1]=='='){
                i=i+1;
                printfToken("!=");
//                System.out.println("(!=,-)");
            }else{
                printfToken2('!');
//                System.out.println("(!,-)");
            }
        }else if(keyData.ch=='~'){
            printfToken2('~');
//            System.out.println("(~,-)");
        }else if(keyData.ch=='['){
            printfToken2('[');
//            System.out.println("([,-)");
        }else if(keyData.ch==']'){
            printfToken2(']');
//            printfToken2(keyData.ch);
        }else if(keyData.ch=='('){
            printfToken2('(');
//            printfToken2(keyData.ch);
        }else if(keyData.ch==')'){
            printfToken2(')');
//            printfToken2(keyData.ch);
        }else if(keyData.ch=='{'){
            printfToken2('{');
//            printfToken2(keyData.ch);
        }else if(keyData.ch=='}'){
            printfToken2('}');
//            printfToken2(keyData.ch);
        }else if(keyData.ch=='='){
            if(chars[line][i+1]=='='){
                i=i+1;
                printfToken("==");
//                System.out.println("(==,-)");
            }else
                printfToken2('=');
        }else if(keyData.ch==','){
            printfToken2(',');
        }else if(keyData.ch==';'){
            printfToken2(';');
            i=-1;
            line++;
        }else if(keyData.ch=='<'){
            if(chars[line][i+1]=='='){
                i=i+1;
                printfToken("<=");
//                System.out.println("(<=,-)");
            }else if(chars[line][i+1]=='<'){
                i=i+1;
                printfToken("<<");
//                System.out.println("(<=,-)");
            }else {
                printfToken2('<');
//                printfToken2(keyData.ch);
            }
        }else if(keyData.ch=='>'){
            if(chars[line][i+1]=='='){
                i=i+1;
                printfToken(">=");
//                System.out.println("(>=,-)");
            }else if(chars[line][i+1]=='<'){
                i=i+1;
                printfToken(">>");
//                System.out.println("(>>,-)");
            }else {
                printfToken2('>');
//                printfToken2(keyData.ch);
            }
        }else if(keyData.ch=='?'){
            printfToken2('?');
        }else if(keyData.ch==':'){
            printfToken2(':');
        }else if(keyData.ch=='\''){
            printfToken2(keyData.ch);
        }else if(keyData.ch=='"'){
            printfToken2('"');
        }else if(keyData.ch=='|'){
            if(chars[line][i+1]=='|'){
                i=i+1;
                printfToken("||");
//                System.out.println("(||,-)");
            }else {
                printfToken2('|');
            }
        }else if(keyData.ch==' '){
        }else if(change()){
            if(chars[line][i-1]!=';'&&chars[line][i]=='\n'){
                line++;
                i=-1;
                error[index]="结尾缺少；";
                handle[index]="直接跳过";
            }
        }else{
            error[index]="出现未定义符号";
            handle[index]="直接跳过";
//            System.out.println(keyData.ch);
        }
    }

}
