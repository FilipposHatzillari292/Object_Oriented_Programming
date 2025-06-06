package CarRental;
import java.util.Scanner;

public class CarRental {
    private static final int RENT_CAR_OPTION = 1;
    private static final int RETURN_CAR_OPTION = 2;
    private static final int DISPLAY_CARS_OPTION = 3;
    private static final int EXIT_OPTION = 0;

    private final CarRentalService rentalService;
    private final Scanner scanner;

    public CarRental() {
        this.rentalService = new CarRentalService(initializeCars());
        this.scanner = new Scanner(System.in);
    }

    private Car[] initializeCars() {
        return new Car[]{
                new Car("ABC123", "Toyota Corolla", 50000),
                new Car("DEF456", "Honda Civic", 62000),
                new Car("GHI789", "Ford Fiesta", 70000),
                new Car("JKL321", "Chevy Malibu", 85000),
                new Car("MNO654", "Nissan Altima", 90000)
        };
    }

    public void start() {
        int choice;
        do {
            displayMenu();
            choice = getUserChoice();
            processUserChoice(choice);
        } while (choice != EXIT_OPTION);
        scanner.close();
    }

    private int getUserChoice() {
        try {
            return getValidatedUserInput();
        } catch (Exception e) {
            scanner.nextLine(); // Clear the invalid input
            System.out.println("Please enter a valid number.");
            return -1; // Invalid choice will be handled by default case
        }
    }

    private void displayMenu() {
        System.out.println("\nCAR RENTAL MENU");
        System.out.println("1. Rent a car");
        System.out.println("2. Return a car");
        System.out.println("3. Display all cars");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    private int getValidatedUserInput() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return choice;
    }

    private void processUserChoice(int choice) {
        switch (choice) {
            case RENT_CAR_OPTION -> handleRentCar();
            case RETURN_CAR_OPTION -> handleReturnCar();
            case DISPLAY_CARS_OPTION -> rentalService.displayAllCars();
            case EXIT_OPTION -> System.out.println("Exiting system...");
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    private void handleRentCar() {
        System.out.print("Enter plate number to rent: ");
        String plateNumber = scanner.nextLine();
        boolean success = rentalService.rentCar(plateNumber);
        System.out.println(success ? "Car rented successfully." : "Car not found or already rented.");
    }

    private void handleReturnCar() {
        System.out.print("Enter plate number to return: ");
        String plateNumber = scanner.nextLine();
        System.out.print("Enter new kilometers: ");
        int newKm = getValidatedUserInput();
        boolean success = rentalService.returnCar(plateNumber, newKm);
        System.out.println(success ? "Car returned and updated." : "Car not found or was not rented.");
    }

    public static void main(String[] args) {
        CarRental rentalSystem = new CarRental();
        rentalSystem.start();
    }

    private static class CarRentalService {
        private final Car[] cars;

        public CarRentalService(Car[] cars) {
            this.cars = cars;
        }

        public void displayAllCars() {
            System.out.println("\nAvailable Cars:");
            System.out.println("--------------------");
            for (Car car : cars) {
                car.display();
            }
            System.out.println("--------------------");
        }

        public boolean rentCar(String plateNumber) {
            for (Car car : cars) {
                if (car.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                    return car.rent();
                }
            }
            return false;
        }

        public boolean returnCar(String plateNumber, int newKm) {
            for (Car car : cars) {
                if (car.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                    return car.returnCar(newKm);
                }
            }
            return false;
        }

        public Car[] getCars() {
            return cars;
        }
    }
}

class Car {
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
        if (isRented && newKm >= kilometers) {
            isRented = false;
            kilometers = newKm;
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("Plate: " + plateNumber + ", Model: " + model +
                ", KM: " + kilometers + ", Status: " + (isRented ? "Rented" : "Available"));
    }
}
