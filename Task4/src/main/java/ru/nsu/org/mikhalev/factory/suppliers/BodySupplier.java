package ru.nsu.org.mikhalev.factory.suppliers;

import ru.nsu.org.mikhalev.factory.detail.Body;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

public class BodySupplier extends DetailSupplier<Body> {
    public BodySupplier(DetailStorage<Body> bodyStorage, Class<Body> clazz) {
        super(bodyStorage, clazz);
    }
}
