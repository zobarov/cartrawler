package com.awg.j20.cartrawler.domain;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.awg.j20.cartrawler.ds.car.CarResult;

/**
 * Container to store only unique by key properties sets Cars.
 * Adding more @see {@link CarResult} has contract that later added erases
 * previous value with the same key-factors.
 * 
 * It fixes iteration order for entries as incoming from source.
 */
public class CarUniqueContainer {
	private LinkedHashMap<String, CarKeyEntry> uniqueCarMap = new LinkedHashMap<>();
	
	public void addCarResult(CarResult carResult) {
		CarKeyEntry entry = new CarKeyEntry(carResult);
		uniqueCarMap.put(entry.uniqueKey, entry);
	}
	
	public boolean containsCar(CarResult carResult) {
		CarKeyEntry entryForCheck = new CarKeyEntry(carResult);
		return uniqueCarMap.containsKey(entryForCheck.uniqueKey);
	}
	
	public List<CarResult> listAllOrdered() {
		List<CarResult> orderedList = new LinkedList<>();
		uniqueCarMap.values().forEach(ce -> orderedList.add(ce.getCar()));
		return orderedList;
	}
	
	/**
	 * Fetch rental cost by car
	 *
	 * @param carResult - a car for checking the cost
	 * @return 0 if car with such key factors not found
	 */
	public Double rentalCostForCar(CarResult carResult) {
		CarKeyEntry entryForCheck = new CarKeyEntry(carResult);
		CarKeyEntry car = uniqueCarMap.get(entryForCheck.getUniqueKey());
		
		if(car == null) {
			return 0d;
		}
		return car.getCar().getRentalCost();
	}
	
	public int uniqueSize() {
		return uniqueCarMap.size();
	}
	
	public static class CarKeyEntry {
		private CarResult car;
		private String uniqueKey;
		
		public CarKeyEntry(CarResult car) {
			this.car = car;
			this.uniqueKey = formUniqueKey();	
		}
		
		private String formUniqueKey() {
			return this.car.getDescription() + "|"
					+ this.car.getSupplierName()  + "|"
					+ this.car.getSippCode()  + "|"
					+ this.car.getFuelPolicy();
		}

		public CarResult getCar() {
			return car;
		}

		public String getUniqueKey() {
			return uniqueKey;
		}
	}
}
