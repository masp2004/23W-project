import static org.junit.Assert.assertEquals;

public class DisjointSetsTest {

    @org.junit.Test
    public void createSet1() {
        util.DisjointSets disjointSets = new util.DisjointSets();
        int element = 42;

        disjointSets.createSet(element);

        int representativeKey = disjointSets.findSet(element);

        assertEquals(element, representativeKey);
    }

    @org.junit.Test
    public void createElement() {
        util.DisjointSets disjointSets = new util.DisjointSets();
        int element = 42;

        disjointSets.createElement(element);

        int representativeKey = disjointSets.findSet(element);

        assertEquals(element, representativeKey);
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
    public void unionSameSet() {
        util.DisjointSets disjointSets = new util.DisjointSets();
        int element1 = 42;
        int element2 = 99;

        disjointSets.createElement(element1);
        disjointSets.createElement(element2);

        // Beide Elemente sind bereits Teil derselben Teilmenge
        disjointSets.union(element1, element2);

        // Überprüfen, dass die Teilmenge unverändert bleibt
        int representativeKey1 = disjointSets.findSet(element1);
        int representativeKey2 = disjointSets.findSet(element2);

        assertEquals(representativeKey1, representativeKey2);
    }

    @org.junit.Test
    public void createSetAndUnion() {
        util.DisjointSets disjointSets = new util.DisjointSets();
        int element1 = 12;
        int element2 = 27;

        disjointSets.createSet(element1);
        disjointSets.createSet(element2);
        disjointSets.union(element1, element2);

        int representativeKey1 = disjointSets.findSet(element1);
        int representativeKey2 = disjointSets.findSet(element2);

        assertEquals(representativeKey1, representativeKey2);
    }
}
