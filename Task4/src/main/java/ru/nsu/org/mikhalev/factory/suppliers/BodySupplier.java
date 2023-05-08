package ru.nsu.org.mikhalev.factory.suppliers;

import ru.nsu.org.mikhalev.factory.detail.Body;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

/*
 * { BodySupplier } class supplies of parts of the type Body
 *
 * Calling the base class DetailSupplier<Body>
 */

public class BodySupplier extends DetailSupplier<Body> {
    public BodySupplier(DetailStorage<Body> bodyStorage, Class<Body> clazz) {
        super(bodyStorage, clazz);
    }
}
