public class Bird extends Animal {
    private int weight;


    public Bird(String name, int age, int weight) {
        super(name, age);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void checkWeight() {
        System.out.printf("Bird weights %s kg.%n", this.getWeight());
    }

    @Override
    public void move() {
        System.out.printf("Bird is flying.%n");
    }

    @Override
    public void sleep() {
        System.out.printf("Bird is sleeping.%n");
    }

    @Override
    public void sleep(int hours) {
        System.out.printf("Bird is sleeping for %d hours.%n",  hours);
    }

    public void makeNoise(){
        System.out.println("Making bird noise.");
    }
}
