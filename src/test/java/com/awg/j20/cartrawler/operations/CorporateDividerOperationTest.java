package com.awg.j20.cartrawler.operations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.awg.j20.cartrawler.domain.CarGroupedByCorpContainer;
import com.awg.j20.cartrawler.domain.CarUniqueContainer;
import com.awg.j20.cartrawler.ds.car.CarResult;
import com.awg.j20.cartrawler.operation.CorporateDividerOperation;
import com.awg.j20.cartrawler.operation.DuplicateFilterOperation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CorporateDividerOperationTest {
	@Autowired
	private CorporateDividerOperation corporateDividerOperation;
	
	@Test
	public void shouldGroup_AvisFirst() {
		List<CarResult> carOrderedList = new ArrayList<>();
		CarResult nonCorpCar1 = new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult corpCar1 = new CarResult("Ford C-Max Diesel", "AVIS", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult nonCorpCar2 = new CarResult("Mini Cooper", "NIZA", "EDMR", 16.75d, CarResult.FuelPolicy.FULLEMPTY);

		carOrderedList.add(nonCorpCar1);
		carOrderedList.add(corpCar1);
		carOrderedList.add(nonCorpCar2);

		CarGroupedByCorpContainer actual = corporateDividerOperation.groupCorporates(carOrderedList);

		assertThat(actual).isNotNull();
		assertThat(actual.sizeAll()).isEqualTo(3);
		assertThat(actual.sizeCorporateCar()).isEqualTo(1);
		assertThat(actual.sizeNonCorporateCars()).isEqualTo(2);
		//
		CarResult actualAvis = actual.getCorporateCarAt(0);
		assertThat(actual.getCorporateCarAt(0)).isNotNull();
		assertThat(actualAvis).isEqualTo(corpCar1);
		
		CarResult actualNizza1 = actual.getNonCorporateCarAt(0);
		assertThat(actualNizza1).isNotNull();
		assertThat(actualNizza1).isEqualTo(nonCorpCar1);
		
		CarResult actualNizza2 = actual.getNonCorporateCarAt(1);
		assertThat(actualNizza2).isNotNull();
		assertThat(actualNizza2).isEqualTo(nonCorpCar2);
	}

}
