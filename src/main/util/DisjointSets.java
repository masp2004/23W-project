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
     * The list of disjoint sets
     */
    private List<Map<Integer, Set<Integer>>> disjointSet;

    /**
     * Constructor for DisjointSets
     */
    public DisjointSets() {
        disjointSet = new ArrayList<Map<Integer, Set<Integer>>>();
    }

    /**
     * Create a set with only one element.
     * @param element
     */
    public void create_set(int element) {
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        Set<Integer> set = new HashSet<Integer>();

        set.add(element);
        map.put(element, set);

        disjointSet.add(map);
    }

    /**
     * Combines two sets together to one.
     * @param first
     * @param second
     * @return true if union successful
     *        false if union failed
     */
    public void union(int first, int second) {

        int first_rep = find_set(first);
        int second_rep = find_set(second);

        Set<Integer> first_set = null;
        Set<Integer> second_set = null;

        for (int index = 0; index < disjointSet.size(); index++) {
            Map<Integer, Set<Integer>> map = disjointSet.get(index);

            if (map.containsKey(first_rep)) {
                first_set = map.get(first_rep);
            } else if (map.containsKey(second_rep)) {
                second_set = map.get(second_rep);
            }
        }

        if (first_set != null && second_set != null)
            first_set.addAll(second_set);

        for (int index = 0; index < disjointSet.size(); index++) {

            Map<Integer, Set<Integer>> map = disjointSet.get(index);

            if (map.containsKey(first_rep)) {
                map.put(first_rep, first_set);
            } else if (map.containsKey(second_rep)) {
                map.remove(second_rep);
                disjointSet.remove(index);
            }
        }
        return;
    }

    /**
     * Finds the representative of this set
     * @param element
     * @return
     */
    public int find_set(int element) {

        for (int index = 0; index < disjointSet.size(); index++) {
            Map<Integer, Set<Integer>> map = disjointSet.get(index);
            Set<Integer> keySet = map.keySet();

            for (Integer key : keySet) {
                Set<Integer> set = map.get(key);
                if (set.contains(element)) {
                    return key;
                }
            }
        }
        return -1;
    }

    /**
     * Find the set of the given element
     */
    public int getNumberofDisjointSets() {
        return disjointSet.size();
    }
}