package ru.nsu.org.mikhalev.factory.detail;

import lombok.Synchronized;
import java.util.LinkedList;

public class Auto extends Detail {
    LinkedList<Detail> caseDetails = new LinkedList<>();
    public Auto(LinkedList<Detail> caseDetails) {
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
