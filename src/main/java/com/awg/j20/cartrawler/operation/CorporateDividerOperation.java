package com.awg.j20.cartrawler.operation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.awg.j20.cartrawler.domain.CarGroupedByCorpContainer;
import com.awg.j20.cartrawler.domain.Suppliers;
import com.awg.j20.cartrawler.ds.car.CarResult;

/**
 * Data transform operation to divide income ordered
 * list of cars to gropus.
 */
@Component
public class CorporateDividerOperation {
	
	public CarGroupedByCorpContainer groupCorporates(List<CarResult> carOrderedList) {
		Predicate<CarResult> corpPredicate = car -> {
			Suppliers supplier = Suppliers.findByName(car.getSupplierName());
			return supplier.isRegistered() && supplier.isCorporate();
		};
		
		Predicate<CarResult> nonCorpPredicate = car -> {
			Suppliers supplier = Suppliers.findByName(car.getSupplierName());
			return (!supplier.isRegistered()) || (!supplier.isCorporate());
		};
		
		CarGroupedByCorpContainer operationResult = new CarGroupedByCorpContainer();
		
		List<CarResult> corporateCars = carOrderedList.stream().filter(corpPredicate).collect(Collectors.toList());
		List<CarResult> nonCorporateCars = carOrderedList.stream().filter(nonCorpPredicate).collect(Collectors.toList());
		
		operationResult.withCorporateCars(corporateCars)
					   .withNonCorporateCars(nonCorporateCars);
		return operationResult;
	}
	
	/*
	private CarGroupedByCorpContainer teeing() {
		
		Collector<CarResult, CarResult, CarResult> compositeCollector = 
				Collectors.teeing(
						Collectors.filtering(corpPredicate, Collectors.toList()),
						Collectors.filtering(nonCorpPredicate, Collectors.toList()),
						(corp, noncorp) -> {
							List<CarResult> corpRes = new ArrayList<>();
							corpRes.addAll(corp);
						});
		
		CarGroupedByCorpContainer operationResult = new CarGroupedByCorpContainer();
		return operationResult;
	}*/
}
