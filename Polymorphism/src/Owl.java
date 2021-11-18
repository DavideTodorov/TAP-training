public class Owl extends Bird{
    private int eggsHatched;
    private int countOfEggs;


    public Owl(String name, int age, int weight, int countOfEggs) {
        super(name, age, weight);
        this.countOfEggs = countOfEggs;
    }

    public int getEggsHatched() {
        return eggsHatched;
    }

    public void setEggsHatched(int eggsHatched) {
        this.eggsHatched = eggsHatched;
    }

    public int getCountOfEggs() {
        return countOfEggs;
    }

    public void setCountOfEggs(int countOfEggs) {
        this.countOfEggs = countOfEggs;
    }

    public void hatchEggs() {
        eggsHatched += this.getCountOfEggs();
        System.out.printf("%s is hatching %d eggs. Total eggs hatched: %d.%n",
                this.getName() ,this.getCountOfEggs(), this.eggsHatched);
    }
    @Override
    public void makeNoise(){
        System.out.printf("Owl %s is hooting.%n", this.getName());
    }

    @Override
    public void move() {
        System.out.printf("Owl %s flies.%n", this.getName());
    }

    @Override
    public void sleep() {
        System.out.printf("Owl %s sleeps.%n", this.getName());
    }

    @Override
    public void sleep(int hour) {
        System.out.printf("Owl %s sleeps for %d hours.%n", this.getName(), hour);
    }
}
