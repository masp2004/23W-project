package util;

import java.util.*;

public class DisjointSets {

    private final Map<Integer, Set<Integer>> disjointSet;

    public DisjointSets() {
        disjointSet = new HashMap<>();
    }

    public void createSet(int element) {
        if (findSet(element) == -1) {
            Set<Integer> set = new HashSet<>();
            set.add(element);
            disjointSet.put(element, set);
        } else {
            throw new AssertionError("Element already exists in another set.");
        }
    }

    public void createElement(int element) {
        if (findSet(element) == -1) {
            disjointSet.put(element, new HashSet<>(List.of(element)));
        } else {
            throw new AssertionError("Element already exists in another set.");
        }
    }

    public void union(int first, int second) {
        int firstRep = findSet(first);
        int secondRep = findSet(second);

        if (firstRep != secondRep) {
            Set<Integer> firstSet = disjointSet.get(firstRep);
            Set<Integer> secondSet = disjointSet.get(secondRep);

            firstSet.addAll(secondSet);
            disjointSet.remove(secondRep);
        }
    }

    public int findSet(int element) {
        for (int key : disjointSet.keySet()) {
            Set<Integer> set = disjointSet.get(key);
            if (set.contains(element)) {
                return key;
            }
        }
        return -1;
    }

    public int getNumberOfDisjointSets() {
        return disjointSet.size();
    }
}
