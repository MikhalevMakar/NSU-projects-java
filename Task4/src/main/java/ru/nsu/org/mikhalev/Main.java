package ru.nsu.org.mikhalev;

import org.apache.commons.cli.ParseException;
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
import ru.nsu.org.mikhalev.proces_input.ParseFileJSON;
import ru.nsu.org.mikhalev.proces_input.properties_read.FactoryReader;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;
import ru.nsu.org.mikhalev.thread_pool.ThreadPool;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        ParseFileJSON parseFileJSON = new ParseFileJSON(args);

        FactoryReader.read(parseFileJSON.getLinkInfoFactory());

        AccessoryStorage accessoryStorage = new AccessoryStorage();
        BodyStorage bodyStorage = new BodyStorage();
        MotorStorage motorStorage = new MotorStorage();
        AutoStorage autoStorage = new AutoStorage();

        Worker worker = new Worker(autoStorage, accessoryStorage, bodyStorage, motorStorage);

        AccessorySupplier accessorySupplier = new AccessorySupplier(accessoryStorage, Accessory.class);
        MotorSupplier motorSupplier = new MotorSupplier(motorStorage, Motor.class);
        BodySupplier bodySupplier = new BodySupplier(bodyStorage, Body.class);

        ThreadPool threadWorker = new ThreadPool(Integer.valueOf(Properties_Value.WORKERS.getValue()), worker);
//        ThreadPool threadMotorSupplier = new ThreadPool(1, motorSupplier);
//        ThreadPool threadBodySupplier = new ThreadPool(1, bodySupplier.getClass());
        ThreadPool threadAccessorySupplier = new ThreadPool(Integer.valueOf(Properties_Value.ACCESSORY_SUPPLIERS.getValue()), accessorySupplier);

        threadWorker.start();
        threadAccessorySupplier.start();
    }

}




//    public static void main(String[] args) {
//        //new GenerateMainMenu();
//        new ru.nsu.org.mikhalev.view.SupplierSpeedSlider();
//    }