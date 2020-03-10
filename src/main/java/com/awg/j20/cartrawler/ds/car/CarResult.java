package com.awg.j20.cartrawler.ds.car;

public class CarResult {
    private final String description;
    private final String supplierName;
    private final String sippCode;
    private final double rentalCost;
    private final FuelPolicy fuelPolicy;
    
	public enum FuelPolicy {
        FULLFULL,
        FULLEMPTY};
    
    public CarResult(String description, String supplierName, String sipp, double cost, FuelPolicy fuelPolicy) {
        this.description = description;
        this.supplierName = supplierName;
        this.sippCode = sipp;
        this.rentalCost = cost;
        this.fuelPolicy = fuelPolicy;
    }
    
    public String getDescription() {
        return this.description;        
    }
    
    public String getSupplierName() {
        return this.supplierName;        
    }
    
    public String getSippCode() {
        return this.sippCode;        
    }
    
    public String getSippCodeType() {
    	return this.sippCode.substring(0, 1);
    }
    
    public double getRentalCost() {
        return this.rentalCost;        
    }
    
    public FuelPolicy getFuelPolicy() {
        return this.fuelPolicy;
    }
    
    public String toString() {
        return this.supplierName + " : " +
            this.description + " : " +
            this.sippCode + " : " +
            this.rentalCost + " : " +
            this.fuelPolicy;
    }
   
    /*
    
    @Override
    public boolean equals(Object obj) {
    	if(obj == this) {
    		return true;
    	}
    	if (obj == null || getClass() != obj.getClass()) return false;
    	CarResult other = (CarResult)obj;
    	return this.getDescription().equals(other.getDescription())
    			&& this.getSupplierName().equals(other.getSupplierName())
    			&& this.getSippCode().equals(other.getSippCode())
    			&& this.getFuelPolicy().equals(other.getFuelPolicy());
    			//&& this.rentalCost == other.rentalCost;
    }
    
    @Override
    public int hashCode() {
           final int prime = 31;
           int result = 1;
           result = prime * result + (description == null ? 0 : description.hashCode());
           result = prime * result +  (supplierName == null ? 0 : supplierName.hashCode());
           result = prime * result + (sippCode == null ? 0 : sippCode.hashCode());
           result = prime * result + (fuelPolicy == null ? 0 : fuelPolicy.hashCode());
          // result = prime * result + (int) rentalCost;
           return result;
    }
    
    */
    
}
