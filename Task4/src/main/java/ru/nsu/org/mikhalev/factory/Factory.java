package ru.nsu.org.mikhalev.factory;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.dealer.Dealer;
import ru.nsu.org.mikhalev.factory.detail.Accessory;
import ru.nsu.org.mikhalev.factory.detail.Body;
import ru.nsu.org.mikhalev.factory.detail.Motor;
import ru.nsu.org.mikhalev.factory.storage.AccessoryStorage;
import ru.nsu.org.mikhalev.factory.storage.BodyStorage;
import ru.nsu.org.mikhalev.factory.storage.MotorStorage;
import ru.nsu.org.mikhalev.factory.storage.auto_storage.AutoStorage;
import ru.nsu.org.mikhalev.factory.suppliers.AccessorySupplier;
import ru.nsu.org.mikhalev.factory.suppliers.BodySupplier;
import ru.nsu.org.mikhalev.factory.suppliers.MotorSupplier;
import ru.nsu.org.mikhalev.factory.worker.Worker;
import ru.nsu.org.mikhalev.proces_input.properties_read.FactoryReader;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;
import ru.nsu.org.mikhalev.thread_pool.ThreadPool;

import java.io.IOException;

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
    @Getter
    private AutoStorage autoStorage;

    private final ThreadPool<Worker> threadWorker;
    private final ThreadPool<MotorSupplier> threadMotorSupplier;
    private final ThreadPool<AccessorySupplier> threadAccessorySupplier;
    private final ThreadPool<BodySupplier> threadBodySupplier;
    private AccessorySupplier accessorySupplier;
    private MotorSupplier motorSupplier;
    private  BodySupplier bodySupplier;
    private final ThreadPool<Dealer> threadDealer;

    public Factory(String link) throws IOException {
        log.info("Call FactoryReader.read()");
        FactoryReader.read(link);


        log.info("Create storage");
        createStorage();

        Worker worker = new Worker(autoStorage, accessoryStorage, bodyStorage, motorStorage);

        accessorySupplier = new AccessorySupplier(accessoryStorage, Accessory.class);
        motorSupplier = new MotorSupplier(motorStorage, Motor.class);
        bodySupplier = new BodySupplier(bodyStorage, Body.class);
        dealer = new Dealer(autoStorage);


        log.info("Generate new thread");
        threadWorker = new ThreadPool<>(Integer.valueOf(Properties_Value.WORKERS.getValue()), worker);

        threadMotorSupplier = new ThreadPool<>(1, motorSupplier);

        threadBodySupplier = new ThreadPool<>(1, bodySupplier);

        threadAccessorySupplier = new ThreadPool<>(
            Integer.valueOf(Properties_Value.ACCESSORY_SUPPLIERS.getValue()), accessorySupplier);

        threadDealer = new ThreadPool<>(Integer.valueOf(Properties_Value.DEALERS.getValue()), dealer);
    }
    private void createStorage() {
        accessoryStorage = new AccessoryStorage();
        bodyStorage = new BodyStorage();
        motorStorage = new MotorStorage();
        autoStorage = new AutoStorage();
    }

    public void start() {
        log.info("factory start work");

        threadWorker.start();
        threadMotorSupplier.start();
        threadAccessorySupplier.start();
        threadBodySupplier.start();
        threadDealer.start();
    }

    public void stop() {
        log.info("factory start interrupt");
        threadWorker.end();
        threadMotorSupplier.end();
        threadAccessorySupplier.end();
        threadBodySupplier.end();
        threadDealer.end();
    }

    public void setTimeDealer(int time) { dealer.setTime(time);}
    public void setTimeAccessorySupplier(int time) { accessorySupplier.setTime(time);}
    public void setTimeBodySupplier(int time) { bodySupplier.setTime(time); }
    public void setTimeMotorSupplier(int time) { motorSupplier.setTime(time);}

}
