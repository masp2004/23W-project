package util;

import java.util.*;

/*
 * Code from: http://www.sanfoundry.com/java-program-implement-disjoint-set-data-structure/
 *
 * Author: Manish Kumar Bhojasia
 *
 */


public class DisjointSets {

    /**
     * The disjoint sets represented as a map
     */
    private final Map<Integer, Set<Integer>> disjointSet;

    /**
     * Constructor for DisjointSets
     */
    public DisjointSets() {
        disjointSet = new HashMap<>();
    }

    /**
     * Create a set with only one element.
     *
     * @param element to create a set with
     */
    public void createSet(int element) {
        try {
            // Überprüfe, ob das Element bereits in einem Set vorhanden ist
            if (findSet(element) != -1) throw new AssertionError();

            Set<Integer> set = new HashSet<>();
            set.add(element);

            disjointSet.put(element, set);

        } catch (AssertionError e) {
            // Falls findSet eine AssertionError wirft, kann das Element dem existierenden Set hinzugefügt werden.
            disjointSet.get(element).add(element);
        }
    }


    /**
     * Combines two sets together to one.
     *
     * @param first set to combine
     * @param second set to combine
     */
    public void union(int first, int second) {
        int firstRep = findSet(first);
        int secondRep = findSet(second);

        java.util.Set<Integer> firstSet = disjointSet.get(firstRep);
        java.util.Set<Integer> secondSet = disjointSet.get(secondRep);

        if (firstSet == null) {
            firstSet = new HashSet<>();
            disjointSet.put(firstRep, firstSet);
        }

        if (secondSet == null) {
            secondSet = new HashSet<>();
            disjointSet.put(secondRep, secondSet);
        }

        if (firstRep != secondRep) {
            firstSet.addAll(secondSet);
            disjointSet.remove(secondRep);
        }
    }

    /**
     * Finds the representative of this set
     *
     * @param element to find
     * @return -1 if element is not in any set
     */
    public int findSet(int element) {
        for (int key : disjointSet.keySet()) {
            Set<Integer> set = disjointSet.get(key);
            if (set.contains(element)) {
                return key;
            }
        }
        return -1;
    }

    /**
     * Find the set of the given element
     */
    public int getNumberOfDisjointSets() {
        return disjointSet.size();
    }
}
