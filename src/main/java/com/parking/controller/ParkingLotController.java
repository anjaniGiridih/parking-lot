package com.parking.controller;
import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.parking.domain.ParkingLot;
import com.parking.domain.ParkingTicket;
import com.parking.exception.ParkingServiceException;
import com.parking.service.ParkingService;
import com.parking.service.ParkingServiceImpl;

public class ParkingLotController {
	

	 
	 private static ParkingService parkingLotService;
	 
	 public static void main(String[] args) throws IOException {
	  parkingLotService  = new ParkingServiceImpl();
	  if(args.length == 0){
	   interactiveMethod();
	  }
	  else if(args.length == 1){
	   inputFileMethod(args);
	  }
	 }

	 private static void interactiveMethod() throws IOException {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  while(true){
	   String input = br.readLine();
	   execute(input);
	  }
	 }

	 private static void inputFileMethod(String[] args) throws IOException {
	  BufferedReader br = new BufferedReader(new FileReader(args[0]));
	  String input;
	  while ((input = br.readLine()) != null) {
	   execute(input);
	  }
	  br.close();
	 }
	 
	 private static void execute(String inputValue) {
		 String serviceApiName = inputValue.split(" ")[0];
		try {
			System.out.println("ServiceName: "+serviceApiName);
			switch (serviceApiName) {
				case "create_parking_lot":
					ParkingLot parkingLot = parkingLotService.createParkingLot(inputValue);
					System.out.println("Created a parking lot with " + parkingLot.getTotalSlot() + " slots");
					
					break;
					
				case "park":
					ParkingTicket parkingTicket;
					parkingTicket = parkingLotService.getParking(inputValue);
					System.out.println(parkingTicket.getMessage() + parkingTicket.getSlotNumber());
					break;
				case "leave":
					parkingTicket = parkingLotService.leaveParking(inputValue);
					System.out.println("slot No " + parkingTicket.getSlotNumber() + " is free");
					break;
				case "status":
					parkingLotService.displayStatus();
					break;
				case "registration_numbers_for_cars_with_colour":
					System.out.println(parkingLotService.findRegistrationNumberByColour(inputValue));
					break;
				case "slot_numbers_for_cars_with_colour":
					System.out.println(parkingLotService.findSlotNumberByColour(inputValue));
					break;
				case "slot_number_for_registration_number":
					System.out.println(parkingLotService.findSlotNumberByRegistrationNumber(inputValue));
					break;
				case "exit":
					exit(0);

				default:
					System.out.println("Not a suitable input value");
			}
		} catch (ParkingServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
