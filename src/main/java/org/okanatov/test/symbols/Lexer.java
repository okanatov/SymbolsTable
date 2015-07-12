package org.okanatov.test.symbols;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Hashtable;

public class Lexer {
    private Reader reader;
    private int peek = ' ';
    private Hashtable<String, Word> words;

    private void reserve(Word word) {
        words.put(word.lexeme, word);
    }

    public Lexer(String string) {
        reader = new StringReader(string);
        words = new Hashtable<String, Word>();

        reserve(new Word(Tag.BOOL, "bool"));
        reserve(new Word(Tag.CHAR, "char"));
        reserve(new Word(Tag.INT, "int"));
    }

    public Token scan() throws IOException {
        for (; ; peek = reader.read()) {
            if (peek == -1)
                return null;
            else if (peek == ' ' || peek == '\t')
                continue;
            else
                break;
        }

        if (Character.isLetter(peek)) {
            StringBuffer b = new StringBuffer();
            do {
                b.append((char) peek);
                peek = reader.read();
            } while (Character.isLetterOrDigit(peek));

            String s = b.toString();
            Word word = words.get(s);
            if (word != null) return word;
            word = new Word(Tag.ID, s);
            words.put(s, word);
            return word;
        } else if (peek == '{') {
            peek = ' ';
            return new Token(Tag.LBRACE);
        } else if (peek == '}') {
            peek = ' ';
            return new Token(Tag.RBRACE);
        } else if (peek == ';') {
            peek = ' ';
            return new Token(Tag.SEMICOLON);
        }

        throw new Error("Lexer error: unknown symbol");
    }
}
