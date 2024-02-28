package ru.meldren.lab1.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BinomialHeapTest {

    @Test
    void addNullElementThrowsNullPointerExceptionTest() {
        assertThrows(NullPointerException.class, () -> new BinomialHeap<>().add(null));
    }

    @ParameterizedTest
    @MethodSource("valuesProvider")
    void addElementsAndCheckSizeTest(int[] values) {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        for (int value : values) {
            heap.add(value);
        }
        checkHeapStructure(heap);
        Arrays.sort(values);
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            assertEquals(values.length - i, heap.size());
            assertEquals(value, heap.element());
            assertEquals(value, heap.remove());
            checkHeapStructure(heap);
        }
        assertEquals(0, heap.size());
    }

    @Test
    void mergeTwoHeapsSizeTest() {
        BinomialHeap<Integer> firstHeap = new BinomialHeap<>();
        firstHeap.add(4);
        firstHeap.add(5);
        firstHeap.add(6);
        BinomialHeap<Integer> secondHeap = new BinomialHeap<>();
        secondHeap.add(1);
        secondHeap.add(2);
        secondHeap.add(3);
        firstHeap.merge(secondHeap);
        assertEquals(0, secondHeap.size());
        assertEquals(6, firstHeap.size());
    }

    private static Stream<int[]> valuesProvider() {
        return Stream.of(
                new int[]{7},
                new int[]{33, 999},
                new int[]{999, 33},
                new int[]{3423, 1297, 5, 127565204, 12345, 1, 999999999}
        );
    }

    private <E extends Comparable<? super E>> void checkHeapStructure(
            BinomialHeap<E> heap
    ) {
        if (heap.head.value != null || heap.head.rank != -1) {
            throw new IllegalStateException("Head must be dummy node");
        }
        // Check chain of nodes and their children
        checkNodeStructure(heap.head, true, null);
    }

    private <E extends Comparable<? super E>> void checkNodeStructure(
            BinomialHeap.Node<E> node,
            boolean isMain,
            E lowerBound
    ) {
        // Basic checks
        if ((node.rank < 0) != (node.value == null)) {
            throw new IllegalStateException("Invalid node rank or value.");
        }
        if (isMain != (lowerBound == null)) {
            throw new IllegalStateException("Invalid arguments.");
        }
        if (!isMain && node.value.compareTo(lowerBound) < 0) {
            throw new IllegalStateException("Min-heap property violated.");
        }
        // Check children and non-main chains
        if (node.rank > 0) {
            if (node.down == null || node.down.rank != node.rank - 1) {
                throw new IllegalStateException("Down node absent or has invalid rank.");
            }
            checkNodeStructure(node.down, false, node.value);
            if (!isMain) {
                if (node.next == null || node.next.rank != node.rank - 1) {
                    throw new IllegalStateException("Next node absent or has invalid rank.");
                }
                checkNodeStructure(node.next, false, lowerBound);
            }
        } else if (node.down != null) {
            throw new IllegalStateException("Down node must be absent.");
        }
        // Check main chain
        if (isMain && node.next != null) {
            if (node.next.rank <= node.rank) {
                throw new IllegalStateException("Next node has invalid rank.");
            }
            checkNodeStructure(node.next, true, null);
        }
    }
}
