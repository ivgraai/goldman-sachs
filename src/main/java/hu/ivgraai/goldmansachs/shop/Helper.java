package hu.ivgraai.goldmansachs.shop;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author ivgraai
 * @since 27th November
 */
class Helper {

    private static final List<Character> INPUT_COLLECTION_EDGES = Arrays.asList('[', ']'); // Collections.unmodifiableList
    private static final char INPUT_ELEMENT_OPENER = '{';
    private static final char INPUT_INNER_SEPARATOR = ',';
    private static final char INPUT_ELEMENT_CLOSING = '}';
    private static final char INPUT_OUTER_SEPARATOR = ';';

    public static InputStream converting(String value) {
        return new ByteArrayInputStream(value.getBytes(/* java.nio.charset.Charset.defaultCharset() */));
    }

    public static final BiPredicate<Data, Data> MINIMUM = new BiPredicate<Data, Data>() {

        @Override
        public boolean test(Data i1, Data i2) {
            return !(i1.getPrice() < i2.getPrice());
        }

    };

    public static List<Data> reading(InputStream stream) throws Exception {
        List<Data> retval = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        Data elem = null;

        int current;
        while (-1 != (current = stream.read())) {
            char temp = (char) current;
            if (Character.isWhitespace(current) || INPUT_COLLECTION_EDGES.contains(temp) || INPUT_OUTER_SEPARATOR == temp) {
                continue;
            }

            switch (temp) {
                case INPUT_ELEMENT_OPENER: {
                    elem = new Data();
                    break;
                }
                case INPUT_ELEMENT_CLOSING:
                    retval.add(elem);
                case INPUT_INNER_SEPARATOR: {
                    setAttribute(elem, builder.toString());
                    builder = new StringBuilder();
                    break;
                }
                default:
                    builder.append(temp);
            }
        }

        return retval;
    }

    // private static boolean isValid() { String regexp = "pattern"; ... }

    private static void setAttribute(Data input, String value) {
        Integer number = Integer.parseInt(value);
        if (null == input.getStartTime()) {
            input.setStartTime(number);
        } else if (null == input.getEndTime()) {
            input.setEndTime(number);
        } else {
            input.setPrice(number);
        }
    }

}
