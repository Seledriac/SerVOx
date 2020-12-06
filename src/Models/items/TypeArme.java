package Models.items;

import java.util.HashMap;
import java.util.Map;

public enum TypeArme {

    EPEE(1),
    ARC(2),
    SORTOFFENSIF(3),
    MARTEAU(4),
    ARBALETE(5);

    private int value;
    private static Map map = new HashMap<>();

    private TypeArme(int value) {
        this.value = value;
    }

    static {
        for (TypeArme type_arme : TypeArme.values()) {
            map.put(type_arme.value, type_arme);
        }
    }

    public static TypeArme valueOf(int type_arme) {
        return (TypeArme)map.get(type_arme);
    }

    public int getValue() {
        return value;
    }

}