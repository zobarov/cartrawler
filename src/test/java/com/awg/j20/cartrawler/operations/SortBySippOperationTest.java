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
import com.awg.j20.cartrawler.operation.SortBySippOperation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SortBySippOperationTest {
	@Autowired
	private SortBySippOperation sortBySippOperation;
	
	@Test
	public void shouldSort_EveryoneOnce() {
		List<CarResult> carOrderedList = new ArrayList<>();
		CarResult carE = new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult carC = new CarResult("Ford C-Max Diesel", "AVIS", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult carJ = new CarResult("Renault Scenic Diesel", "NIZA", "JGAD", 115.58d, CarResult.FuelPolicy.FULLFULL);
		CarResult carM = new CarResult("Volkswagen Up", "NIZA", "MDMR", 9.78d, CarResult.FuelPolicy.FULLEMPTY);
		
		carOrderedList.add(carE);
		carOrderedList.add(carC);
		carOrderedList.add(carJ);
		carOrderedList.add(carM);
		
		List<CarResult> actual = sortBySippOperation.sortBySipp(carOrderedList);
		//M,E,C,J
		assertThat(actual.get(0)).isEqualTo(carM);
		assertThat(actual.get(1)).isEqualTo(carE);
		assertThat(actual.get(2)).isEqualTo(carC);
		assertThat(actual.get(3)).isEqualTo(carJ);
	}
	
	@Test
	public void shouldSort_MultipleKnown() {
		List<CarResult> carOrderedList = new ArrayList<>();
		
		CarResult carS = new CarResult("Mercedes Vito Traveliner", "CENTAURO", "SVMR", 168.59d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult carC1 = new CarResult("Ford C-Max Diesel", "AVIS", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult carE1 = new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult carM1 = new CarResult("Peugeot 107", "CENTAURO", "MCMR", 9.53d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult carC2 = new CarResult("Nissan Juke", "GOLDCAR", "CFMR", 146.54d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult carJ = new CarResult("Renault Scenic Diesel", "NIZA", "JGAD", 115.58d, CarResult.FuelPolicy.FULLFULL);
		CarResult carM2 = new CarResult("Volkswagen Up", "NIZA", "MDMR", 9.78d, CarResult.FuelPolicy.FULLEMPTY);
		
		carOrderedList.add(carS);
		carOrderedList.add(carC1);
		carOrderedList.add(carE1);
		carOrderedList.add(carM1);
		carOrderedList.add(carC2);
		carOrderedList.add(carJ);
		carOrderedList.add(carM2);
		//when:
		List<CarResult> actual = sortBySippOperation.sortBySipp(carOrderedList);
		//M,E,C,J
		assertThat(actual.get(0)).isEqualTo(carM1);
		assertThat(actual.get(1)).isEqualTo(carM2);
		assertThat(actual.get(2)).isEqualTo(carE1);
		assertThat(actual.get(3)).isEqualTo(carC1);
		assertThat(actual.get(4)).isEqualTo(carC2);
		assertThat(actual.get(5)).isEqualTo(carS);
		assertThat(actual.get(6)).isEqualTo(carJ);
	}
	

}
