package com.awg.j20.cartrawler.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.awg.j20.cartrawler.ds.HardcodedCarSource;
import com.awg.j20.cartrawler.ds.view.Display;
import com.awg.j20.cartrawler.operation.CarGroupedByCorpContainer;
import com.awg.j20.cartrawler.operation.CarUniqueContainer;
import com.awg.j20.cartrawler.operation.CorporateDividerOperation;
import com.awg.j20.cartrawler.operation.DuplicateFilterOperation;
import com.awg.j20.cartrawler.operation.SortByCostWithinSippGroupOperation;
import com.awg.j20.cartrawler.operation.SortBySippOperation;

@Component
public class MainWorkflow {
	private Logger logger = LoggerFactory.getLogger("MainWorkflow");
	@Autowired
	private DuplicateFilterOperation dubFilter;
	@Autowired
	private CorporateDividerOperation corpDivOperation;
	@Autowired
	private SortBySippOperation sippSorterOperation;
	@Autowired
	private SortByCostWithinSippGroupOperation costInGroupSorterOperation;
	
	public void executeAllOperationsFlow() {
		HardcodedCarSource carSource = new HardcodedCarSource();
		logger.info("Initial DataSet contains:" + carSource.carResults().size() + " entries.");
		
		CarUniqueContainer uc = dubFilter.filterDublicates(carSource.carResults());
		logger.info("Unique cars DataSet contains:" + uc.uniqueSize() + " entries.");
		
		CarGroupedByCorpContainer carGrouped = corpDivOperation.groupCorporates(uc.listAllOrdered());
		
		logger.info("CarGrouped Corp size:" + carGrouped.sizeCorporateCar() + " entries.");
		logger.info("CarGrouped NonCorp size:" + carGrouped.sizeNonCorporateCars() + " entries.");
		logger.info("CarGrouped All size:" + carGrouped.sizeAll() + " entries.");
		
		Display display = new Display();
		
		display.render(carGrouped.consolidatedCorporateFirst(), "NON-SORTED. GROUPPED.");		
		//sorting by groups:
		CarGroupedByCorpContainer carGroupedSortedBySipp = new CarGroupedByCorpContainer();
		carGroupedSortedBySipp
			.withCorporateCars(sippSorterOperation.sortBySipp(carGrouped.listAllCorporateCars()))
			.withNonCorporateCars(sippSorterOperation.sortBySipp(carGrouped.listAllNonCorporateCars()));
		display.render(carGroupedSortedBySipp.consolidatedCorporateFirst(), "SORTED BY SIPP");
		//sorting by cost in groups:
		CarGroupedByCorpContainer carSortedByCostInGroups = new CarGroupedByCorpContainer();
		carSortedByCostInGroups
				.withCorporateCars(costInGroupSorterOperation.sortByCost(carGroupedSortedBySipp.listAllCorporateCars()))
				.withNonCorporateCars(costInGroupSorterOperation.sortByCost(carGroupedSortedBySipp.listAllNonCorporateCars()));
		display.render(carGroupedSortedBySipp.consolidatedCorporateFirst(), "SORTED BY COST IN GROUPS");
	}
}
