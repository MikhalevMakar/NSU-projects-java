package ru.nsu.org.mikhalev.factory.detail;

import lombok.Synchronized;
import java.util.LinkedList;
import java.util.List;

/*
 * { Auto } class this product that is manufactured at the factory.
 */

public class Auto extends Detail {
    private final LinkedList<Detail> caseDetails = new LinkedList<>();
    public Auto(List<Detail> caseDetails) {
        super();
        synchronized(this.caseDetails) {
            this.caseDetails.addAll(caseDetails);
        }
    }

    @Synchronized
    public synchronized StringBuilder getId() {
        StringBuilder id = new StringBuilder("Auto:" + this.id + " ");
        for (var detail: caseDetails) {
            id.append(detail.getClass().getSimpleName()).append(":").append(detail.id).append(" ");
        }
        return id;
    }
}
