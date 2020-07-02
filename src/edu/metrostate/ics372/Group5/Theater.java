package edu.metrostate.ics372.Group5;

/**
 * @author Anthony
 *
 */
public class Theater {

    private static CustomerList customerList;
    private static Theater theater;

    /**
     * Private for the singleton pattern Creates the catalog and member collection
     * objects
     */
    private Theater() {
	customerList = CustomerList.instance();
    }

    public Customer

	    addCustomer(String name, String address, String phone, String creditCard, String expiray) {

    }

}
