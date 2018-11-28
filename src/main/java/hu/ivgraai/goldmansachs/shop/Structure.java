package hu.ivgraai.goldmansachs.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ivgraai
 * @since 27th November
 */
final class Structure {

    private final Map<Integer, List<Data>> container = new HashMap<>();

    public boolean add(Data value) {
        boolean l = false;
        for (int i = value.getStartTime(); i <= value.getEndTime(); ++i) {

            if (!container.containsKey(i)) {
                container.put(i, new ArrayList<Data>());
            }
            l |= container.get(i).add(value);

        }

        return l;
    }

    public List<Data> get(int index) {
        return container.get(index);
    }

}
