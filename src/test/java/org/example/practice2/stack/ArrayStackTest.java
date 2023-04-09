package org.example.practice2.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArrayStackTest {
    private List<Integer> randomNumbers = new ArrayList<>();
    private static int N = 100;
    private Stack<Integer> stack;

    @BeforeAll
    public void prepareData() {
        N = ThreadLocalRandom.current().nextInt(10, 20);
        randomNumbers = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            randomNumbers.add(ThreadLocalRandom.current().nextInt(10, 100));
        }
    }

    @Test
    public final void shouldCorrectCountNumbers() {
        stack = ArrayStack.init(N, i -> randomNumbers.get(i));
        System.out.println(stack);

        final int expected = (int) randomNumbers.stream().filter(i -> i % 7 == 2).count();
        int actual = 0;

        while (stack.getSize() > 0) {
            if (stack.pop() % 7 == 2) {
                actual++;
            }
        }
        System.out.println(actual);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public final void shouldThrowsExceptionWithEmptyStack() {
        stack = new ArrayStack<>();
        while (stack.getSize() > 0) {
            stack.pop();
        }

        Assertions.assertThrows(Exception.class, () -> stack.pop());
    }
}
