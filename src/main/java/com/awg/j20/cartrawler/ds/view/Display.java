package com.awg.j20.cartrawler.ds.view;

import java.util.List;
import java.util.Set;

import com.awg.j20.cartrawler.ds.car.CarResult;

public class Display {
    public void render(Set<CarResult> cars) {
        for (CarResult car : cars) {
            System.out.println (car);
        }
    }
    
    public void render(List<CarResult> cars, String header) {
    	System.out.println("------------" + header + "-------------");
        for (CarResult car : cars) {
            System.out.println (car);
        }
        System.out.println("---END-----" + header + "-------------");
    }
}
