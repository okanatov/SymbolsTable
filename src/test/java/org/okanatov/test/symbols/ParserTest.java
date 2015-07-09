package org.okanatov.test.symbols;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void simpleTest() throws IOException {
        Parser parser = new Parser("{ int x; x; }");
        assertEquals("{ x:int; } ", parser.program());
    }

    @Test
    public void complexTest() throws IOException {
        Parser parser = new Parser("{ int x; { int x; x; } x; }");
        assertEquals("{ { x:int; } x:int; } ", parser.program());
    }
}