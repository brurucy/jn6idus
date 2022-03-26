package spine;

import org.junit.jupiter.api.Test;
import spine.vertebra.Vertebra;

public class spineTest {
    @Test
    void PushWithBalanceTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);
        Vertebra<Integer> expectedVertebraOne = new Vertebra<>();
        expectedVertebraOne.add(1);
        Vertebra<Integer> expectedVertebraTwo = new Vertebra<>();
        expectedVertebraTwo.add(2);
        Vertebra<Integer> expectedVertebraThree = new Vertebra<>();
        expectedVertebraThree.add(3);
        expectedVertebraThree.add(5);

        assert spine.vertebrae.get(0).equals(expectedVertebraOne);
        assert spine.vertebrae.get(1).equals(expectedVertebraTwo);
        assert spine.vertebrae.get(2).equals(expectedVertebraThree);
    }

    @Test
    void PushWithBalanceComplexTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);
        spine.push(9);
        spine.push(0);
        spine.push(13);
        spine.push(1);
        spine.push(13);
        spine.push(8);
        spine.push(6);
        spine.push(7);
        spine.push(4);
        spine.push(15);
    }

    @Test
    void DeleteWithBalanceTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);

        spine.delete(3);
        Vertebra<Integer> expectedVertabraOne = new Vertebra<>();
        expectedVertabraOne.add(5);
        assert spine.vertebrae.get(2).equals(expectedVertabraOne);

        assert spine.vertebrae.size() == 3;
        spine.delete(1);
        assert spine.vertebrae.size() == 2;
        spine.delete(2);
        assert spine.vertebrae.size() == 1;
        spine.delete(5);
        assert spine.length == 0;
        assert spine.vertebrae.size() == 1;
        assert !spine.delete(5);
        assert !spine.delete(2);
        assert !spine.delete(1);
        assert !spine.delete(3);
    }

    @Test
    void DeleteByIndexBalanceTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);

        assert spine.length == 4;
        spine.deleteByIndex(0);
        assert spine.length == 3;
        assert spine.vertebrae.get(0).get(0).compareTo(1) != 0;
        assert spine.deleteByIndex(0).compareTo(2) == 0;
        assert spine.deleteByIndex(1).compareTo(5) == 0;
        assert spine.deleteByIndex(0).compareTo(3) == 0;
        assert spine.deleteByIndex(0) == null;
        assert spine.length == 0;
    }

    @Test
    void GetTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);

        assert spine.get(0).compareTo(1) == 0;
        assert spine.get(1).compareTo(2) == 0;
        assert spine.get(2).compareTo(3) == 0;
        assert spine.get(3).compareTo(5) == 0;
        assert spine.get(4) == null;
    }

    @Test
    void hasTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);

        assert spine.has(1);
        assert spine.has(5);
        assert spine.has(2);
        assert spine.has(3);
        assert !spine.has(10);
        assert !spine.has(12);
        assert !spine.has(0);
    }
}

