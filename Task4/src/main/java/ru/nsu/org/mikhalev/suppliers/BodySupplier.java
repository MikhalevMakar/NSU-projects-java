package ru.nsu.org.mikhalev.suppliers;

import ru.nsu.org.mikhalev.detail.Body;
import ru.nsu.org.mikhalev.storage.DetailStorage;

public class BodySupplier extends DetailSupplier<Body> {
    public BodySupplier(DetailStorage<Body> bodyStorage, Class<Body> clazz) {
        super(bodyStorage, clazz);
    }

}
