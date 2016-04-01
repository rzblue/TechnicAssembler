package com.github.firecrafty.technicassembler;

import java.util.Locale;

/**
 *
 * @author firecrafty
 */
public enum Side {
    COMMON,
    CLIENT,
    SERVER;
    
    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
