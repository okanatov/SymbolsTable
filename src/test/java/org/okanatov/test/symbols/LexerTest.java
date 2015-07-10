package org.okanatov.test.symbols;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LexerTest {
    @Test
    public void testScan() throws IOException {
        Lexer lexer = new Lexer("{ int x; char y; { bool y; x; y; } x; y; }");
        assertEquals(Tag.LBRACE, lexer.scan().tag);
        assertEquals(Tag.INT, lexer.scan().tag);
        assertEquals(Tag.ID, lexer.scan().tag);
        assertEquals(Tag.SEMICOLON, lexer.scan().tag);
        assertEquals(Tag.CHAR, lexer.scan().tag);
        assertEquals(Tag.ID, lexer.scan().tag);
        assertEquals(Tag.SEMICOLON, lexer.scan().tag);
        assertEquals(Tag.LBRACE, lexer.scan().tag);
        assertEquals(Tag.BOOL, lexer.scan().tag);
        assertEquals(Tag.ID, lexer.scan().tag);
        assertEquals(Tag.SEMICOLON, lexer.scan().tag);
        assertEquals(Tag.ID, lexer.scan().tag);
        assertEquals(Tag.SEMICOLON, lexer.scan().tag);
        assertEquals(Tag.ID, lexer.scan().tag);
        assertEquals(Tag.SEMICOLON, lexer.scan().tag);
        assertEquals(Tag.RBRACE, lexer.scan().tag);
        assertEquals(Tag.ID, lexer.scan().tag);
        assertEquals(Tag.SEMICOLON, lexer.scan().tag);
        assertEquals(Tag.ID, lexer.scan().tag);
        assertEquals(Tag.SEMICOLON, lexer.scan().tag);
        assertEquals(Tag.RBRACE, lexer.scan().tag);
    }
}