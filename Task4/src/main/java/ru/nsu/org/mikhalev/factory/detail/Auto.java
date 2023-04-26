package ru.nsu.org.mikhalev.factory.detail;

import java.util.LinkedList;
import java.util.UUID;

public class Auto extends Detail {
    LinkedList<Detail> caseDetails = new LinkedList<>();
    public Auto(LinkedList<Detail> caseDetails) {
        super();
        this.caseDetails.addAll(caseDetails);
    }

    public UUID getId() {
        return id;
    }
}
