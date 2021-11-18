public class Dog extends Mammal{
    private String hairColor;

    public Dog(String name, int age, int hairLength, String hairColor) {
        super(name, age, hairLength);
        this.hairColor = hairColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void bark(){
        System.out.printf("The dog %s is barking.%n", this.getName());
    }

    @Override
    public void move() {
        System.out.printf("The dog %s is moving.%n", this.getName());

    }

    @Override
    public void sleep() {
        System.out.printf("The dog %s is sleeping.%n", this.getName());

    }

    @Override
    public void sleep(int hours) {
        System.out.printf("The dog %s is sleeping for %d hours.%n", this.getName(), hours);

    }
}
