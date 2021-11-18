public class Eagle extends Bird{
    private int animalsCaught;

    public Eagle(String name, int age, int weight, int animalsCaught) {
        super(name, age, weight);
        this.animalsCaught = animalsCaught;
    }

    public void catchAnimal(){
        animalsCaught++;
        System.out.printf("%s has caught an animal. Total animals caught: %d.%n",
                this.getName(), this.animalsCaught);
    }

    @Override
    public void move() {
        System.out.println("Eagle is flying.");
    }

    @Override
    public void sleep() {
        System.out.println("Eagle is sleeping.");
    }

    @Override
    public void makeNoise() {
        System.out.println("Eagle is making noise.");
    }
}
