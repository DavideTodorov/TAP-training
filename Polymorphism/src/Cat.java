public class Cat extends Mammal {
    public Cat(String name, int age, int hairLength) {
        super(name, age, hairLength);
    }

    public void pur(){
        System.out.println("The cat is purring.");
    }

    @Override
    public void move() {
        System.out.printf("The cat %s is moving.%n", this.getName());
    }

    @Override
    public void sleep() {
        System.out.printf("The cat %s is sleeping.%n", this.getName());
    }

    @Override
    public void sleep(int hours) {
        System.out.printf("The cat %s sleeping for %d hours%n.", this.getName(), hours);
    }

    @Override
    public void drinkMilk() {
        System.out.printf("The cat %s is drinking milk.%n", this.getName());
    }
}
