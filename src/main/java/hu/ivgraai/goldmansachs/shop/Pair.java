package hu.ivgraai.goldmansachs.shop;

/**
 * @author ivgraai
 * @param <R>
 * @param <S>
 * @since 27th November
 */
public class Pair<R, S> {

    protected final R first;
    protected final S second;

    public Pair(R first, S second) {
        this.first = first;
        this.second = second;
    }

    public R getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

}
