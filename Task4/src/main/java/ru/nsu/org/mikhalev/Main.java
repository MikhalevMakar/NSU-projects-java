package ru.nsu.org.mikhalev;

import org.apache.commons.cli.ParseException;
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
import ru.nsu.org.mikhalev.proces_input.ParseFileJSON;
import ru.nsu.org.mikhalev.proces_input.properties_read.FactoryReader;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;
import ru.nsu.org.mikhalev.thread_pool.ThreadPool;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws ParseException, IOException {
        ParseFileJSON parseFileJSON = new ParseFileJSON(args);

    }

//    public static void main(String[] args) throws ExcParseFileJSON, ParseException{
//        ParseFileJSON parseFileJSON = new ParseFileJSON(args);
//        //new GenerateMainMenu();
//        new Chat();
//        new ru.nsu.org.mikhalev.view.SupplierSpeedSlider(parseFileJSON.getLinkGuiComponents());
//    }

}
