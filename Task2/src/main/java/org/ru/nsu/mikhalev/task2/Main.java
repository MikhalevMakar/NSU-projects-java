package org.ru.nsu.mikhalev.task2;//package org.ru.nsu.mikhalev.task2;
//
//import java.io.*;
//import org.apache.commons.cli.ParseException;
//import org.ru.nsu.mikhalev.task2.CalculatorController.CalculatorController;
//import java.util.logging.*;
//public class Main {
//
//   // private  static  final Logger LOGGER = Logger.getLogger(Main.class.getName());
//    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException {
////        LOGGER.info ("Start process");
////        LOGGER.info ("Call calculator controller");
//        CalculatorController calculator = new CalculatorController (args);
//        calculator.launch ();
//
//    }
//}

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface JavaFileInfo {
    String name() default "Igor M.";
    String value() default "0.0";
}
@JavaFileInfo(name = "Denis" ,value = "2.0")
class DemoClass {
    @JavaFileInfo(name = "Chi Max", value = "1.0")
    public void printString() {}
}

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        Class<DemoClass> demoClassObj = DemoClass.class;
        readAnnotationOn(demoClassObj);
        Method method = demoClassObj.getMethod("printString");
        readAnnotationOn(method);
    }

    static void readAnnotationOn(AnnotatedElement element) {
        try {
            System.out.println("\nПоиск аннотаций в " + element.getClass().getName());
            Annotation[] annotations = element.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof JavaFileInfo fileInfo) {
                    System.out.println("Автор: " + fileInfo.name());
                    System.out.println("Версия: " + fileInfo.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}