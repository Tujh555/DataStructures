package org.example.practice2.list;

import org.example.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SinglyLinkedListTest {
    private List<Furniture> testList = new ArrayList<>();
    private SinglyLinkedList<Furniture> singlyLinkedList;

    @BeforeAll
    public void resetList() {
        testList = TestUtils.generateRandomFurniture(5);
        singlyLinkedList = ListUtils.singlyLinkedList(5, (i) -> testList.get(i));
    }

    @Test
    public void shouldSortByPartNumber() {
        testList.sort(Comparator.comparing(Furniture::getPartNumber));
        singlyLinkedList.sortBy(Furniture::getPartNumber);

        TestUtils.assertEqualsIterable(testList,  singlyLinkedList);
    }

    @Test
    public void shouldSortByCopiesCount() {
        testList.sort(Comparator.comparing(Furniture::getCopiesCount));
        singlyLinkedList.sortBy(Furniture::getCopiesCount);

        TestUtils.assertEqualsIterable(testList,  singlyLinkedList);
    }

    @Test
    public void shouldSortByCreationDate() {
        System.out.println(singlyLinkedList);
        testList.sort(Comparator.comparing(Furniture::getProductionDate));
        singlyLinkedList.sortBy(Furniture::getProductionDate);

        System.out.println(singlyLinkedList);
        TestUtils.assertEqualsIterable(testList,  singlyLinkedList);
    }

    @Test
    public void shouldFindItemByPartNumber() {
        final String number = testList.get(ThreadLocalRandom.current().nextInt(0,
                testList.size())).getPartNumber();

        final Optional<Furniture> expected = testList
                .stream()
                .filter(furniture -> furniture.getPartNumber().equals(number))
                .findFirst();

        Assertions.assertTrue(expected.isPresent());

        final Furniture actual = singlyLinkedList.getByOrNull(number, Furniture::getPartNumber);
        Assertions.assertNotNull(actual);

        expected.ifPresent(f -> Assertions.assertEquals(f, actual));
    }

    @Test
    public void shouldFindByCopiesCount() {
        final int count = testList.get(ThreadLocalRandom.current().nextInt(0,
                testList.size())).getCopiesCount();

        System.out.println(count);
        final Optional<Furniture> expected = testList
                .stream()
                .filter(furniture -> furniture.getCopiesCount() == count)
                .findFirst();

        Assertions.assertTrue(expected.isPresent());

        final Furniture actual = singlyLinkedList.getByOrNull(count, Furniture::getCopiesCount);
        Assertions.assertNotNull(actual);

        System.out.println(actual);
        expected.ifPresent(f -> Assertions.assertEquals(f, actual));
    }

    @Test
    public void shouldFindItemByCreationDate() {
        final Date date = testList.get(ThreadLocalRandom.current().nextInt(0,
                testList.size())).getProductionDate();

        System.out.println(date);
        final Optional<Furniture> expected = testList
                .stream()
                .filter(furniture -> furniture.getProductionDate().equals(date))
                .findFirst();

        Assertions.assertTrue(expected.isPresent());

        final Furniture actual = singlyLinkedList.getByOrNull(date, Furniture::getProductionDate);
        Assertions.assertNotNull(actual);

        System.out.println(actual);
        expected.ifPresent(f -> Assertions.assertEquals(f, actual));
    }

    @Test
    public void shouldReturnNullWithIncorrectQuery() {
        final Furniture value = singlyLinkedList.getByOrNull("", Furniture::getPartNumber);
        Assertions.assertNull(value);
    }
}
