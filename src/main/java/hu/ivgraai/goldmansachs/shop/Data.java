package hu.ivgraai.goldmansachs.shop;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author ivgraai
 * @since 27th November
 */
class Data implements Serializable {

    private Integer startTime;
    private Integer endTime;
    private Integer price;

    public Data() {
        this(null, null, null);
    }

    public Data(Integer startTime, Integer endTime, Integer price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Data(Data other) {
        this(other.startTime, other.endTime, other.price);
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.startTime);
        hash = 97 * hash + Objects.hashCode(this.endTime);
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
        final Data other = (Data) obj;
        return (Objects.equals(this.startTime, other.startTime) && Objects.equals(this.endTime, other.endTime));
    }

    @Override
    public String toString() {
        return "Data{" + "startTime=" + startTime + ", endTime=" + endTime + ", price=" + price + '}';
    }

}
