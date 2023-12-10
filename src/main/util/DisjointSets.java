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
     * @param element
     */
    public void createSet(int element) {
        Set<Integer> set = new HashSet<>();
        set.add(element);
        disjointSet.put(element, set);
    }

    /**
     * Combines two sets together to one.
     *
     * @param first
     * @param second
     */
    public void union(int first, int second) {
        int firstRep = findSet(first);
        int secondRep = findSet(second);

        Set<Integer> firstSet = disjointSet.get(firstRep);
        Set<Integer> secondSet = disjointSet.get(secondRep);

        if (firstSet != null && secondSet != null) {
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
