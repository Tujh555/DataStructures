package org.example.practice3.queue;

import org.example.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueueTest {
    private Queue<Integer> queue;
    private List<Integer> list;
    private final int[] positiveNumbers = new int[] {2, 4, 5, 70, 100, 20};

    @BeforeAll
    public void prepareQueue() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int size = random.nextInt(10, 50);
        list = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100, 505));
        }

        for (int positiveNumber : positiveNumbers) {
            list.set(random.nextInt(0, list.size()), positiveNumber);
        }

        queue = QueueUtils.init(list.size(), i -> list.get(i));
    }

    @Test
    public void shouldDeleteAllEvenNumbers() {
        int size = queue.getSize();
        list = list.stream().filter(i -> i % 2 == 1).collect(Collectors.toList());

        for (int i = 0; i < size; i++) {
            int value = queue.pop();
            if (value % 2 == 1) {
                queue.put(value);
            }
        }

        TestUtils.assertEqualsIterable(list, queue);
    }
}

