package com.yejf.lang.stone;

public interface LexerI {
    Token read();
    Token peek(int index);
}
