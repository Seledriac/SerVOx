package Models.items;

import java.util.HashMap;
import java.util.Map;

public enum NatureItem {

    BOUCLIER(1),
    ARME(2),
    SORT(3);

    private int value;
    private static Map map = new HashMap<>();

    private NatureItem(int value) {
        this.value = value;
    }

    static {
        for (NatureItem nature_item : NatureItem.values()) {
            map.put(nature_item.value, nature_item);
        }
    }

    public static NatureItem valueOf(int nature_item) {
        return (NatureItem)map.get(nature_item);
    }

    public int getValue() {
        return value;
    }

}