package org.okanatov.test.symbols;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnvTest {
    @Test
    public void testPutAndGet() {
        Env env = new Env(null);
        Symbol s = new Symbol();

        env.put("bool", s);
        assertEquals(s, env.get("bool"));
    }
}