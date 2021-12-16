import java.util.concurrent.atomic.AtomicInteger;



/**
 * We should have duplicates of both services in order to ensure
 * that in case of some kind of failure we have another instance
 * of the service that can be used.
 */



public class EntryService {
    //Max capacity of the parking
    private static final int MAX_CAPACITY = 100;

    /*
      Current cars count in the parking. It is Atomic, because it will be used
      from two threads
     */
    private final AtomicInteger carsCount = new AtomicInteger(0);


    /**
     * This method will be invoked when a vehicle tries to enter the parking.
     * First it will check whether there is free space in the parking. If the
     * parking is full it will return. If the park is not full it will add the
     * new vehicle to the DB and increment the current cars count.
     */
    public void acceptVehicle() {
        if (carsCount.get() >= MAX_CAPACITY) {
            System.out.println("Parking is full");
            return;
        }

        System.out.println("Vehicle entered");

        //Add vehicle to DB

        carsCount.incrementAndGet();
    }

    /**
     * This is a method which will run in separate thread. Its purpose is to wait
     * for messages from some third party message broker. The message received will be
     * sent from the ExitService. When the method receives the request it will decrement
     * the current cars count and remove the vehicle from the DB. The method should be
     * idempotent, because the ExitService will send multiple identical messages to the
     * message broker in order to guarantee that the message will be delivered without
     * loss.
     */
    public void vehicleExited() {
        //Create new thread which will wait for message from a message broker

        //Remove vehicle from DB

        carsCount.decrementAndGet();

        System.out.println("Vehicle exited");
    }
}
