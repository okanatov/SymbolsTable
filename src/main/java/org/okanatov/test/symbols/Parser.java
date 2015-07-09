package org.okanatov.test.symbols;

import java.io.IOException;

public class Parser {
    private Lexer lexer;
    private String lookahead;
    private Env top;
    private StringBuffer result = new StringBuffer();
    private Symbol s;

    public Parser(String string) throws IOException {
        lexer = new Lexer(string);
        lookahead = lexer.scan();
    }

    private void match(String s) throws IOException {
        if (lookahead.equals(s)) lookahead = lexer.scan();
        else throw new Error("syntax error");
    }

    private void block() throws IOException {
        match("{");
        Env saved = top;
        top = new Env(top);
        result.append("{ ");
        declarations();
        statements();
        match("}");
        top = saved;
        result.append(" } ");
    }

    private void declarations() throws IOException {
        while (true) {
            if (lookahead.equals("int")) {
                match("int");
                match("x");
                match(";");

                s = new Symbol();
                s.type = "int";
                top.put("x", s);
                continue;
            }
            break;
        }
    }

    private void statements() throws IOException {
        while (true) {
            if (lookahead.equals("{")) {
                block();
                continue;
            } else if (lookahead.equals("x")) {
                factor();
                match(";");
                continue;
            }
            break;
        }
    }

    private void factor() throws IOException {
        s = top.get(lookahead);
        match("x");
        result.append("x");
        result.append(":");
        result.append(s.type);
        result.append(";");
    }

    public String program() throws IOException {
        top = new Env(null);
        block();
        return result.toString();
    }
}
