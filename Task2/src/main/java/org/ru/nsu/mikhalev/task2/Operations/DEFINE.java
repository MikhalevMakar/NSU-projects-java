package org.ru.nsu.mikhalev.task2.Operations;

import java.util.HashMap;
import java.util.Map;
import  org.ru.nsu.mikhalev.task2.CheckerDouble.*;
import java.lang.Double;
import java.util.Iterator;
import java.lang.Exception;

public class DEFINE {
    private static final Map<String, Double>  paramValue;
    static {
        paramValue = new HashMap<>();
    }
    private static int  countValue = 0;
    public   static void addParamValue(String parameter, double value) {
        if(!CheckerDouble.IsNumberFormat(parameter)) {
            countValue++;
            paramValue.put(parameter, value);
        }
    }

    public static int getCountValue() {
        return countValue;
    }

    public static Double getParamValue(String param) throws Exception {
        Iterator iterator  = paramValue.entrySet().iterator();

        for(var entry: paramValue.entrySet()) {
            if(entry.getKey().equals(param)) return entry.getValue();
        }
        throw new Exception("Not found element\n");
    }
}
