package com.yejf.lang.stone;

import java.util.List;

public abstract class Token<T> {
    /*
    * token 包含以下信息：
    * 1. lineNumber
    * 2. token type
    * 3. value
    * */

    private int lineNumber;
    private TokenTypeEnum type;
    private T value;

    public Token(int lineNumber, TokenTypeEnum type, T value) {
        this.lineNumber = lineNumber;
        this.type = type;
        this.value = value;
    }

    public static final Token EOF = new Token(-1,TokenTypeEnum.EOF,null){

    };
}
