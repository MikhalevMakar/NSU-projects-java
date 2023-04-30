package ru.nsu.org.mikhalev.factory.detail;

import lombok.Synchronized;
import java.util.LinkedList;
import java.util.UUID;

public class Auto extends Detail {
    LinkedList<Detail> caseDetails = new LinkedList<>();
    public Auto(LinkedList<Detail> caseDetails) {
        super();
        synchronized(this.caseDetails) {
            this.caseDetails.addAll(caseDetails);
        }
    }

    @Synchronized
    public synchronized UUID getId() { //TODO: add correct output : full auto with details
        return id;
    }
}
