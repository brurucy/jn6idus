package spine.vertebra.test;

import org.junit.jupiter.api.Test;
import spine.vertebra.Vertebra;

public class vertebraTest {
    @Test
    void AddAndIthTest() {
        Vertebra vertebra = new Vertebra<Integer>();
        vertebra.add(1);
        vertebra.add(5);
        vertebra.add(2);
        vertebra.add(3);
        assert vertebra.ith(1) == 0;
        assert vertebra.ith(5) == 3;
        assert vertebra.ith(2) == 1;
        assert vertebra.ith(3) == 2;
    }

    @Test
    void RemoveTest() {
        Vertebra vertebra = new Vertebra<Integer>();
        vertebra.add(1);
        vertebra.add(5);
        vertebra.add(2);
        vertebra.add(3);
        assert vertebra.ith(5) == 3;
        vertebra.delete(3);
        assert vertebra.ith(5) == 2;
        vertebra.delete(2);
        assert vertebra.ith(5) == 1;
    }

    @Test
    void HasTest() {
        Vertebra vertebra = new Vertebra<Integer>();
        vertebra.add(1);
        vertebra.add(5);
        vertebra.add(2);
        vertebra.add(3);
        assert vertebra.has(1);
        assert vertebra.has(5);
        assert vertebra.has(2);
        assert vertebra.has(3);
        vertebra.delete(1);
        assert !vertebra.has(1);
        assert vertebra.has(5);
        assert vertebra.has(2);
        assert vertebra.has(3);
        vertebra.delete(5);
        assert !vertebra.has(1);
        assert !vertebra.has(5);
        assert vertebra.has(2);
        assert vertebra.has(3);
        vertebra.delete(3);
        assert !vertebra.has(1);
        assert !vertebra.has(5);
        assert vertebra.has(2);
        assert !vertebra.has(3);
        vertebra.delete(2);
        assert !vertebra.has(1);
        assert !vertebra.has(5);
        assert !vertebra.has(2);
        assert !vertebra.has(3);
    }
}
