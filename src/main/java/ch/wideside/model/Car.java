package ch.wideside.model;

/**
 * 2018/12/05
 *
 * @author Sirius
 */
public class Car {

    private String model;

    private String color;

    public Car(String model) {
        this(model, "red");
    }

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }
}
