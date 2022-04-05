package spine.vertebra;

import java.util.ArrayList;

public class Vertebra<T> extends ArrayList<T> {
    public T max;

    public Vertebra(int vertebraSize) {
        this.ensureCapacity(vertebraSize * 2);
    }

    public int lowerBound(T key) {
        int low = 0;
        int mid;
        int high = this.size();

        while (low < high) {
            mid = (low + high) >>> 1;
            Comparable<? super T> midVal = (Comparable<T>) this.get(mid);
            int cmp = midVal.compareTo(key);
            if (cmp < 0)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    public int upperBound(T key) {
        int low = 0;
        int mid;
        int high = this.size();

        while (low < high) {
            mid = (low + high) >>> 1;
            Comparable<? super T> midVal = (Comparable<T>) this.get(mid);
            int cmp = midVal.compareTo(key);
            if (cmp > 0)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    public boolean has(T key) {
        int lowerBound = this.lowerBound(key);
        int upperBound = this.upperBound(key);

        if (lowerBound >= this.size()) {
            return false;
        }

        for (int i = lowerBound; i < upperBound; i++) {
            if (this.get(i).equals(key))
                return true;
        }

        return false;
    }

    @Override
    public boolean add(T key) {
        int position = this.lowerBound(key);
        if (position >= this.size()) {
            this.max = key;
            return super.add(key);
        } else {
            Comparable<? super T> comparableKey = (Comparable<T>) key;
            this.add(position, key);
            int maxCmp = comparableKey.compareTo(this.max);
            if (maxCmp > 0) {
                this.max = key;
            }
            return true;
        }
    }

    public boolean delete(T key) {
        int lowerBound = this.lowerBound(key);
        int upperBound = this.upperBound(key);

        if (lowerBound >= this.size()) {
            return false;
        }

        for (int i = lowerBound; i < upperBound; i++) {
            if (this.get(i).equals(key)) {
                this.remove(i);
                return true;
            }
        }

        return false;
    }

}
