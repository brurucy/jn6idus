package spine;

import org.junit.jupiter.api.Test;
import spine.vertebra.Vertebra;

import java.util.PriorityQueue;
import java.util.Random;

public class spineTest {
    @Test
    void PushWithBalanceTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);
        Vertebra<Integer> expectedVertebraOne = new Vertebra<>(1);
        expectedVertebraOne.add(1);
        Vertebra<Integer> expectedVertebraTwo = new Vertebra<>(1);
        expectedVertebraTwo.add(2);
        Vertebra<Integer> expectedVertebraThree = new Vertebra<>(1);
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
        Vertebra<Integer> expectedVertabraOne = new Vertebra<>(1);
        expectedVertabraOne.add(5);
        assert spine.vertebrae.get(2).equals(expectedVertabraOne);

        assert spine.size() == 3;
        spine.delete(1);
        assert spine.size() == 2;
        spine.delete(2);
        assert spine.size() == 1;
        spine.delete(5);
        assert spine.size() == 0;
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

        assert spine.size() == 4;
        spine.deleteByIndex(0);
        assert spine.size() == 3;
        assert spine.vertebrae.get(0).get(0).compareTo(1) != 0;
        assert spine.deleteByIndex(0).compareTo(2) == 0;
        assert spine.deleteByIndex(1).compareTo(5) == 0;
        assert spine.deleteByIndex(0).compareTo(3) == 0;
        assert spine.deleteByIndex(0) == null;
        assert spine.size() == 0;
    }

    @Test
    void GetTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(2);
        spine.push(3);

        assert spine.get(0).compareTo(1) == 0;
        assert spine.get(1).compareTo(2) == 0;
        assert spine.get(2).compareTo(2) == 0;
        assert spine.get(3).compareTo(3) == 0;
        assert spine.get(4).compareTo(5) == 0;
        assert spine.get(5) == null;
    }

    class container implements Comparable<container> {
        public int k;
        public int v;
        public container(int k, int v) {
            this.k = k;
            this.v = v;
        }
        @Override
        public int compareTo(container other){
            if (this.k < other.k)
                return -1;
            else if (this.k == other.k)
                return 0;
            else
                return 1;
        }
    }

    @Test
    void hasTest() {
        Spine<container> spine = new Spine<>(2);
        PriorityQueue<container> priorityQueue = new PriorityQueue<>();

        container container1 = new container(1, 5);
        container container2 = new container(5, 2);
        container container3 = new container(2, 9);
        container container4 = new container(5, 3);

        spine.push(container1);
        priorityQueue.add(container1);

        spine.push(container2);
        priorityQueue.add(container2);

        spine.push(container3);
        priorityQueue.add(container3);

        spine.push(container4);
        priorityQueue.add(container4);

        assert spine.has(container1);
        assert priorityQueue.contains(container1);
        assert !spine.has(new container(1, 5));
        assert !priorityQueue.contains(new container(1, 5));

        assert spine.has(container2);
        assert priorityQueue.contains(container2);
        assert !spine.has(new container(5, 2));
        assert !priorityQueue.contains(new container(5, 2));

        assert spine.has(container3);
        assert priorityQueue.contains(container3);
        assert !spine.has(new container(2, 9));
        assert !priorityQueue.contains(new container(2, 9));

        assert priorityQueue.contains(container4);
        assert spine.has(container4);
        assert !spine.has(new container(5, 3));
        assert !priorityQueue.contains(new container(5, 3));
    }

    @Test
    void complexHasTest() {
        Spine<container> spine = new Spine<>(1);

        container container1 = new container(5, 1);
        container container2 = new container(5, 2);
        container container3 = new container(5, 3);
        container container4 = new container(5, 4);
        container container5 = new container(5, 5);
        container container6 = new container(5, 6);
        container container7 = new container(5, 7);
        container container8 = new container(5, 8);
        container container9 = new container(5, 9);

        spine.push(container1);
        spine.push(container2);
        spine.push(container3);
        spine.push(container4);
        spine.push(container5);
        spine.push(container6);
        spine.push(container7);
        spine.push(container8);

        assert spine.has(container1);
        assert spine.has(container2);
        assert spine.has(container3);
        assert spine.has(container4);
        assert spine.has(container5);
        assert spine.has(container6);
        assert spine.has(container7);
        assert spine.has(container8);
        assert !spine.has(container9);

        assert spine.delete(container1);
        assert !spine.has(container1);
        assert !spine.delete(container1);

        assert spine.delete(container2);
        assert !spine.has(container2);
        assert !spine.delete(container2);

        assert spine.delete(container3);
        assert !spine.has(container3);
        assert !spine.delete(container3);

        assert spine.delete(container4);
        assert !spine.has(container4);
        assert !spine.delete(container4);

        assert spine.delete(container5);
        assert !spine.has(container5);
        assert !spine.delete(container5);

        assert spine.delete(container6);
        assert !spine.has(container6);
        assert !spine.delete(container6);

        assert spine.delete(container7);
        assert !spine.has(container7);
        assert !spine.delete(container7);

        assert spine.delete(container8);
        assert !spine.has(container8);
        assert !spine.delete(container8);
        assert spine.size() == 0;

        assert !spine.delete(container9);
        assert !spine.has(container9);
        assert spine.size() == 0;
    }

    @Test
    void pollAndPeekFirstTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);

        assert spine.size() == 4;
        assert spine.peekFirst().compareTo(spine.pollFirst()) == 0;
        assert spine.vertebrae.get(spine.vertebrae.size() - 1).max == 5;

        assert spine.size() == 3;
        assert spine.peekFirst().compareTo(spine.pollFirst()) == 0;
        assert spine.vertebrae.get(spine.vertebrae.size() - 1).max == 5;

        assert spine.size() == 2;
        assert spine.peekFirst().compareTo(spine.pollFirst()) == 0;
        assert spine.vertebrae.get(spine.vertebrae.size() - 1).max == 5;

        assert spine.size() == 1;
        assert spine.peekFirst().compareTo(spine.pollFirst()) == 0;
        assert spine.vertebrae.get(spine.vertebrae.size() - 1).max == 5;

        assert spine.size() == 0;
        assert spine.peekFirst() == null && spine.pollFirst() == null;
        assert spine.size() == 0;
    }

    @Test
    void pollAndPeekLastTest() {
        Spine<Integer> spine = new Spine<>(2);
        spine.push(1);
        spine.push(5);
        spine.push(2);
        spine.push(3);

        assert spine.vertebrae.get(spine.vertebrae.size() - 1).max.compareTo(spine.peekLast()) == 0;

        assert spine.size() == 4;
        assert spine.peekLast().compareTo(spine.pollLast()) == 0;
        assert spine.vertebrae.get(spine.vertebrae.size() - 1).max == 3;

        assert spine.size() == 3;
        assert spine.peekLast().compareTo(spine.pollLast()) == 0;
        assert spine.vertebrae.get(spine.vertebrae.size() - 1).max == 2;

        assert spine.size() == 2;
        assert spine.peekLast().compareTo(spine.pollLast()) == 0;
        assert spine.vertebrae.get(spine.vertebrae.size() - 1).max == 1;

        assert spine.size() == 1;
        assert spine.peekLast().compareTo(spine.pollLast()) == 0;

        assert spine.size() == 0;
        assert spine.peekLast() == null && spine.pollLast() == null;
        assert spine.size() == 0;
    }

    @Test
    void stressTest() {
        Spine<Integer> spine = new Spine<>(1024);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        Random random = new Random();

        int[] randomInts = random.ints(1000000, 0, 1000000).toArray();

        for (int randomInt : randomInts) {
            spine.push(randomInt);
        }

        for (int randomInt : randomInts) {
            priorityQueue.add(randomInt);
        }

        for (int i = 0; i < randomInts.length; i++) {
            assert spine.pollFirst().compareTo(priorityQueue.poll()) == 0;
        }

        assert spine.size() == priorityQueue.size();
    }
}

