package org.okanatov.test.symbols;

public final class Word extends Token {
    public final String lexeme;

    public Word(Tag t, String l) {
        super(t);
        lexeme = l;
    }
}
