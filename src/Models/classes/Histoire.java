package Models.classes;

import java.util.HashMap;
import java.util.Map;

public enum Histoire {

    RANDOM(0),
    GUERRIERS(1),
    MAGES(2),
    CHASSEURS(3);

    private int value;
    private static Map map = new HashMap<>();

    private Histoire(int value) {
        this.value = value;
    }

    static {
        for (Histoire histoire : Histoire.values()) {
            map.put(histoire.value, histoire);
        }
    }

    public static Histoire valueOf(int histoire) {
        return (Histoire)map.get(histoire);
    }

    public int getValue() {
        return value;
    }

}