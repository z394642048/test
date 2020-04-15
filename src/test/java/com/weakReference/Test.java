package com.weakReference;

public class Test {

    public static void main(String[] args) {
        Car car = new Car(2000.0, "red");
        WeakReferenceCar wrc = new WeakReferenceCar(car);
        int i = 0;
        while (true) {
            if (wrc.get() != null) {
                i++;
                new Car(111.0, "blue");
                System.out.println("WeakReferenceCar's Car is alive for " + i + ", loop - " + wrc);
            } else {
                System.out.println("WeakReferenceCar's Car has bean collected" + wrc);
                break;
            }
        }
        System.out.println(car);
        System.out.println(wrc);
    }
}
