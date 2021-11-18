public class Puppy extends Dog {
    public Puppy(String name, int age, int hairLength, String hairColor) {
        super(name, age, hairLength, hairColor);
    }

    @Override
    public void bark() {
        System.out.printf("The puppy %s is barking.%n", this.getName());
    }

    @Override
    public void move() {
        System.out.printf("The puppy %s is moving.%n", this.getName());
    }

    @Override
    public void sleep() {
        System.out.printf("The puppy %s is sleeping.%n", this.getName());
    }

    @Override
    public void sleep(int hours) {
        System.out.printf("The puppy %s is sleeping for %d hours.%n", this.getName());

    }
}
