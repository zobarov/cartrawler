package com.awg.j20.cartrawler.operation;

/**
 * Enumerates all registered car suppliers.
 * Divided in two groups corporate and non-corporate.
 */
public enum Suppliers {
	
	AVIS(true),
	BUDGET(true),
	ENTERPRISE(true),
	FIREFLY(true),
	HERTZ(true),
	SIXT(true),
	THRIFTY(true),
	//
	MARBESOL(false),
	GOLDCAR(false),
	NIZA(false),
	//special case:
	UNREGISTERED(false);
	
	private boolean isCorporate;
	
	private Suppliers(boolean corporateFlag) {
		this.isCorporate = corporateFlag;
	}
	
	public static Suppliers findByName(String name) {
		if(name == null || name.isEmpty()) {
			return UNREGISTERED;
		}
		
		for(Suppliers s : values()) {
			if(name.equalsIgnoreCase(s.name())) {
				return s;
			}
		}
		return UNREGISTERED;	
	}
	
	public boolean isRegistered() {
		return !this.equals(UNREGISTERED);
	}
	
	public boolean isCorporate() {
		return isCorporate;
	}
}
