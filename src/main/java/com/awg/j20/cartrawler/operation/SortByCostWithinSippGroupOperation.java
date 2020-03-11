package com.awg.j20.cartrawler.operation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.awg.j20.cartrawler.ds.car.CarResult;

/**
 * Sorting data transformation operation.
 * Sorts by rental costs within SIPP groups.
 */
@Component
public class SortByCostWithinSippGroupOperation {
	
	public List<CarResult> sortByCost(List<CarResult> cars) {
		LinkedHashMap<String, List<CarResult>> groupsCollector = new LinkedHashMap<>();
		for(CarResult car : cars) {
			String sippType = car.getSippCodeType();
			
			List<CarResult> typeGroup = groupsCollector.get(sippType);
			if(typeGroup == null) {
				typeGroup = new ArrayList<>();
				groupsCollector.put(sippType, typeGroup);
			}
			typeGroup.add(car);
		}
		
		LinkedHashMap<String, List<CarResult>> groupsSortedByCostCollector = new LinkedHashMap<>();
		for(String key : groupsCollector.keySet()) {
			List<CarResult> group = groupsCollector.get(key);
			
			List<CarResult> sortedGroup = group.stream()
					  .sorted(Comparator.comparing(CarResult::getRentalCost))
					  .collect(Collectors.toList());
			
			groupsSortedByCostCollector.put(key, sortedGroup);			
		}
		
		List<CarResult> orderedList = new LinkedList<>();
		groupsSortedByCostCollector.values().forEach(list -> {
			orderedList.addAll(list);
		});
		return orderedList;		
	}

}
