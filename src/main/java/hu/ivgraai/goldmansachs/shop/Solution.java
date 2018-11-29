package hu.ivgraai.goldmansachs.shop;

import static hu.ivgraai.goldmansachs.shop.Helper.converting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * @author ivgraai
 * @since 27th November
 */
public class Solution {

    // TODO: Implement some test-cases!
    public static void main(String[] args) {
        try {
            String raw = "[{0, 4, 5}; {2, 8, 3}; {7, 11, 10}]";

            List<Data> input = Helper.reading(converting(raw));
            Pair<Integer, Integer> fromTo = prerequisite(input);

            List<Data> output = doing(input, fromTo);
            System.out.println(resolving(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Pair<Integer, Integer> prerequisite(List<Data> items) {
        if (items.isEmpty()) {
            throw new RuntimeException("At least one item is required!");
        }
        initialize(items);

        int max = items.get(0).getEndTime();
        for (int i = 1; i < items.size(); ++i) {
            Data ith = items.get(i);
            if (max < ith.getEndTime()) {
                max = ith.getEndTime();
            }
        }

        return new Pair<>(items.get(0).getStartTime(), max);
    }

    private static List<Data> doing(List<Data> items, Pair<Integer, Integer> minmax) {
        List<Data> result = new ArrayList<>();

        Structure type = new Structure();
        for (Data temp : items) {
            type.add(temp);
        }
        for (int i = minmax.getFirst(); i <= minmax.getSecond(); ++i) {
            Data temp = selection(type.get(i), Helper.MINIMUM);
            result.add(new Data(i, i, temp.getPrice()));
        }

        return result;
    }

    private static List<Data> resolving(List<Data> items) {
        List<Data> simplified = new ArrayList<>();

        Data actual = new Data();
        for (Data i : items) {
            if (Objects.equals(actual.getPrice(), i.getPrice())) {
                actual.setEndTime(i.getStartTime());
            } else {
                actual = new Data(i);
                simplified.add(actual);
            }
        }

        return simplified;
    }

    private static void initialize(List<Data> items) {
        Collections.sort(items, new Comparator<Data>() { // TODO: We do not need this truly. (consider {@code minmax.getFirst() })

            @Override
            public int compare(Data o1, Data o2) {
                int c = Integer.compare(o1.getStartTime(), o2.getStartTime());
                return (0 != c) ? c : (Integer.compare(o1.getPrice(), o2.getPrice()));
            }

        });
    }

    private static Data selection(List<Data> items, BiPredicate<Data, Data> predicate) {
        if (null == items) {
            Data extreme = new Data();
            extreme.setPrice(null);
            return extreme;
        }

        Data potential = items.get(0);
        for (int i = 1; i < items.size(); ++i) {
            if (predicate.test(potential, items.get(i))) {
                potential = items.get(i);
            }
        }

        return potential;
    }

}
