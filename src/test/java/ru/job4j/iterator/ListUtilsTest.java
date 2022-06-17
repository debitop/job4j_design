package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 4, 2, 1));
        Predicate<Integer> pred = a -> a > 1;
        ListUtils.removeIf(input, pred);
        assertThat(input, is(Arrays.asList(0, 1)));
    }

    @Test
    public void whenReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 4, 2, 1));
        Predicate<Integer> pred = a -> a > 1;
        ListUtils.replaceIf(input, pred, 55);
        assertThat(input, is(Arrays.asList(0, 55, 55, 1)));
    }
 @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 4, 2, 1));
        List<Integer> elements = new ArrayList<>(Arrays.asList(4, 1));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(0, 2)));
    }


}