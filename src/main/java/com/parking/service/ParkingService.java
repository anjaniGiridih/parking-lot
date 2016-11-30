package com.parking.service;

import com.parking.domain.ParkingLot;
import com.parking.domain.ParkingTicket;
import com.parking.exception.ParkingServiceException;

public interface ParkingService {

	ParkingLot createParkingLot(String slotNumber) throws ParkingServiceException;
	ParkingTicket getParking(String inputValue) throws ParkingServiceException;
	String findRegistrationNumberByColour(String inputValue) throws ParkingServiceException;
	String findSlotNumberByColour(String inputValue) throws ParkingServiceException;
	String findSlotNumberByRegistrationNumber(String inputValue) throws ParkingServiceException;
	void displayStatus();
	ParkingTicket leaveParking(String inputValue) throws ParkingServiceException;
}
