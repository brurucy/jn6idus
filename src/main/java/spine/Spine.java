// GPL License.
// Copyright: brurucy at protonmail dot ch
package spine;

import spine.cord.Cord;
import spine.vertebra.Vertebra;

import java.util.ArrayList;

class IntegerTuple {
    public int k;
    public int v;

    public IntegerTuple(int k, int v) {
        this.k = k;
        this.v = v;
    }
}

/* Spine is a sorted set data structure. It is a one-level B-Tree without any pointers.
 * */
public class Spine<T extends Comparable<T>> {
    public ArrayList<Vertebra<T>> vertebrae;
    public Cord cord;
    public int length;
    public int vertebraSize;

    /* Initializes the spine with a vertebra size.
     * @param vertebraSize should be set to 1024 unless you have a *very* good reason.
     * */
    public Spine(int vertebraSize) {
        int[] emptyArray = {0};
        this.cord = new Cord(emptyArray);
        this.vertebrae = new ArrayList<>();
        this.vertebrae.add(new Vertebra<>());
        this.vertebraSize = vertebraSize;
        this.length = 0;
    }

    private void buildCord() {
        this.cord = new Cord(this.vertebrae);
    }

    private void balance(int firstLevelIndex) {
        Vertebra<T> targetVertebra = this.vertebrae.get(firstLevelIndex);
        int firstLevelIndexLength = targetVertebra.size();
        int half = firstLevelIndexLength / 2;
        Vertebra<T> newVertebra = new Vertebra<>();
        Vertebra<T> oldVertebra = new Vertebra<>();
        for (int i = 0; i < firstLevelIndexLength; i++) {
            if (i < half) {
                oldVertebra.add(targetVertebra.get(i));
            } else {
                newVertebra.add(targetVertebra.get(i));
            }
        }
        this.vertebrae.set(firstLevelIndex, oldVertebra);
        this.vertebrae.add(firstLevelIndex + 1, newVertebra);
        this.buildCord();
    }

    private int spineIndexOf(T key) {
        int low = 0;
        int mid;
        int high = this.vertebrae.size() - 1;

        while (low <= high) {
            mid = (low + high) >>> 1;
            Comparable<? super T> midVal = this.vertebrae.get(mid).max;
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

    /* Adds a key of type T and retains sortedness.
     * @param key the key to be inserted.
     * @return true if the underlying collection was changed or false otherwise.
     * */
    public boolean push(T key) {
        if (this.length == 0) {
            this.vertebrae.get(0).add(key);
            this.cord.increaseLength(0);
            this.length += 1;
            return true;
        }
        int vertebraIndex = this.spineIndexOf(key);
        if (vertebraIndex >= this.vertebrae.size()) {
            vertebraIndex -= 1;
        }
        boolean extended = this.vertebrae.get(vertebraIndex).add(key);
        if (!extended) {
            return false;
        }
        if (this.vertebrae.get(vertebraIndex).size() > this.vertebraSize) {
            this.balance(vertebraIndex);
        } else {
            this.cord.increaseLength(vertebraIndex);
        }
        this.length += 1;
        return true;
    }

    /* Deletes a key ensuring sortedness.
     * @param key the key to be removed.
     * @return true if the underlying collection was changed or false otherwise.
     * */
    public boolean delete(T key) {
        if (this.length == 0) {
            return false;
        }
        int vertebraIndex = this.spineIndexOf(key);
        if (vertebraIndex >= this.vertebrae.size()) {
            vertebraIndex -= 1;
        }
        boolean shrink = this.vertebrae.get(vertebraIndex).delete(key);
        if (!shrink) {
            return false;
        }
        this.cord.decreaseLength(vertebraIndex);
        if (this.vertebrae.get(vertebraIndex).size() == 0) {
            if (this.length != 1) {
                this.vertebrae.remove(vertebraIndex);
            }
            this.cord = new Cord(this.vertebrae);
        }
        this.length -= 1;
        return true;
    }

    private IntegerTuple locate(int ith) {
        if (ith >= this.length || ith < 0) {
            return new IntegerTuple(-1,-1);
        }
        int vertebraIndex = this.cord.indexOf(ith);
        int offset = 0;
        if (vertebraIndex != 0) {
            offset = this.cord.prefixSum(vertebraIndex);
        }
        return new IntegerTuple(vertebraIndex, ith - offset);
    }

    /* Deletes the ith element.
     * @param ith
     * @return the deleted element, null otherwise.
     * */
    public T deleteByIndex(int ith) {
        if (this.length == 0 || ith >= this.length) {
            return null;
        }
        IntegerTuple indexes = this.locate(ith);
        int spineIndex = indexes.k;
        int vertebraIndex = indexes.v;
        if (spineIndex == -1 || vertebraIndex == -1) {
            return null;
        }
        T out = this.vertebrae.get(spineIndex).get(vertebraIndex);
        this.vertebrae.get(spineIndex).remove(vertebraIndex);
        this.cord.decreaseLength(vertebraIndex);
        if (this.vertebrae.get(spineIndex).size() == 0) {
            if (this.length != 1) {
                this.vertebrae.remove(spineIndex);
            }
            this.cord = new Cord(this.vertebrae);
        }
        this.length -= 1;
        return out;
    }

    /* Gets the ith element.
     * @param ith
     * @return the ith element, null otherwise.
     * */
    public T get(int ith) {
        if (this.length == 0 || ith >= this.length) {
            return null;
        }
        IntegerTuple indexes = this.locate(ith);
        int spineIndex = indexes.k;
        int vertebraIndex = indexes.v;
        return this.vertebrae.get(spineIndex).get(vertebraIndex);
    }

    /* Asserts the existence of some key.
     * @param key the key whose existence within the container is in question.
     * @return true if it exists, false otherwise.
     * */
    public boolean has(T key) {
        if (this.length == 0) {
            return false;
        }
        int vertebraIndex = this.spineIndexOf(key);
        if (vertebraIndex >= this.vertebrae.size()) {
            return false;
        }
        return this.vertebrae.get(vertebraIndex).has(key);
    }
}
