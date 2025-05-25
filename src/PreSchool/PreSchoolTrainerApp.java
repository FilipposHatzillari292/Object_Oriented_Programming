package PreSchool;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

class PreschoolTrainerApp {
    private JFrame frame;
    private JLabel imageLabel;
    private JTextField inputField;
    private JButton checkButton;
    private JLabel resultLabel;
    private Animal currentAnimal;
    private List<Animal> animals;

    public PreschoolTrainerApp() {

        initializeAnimals();
        setupUI();
        loadRandomAnimal();
    }

    private void initializeAnimals() {
        animals = new ArrayList<>();


        String currentDirectory = System.getProperty("user.dir");
        String imagePath = currentDirectory + "/src/images/";


        animals.add(new Animal("Horse", imagePath + "horse.png"));
        animals.add(new Animal("Elephant", imagePath + "elephant.png"));
        animals.add(new Animal("Monkey", imagePath + "monkey.png"));


        System.out.println("Image paths:");
        for (Animal animal : animals) {
            System.out.println(animal.getImagePath());
        }
    }

    private void setupUI() {
        frame = new JFrame("Preschool Animal Trainer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout());


        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        inputField = new JTextField(10);
        checkButton = new JButton("Check Answer");
        resultLabel = new JLabel("Enter the first letter", SwingConstants.CENTER);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(new JLabel("First Letter:"));
        bottomPanel.add(inputField);
        bottomPanel.add(checkButton);


        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(resultLabel, BorderLayout.NORTH);


        checkButton.addActionListener(e -> checkAnswer());

        frame.setVisible(true);
    }

    private void loadRandomAnimal() {
        Random rand = new Random();
        currentAnimal = animals.get(rand.nextInt(animals.size()));

        try {

            ImageIcon imageIcon = new ImageIcon(currentAnimal.getImagePath());
            Image image = imageIcon.getImage();

            Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));


            System.out.println("Loading image: " + currentAnimal.getImagePath());

            if (imageIcon.getIconWidth() <= 0) {
                System.out.println("Warning: Image failed to load properly");
            }

        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            imageLabel.setIcon(null);
            imageLabel.setText("Image not found");
        }

        inputField.setText("");
        resultLabel.setText("Guess the first letter of the animal's name");
    }

    private void checkAnswer() {
        String userInput = inputField.getText().trim().toUpperCase();
        if (userInput.length() != 1) {
            resultLabel.setText("Please enter one letter.");
            return;
        }

        if (userInput.charAt(0) == currentAnimal.getFirstLetter()) {
            resultLabel.setText("Correct! It's a " + currentAnimal.getName() + "!");
            JOptionPane.showMessageDialog(frame, "Great job! Let's try another one!");
            loadRandomAnimal();
        } else {
            resultLabel.setText("Try again! Hint: It starts with '" + currentAnimal.getFirstLetter() + "'");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PreschoolTrainerApp());
    }
}

class Animal {
    private final String name;
    private final String imagePath;

    public Animal(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public char getFirstLetter() {
        return name.toUpperCase().charAt(0);
    }
}
