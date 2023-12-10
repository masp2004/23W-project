import static org.junit.Assert.*;

public class DisjointSetsTest {

    @org.junit.Test
    public void create_set() {

        util.DisjointSets disjointSets = new util.DisjointSets();
        int element = 42;

        disjointSets.create_set(element);

        int representativeKey = disjointSets.find_set(element);

        assertEquals(element, representativeKey);
    }

    @org.junit.Test
    public void union() {

        util.DisjointSets disjointSets = new util.DisjointSets();
        int first = 42;
        int second = 43;

        disjointSets.create_set(first);
        disjointSets.create_set(second);

        disjointSets.union(first, second);

        int firstRepresentativeKey = disjointSets.find_set(first);
        int secondRepresentativeKey = disjointSets.find_set(second);

        assertEquals(firstRepresentativeKey, secondRepresentativeKey);
    }
}
