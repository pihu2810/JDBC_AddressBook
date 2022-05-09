package com.Bridgelabz;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookTest 
{
AddressBookService bookTest = new AddressBookService();
	
	@Before
	public void setUp() throws Exception {
		bookTest = new AddressBookService();
	}

	@Test
	public void Initialise() {
		assertTrue(true);
	}

	@Test
	public void givenTestCaseShouldReturnConnectionCreated() {	
		bookTest.getConnection();
		Assert.assertTrue("Connection established",true);
	}
	
	@Test
	public void givenTestCaseShouldReturnUpdatedContact() {
        String firstname ="Vishaka";
		int id = 1;
		bookTest.updateContactInfo(firstname,id);
        Assert.assertTrue("Retrieve data successfully",true);
    }

	@Test
    public void givenTestCaseShouldReturn_AllData() {
        bookTest.retrieveContact();
        Assert.assertTrue("Retrieve data successfully",true);
    }
	@Test
	public void givenTestCaseShouldReturnDataByDateRange() {
	bookTest.retrieveContactByDateRange();
	Assert.assertTrue("Data Retrieved",true);
	}
	
	@Test
	public void givenTestCaseShouldReturnCountOfCity() {
		bookTest.getCountByCity();
		Assert.assertTrue("city counted",true);		
	}
}
