package hu.ivgraai.goldmansachs.shop;

/**
 * @author ivgraai
 * @since 27th November
 */
class Pair<R, S> {

    private final R first;
    private final S second;

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
