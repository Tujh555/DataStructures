package org.example.practice3.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MySetTest {

    @Test
    public void allElementsShouldBeUnique() {
        ThreadLocalRandom local = ThreadLocalRandom.current();
        Random rnd = new Random();

        HashSet<Integer> hashSet = new HashSet<>();
        MySet<Integer> mySet = new MySet<>();

        int[] testArray = new int[local.nextInt(10, 100)];

        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = rnd.nextInt();
            hashSet.add(testArray[i]);
            mySet.add(testArray[i]);
        }

        Assertions.assertTrue(SetUtils.isEquals(mySet, hashSet));
    }

    @Test
    public void shouldNotAddContainedElement() {
        int[] arr = new int[] { -2, -3, -4, -5, -7 };
        MySet<Integer> mySet = new MySet<>();

        for (int i = 0; i < arr.length * 2; i++) {
            mySet.add(arr[i % arr.length]);
        }

        Assertions.assertEquals(arr.length, mySet.size());
    }

    @Test
    public void shouldUnionSets() {
        Set<Integer> set1 = Set.of(2, 3, 8, 9, 10, 11);
        Set<Integer> set2 = Set.of(4, 5, 8, 9, 10, 12);
        Set<Integer> set3 = Set.of(6, 7, 8, 9, 10, 12, 11, 1203);

        Set<Integer> expected = Stream.concat(Stream.concat(set1.stream(), set2.stream()), set3.stream())
                .collect(Collectors.toSet());

        MySet<Integer> actual = SetUtils.union(set1, set2, set3);
        Assertions.assertTrue(SetUtils.isEquals(expected, actual));
    }

    @Test
    public void shouldIntersectSets() {
        Set<Integer> set1 = Set.of(2, 3, 8, 9, 10, 11);
        Set<Integer> set2 = Set.of(4, 5, 8, 9, 10, 12);
        Set<Integer> set3 = Set.of(6, 7, 8, 9, 10, 12, 11, 1203);

        Set<Integer> expected = set1.stream()
                .filter(i -> set2.contains(i) && set3.contains(i))
                .collect(Collectors.toSet());

        MySet<Integer> actual = SetUtils.intersection(set1, set2, set3);
        Assertions.assertTrue(SetUtils.isEquals(expected, actual));
    }

    @Test
    public void shouldSubtractSets() {
        Set<Integer> set1 = new HashSet<>(Set.of(2, 3, 8, 9, 10, 11));
        Set<Integer> set2 = Set.of(4, 5, 8, 9, 10, 12);

        MySet<Integer> actual = SetUtils.div(set1, set2);
        set1.removeAll(set2);
        Assertions.assertTrue(SetUtils.isEquals(set1, actual));
    }

    @Test
    public void checkGirlsInClassrooms() {
        String nameString = "Александра, Алёна, Алина, Алиса, Алла, Анастасия, Анна, Арина, Валентина, Валерия, Варвара, Вера, Вероника, Виктория, Виталия";
        String[] names = nameString.split(", ");

        ArrayList<MySet<String>> parallel = new ArrayList<>();
        parallel.add(SetUtils.from("Александра", "Алёна", "Алина", "Алиса", "Алла"));
        parallel.add(SetUtils.from("Алиса", "Алла", "Анастасия", "Анна", "Арина", "Валентина"));
        parallel.add(SetUtils.from("Валентина", "Валерия", "Варвара", "Алиса", "Алла"));

        MySet<String> intersection = SetUtils.intersection(parallel);
        System.out.println("Имена девочек, которые встречаются во всех классах: " + intersection);

        MySet<String> union = SetUtils.union(parallel);
        MySet<String> symmetricDifference = SetUtils.div(union, intersection);
        System.out.println("Имена девочек, которые встречаются в некоторых классах: " + symmetricDifference);

        MySet<String> allNames = SetUtils.from(names);
        MySet<String> unmatchedNames = SetUtils.div(allNames, union);
        System.out.println("Имена девочек, которые не встречаются ни в одном из классов: " + unmatchedNames);
    }
}
