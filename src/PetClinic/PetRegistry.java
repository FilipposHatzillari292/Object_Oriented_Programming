package PetClinic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetRegistry {
    private static final Object[][] INITIAL_PETS = {
            {"dog", "Max"}, {"cat", "Garfield"}, {"dog", "Jack"},
            {"duck", "Daffy"}, {"dog", "Mali"}, {"rabbit", "Flopsy"},
            {"cat", "Harry"}, {"turtle", "Leonardo"},
            {"rabbit", "Cottontail"}, {"duck", "Garry"}
    };

    public static void main(String[] args) {
        List<Pet> pets = initializePets();
        String searchType = getUserInput();
        displayPetsByType(pets, searchType);
    }

    private static List<Pet> initializePets() {
        List<Pet> pets = new ArrayList<>();
        for (Object[] petData : INITIAL_PETS) {
            pets.add(new Pet((String)petData[0], (String)petData[1]));
        }
        return pets;
    }

    private static String getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Give type: ");
            return scanner.nextLine().toLowerCase();
        }
    }

    private static void displayPetsByType(List<Pet> pets, String type) {
        int count = 0;
        for (Pet pet : pets) {
            if (pet.getSpecie().equals(type)) {
                System.out.println("- " + pet.getName());
                count++;
            }
        }
        System.out.println("Total animals of " + type + " type: " + count);
    }
}
