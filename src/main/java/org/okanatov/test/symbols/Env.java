package org.okanatov.test.symbols;

import java.util.Hashtable;

public class Env {
    private Env prev = null;
    private Hashtable<String, Symbol> table;

    public Env(Env prev) {
        this.prev = prev;
        this.table = new Hashtable<String, Symbol>();
    }

    public void put(String s, Symbol sym) {
        table.put(s, sym);
    }

    public Symbol get(String s) {
        for (Env e = this; e != null; e = e.prev) {
            Symbol found = e.table.get(s);
            if (found != null)
                return found;
        }
        return null;
    }
}
