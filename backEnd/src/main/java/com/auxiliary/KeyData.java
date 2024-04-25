package com.auxiliary;

public class KeyData {
        static public String[] strings = {"short","int","bool","long","float",
                "double", "char","static","for","while",
                "do","continue","break","return","void",
                "switch","case","auto","goto","const",
                "default","typedef","sizeof","struct",
                "static","signed","unsigned","exit",
                "main","scanf","printf","getchar"};
        public char ch;
        public char prech ='$';
        public String name;
//      数字变量
        public int number=0,idnum=0,presenum=0;
//      各种数字
        public char[][] numch = new char[100][20];
//      各种id
        public char[][] id = new char[100][20];
//      各种保留字
        public char[][] prese = new char[100][20];
//      传递的变量
        public char[][] lexicalDemonstrate = new char[300][20];
}
