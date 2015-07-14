package solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Modified from http://stackoverflow.com/a/29661084/2429611
 */
public class PowerSet {

    public static <T> List<List<T>> powerset(List<T> src) {
        List<List<T>> all = new ArrayList<>();
        powerset(new LinkedList<>(), src, all);
        return all;
    }

    private static <T> void powerset(LinkedList<T> prefix, List<T> src, List<List<T>> all) {
        if (src.size() > 0) {
            prefix = new LinkedList<>(prefix); //create a copy to not modify the orig
            src = new LinkedList<>(src); //copy
            T curr = src.remove(0);
            collectResult(prefix, curr, all);
            powerset(prefix, src, all);
            prefix.add(curr);
            powerset(prefix, src, all);
        }
    }

    private static <T> void collectResult(LinkedList<T> prefix, T curr, List<List<T>> all) {
        prefix = new LinkedList<>(prefix); //copy
        prefix.add(curr);
//        List<LinkedList<Integer>> addTo;
//        if (powerset.get(prefix.size()) == null) {
//            List<LinkedList<Integer>> newList = new LinkedList<>();
//            addTo = newList;
//        } else {
//            addTo = powerset.get(prefix.size());
//        }
//        addTo.add(prefix);
//        powerset.put(prefix.size(), addTo);
        all.add(prefix);
    }
    
}
