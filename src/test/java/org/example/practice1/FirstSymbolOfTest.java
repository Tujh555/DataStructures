package org.example.practice1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstSymbolOfTest {
    @Test
    public void shouldReturnNullWithEmptyString() {
        final String s = "";

        Assertions.assertNull(Practice1.firstSymbolOf(s, ""));
    }

    @Test
    public void shouldReturnNullWithEmptyArgumentString() {
        final String s = this.getClass().getSimpleName();
        final Character actual = Practice1.firstSymbolOf(s, "");

        Assertions.assertNull(actual);
    }

    @Test
    public void shouldReturnFirstPositionOfChar() {
        final char expectedSymbol = 'g';
        final String s = "abcdef" + expectedSymbol;
        final String arg = "lllm,lmlm" + expectedSymbol;

        Assertions.assertEquals(expectedSymbol, Practice1.firstSymbolOf(s, arg));
    }
}

