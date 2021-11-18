public class Mammal extends Animal {

    private int hairLength;


    public Mammal(String name, int age, int hairLength) {
        super(name, age);
        this.hairLength = hairLength;
    }

    public int getHairLength() {
        return hairLength;
    }

    public void setHairLength(int hairLength) {
        this.hairLength = hairLength;
    }

    public void drinkMilk(){
        System.out.println("The mammal is drinking milk.");
    }

    @Override
    public void move() {
        System.out.printf("Mammal moving.%n");
    }

    @Override
    public void sleep() {
        System.out.printf("Mammal is sleeping.%n");
    }

    @Override
    public void sleep(int hours) {
        System.out.printf("Mammal is sleeping for %d hours.%n",  hours);
    }
}
