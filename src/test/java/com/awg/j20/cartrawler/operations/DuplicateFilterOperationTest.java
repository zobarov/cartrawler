package com.awg.j20.cartrawler.operations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.awg.j20.cartrawler.ds.HardcodedCarSource;
import com.awg.j20.cartrawler.ds.car.CarResult;
import com.awg.j20.cartrawler.operation.CarUniqueContainer;
import com.awg.j20.cartrawler.operation.DuplicateFilterOperation;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DuplicateFilterOperationTest {
	@MockBean
	private HardcodedCarSource carDataSource;
	@Autowired
	private DuplicateFilterOperation dublicateFilter;

	@Test
	public void shouldRemainTheSame_noDublicates() {
		Set<CarResult> expectedOne = new HashSet<>();
		CarResult car1 = new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car2 = new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car3 = new CarResult("Mini Cooper", "NIZA", "EDMR", 16.75d, CarResult.FuelPolicy.FULLEMPTY);

		expectedOne.add(car1);
		expectedOne.add(car2);
		expectedOne.add(car3);

		when(carDataSource.carResults()).thenReturn(expectedOne);

		CarUniqueContainer actual = dublicateFilter.filterDublicates(carDataSource.carResults());

		assertThat(actual).isNotNull();
		assertThat(actual.uniqueSize()).isEqualTo(3);
		assertTrue("Car1 present", actual.containsCar(car1));
		assertTrue("Car2 present", actual.containsCar(car2));
		assertTrue("Car3 present", actual.containsCar(car3));
	}

	@Test
	public void shouldRemoveFord_AND_remainOthers() {
		Set<CarResult> carSet = new HashSet<>();
		CarResult car1 = new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult ford1 = new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car2 = new CarResult("Volkswagen Polo", "CENTAURO", "EDMR", 22.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car3 = new CarResult("Mini Cooper", "NIZA", "EDMR", 16.75d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult ford2 = new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY);

		carSet.add(car1);
		carSet.add(ford1);
		carSet.add(car2);
		carSet.add(car3);
		carSet.add(ford2);
		when(carDataSource.carResults()).thenReturn(carSet);

		CarUniqueContainer actual = dublicateFilter.filterDublicates(carDataSource.carResults());
		// then:
		assertThat(actual).isNotNull();
		assertThat(actual.uniqueSize()).isEqualTo(4);
		assertTrue("Car1 present", actual.containsCar(car1));
		assertTrue("Car2 present", actual.containsCar(car2));
		assertTrue("Car3 present", actual.containsCar(car3));
		// by checking both i guarantee equivalent reference with only one value:
		assertTrue("Ford1 present", actual.containsCar(ford1));
		assertTrue("Ford2 present", actual.containsCar(ford2));
		assertThat(actual.rentalCostForCar(ford1)).isEqualTo(22.04d);
		assertThat(actual.rentalCostForCar(ford2)).isEqualTo(22.04d);
	}

	@Test
	public void shouldRemainVWGolf_diffSupplier_AND_RemoveFord_OnlyCostDifferent() {
		// given:
		Set<CarResult> carSet = new HashSet<>();
		CarResult car1 = new CarResult("Volkswagen Golf", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult ford1 = new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car2 = new CarResult("Volkswagen Golf", "CENTAURO", "EDMR", 26.96d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult car3 = new CarResult("Audi A3 Diesel", "NIZA", "CDMD", 41.16d, CarResult.FuelPolicy.FULLEMPTY);
		CarResult ford2 = new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 15.70d, CarResult.FuelPolicy.FULLEMPTY);

		carSet.add(car1);
		carSet.add(ford1);
		carSet.add(car2);
		carSet.add(car3);
		carSet.add(ford2);

		when(carDataSource.carResults()).thenReturn(carSet);
		// when:
		CarUniqueContainer actual = dublicateFilter.filterDublicates(carDataSource.carResults());
		// then:
		assertThat(actual).isNotNull();
		assertThat(actual.uniqueSize()).isEqualTo(4);
		assertTrue("Car1 present", actual.containsCar(car1));
		assertTrue("Car2 present", actual.containsCar(car2));
		assertTrue("Car3 present", actual.containsCar(car3));
		// by checking both i guarantee equivalent reference with only one value:
		assertTrue("Ford1 present", actual.containsCar(ford1));
		assertTrue("Ford2 present", actual.containsCar(ford2));
		assertThat(actual.rentalCostForCar(ford1)).isEqualTo(22.04d);
		assertThat(actual.rentalCostForCar(ford2)).isEqualTo(22.04d);
	}
}
