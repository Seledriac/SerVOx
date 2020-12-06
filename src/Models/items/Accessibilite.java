package Models.items;

import java.util.HashMap;
import java.util.Map;

public enum Accessibilite {

    GUERRIERS(1),
    MAGES(2),
    CHASSEURS(3),
    TOUS(4),
    GUERRIERS_CHASSEURS(5),
    GUERRIERS_MAGES(6),
    CHASSEURS_MAGES(7);

    private int value;
    private static Map map = new HashMap<>();

    private Accessibilite(int value) {
        this.value = value;
    }

    static {
        for (Accessibilite accessibilite : Accessibilite.values()) {
            map.put(accessibilite.value, accessibilite);
        }
    }

    public static Accessibilite valueOf(int accessibilite) {
        return (Accessibilite)map.get(accessibilite);
    }

    public int getValue() {
        return value;
    }

}