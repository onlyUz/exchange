package company;

public class NotBook implements  Equipment{
    
    private String model; // 机器型号
    private double price;// 价格

    public NotBook() {
    }

    public NotBook(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String getDescription() {
        return model + "(" + price + ")";
    }
}
