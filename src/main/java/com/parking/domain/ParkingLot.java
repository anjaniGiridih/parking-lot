package com.parking.domain;

import java.util.Map;

public class ParkingLot {
	private int totalSlot;
	private Map<Integer, String> slotAvailabilityMap;

	public ParkingLot() {
		// TODO Auto-generated constructor stub
	}
	
	public ParkingLot(int totalSlot, Map<Integer, String> slotAvailabilityMap) {
		super();
		this.totalSlot = totalSlot;
		this.slotAvailabilityMap = slotAvailabilityMap;
	}

	public int getTotalSlot() {
		return totalSlot;
	}

	public void setTotalSlot(int totalSlot) {
		this.totalSlot = totalSlot;
	}

	public Map<Integer, String> getSlotAvailabilityMap() {
		return slotAvailabilityMap;
	}

	public void setSlotAvailabilityMap(Map<Integer, String> slotAvailabilityMap) {
		this.slotAvailabilityMap = slotAvailabilityMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((slotAvailabilityMap == null) ? 0 : slotAvailabilityMap.hashCode());
		result = prime * result + totalSlot;
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
		ParkingLot other = (ParkingLot) obj;
		if (slotAvailabilityMap == null) {
			if (other.slotAvailabilityMap != null)
				return false;
		} else if (!slotAvailabilityMap.equals(other.slotAvailabilityMap))
			return false;
		if (totalSlot != other.totalSlot)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParkingLot [totalSlot=").append(totalSlot).append(", slotAvailabilityMap=").append(slotAvailabilityMap).append("]");
		return builder.toString();
	}
}
