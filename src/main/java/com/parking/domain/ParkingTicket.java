package com.parking.domain;

public class ParkingTicket {

//	private String ;
	private String slotNumber;
	private String message;
	private VehicleInfo vehicleInfo;
	public ParkingTicket() {
		// TODO Auto-generated constructor stub
	}
	public ParkingTicket(String slotNumber, String message, VehicleInfo carInfo) {
		super();
		this.slotNumber = slotNumber;
		this.message = message;
		this.vehicleInfo = carInfo;
	}
	public String getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}
	public void setVehicleInfo(VehicleInfo carInfo) {
		this.vehicleInfo = carInfo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehicleInfo == null) ? 0 : vehicleInfo.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((slotNumber == null) ? 0 : slotNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingTicket other = (ParkingTicket) obj;
		if (vehicleInfo == null) {
			if (other.vehicleInfo != null)
				return false;
		} else if (!vehicleInfo.equals(other.vehicleInfo))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (slotNumber == null) {
			if (other.slotNumber != null)
				return false;
		} else if (!slotNumber.equals(other.slotNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParkingTicket [slotNumber=");
		builder.append(slotNumber);
		builder.append(", message=");
		builder.append(message);
		builder.append(", carInfo=");
		builder.append(vehicleInfo);
		builder.append("]");
		return builder.toString();
	}
}
