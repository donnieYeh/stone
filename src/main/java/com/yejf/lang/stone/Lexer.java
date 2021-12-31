package com.yejf.lang.stone;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer implements LexerI {
    private BufferedReader reader;
    private List<Token> queue;
    private int lineNumber = 0;


    public Lexer(BufferedReader reader) {
        this.reader = reader;
        this.queue  = new ArrayList<>();
    }

    /*
    * 1. 每次读取一行
    * 2. 解析一行里的每一个词，存放到队列里
    * 3. 从队列里获取对应下标的词
    * 4. 特殊Token：行尾EOL、文件末尾EOF
    * 5. 普通Token分类：Number、String、Identity（标识符）
    * */

    @Override
    public Token read() {
        while (queue.size() < 1){
            if (!fillQueue()){
                return Token.EOF;
            }
        }
        return queue.remove(0);
    }

    @Override
    public Token peek(int index) {
        while (queue.size() <= index){
            if(!fillQueue()){
                return Token.EOF;
            }
        }
        return queue.get(index);
    }

    private boolean fillQueue() {
        try {
            String line = reader.readLine();
            if (line == null){
                return false;
            }

            parse(line,lineNumber);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void parse(String line, int lineNumber) {
        /**
         * 空格((注释)|(数字)|(字符串)|标记符{变量名，运算符，逻辑符})
         * \s*((//.*)|([0-9]+)|"([^"]*)"|([a-zA-Z]\w*|==|>=|<=|&&|\|\||p{Punct}))
         */
        Pattern pattern = Pattern.compile("\\s*((//.*)|([0-9]+)|\"([^\"]*)\"|" +
                "([a-zA-Z]\\w*|==|>=|<=|&&|\\|\\||\\p{Punct}))");
        Matcher matcher = pattern.matcher(line);
        while(matcher.find()){
            String match = matcher.group();
            if (matcher.group(2) != null){
                System.out.println("解析到注释："+match);
            }else if (matcher.group(3) != null){
                System.out.println("解析到数字："+match);
            }else if(matcher.group(4) != null){
                System.out.println("解析到字符串："+matcher.group(4));
            }else{
                System.out.println("解析到标识符："+match);
            }
        }
    }
}
