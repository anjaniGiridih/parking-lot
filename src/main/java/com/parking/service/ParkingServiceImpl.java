package com.parking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.parking.domain.VehicleInfo;
import com.parking.domain.ParkingLot;
import com.parking.domain.ParkingTicket;
import com.parking.exception.ParkingServiceException;

public class ParkingServiceImpl implements ParkingService {

	private ParkingLot parkingLot;
	private List<ParkingTicket> parkingTicketList = new ArrayList<ParkingTicket>();
	
	public ParkingLot createParkingLot(String inputSlot) throws ParkingServiceException {
		String [] value = inputSlot.split(" ");
		if(!("create_parking_lot".equalsIgnoreCase(value[0]))){
			throw new ParkingServiceException("Invalid command");
		}
		parkingLot = new ParkingLot();
		parkingLot.setTotalSlot(Integer.parseInt(value[1]));
		Map<Integer,String> parkingMap = new HashMap<Integer, String>();
		for(int index=1; index <= Integer.parseInt(value[1]); index++){
			
			parkingMap.put(index, "available");
		}
		parkingLot.setSlotAvailabilityMap(parkingMap);
		return parkingLot;
	}

	public ParkingTicket getParking(String inputValue) throws ParkingServiceException{
		ParkingTicket parkingTicket = new ParkingTicket();
		String [] values = inputValue.split(" ");
		if(!("park".equalsIgnoreCase(values[0]))){
			throw new ParkingServiceException("Invalid command");
		}
		String colour = values[2];
		String regNumber=values[1];
		int index = 1;
		for(; index <= parkingLot.getTotalSlot(); index++){
			if(parkingLot.getSlotAvailabilityMap().containsKey(index) && (parkingLot.getSlotAvailabilityMap().get(index)==null || parkingLot.getSlotAvailabilityMap().get(index)=="" || parkingLot.getSlotAvailabilityMap().get(index).equalsIgnoreCase("Not Available"))){
				continue;
			}
			parkingTicket.setSlotNumber(String.valueOf(index));
			parkingLot.getSlotAvailabilityMap().put(index, "Not Available");
			break;
		}
		if(index == parkingLot.getTotalSlot()+1){
			parkingTicket.setSlotNumber(null);
			parkingTicket.setMessage("Sorry, parking lot is full");
			parkingTicket.setVehicleInfo(null);
			return parkingTicket;
		}
		VehicleInfo carInfo = new VehicleInfo();
		carInfo.setColour(colour);
		carInfo.setRegistrationNumber(regNumber);
		parkingTicket.setMessage("Allocated slot number: ");
		parkingTicket.setVehicleInfo(carInfo);
		parkingTicketList.add(parkingTicket);
		return parkingTicket;
	}
	
	public String findRegistrationNumberByColour(String inputValue) throws ParkingServiceException{
		String [] values = inputValue.split(" ");
		String registrationNumberString="";
		if(!("registration_numbers_for_cars_with_colour".equalsIgnoreCase(values[0]))){
			throw new ParkingServiceException("Invalid command");
		}
		for(ParkingTicket parkingTicket: parkingTicketList){
			if(parkingTicket.getVehicleInfo().getColour().equalsIgnoreCase(values[1])){
				registrationNumberString += parkingTicket.getVehicleInfo().getRegistrationNumber()+",";
			}
		}
		if(registrationNumberString == ""){
			return registrationNumberString+"Not matched";
		}
		return registrationNumberString.substring(0, registrationNumberString.length()-1);
	}
	
	public String findSlotNumberByColour(String inputValue) throws ParkingServiceException{
		String [] values = inputValue.split(" ");
		String slotNumberString="";
		if(!("slot_numbers_for_cars_with_colour".equalsIgnoreCase(values[0]))){
			throw new ParkingServiceException("Invalid command");
		}
		for(ParkingTicket parkingTicket: parkingTicketList){
			if(parkingTicket.getVehicleInfo().getColour().equalsIgnoreCase(values[1])){
				slotNumberString += parkingTicket.getSlotNumber()+",";
			}
		}
		if(slotNumberString == ""){
			return slotNumberString+"Not matched";
		}
		return slotNumberString.substring(0, slotNumberString.length()-1);
	}
	
	public String findSlotNumberByRegistrationNumber(String inputValue) throws ParkingServiceException{
		String [] values = inputValue.split(" ");
		if(!("slot_number_for_registration_number".equalsIgnoreCase(values[0]))){
			throw new ParkingServiceException("Invalid command");
		}
		for(ParkingTicket parkingTicket: parkingTicketList){
			String regNoString = parkingTicket.getVehicleInfo().getRegistrationNumber().trim().toString();
			String value=values[1].trim().toString();
			if((regNoString).equals(value)){
				return parkingTicket.getSlotNumber();
			}
		}
		return "Not present";
	}
	
	public void displayStatus(){
		System.out.println("Output:");
		System.out.println("Slot No. \t Registration No \t Colour");
		for(ParkingTicket parkingTicket: parkingTicketList){
			if(parkingTicket.getSlotNumber()!=null){
				System.out.println(parkingTicket.getSlotNumber()+" \t "+parkingTicket.getVehicleInfo().getRegistrationNumber()+" \t "+parkingTicket.getVehicleInfo().getColour());
			}
			
		}
	}
	
	public ParkingTicket leaveParking(String inputValue) throws ParkingServiceException{
		String [] values = inputValue.split(" ");
		if(!("leave".equalsIgnoreCase(values[0]))){
			throw new ParkingServiceException("Invalid command");
		}
		for(ParkingTicket parkingTicket: parkingTicketList){
			if(parkingTicket.getSlotNumber() == values[1]){
				ParkingTicket emptyParkingTicket = parkingTicket; 
				parkingTicket.setMessage(null);
				parkingTicket.setSlotNumber(null);
				parkingTicket.setVehicleInfo(null);
				return emptyParkingTicket;
			}
		}
		ParkingTicket emptyParkingTicket = new ParkingTicket();
		emptyParkingTicket.setSlotNumber(values[1]);
		return emptyParkingTicket;
	}
	public void setparkingTicketList(List<ParkingTicket> parkingTicketList){
		this.parkingTicketList = parkingTicketList;
	}
	
	public void setParkingLot(ParkingLot parkingLot){
		this.parkingLot=parkingLot;
	}
}
