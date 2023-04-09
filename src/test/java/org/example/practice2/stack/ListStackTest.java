package org.example.practice2.stack;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ListStackTest {
    private List<Integer> randomNumbers = new ArrayList<>();
    private static int N;
    private Stack<Integer> stack;

    @BeforeAll
    public void prepareData() {
        N = ThreadLocalRandom.current().nextInt(20, 200);
        randomNumbers = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            randomNumbers.add(ThreadLocalRandom.current().nextInt(10, 100));
        }
    }

    @Test
    public final void shouldCorrectCountNumbers() {
        stack = ListStack.init(N, i -> randomNumbers.get(i));

        final int expected = (int) randomNumbers.stream().filter(i -> i % 7 == 2).count();
        int actual = 0;

        while (stack.getSize() > 0) {
            if (stack.pop() % 7 == 2) {
                actual++;
            }
        }

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public final void shouldThrowsExceptionWithEmptyStack() {
        stack = new ListStack<>();
        while (stack.getSize() > 0) {
            stack.pop();
        }

        Assertions.assertThrows(AssertionError.class, () -> stack.pop());
    }
}
