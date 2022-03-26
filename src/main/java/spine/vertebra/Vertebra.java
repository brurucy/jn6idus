package spine.vertebra;

import java.util.ArrayList;

public class Vertebra<T> extends ArrayList<T> {
    public T max;

    public int ith(T key) {
        int low = 0;
        int mid;
        int high = this.size() - 1;

        while (low <= high) {
            mid = (low + high) >>> 1;
            Comparable<? super T> midVal = (Comparable<T>) this.get(mid);
            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else {
                return mid;
            }
        }

        return low;
    }

    public boolean has(T key) {
        int position = this.ith(key);
        if (position >= this.size()) {
            return false;
        }
        return this.get(position).equals(key);
    }

    @Override
    public boolean add(T key) {
        int position = this.ith(key);
        if (position >= this.size()) {
            this.max = key;
            return super.add(key);
        } else {
            Comparable<? super T> candidate = (Comparable<T>) this.get(position);
            int cmp = candidate.compareTo(key);
            if (cmp != 0) {
                this.add(position, key);
                int maxCmp = candidate.compareTo(this.max);
                if (maxCmp > 0) {
                    this.max = key;
                }
                return true;
            }
            return false;
        }
    }

    public boolean delete(T key) {
        int position = this.ith(key);
        if (position >= this.size()) {
            return false;
        }
        Comparable<? super T> candidate = (Comparable<T>) this.get(position);
        int cmp = candidate.compareTo(key);
        if (cmp == 0) {
            int maxCmp = candidate.compareTo(this.max);
            if (maxCmp == 0) {
                this.max = this.get(this.size() - 1);
            }
            this.remove(position);
            return true;
        }
        return false;
    }

}
