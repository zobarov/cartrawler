package com.awg.j20.cartrawler.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.awg.j20.cartrawler.domain.Suppliers;
import com.awg.j20.cartrawler.ds.car.CarResult;

@Component
public class CorporateDividerOperation {
	
	//Note corporate cars are those supplied by AVIS, BUDGET, ENTERPRISE, FIREFLY, HERTZ, SIXT, THRIFTY. 
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
	
	private CarGroupedByCorpContainer teeing() {
		/*
		Collector<CarResult, CarResult, CarResult> compositeCollector = 
				Collectors.teeing(
						Collectors.filtering(corpPredicate, Collectors.toList()),
						Collectors.filtering(nonCorpPredicate, Collectors.toList()),
						(corp, noncorp) -> {
							List<CarResult> corpRes = new ArrayList<>();
							corpRes.addAll(corp);
						});
		*/
		CarGroupedByCorpContainer operationResult = new CarGroupedByCorpContainer();
		return operationResult;
	}
	
	private static <T, U, R> List<R> listCombiner(
			  List<T> list1, List<U> list2, BiFunction<T, U, R> combiner) {
			    List<R> result = new ArrayList<>();
			    for (int i = 0; i < list1.size(); i++) {
			        result.add(combiner.apply(list1.get(i), list2.get(i)));
			    }
			    return result;
			}

}
