package com.parking.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.parking.domain.VehicleInfo;
import com.parking.domain.ParkingLot;
import com.parking.domain.ParkingTicket;
import com.parking.exception.ParkingServiceException;

@RunWith(MockitoJUnitRunner.class)
public class ParkingServiceImplTest {

	private ParkingLot parkingLot;
	private List<ParkingTicket> parkingTicketList;
	@InjectMocks
	private ParkingServiceImpl parkingLotService = new ParkingServiceImpl();
	
	
	@Before
	public void createMocks() {
		parkingTicketList = new ArrayList<>();
		parkingTicketList.add(new ParkingTicket("1","Allocated slot number: ", new VehicleInfo("KA­01­HH­1234", "White")));
		parkingTicketList.add(new ParkingTicket("2","Allocated slot number: ", new VehicleInfo("KA­01­HH­8055", "Red")));
		parkingTicketList.add(new ParkingTicket("3","Allocated slot number: ", new VehicleInfo("KA­01­HH­1235", "White")));
		parkingLotService.setparkingTicketList(parkingTicketList);
		parkingLot = new ParkingLot();
		parkingLot.setTotalSlot(4);
		Map<Integer,String> parkingMap = new HashMap<Integer, String>();
		for(int index=1; index <= 4; index++){
			parkingMap.put(index, "available");
		}
		parkingLot.setSlotAvailabilityMap(parkingMap);
		parkingLotService.setParkingLot(parkingLot);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetParking() throws ParkingServiceException {
		
		ParkingTicket result = parkingLotService.getParking("park KA\u00AD01\u00ADHH\u00AD3141 Black");
		Assert.assertEquals("1", result.getSlotNumber());
	}
	
	@Test
	public void createParkingLotTest() throws ParkingServiceException {
		ParkingLot result = parkingLotService.createParkingLot("create_parking_lot 7");
		Assert.assertEquals(7, result.getTotalSlot());
	}

	@Test
	public void TestFindSlotNumberByColour() throws ParkingServiceException {
		String result = parkingLotService.findSlotNumberByColour("slot_numbers_for_cars_with_colour White");
		Assert.assertEquals("1,3", result);
	}

	@Test
	public void testFindSlotNumberByRegistrationNumber() throws ParkingServiceException {
		
		String result = parkingLotService.findSlotNumberByRegistrationNumber("slot_number_for_registration_number KA­01­HH­8055");
		Assert.assertEquals("2", result);
	}

	@Test
	public void testFindRegistrationNumberByColour() throws ParkingServiceException {
		
		String result = parkingLotService.findRegistrationNumberByColour("registration_numbers_for_cars_with_colour White");
		Assert.assertEquals("KA­01­HH­1234,KA­01­HH­1235", result);
	}

	@Test
	public void testDisplayStatus() {
		parkingLotService.displayStatus();
	}

	@Test
	public void testLeaveParking() throws ParkingServiceException {
		ParkingTicket result = parkingLotService.leaveParking("leave 1");
		Assert.assertEquals("1", result.getSlotNumber());
	}

}
