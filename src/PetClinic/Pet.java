package PetClinic;

public class Pet {
    private final String specie;
    private final String name;


    public Pet(String specie, String name) {
        this.specie = specie.toLowerCase(); // standardize for easy comparison
        this.name = name;
    }


    public String getSpecie() {
        return specie;
    }

    public String getName() {
        return name;
    }
}
