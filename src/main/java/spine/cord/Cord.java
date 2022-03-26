package spine.cord;

import spine.vertebra.Vertebra;

import java.util.ArrayList;

public class Cord {
    public int[] innerStructure;
    private int mostSignificantBit(int key) {
        int result = key;

        result |= result >> 1;
        result |= result >> 2;
        result |= result >> 4;
        result |= result >> 8;
        result |= result >> 16;
        result |= result >> 32;

        return result - (result >> 1);
    }

    private int leastSignificantBit(int key) {
        return key & -key;
    }

    public Cord(int[] lengthArray) {
        int length = lengthArray.length;
        this.innerStructure = new int[length];
        this.innerStructure[0] = lengthArray[0];
        for (int i = 1; i < length; i++) {
            this.innerStructure[i] = this.innerStructure[i - 1] + lengthArray[i];
        }
        for (int i = length - 1; i > 0; i--) {
            int lowerBound = (i & (i + 1)) - 1;
            if (lowerBound >= 0) {
                this.innerStructure[i] -= this.innerStructure[lowerBound];
            }
        }
    }

    public<T> Cord(ArrayList<Vertebra<T>> spine) {
        int length = spine.size();
        this.innerStructure = new int[length];
        this.innerStructure[0] = spine.get(0).size();
        for (int i = 1; i < length; i++) {
            this.innerStructure[i] = this.innerStructure[i - 1] + spine.get(i).size();
        }
        for (int i = length - 1; i > 0; i--) {
            int lowerBound = (i & (i + 1)) - 1;
            if (lowerBound >= 0) {
                this.innerStructure[i] -= this.innerStructure[lowerBound];
            }
        }
    }

    public int prefixSum(int ith) {
        int sum = 0;
        int i = ith;
        while (i > 0) {
            sum += this.innerStructure[i - 1];
            i &= i - 1;
        }
        return sum;
    }

    public void increaseLength(int ith) {
        int length = this.innerStructure.length;
        int i = ith;
        while (i < length) {
            this.innerStructure[i] += 1;
            i |= i + 1;
        }

    }

    public void decreaseLength(int ith) {
        int length = this.innerStructure.length;
        int i = ith;
        while (i < length) {
            this.innerStructure[i] -= 1;
            i |= i + 1;
        }
    }

    public int indexOf(int ith) {
        int length = this.innerStructure.length;
        int high = mostSignificantBit(length) * 2;
        int mid = 0;

        while (high != mid) {
            int lsb = leastSignificantBit(high);
            if (high <= length && this.innerStructure[high - 1] <= ith) {
                ith -= this.innerStructure[high - 1];
                mid = high;
                high += lsb / 2;
            } else {
                high += lsb / 2 - lsb;
            }
        }
        return mid;
    }
}
