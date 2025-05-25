package CarRental;

public class Car {
    private String plateNumber;
    private String model;
    private int kilometers;
    private boolean isRented;


    public Car(String plateNumber, String model, int kilometers) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.kilometers = kilometers;
        this.isRented = false;
    }


    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public boolean isRented() {
        return isRented;
    }


    public boolean rent() {
        if (!isRented) {
            isRented = true;
            return true;
        }
        return false;
    }


    public boolean returnCar(int newKm) {
        if (isRented) {
            isRented = false;
            kilometers = newKm;
            return true;
        }
        return false;
    }


    public void display() {
        System.out.println("Plate: " + plateNumber + ", Model: " + model +
                ", KM: " + kilometers + ", Rented: " + (isRented ? "Yes" : "No"));
    }
}
