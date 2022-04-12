package pt.ua.tqs.carsservice.dto;

public class CarDTO {
    private String maker;
    private String model;

    public CarDTO(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
