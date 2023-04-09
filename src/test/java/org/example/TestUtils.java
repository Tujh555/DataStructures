package org.example;

import org.example.practice2.list.Furniture;
import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {
    public static void assertEqualsIterable(Iterable<?> expected, Iterable<?> actual) {
        Iterator<?> expectedIterator = expected.iterator();
        Iterator<?> actualIterator = actual.iterator();

        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            Assertions.assertEquals(expectedIterator.next(), actualIterator.next());
        }

        Assertions.assertTrue(!expectedIterator.hasNext() && !actualIterator.hasNext());
    }

    public static List<Furniture> generateRandomFurniture(int count) {
        ArrayList<Furniture> list = new ArrayList<>(count);

        for (int i = 1; i <= count; i++) {
            final String partNumber = UUID.randomUUID().toString();
            Furniture furniture = new Furniture(partNumber.substring(0, Math.min(8, partNumber.length())), "title " + i,
                    new Random().nextFloat(), new Date(), i * ThreadLocalRandom.current().nextInt(5, 100));

            list.add(furniture);
        }

        return list;
    }
}
