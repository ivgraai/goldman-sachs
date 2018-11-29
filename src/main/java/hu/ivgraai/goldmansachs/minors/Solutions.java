package hu.ivgraai.goldmansachs.minors;

import hu.ivgraai.goldmansachs.shop.Pair;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author ivgraai
 * @since 28th November
 */
public class Solutions {

    public static void main(String[] args) {
        /* String input = "aabbbcdd";
        System.out.println(unfold(input)); */

        /* String input = "aabbbcdd";
        System.out.println(search(input)); */

        /* List<Integer> input = java.util.Arrays.asList(4, -2, 1, 2, 5, 3, 0);
        int number = 3;
        System.out.println(find(input, number)); */

        /* int n = 3;
        powerof(System.out, 10, n); */
    }

    public static List<String> unfold(String value) {
        List<String> pieces = new ArrayList<>();
        for (int i = 0; i < value.length(); i++) {

            char character = value.charAt(i);
            StringBuilder builder = new StringBuilder().append(character);
            while (i + 1 < value.length() && character == value.charAt(i + 1)) {
                builder.append(value.charAt(++i));
            }
            pieces.add(builder.toString());

        }

        return pieces;
    }

    public static Character search(String value) {
        if (value.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        boolean l = true;
        int ind = 0;
        for (int i = 1; l && i < value.length(); ++i) {
            l = value.charAt(i - 1) == value.charAt(i);
            ind = i;
        }

        return (l ? null : value.charAt(ind));
    }

    public static Collection<Pair<Integer, Integer>> find(List<Integer> array, int k) {
        Set<Pair<Integer, Integer>> result = new HashSet<>();

        for (int i = 0; i < array.size(); ++i) {
            for (int j = 0; j < array.size(); ++j) {
                if (j == i) {
                    continue;
                }
                if (k == array.get(i) + array.get(j)) {
                    result.add(new AddUpToPair(array.get(i), array.get(j)));
                }
            }
        }

        return result;
    }

    private static class AddUpToPair extends Pair<Integer, Integer> {

        public AddUpToPair(Integer first, Integer second) {
            super(first, second);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 83 * hash + Objects.hashCode(this.first + this.second); // We combine the equals that's why we need to take care of hashCode!
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final AddUpToPair other = (AddUpToPair) obj;
            return (
                    Objects.equals(this.first, other.first) && Objects.equals(this.second, other.second) ||
                    Objects.equals(this.first, other.second) && Objects.equals(this.second, other.first)
                );
        }

        @Override
        public String toString() {
            return "Pair{" + "first=" + first + ", second=" + second + '}';
        }

    }

    public static void powerof(PrintStream out, int base, int exponent) {
        recursion(out, BigInteger.valueOf(base), exponent, 0, BigInteger.ONE);
    }

    private static void recursion(PrintStream out, BigInteger base, int exponent, int n, BigInteger value) {
        String pattern = "%d^%d = %s";
        out.println(String.format(pattern, base.intValue(), n, value));

        if (n == exponent) {
            return;
        }
        recursion(out, base, exponent, n + 1, value.multiply(base));
    }

}
