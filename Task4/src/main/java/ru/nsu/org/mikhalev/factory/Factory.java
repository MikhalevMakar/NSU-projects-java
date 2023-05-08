package ru.nsu.org.mikhalev.factory;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.dealer.Dealer;
import ru.nsu.org.mikhalev.factory.detail.*;
import ru.nsu.org.mikhalev.factory.storage.*;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.*;
import ru.nsu.org.mikhalev.factory.suppliers.*;
import ru.nsu.org.mikhalev.factory.worker.Worker;
import ru.nsu.org.mikhalev.proces_input.properties_read.*;
import ru.nsu.org.mikhalev.thread_pool.ProduceThread;

import java.io.IOException;

/*
 * { Factory } class runs all the factories
 */

@Log4j2
public class Factory {

    @Getter
    private final Dealer dealer;

    @Getter
    private AccessoryStorage accessoryStorage;

    @Getter
    private BodyStorage bodyStorage;

    @Getter
    private MotorStorage motorStorage;

    private final ProduceThread<MotorSupplier> threadMotorSupplier;

    private final ProduceThread<AccessorySupplier> threadAccessorySupplier;

    private final ProduceThread<BodySupplier> threadBodySupplier;

    private final AccessorySupplier accessorySupplier;

    private final MotorSupplier motorSupplier;

    private  final BodySupplier bodySupplier;

    @Getter
    private final ControllerAutoStorage controllerAutoStorage;

    private final ProduceThread<Dealer> threadDealer;

    /*
     * Construct
     *
     * This is where all the necessary objects to start the factory are created
     *
     * @param String this link for info_factory
     */
    public Factory(String link) throws IOException {
        log.info("Call FactoryReader.read()");
        FactoryReader.read(link);

        log.info("Create storage");
        createStorage();

        log.info("Generate suppliers");
        accessorySupplier = new AccessorySupplier(accessoryStorage, Accessory.class);
        motorSupplier = new MotorSupplier(motorStorage, Motor.class);
        bodySupplier = new BodySupplier(bodyStorage, Body.class);
        controllerAutoStorage = new ControllerAutoStorage(Integer.valueOf(Properties_Value
                                                                                          .STORAGE_AUTO_SIZE
                                                                                          .getValue()));

        log.info("Create dealer");
        dealer = new Dealer(controllerAutoStorage.getAutoStorage());

        log.info("Registration storages");
        Worker.registrationStorages(accessoryStorage, bodyStorage, motorStorage);

        log.info("Generate new thread");
        threadMotorSupplier = new ProduceThread<>(1, motorSupplier);

        threadBodySupplier = new ProduceThread<>(1, bodySupplier);

        threadAccessorySupplier = new ProduceThread<>(Integer.valueOf(Properties_Value
                                                                                       .ACCESSORY_SUPPLIERS
                                                                                       .getValue()),
                                                      accessorySupplier);

        threadDealer = new ProduceThread<>(Integer.valueOf(Properties_Value.DEALERS.getValue()), dealer);

    }

    /*
     * This method create storages for factory
     *
     * @param void
     * @return void
     */
    private void createStorage() {
        log.info("Create storage");

        accessoryStorage = new AccessoryStorage();
        bodyStorage = new BodyStorage();
        motorStorage = new MotorStorage();
    }

    /*
     * Launch work storages
     *
     * @param void
     * @return void
     */
    public void start() {
        log.info("factory start work");

        controllerAutoStorage.start();
        threadMotorSupplier.start();
        threadAccessorySupplier.start();
        threadBodySupplier.start();
        threadDealer.start();
    }

    /*
     * Interrupt work storages
     *
     * @param void
     * @return void
     */
    public void stop() {
        log.info("factory start interrupt");

        threadMotorSupplier.end();
        threadAccessorySupplier.end();
        threadBodySupplier.end();
        threadDealer.end();
        controllerAutoStorage.end();
    }

    public void setTimeDealer(int time) { dealer.setTime(time);}

    public void setTimeAccessorySupplier(int time) { accessorySupplier.setTime(time);}

    public void setTimeBodySupplier(int time) { bodySupplier.setTime(time); }

    public void setTimeMotorSupplier(int time) { motorSupplier.setTime(time);}

    public AutoStorage getAutoStorage() {
        return controllerAutoStorage.getAutoStorage();
    }
}
