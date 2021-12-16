public class ExitService {

    /**
     * This method will be invoked when a vehicle exits the parking.
     * The method will send a message to a message broker every 100ms until
     * it receives acknowledgment from the message broker. This message will then
     * be sent to the EntryService which will execute the vehicleExited method.
     */
    public void vehicleExit() {
        /*
          Send identical message containing information about the vehicle that has exited the parking
          to a message broker every 100ms until the method receives acknowledgment.By doing so,
          the method ensures that it will provide the message that will be sent to EntryService
          by the message broker.
         */
    }
}
