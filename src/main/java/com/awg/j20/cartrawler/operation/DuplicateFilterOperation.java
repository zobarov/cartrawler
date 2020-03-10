package com.awg.j20.cartrawler.operation;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.awg.j20.cartrawler.ds.car.CarResult;

@Component
public class DuplicateFilterOperation {
	
	/**
	 * Removes any duplicates from the list (duplicates = same make, model, supplier, SIPP, FuelPolicy)
	 * @param origSet - set of @see CarResult
	 * @return filtered (no duplicates) set of CarResult.
	 */
	public CarUniqueContainer filterDublicates(Set<CarResult> origSet) {		
		CarUniqueContainer uniqueCarContainer = new CarUniqueContainer();
		
		origSet.forEach(c -> {
			uniqueCarContainer.addCarResult(c);
		});
	
		return uniqueCarContainer;
	}
	
	
	/*
	public Set<CarResult> filterDublicates(Set<CarResult> origSet) {
		Set<CarResult> uniqueCarSet = new TreeSet<>(new Comparator<CarResult>() {
			  @Override
			  public int compare(CarResult car1, CarResult car2) {
				  if(car1.getDescription().equalsIgnoreCase(car2.getDescription())
				     && car1.getSupplierName().equalsIgnoreCase(car2.getSupplierName())
				     && car1.getSippCode().equals(car2.getSippCode())
				    // && car1.getFuelPolicy().equals(car2.getFuelPolicy())
						  ) {
					  return 0;
				  }
				  return 1;
			  }
			});
		
		origSet.forEach(c -> {
			boolean added = uniqueCarSet.add(c);
			System.out.println("Added " + added + " for model:" + c.getDescription() + ", " + c.getRentalCost());
		});
		//uniqueCarSet.addAll(origSet);
		return uniqueCarSet;
	}
	*/
}
