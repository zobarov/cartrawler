package com.awg.j20.cartrawler.operation;

import java.util.ArrayList;
import java.util.List;

import com.awg.j20.cartrawler.ds.car.CarResult;

public class CarGroupedByCorpContainer {
	private List<CarResult> corporateCars = new ArrayList<>();
	private List<CarResult> nonCorporateCars = new ArrayList<>();
	
	public CarGroupedByCorpContainer withCorporateCars(List<CarResult> cars) {
		this.corporateCars = cars;
		return this;
	}
	
	public CarGroupedByCorpContainer withNonCorporateCars(List<CarResult> cars) {
		this.nonCorporateCars = cars;
		return this;
	}
	
	public CarResult getCorporateCarAt(int index) {
		if(index >= corporateCars.size()) {
			return null;
		}
		return this.corporateCars.get(index);
	}
	
	public CarResult getNonCorporateCarAt(int index) {
		if(index >= nonCorporateCars.size()) {
			return null;
		}
		return this.nonCorporateCars.get(index);
	}
	
	public int sizeCorporateCar() {
		return this.corporateCars.size();
	}
	
	public int sizeNonCorporateCars() {
		return this.nonCorporateCars.size();
	}
	
	public int sizeAll( ) {
		return sizeCorporateCar() + sizeNonCorporateCars();
	}
	
	public List<CarResult> consolidatedCorporateFirst() {
		List<CarResult> groupedCars = new ArrayList<>();
		groupedCars.addAll(corporateCars);
		groupedCars.addAll(nonCorporateCars);
		
		return groupedCars;
	}

}
