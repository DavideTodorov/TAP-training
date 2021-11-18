import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Animal> allAnimals = new ArrayList<>();
    static List<Mammal> allMammals = new ArrayList<>();
    static List<Bird> allBirds = new ArrayList<>();

    public static void main(String[] args) {
        /*
         * Before each output I have printed the code that creates the object.
         * */

        System.out.println();
        System.out.println("--Mammal mammal1 = new Mammal(\"Mammal1\", 100, 5);");
        Mammal mammal = new Mammal("Mammal", 100, 5);
        mammal.move();
        mammal.sleep();
        mammal.sleep(3);
        mammal.drinkMilk();
        addAnimalToCollections(mammal);

        System.out.println();
        System.out.println("--Bird bird = new Bird(\"Bird\", 2, 1);");
        Bird bird = new Bird("Bird", 2, 1);
        bird.move();
        bird.sleep();
        bird.sleep(8);
        addAnimalToCollections(bird);

        System.out.println();
        System.out.println("--Mammal dog1 = new Dog(\"Dog1\", 10, 1, \"brown\");");
        Mammal dog1 = new Dog("Dog1", 10, 1, "brown");
        dog1.move();
        dog1.sleep();
        addAnimalToCollections(dog1);


        System.out.println();
        System.out.println("--Dog dog2 = new Dog(\"Dog2\", 4, 1, \"black\");");
        Dog dog2 = new Dog("Dog2", 4, 1, "black");
        dog2.bark();
        dog2.move();
        dog2.sleep();
        addAnimalToCollections(dog2);

        System.out.println();
        System.out.println("--Mammal cat1 = new Cat(\"Cat1\", 9, 2);");
        Mammal cat1 = new Cat("Cat1", 9, 2);
        cat1.move();
        cat1.sleep();
        addAnimalToCollections(cat1);

        System.out.println();
        System.out.println("--Cat cat2 = new Cat(\"Cat2\", 8, 4);");
        Cat cat2 = new Cat("Cat2", 8, 4);
        cat2.move();
        cat2.sleep();
        cat2.drinkMilk();
        cat2.pur();
        addAnimalToCollections(cat2);

        System.out.println();
        System.out.println("--Animal puppy1 = new Puppy(\"Puppy1\", 1, 3, \"black\");");
        Animal puppy1 = new Puppy("Puppy1", 1, 3, "black");
        puppy1.move();
        puppy1.sleep();
        addAnimalToCollections(puppy1);

        System.out.println();
        System.out.println("--Puppy puppy2 = new Puppy(\"Puppy2\", 1,4,\"colorful\");");
        Puppy puppy2 = new Puppy("Puppy2", 1, 4, "colorful");
        puppy2.bark();
        puppy2.drinkMilk();
        puppy2.move();
        puppy2.sleep();
        addAnimalToCollections(puppy2);

        System.out.println();
        System.out.println("--Owl owl1 = new Owl(\"Owl1\", 17, 2, 20);");
        Owl owl1 = new Owl("Owl1", 17, 2, 20);
        owl1.move();
        owl1.sleep();
        owl1.checkWeight();
        owl1.hatchEggs();
        owl1.sleep(10);
        owl1.makeNoise();
        addAnimalToCollections(owl1);

        System.out.println();
        System.out.println("--Bird owl2 = new Owl(\"Owl2\", 15, 3,30);");
        Bird owl2 = new Owl("Owl2", 15, 3,30);
        owl2.move();
        owl2.sleep();
        owl2.sleep(5);
        addAnimalToCollections(owl2);

        System.out.println();
        System.out.println("--Eagle eagle = new Eagle(\"EagleName\", 30, 5, 2);");
        Eagle eagle = new Eagle("EagleName", 30, 5, 2);
        eagle.catchAnimal();
        eagle.catchAnimal();
        eagle.catchAnimal();
        eagle.move();
        eagle.makeNoise();
        addAnimalToCollections(eagle);

        System.out.println();
        printAllMammals();
    }

    private static void printAllMammals() {
        System.out.println("Print all animals:");
        for (Animal animal : allAnimals) {
            System.out.println(animal.getName() + " is subclass of Animal");
        }
        System.out.println();

        for (Mammal mammal : allMammals) {
            System.out.println(mammal.getName() + " is subclass of Mammal");
        }
        System.out.println();

        for (Bird bird : allBirds) {
            System.out.println(bird.getName() + " is subclass of Bird");
        }
    }

    private static void addAnimalToCollections(Animal animal) {
        if (animal instanceof Mammal) {
            allMammals.add((Mammal) animal);
        } else if (animal instanceof Bird) {
            allBirds.add((Bird) animal);
        }

        allAnimals.add(animal);
    }
}
