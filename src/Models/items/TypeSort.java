package Models.items;

import java.util.HashMap;
import java.util.Map;

public enum TypeSort {

    ;

    private int value;
    private static Map map = new HashMap<>();

    private TypeSort(int value) {
        this.value = value;
    }

    static {
        for (TypeSort type_sort : TypeSort.values()) {
            map.put(type_sort.value, type_sort);
        }
    }

    public static TypeSort valueOf(int type_sort) {
        return (TypeSort)map.get(type_sort);
    }

    public int getValue() {
        return value;
    }

}