package ch.wideside.model;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class Bean1 {

    private String x;
    private Integer y;

    public Bean1(String x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets y.
     *
     * @return Value of y.
     */
    public Integer getY() {
        return y;
    }


    /**
     * Gets x.
     *
     * @return Value of x.
     */
    public String getX() {
        return x;
    }
}
