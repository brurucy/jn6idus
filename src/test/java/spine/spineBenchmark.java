package spine;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class spineBenchmark {
    /*
     * Here we compare the Spine with other ordered sets structures.
     * The only data structure that provides similar complexity, within the regular Java collections library
     * Is the SkipList.
     * */
    @Test
    void BenchmarkSequentialTest() {
        Spine<Integer> spine = new Spine<>(1024);
        Spine<Integer> spineTiny = new Spine<>(128);
        Spine<Integer> spineLarge = new Spine<>(8192);
        ConcurrentSkipListSet<Integer> skipList = new ConcurrentSkipListSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        System.out.println("Add - Sequential");
        long now = System.nanoTime();
//        for (int i = 0; i < 1000000; i++) {
//            spineLarge.push(i);
//        }
//        System.out.printf("\tSpine 8192 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        for (int i = 0; i < 1000000; i++) {
            spine.push(i);
        }
        System.out.printf("\tSpine 1024 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);
        assert spine.length == 1000000;

//        now = System.nanoTime();
//        for (int i = 0; i < 1000000; i++) {
//            spineTiny.push(i);
//        }
//        System.out.printf("\tSpine 128 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            skipList.add(i);
        }
        System.out.printf("\tSkip List - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);
        assert skipList.size() == 1000000;

        now = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            treeSet.add(i);
        }
        System.out.printf("\tTree Set - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);
        assert treeSet.size() == 1000000;

        System.out.println("Contains - Sequential");
//        now = System.nanoTime();
//        for (int i = 0; i < 1000000; i++) {
//            spineLarge.has(i);
//        }
//        System.out.printf("\tSpine 8192 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            spine.has(i);
        }
        System.out.printf("\tSpine 1024 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

//        now = System.nanoTime();
//        for (int i = 0; i < 1000000; i++) {
//            spineTiny.has(i);
//        }
//        System.out.printf("\tSpine 128 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            skipList.contains(i);
        }
        System.out.printf("\tSkip List - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            treeSet.contains(i);
        }
        System.out.printf("\tTree Set - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        System.out.println("Remove - Sequential");
//        now = System.nanoTime();
//        for (int i = 0; i < 1000000; i++) {
//            spineLarge.delete(i);
//        }
//        System.out.printf("\tSpine 8192 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            spine.delete(i);
        }
        assert spine.vertebrae.size() == 1;
        assert spine.length == 0;
        System.out.printf("\tSpine 1024 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

//        now = System.nanoTime();
//        for (int i = 0; i < 1000000; i++) {
//            spineTiny.delete(i);
//        }
//        System.out.printf("\tSpine 128 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            skipList.remove(i);
        }
        assert skipList.size() == 0;
        System.out.printf("\tSkip List - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            treeSet.remove(i);
        }
        assert treeSet.size() == 0;
        System.out.printf("\tTree Set - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);
    }

    @Test
    void BenchmarkRandomTest() {
        Spine<Integer> spine = new Spine<>(1024);
        Spine<Integer> spineTiny = new Spine<>(128);
        Spine<Integer> spineLarge = new Spine<>(8192);
        ConcurrentSkipListSet<Integer> skipList = new ConcurrentSkipListSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        Random random = new Random();

        int[] randomInts = random.ints(1000000, 0, 1000000).toArray();

        System.out.println("Add - Random");
        long now = System.nanoTime();
//        for (int randomInt : randomInts) {
//            spineLarge.push(randomInt);
//        }
//        System.out.printf("\tSpine 8192 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int randomInt : randomInts) {
            spine.push(randomInt);
        }
        System.out.printf("\tSpine 1024 - Time taken %d ns/op \n", (System.nanoTime() - now) / spine.length);

//        now = System.nanoTime();
//        for (int randomInt : randomInts) {
//            spineTiny.push(randomInt);
//        }
//        System.out.printf("\tSpine 128 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int randomInt : randomInts) {
            skipList.add(randomInt);
        }
        System.out.printf("\tSkip List - Time taken %d ns/op \n", (System.nanoTime() - now) / skipList.size());

        now = System.nanoTime();
        for (int randomInt : randomInts) {
            treeSet.add(randomInt);
        }
        System.out.printf("\tTree Set - Time taken %d ns/op \n", (System.nanoTime() - now) / treeSet.size());

        System.out.println("Contains - Random");
//        now = System.nanoTime();
//        for (int randomInt : randomInts) {
//            spineLarge.has(randomInt);
//        }
//        System.out.printf("\tSpine 8192 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int randomInt : randomInts) {
            spine.has(randomInt);
        }
        System.out.printf("\tSpine 1024 - Time taken %d ns/op \n", (System.nanoTime() - now) / spine.length);

//        now = System.nanoTime();
//        for (int randomInt : randomInts) {
//            spineTiny.has(randomInt);
//        }
//        System.out.printf("\tSpine 128 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        now = System.nanoTime();
        for (int randomInt : randomInts) {
            skipList.contains(randomInt);
        }
        System.out.printf("\tSkip List - Time taken %d ns/op \n", (System.nanoTime() - now) / skipList.size());

        now = System.nanoTime();
        for (int randomInt : randomInts) {
            treeSet.contains(randomInt);
        }
        System.out.printf("\tTree Set - Time taken %d ns/op \n", (System.nanoTime() - now) / treeSet.size());

        System.out.println("Remove - Random");
//        now = System.nanoTime();
//        for (int randomInt : randomInts) {
//            spineLarge.delete(randomInt);
//        }
//        System.out.printf("\tSpine 8192 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        int spineLength = spine.length;
        now = System.nanoTime();
        for (int randomInt : randomInts) {
            spine.delete(randomInt);
        }
        System.out.printf("\tSpine 1024 - Time taken %d ns/op \n", (System.nanoTime() - now) / spineLength);
        assert spine.vertebrae.size() == 1;
        assert spine.length == 0;
//        now = System.nanoTime();
//        for (int randomInt : randomInts) {
//            spineTiny.delete(randomInt);
//        }
//        System.out.printf("\tSpine 128 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);
        int skipListSize = skipList.size();
        now = System.nanoTime();
        for (int randomInt : randomInts) {
            skipList.remove(randomInt);
        }
        System.out.printf("\tSkip List - Time taken %d ns/op \n", (System.nanoTime() - now) / skipListSize);
        assert skipList.size() == 0;

        int treeSetSize = treeSet.size();
        now = System.nanoTime();
        for (int randomInt : randomInts) {
            treeSet.remove(randomInt);
        }
        System.out.printf("\tTree Set - Time taken %d ns/op \n", (System.nanoTime() - now) / treeSetSize);
        assert treeSet.size() == 0;
    }

    @Test
    void BenchmarkIndexTest() {
        Spine<Integer> spine = new Spine<>(1024);
        Spine<Integer> spineTiny = new Spine<>(128);
        Spine<Integer> spineLarge = new Spine<>(8192);
        ConcurrentSkipListSet<Integer> skipList = new ConcurrentSkipListSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        Random random = new Random();

        int[] randomInts = random.ints(1000000, 0, 1000000).toArray();

        long now = System.nanoTime();
//        for (int randomInt : randomInts) {
//            spineLarge.push(randomInt);
//        }
//        System.out.printf("\tSpine 8192 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        for (int randomInt : randomInts) {
            spine.push(randomInt);
        }

//        now = System.nanoTime();
//        for (int randomInt : randomInts) {
//            spineTiny.push(randomInt);
//        }
//        System.out.printf("\tSpine 128 - Time taken %d ns/op \n", (System.nanoTime() - now) / 1000000);

        for (int randomInt : randomInts) {
            skipList.add(randomInt);
        }

        for (int randomInt : randomInts) {
            treeSet.add(randomInt);
        }

        System.out.println("Get the i-th element - Order Irrelevant");

        now = System.nanoTime();
        for (int ith = 0; ith < spine.length; ith++) {
            spine.get(ith);
        }
        System.out.printf("\tSpine 1024 - Time taken %s ns/op \n", (System.nanoTime() - now) / spine.length);

        now = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            Object[] sortedArray = skipList.toArray();
        }
        System.out.printf("\tSkiplist - Time taken %s ns/op \n", (System.nanoTime() - now) / 10);

        now = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            Object[] sortedArray = treeSet.toArray();
        }
        System.out.printf("\tTreeSet - Time taken %s ns/op \n", (System.nanoTime() - now) / 10);
    }
}
