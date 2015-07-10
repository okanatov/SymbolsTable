package org.okanatov.test.symbols;

import java.io.IOException;

public class Parser {
    private Lexer lexer;
    private Token lookahead;
    private Env top;
    private StringBuffer result = new StringBuffer();
    private Symbol s;

    public Parser(String string) throws IOException {
        lexer = new Lexer(string);
        lookahead = lexer.scan();
    }

    private void match(Tag tag) throws IOException {
        if (lookahead.tag == tag) lookahead = lexer.scan();
        else throw new Error("Parser error: unexpected token");
    }

    public String program() throws IOException {
        top = new Env(null);
        block();
        return result.toString();
    }

    private void block() throws IOException {
        match(Tag.LBRACE);
        Env saved = top;
        top = new Env(top);
        result.append("{ ");
        declarations();
        statements();
        match(Tag.RBRACE);
        top = saved;
        result.append("} ");
    }

    private void declarations() throws IOException {
        while (true) {
            if (lookahead.tag == Tag.BOOL ||
                lookahead.tag == Tag.CHAR ||
                lookahead.tag == Tag.INT)
            {
                s = new Symbol();
                s.type = ((Word) lookahead).lexeme;
                match(lookahead.tag);
                top.put(((Word) lookahead).lexeme, s);
                match(Tag.ID);
                match(Tag.SEMICOLON);
                continue;
            }
            break;
        }
    }

    private void statements() throws IOException {
        while (true) {
            if (lookahead.tag == Tag.LBRACE) {
                block();
                continue;
            } else if (lookahead.tag == Tag.ID) {
                factor();
                match(Tag.SEMICOLON);
                continue;
            }
            break;
        }
    }

    private void factor() throws IOException {
        s = top.get(((Word) lookahead).lexeme);
        result.append(((Word) lookahead).lexeme);
        result.append(":");
        result.append(s.type);
        result.append("; ");
        match(Tag.ID);
    }
}
