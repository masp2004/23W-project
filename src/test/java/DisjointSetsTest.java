import static org.junit.Assert.assertEquals;

public class DisjointSetsTest {

    @org.junit.Test
    public void create_set1() {
        util.DisjointSets disjointSets = new util.DisjointSets();
        int element = 42;

        disjointSets.createSet(element);

        int representativeKey = disjointSets.findSet(element);

        assertEquals(element, representativeKey);
    }
    @org.junit.Test
    public void create_set2() {
        var sets = new util.DisjointSets();
        sets.createSet(12);
        sets.createSet(27);
        sets.union(12, 27);
        sets.createSet(27);
        sets.findSet(27);
    }
    @org.junit.Test
    public void union1() {
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

    @org.junit.Test
    public void unionWithSameElement() {
        util.DisjointSets disjointSets = new util.DisjointSets();
        int first = 42;
        int second = 42;

        disjointSets.createSet(first);
        disjointSets.createSet(second);

        int initialSets = disjointSets.getNumberOfDisjointSets();

        disjointSets.union(first, second);

        int firstRepresentativeKey = disjointSets.findSet(first);
        int secondRepresentativeKey = disjointSets.findSet(second);

        assertEquals(firstRepresentativeKey, secondRepresentativeKey);
        assertEquals(initialSets, disjointSets.getNumberOfDisjointSets());
    }
}
