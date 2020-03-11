package com.awg.j20.cartrawler.operation;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.awg.j20.cartrawler.domain.CarUniqueContainer;
import com.awg.j20.cartrawler.ds.car.CarResult;

/**
 * Data transform operation to filter duplicates from initial data set.
 */
@Component
public class DuplicateFilterOperation {
	
	/**
	 * Removes any duplicates from the list (duplicates = same make, model, supplier, SIPP, FuelPolicy)
	 * @param origSet - set of @see CarResult
	 * @return filtered (no duplicates) container with filtered car set.
	 */
	public CarUniqueContainer filterDublicates(Set<CarResult> origSet) {		
		CarUniqueContainer uniqueCarContainer = new CarUniqueContainer();
		
		origSet.forEach(c -> {
			uniqueCarContainer.addCarResult(c);
		});
	
		return uniqueCarContainer;
	}
}
