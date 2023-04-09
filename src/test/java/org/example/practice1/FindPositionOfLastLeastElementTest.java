package org.example.practice1;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class FindPositionOfLastLeastElementTest {
    @Test
    public void shouldReturnMinusOneAsPositionWithEmptyArray() {
        int[] arr = new int[0];

        Assertions.assertEquals(-1, Practice1.findPositionOfLastLeastElement(arr));
    }

    @Test
    public void shouldReturnMinusOneAsPositionWithAllNegativeElements() {
        int[] arr = new int[]{-1, -1, -1, -1, -1, -1, -1, -2, -3, -10};
        Assertions.assertEquals(-1, Practice1.findPositionOfLastLeastElement(arr));
    }

    @Test
    public void shouldReturnThePositionOfTheOnlyPositiveElement() {
        int searchElement = 33;
        int[] arr = new int[]{-1, -100, 0, searchElement, 0, -1};

        Assertions.assertEquals(indexOf(arr, searchElement), Practice1.findPositionOfLastLeastElement(arr));
    }

    @Test
    public void shouldReturnLastPositionOfTwoEqualsElements() {
        int searchElement = 33;
        int[] arr = new int[]{-1, -100, 0, searchElement, 0, -1, searchElement, -10, searchElement};

        Assertions.assertEquals(lastIndexOf(arr, searchElement), Practice1.findPositionOfLastLeastElement(arr));
    }

    @Test
    public void shouldReturnPositionOfLeastPositiveElement() {
        int searchElement = 1;
        int[] arr = new int[]{2, 30, 3, 100, searchElement, 3, -100};

        Assertions.assertEquals(indexOf(arr, searchElement), Practice1.findPositionOfLastLeastElement(arr));
    }

    @Test
    public void shouldReturnLastPositionOfLeastElement() {
        int searchElement = 1;
        int[] arr = new int[]{2, 30, 3, 100, searchElement, 3, -100, searchElement, -100, searchElement};

        System.out.println(lastIndexOf(arr, searchElement));
        Assertions.assertEquals(lastIndexOf(arr, searchElement), Practice1.findPositionOfLastLeastElement(arr));
    }

    private static int indexOf(int @NotNull [] arr, int val) {
        return IntStream.range(0, arr.length).filter(i -> arr[i] == val).findFirst().orElse(-1);
    }

    private static int lastIndexOf(int @NotNull [] arr, int val) {
        ArrayList<Integer> list = new ArrayList<>(arr.length);
        for (int a : arr) {
            list.add(a);
        }

        return list.lastIndexOf(val);
    }
}
