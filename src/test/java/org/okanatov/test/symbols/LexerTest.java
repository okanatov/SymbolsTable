package org.okanatov.test.symbols;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LexerTest {
    @Test
    public void testScan() throws IOException {
        Lexer lexer = new Lexer("{ int x; char y; { bool y; x; y; } x; y; }");
        assertEquals("{", lexer.scan());
        assertEquals("int", lexer.scan());
        assertEquals("x", lexer.scan());
        assertEquals(";", lexer.scan());
        assertEquals("char", lexer.scan());
        assertEquals("y", lexer.scan());
        assertEquals(";", lexer.scan());
        assertEquals("{", lexer.scan());
        assertEquals("bool", lexer.scan());
        assertEquals("y", lexer.scan());
        assertEquals(";", lexer.scan());
        assertEquals("x", lexer.scan());
        assertEquals(";", lexer.scan());
        assertEquals("y", lexer.scan());
        assertEquals(";", lexer.scan());
        assertEquals("}", lexer.scan());
        assertEquals("x", lexer.scan());
        assertEquals(";", lexer.scan());
        assertEquals("y", lexer.scan());
        assertEquals(";", lexer.scan());
        assertEquals("}", lexer.scan());
    }
}