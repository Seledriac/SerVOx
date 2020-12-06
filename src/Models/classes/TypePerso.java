package Models.classes;

import java.util.HashMap;
import java.util.Map;

public enum TypePerso {

    GUERRIER(1),
    MAGE(2),
    CHASSEUR(3);

    private int value;
    private static Map map = new HashMap<>();

    private TypePerso(int value) {
        this.value = value;
    }

    static {
        for (TypePerso type_perso : TypePerso.values()) {
            map.put(type_perso.value, type_perso);
        }
    }

    public static TypePerso valueOf(int type_perso) {
        return (TypePerso)map.get(type_perso);
    }

    public int getValue() {
        return value;
    }

}