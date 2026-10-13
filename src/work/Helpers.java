package work;

import java.util.HashMap;

public class Helpers {

    public static <T> HashMap<T, Integer> add(HashMap<T, Integer> map, HashMap<T, Integer> map2) {
        HashMap<T, Integer> added = new HashMap<>();
        for (T object : map.keySet()) {
            added.put(object, map.get(object));
        }
        for (T object : map2.keySet()) {
            added.put(object, (added.get(object) != null ? added.get(object) : 0) + map2.get(object));
        }
        return added;
    }

}
