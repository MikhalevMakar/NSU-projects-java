package ru.nsu.org.mikhalev.factory;

import lombok.Getter;
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
    private final ThreadPool<Dealer> threadDealer;

    public Factory(String link) throws IOException{
        FactoryReader.read(link);

        accessoryStorage = new AccessoryStorage();
        bodyStorage = new BodyStorage();
        motorStorage = new MotorStorage();
        autoStorage = new AutoStorage();

        Worker worker = new Worker(autoStorage, accessoryStorage, bodyStorage, motorStorage);

        AccessorySupplier accessorySupplier = new AccessorySupplier(accessoryStorage, Accessory.class);
        MotorSupplier motorSupplier = new MotorSupplier(motorStorage, Motor.class);
        BodySupplier bodySupplier = new BodySupplier(bodyStorage, Body.class);
        dealer = new Dealer(autoStorage);

        threadWorker = new ThreadPool<>(Integer.valueOf(Properties_Value.WORKERS.getValue()), worker);

        threadMotorSupplier = new ThreadPool<>(1, motorSupplier);

        threadBodySupplier = new ThreadPool<>(1, bodySupplier);

        threadAccessorySupplier = new ThreadPool<>(
            Integer.valueOf(Properties_Value.ACCESSORY_SUPPLIERS.getValue()), accessorySupplier);

        threadDealer = new ThreadPool<>(Integer.valueOf(Properties_Value.DEALERS.getValue()), dealer);
    }

    public void start() {
        threadWorker.start();
        threadMotorSupplier.start();
        threadAccessorySupplier.start();
        threadBodySupplier.start();
        threadDealer.start();
    }

    public void stop() {
        threadWorker.end();
        threadMotorSupplier.end();
        threadAccessorySupplier.end();
        threadBodySupplier.end();
        threadDealer.end();
    }
}
