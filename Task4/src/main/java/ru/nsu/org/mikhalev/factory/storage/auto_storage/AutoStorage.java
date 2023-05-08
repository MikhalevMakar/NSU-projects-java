package ru.nsu.org.mikhalev.factory.storage.auto_storage;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.detail.Auto;
import ru.nsu.org.mikhalev.factory.storage.Storage;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;


/*
 * { Auto Storage } is a class that refers to a car storage warehouse.
 * This class has a dependency with the controller and workers.
 * The controller manages the warehouse, monitors its filling.
 * Workers once for a while send cars to the warehouse.
 */

@Log4j2
public class AutoStorage extends Storage<Auto> {
    private final ControllerAutoStorage controllerAutoStorage;

    /*
     * Сonstructor AutoStorage.
     * @parameters ControllerAutoStorage.
     */
    public AutoStorage(ControllerAutoStorage controllerAutoStorage){
        super(Integer.parseInt(Properties_Value.STORAGE_AUTO_SIZE.getValue()));
        this.controllerAutoStorage = controllerAutoStorage;
    }

    /*
     * Add auto to LinkedList<Details> detail.
     * synchronized this.
     * @parameters Auto.
     * @return void.
     */
    public synchronized void addAuto(Auto auto) {
        notifyObservers(String.valueOf(details.size()), startSizeStorage);
        details.add(auto);
    }

    /*
     * This method getAuto() issues cars from the warehouse.
     * If the storage is full then workers do not supply the machines.
     * In case of interruption, the thread will be interrupted and the machines will = null.
     * Then the warehouse notifies about the number of cars
     * @parameters is void.
     * @return value is type Auto.
     */
    public synchronized Auto getAuto() {
        while (details.isEmpty() && Thread.currentThread().isAlive()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.warn("Interrupted Exception in auto storage");
                Thread.currentThread().interrupt();
                return null;
            }
        }

        Auto auto = details.remove();
        controllerAutoStorage.distributionTask(details.size());
        notifyObservers(String.valueOf(details.size()), startSizeStorage);
        return auto;
    }
}