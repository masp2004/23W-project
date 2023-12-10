import static org.junit.Assert.*;

public class DisjointSetsTest {

    @org.junit.Test
    public void create_set() {
        util.DisjointSets disjointSets = new util.DisjointSets();
        int element = 42;

        disjointSets.createSet(element);

        int representativeKey = disjointSets.findSet(element);

        assertEquals(element, representativeKey);
    }

    @org.junit.Test
    public void union() {
        util.DisjointSets disjointSets = new util.DisjointSets();
        int first = 42;
        int second = 43;

        disjointSets.createSet(first);
        disjointSets.createSet(second);

        int initialSets = disjointSets.getNumberOfDisjointSets();

        disjointSets.union(first, second);

        int firstRepresentativeKey = disjointSets.findSet(first);
        int secondRepresentativeKey = disjointSets.findSet(second);

        assertEquals(firstRepresentativeKey, secondRepresentativeKey);
        assertEquals(initialSets - 1, disjointSets.getNumberOfDisjointSets());
    }
}
