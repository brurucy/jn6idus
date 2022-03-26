package spine.cord.test;

import org.junit.jupiter.api.Test;
import spine.cord.Cord;

import java.util.Arrays;

public class cordTest {
    @Test
    void BuildIndexSimpleTest() {
        int[] lengths = {1, 6, 3, 9, 2};
        int[] expectedInnerStructure = {1, 7, 3, 19, 2};
        Cord cord = new Cord(lengths);

        assert Arrays.equals(cord.innerStructure, expectedInnerStructure);
    }

    @Test
    void PrefixSumSimpleTest() {
        int[] lengths = {1, 6, 3, 9, 2};
        Cord cord = new Cord(lengths);
        int[] prefixSum = {1, 7, 10, 19, 21};

        int[] indices = {1, 2, 3, 4, 5};
        int[] calculatedPrefixSum = Arrays
                .stream(indices)
                .map((int length) -> cord.prefixSum(length)).toArray();

        assert Arrays.equals(prefixSum, calculatedPrefixSum);
    }

    @Test
    void UpdateSimpleAddTest() {
        int[] lengths = {1, 6, 3, 9, 2};
        Cord cord = new Cord(lengths);
        int[] prefixSum = {1, 7, 10, 19, 21};

        int[] indices = {1, 2, 3, 4, 5};
        int[] calculatedPrefixSum = Arrays
                .stream(indices)
                .map((int length) -> cord.prefixSum(length)).toArray();

        assert Arrays.equals(prefixSum, calculatedPrefixSum);

        lengths[1] += 1;
        cord.increaseLength(1);
        int[] newPrefixSum = {1, 8, 11, 20, 22};
        int[] newCalculatedPrefixSum = Arrays
                .stream(indices)
                .map((int length) -> cord.prefixSum(length)).toArray();
        assert Arrays.equals(newPrefixSum, newCalculatedPrefixSum);
    }

    @Test
    void UpdateSimpleRemoveTest() {
        int[] lengths = {1, 6, 3, 9, 2};
        Cord cord = new Cord(lengths);
        int[] prefixSum = {1, 7, 10, 19, 21};

        int[] indices = {1, 2, 3, 4, 5};
        int[] calculatedPrefixSum = Arrays
                .stream(indices)
                .map((int length) -> cord.prefixSum(length)).toArray();

        assert Arrays.equals(prefixSum, calculatedPrefixSum);

        lengths[1] -= 1;
        cord.decreaseLength(1);
        int[] newPrefixSum = {1, 6, 9, 18, 20};
        int[] newCalculatedPrefixSum = Arrays
                .stream(indices)
                .map((int length) -> cord.prefixSum(length)).toArray();
        assert Arrays.equals(newPrefixSum, newCalculatedPrefixSum);
    }

    @Test
    void IndexOfSimple() {
        int[] lengths = {1, 6, 3, 9, 2};
        Cord cord = new Cord(lengths);

        assert cord.indexOf(0) == 0;
        assert cord.indexOf(1) == 1;
        assert cord.indexOf(2) == 1;
        assert cord.indexOf(3) == 1;
        assert cord.indexOf(4) == 1;
        assert cord.indexOf(5) == 1;
        assert cord.indexOf(6) == 1;

        assert cord.indexOf(7) == 2;
        assert cord.indexOf(8) == 2;
        assert cord.indexOf(9) == 2;

        assert cord.indexOf(10) == 3;
        assert cord.indexOf(11) == 3;
        assert cord.indexOf(12) == 3;
        assert cord.indexOf(13) == 3;
        assert cord.indexOf(14) == 3;
        assert cord.indexOf(15) == 3;
        assert cord.indexOf(16) == 3;
        assert cord.indexOf(17) == 3;
        assert cord.indexOf(18) == 3;

        assert cord.indexOf(19) == 4;
        assert cord.indexOf(20) == 4;
    }
}
