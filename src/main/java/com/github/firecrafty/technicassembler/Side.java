package com.github.firecrafty.technicassembler;

import java.util.Locale;

/**
 * @author firecrafty
 */
public enum Side {
    COMMON,
    CLIENT,
    SERVER;

    /**
     * Make the name usable for naming files
     *
     * @return the lowercase name of the {@code Side}
     */
    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
