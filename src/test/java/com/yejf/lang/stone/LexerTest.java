package com.yejf.lang.stone;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LexerTest{
    @Test
    public void lexerTest() throws FileNotFoundException {
        URL resource = this.getClass().getResource("/helloWorld.st");
        Path path = null;
        try {
            path = Paths.get(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File file = path.toFile();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        LexerI lexer = new Lexer(reader);
        Token token;
        while((token = lexer.read()) != Token.EOF){
            System.out.println(token);
        }

    }

}