package com.awg.j20.cartrawler.operation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.awg.j20.cartrawler.ds.car.CarResult;

@Component
public class SortBySippOperation {
	
	public List<CarResult> sortBySipp(List<CarResult> cars) {
		Comparator<CarResult> carSippComparator 
		 	= Comparator.comparing(CarResult::getSippCode, (s1, s2) -> {
				String sippFirst = s1.substring(0, 1);
				String sippSecond = s2.substring(0, 1);
				
				return compareSippTypeLetters(sippFirst, sippSecond);
	     });
		return cars.stream().sorted(carSippComparator).collect(Collectors.toList());
	}
	
	private int compareSippTypeLetters(String one, String two) {
		List<String> orderList = Arrays.asList("C", "E", "M");
		int indOne = orderList.indexOf(one);
		int indTwo = orderList.indexOf(two);
		
		return indTwo - indOne;
	}
}
