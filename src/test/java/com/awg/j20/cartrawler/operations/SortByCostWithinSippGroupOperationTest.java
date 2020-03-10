package com.awg.j20.cartrawler.operations;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.awg.j20.cartrawler.ds.car.CarResult;
import com.awg.j20.cartrawler.operation.SortByCostWithinSippGroupOperation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SortByCostWithinSippGroupOperationTest {
	@Autowired
	private SortByCostWithinSippGroupOperation sortByCostOperation;	
	
	@Test
	public void shouldSort_AllMdmr() {
		List<CarResult> carOrderedList = new ArrayList<>();
		CarResult car1 = new CarResult("Volkswagen Polo", "NIZA", "MDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car2 = new CarResult("Ford C-Max Diesel", "AVIS", "MDMR", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car3 = new CarResult("Renault Scenic Diesel", "NIZA", "MDMR", 115.58d, CarResult.FuelPolicy.FULLFULL);
		CarResult car4 = new CarResult("Volkswagen Up", "NIZA", "MDMR", 9.78d, CarResult.FuelPolicy.FULLEMPTY);
		
		carOrderedList.add(car1);
		carOrderedList.add(car2);
		carOrderedList.add(car3);
		carOrderedList.add(car4);
		
		List<CarResult> actual = sortByCostOperation.sortByCost(carOrderedList);
		//then:
		assertThat(actual.get(0)).isEqualTo(car4);
		assertThat(actual.get(1)).isEqualTo(car1);
		assertThat(actual.get(2)).isEqualTo(car2);
		assertThat(actual.get(3)).isEqualTo(car3);
	}
	
	@Test
	public void shouldSort_AllTwoGroups() {
		List<CarResult> carOrderedList = new ArrayList<>();
		CarResult car1 = new CarResult("Volkswagen Polo", "NIZA", "IVAR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car2 = new CarResult("Ford C-Max Diesel", "AVIS", "MDMR", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car3 = new CarResult("Renault Scenic Diesel", "NIZA", "IVAR", 115.58d, CarResult.FuelPolicy.FULLFULL);
		CarResult car4 = new CarResult("Volkswagen Up", "NIZA", "MDMR", 9.78d, CarResult.FuelPolicy.FULLEMPTY);
		
		carOrderedList.add(car1);
		carOrderedList.add(car2);
		carOrderedList.add(car3);
		carOrderedList.add(car4);
		
		List<CarResult> actual = sortByCostOperation.sortByCost(carOrderedList);
		//then:
		assertThat(actual.get(0)).isEqualTo(car1);
		assertThat(actual.get(1)).isEqualTo(car3);
		assertThat(actual.get(2)).isEqualTo(car4);
		assertThat(actual.get(3)).isEqualTo(car2);
	}
	
	@Test
	public void shouldBeTheSameOrder_AllDifferentGroups() {
		List<CarResult> carOrderedList = new ArrayList<>();
		CarResult car1 = new CarResult("Volkswagen Polo", "NIZA", "IVAR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car2 = new CarResult("Ford C-Max Diesel", "AVIS", "FVMR", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car3 = new CarResult("Renault Scenic Diesel", "NIZA", "FDAR", 115.58d, CarResult.FuelPolicy.FULLFULL);
		CarResult car4 = new CarResult("Volkswagen Up", "NIZA", "MDMR", 9.78d, CarResult.FuelPolicy.FULLEMPTY);
		
		carOrderedList.add(car1);
		carOrderedList.add(car2);
		carOrderedList.add(car3);
		carOrderedList.add(car4);
		
		List<CarResult> actual = sortByCostOperation.sortByCost(carOrderedList);
		//then:
		assertThat(actual.get(0)).isEqualTo(car1);
		assertThat(actual.get(1)).isEqualTo(car2);
		assertThat(actual.get(2)).isEqualTo(car3);
		assertThat(actual.get(3)).isEqualTo(car4);
	}
}
