package org.okanatov.test.symbols;

import java.io.IOException;
import java.io.StringReader;

public class Lexer {
    private StringReader reader;
    private char peek = ' ';

    public Lexer(String string) {
        reader = new StringReader(string);
    }

    public String scan() throws IOException {
        for (;; peek = (char) reader.read()) {
            if (peek == ' ' || peek == '\t')
                continue;
            else
                break;
        }

        if (Character.isLetter(peek)) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                peek = (char) reader.read();
            } while (Character.isLetterOrDigit(peek));

            String s = b.toString();
            return s;
        }

        String result = String.valueOf(peek);
        peek = ' ';
        return result;
    }
}
