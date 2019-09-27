package com.ust.model;

public class VendorPerson {
	
		//initializing variables
		private int vendorId;
		private String vendorName;
		private String vendorAddress;
		private String vendorLocation;
		private String vendorService;
		private int vendorPinCode;
		private int vendorIsActive;
		private int contactId;
		private String contactName;
		private String department;
		private String contactEmail;
		private String contactPhone;
		private int contactIsActive;
		
		//getters and setters
		public int getVendorId() {
			return vendorId;
		}
		public void setVendorId(int vendorId) {
			this.vendorId = vendorId;
		}
		public String getVendorName() {
			return vendorName;
		}
		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}
		public String getVendorAddress() {
			return vendorAddress;
		}
		public void setVendorAddress(String vendorAddress) {
			this.vendorAddress = vendorAddress;
		}
		public String getVendorLocation() {
			return vendorLocation;
		}
		public void setVendorLocation(String vendorLocation) {
			this.vendorLocation = vendorLocation;
		}
		public String getVendorService() {
			return vendorService;
		}
		public void setVendorService(String vendorService) {
			this.vendorService = vendorService;
		}
		public int getVendorPinCode() {
			return vendorPinCode;
		}
		public void setVendorPinCode(int vendorPinCode) {
			this.vendorPinCode = vendorPinCode;
		}
		public int getVendorIsActive() {
			return vendorIsActive;
		}
		public void setVendorIsActive(int vendorIsActive) {
			this.vendorIsActive = vendorIsActive;
		}
		public int getContactId() {
			return contactId;
		}
		public void setContactId(int contactId) {
			this.contactId = contactId;
		}
		public String getContactName() {
			return contactName;
		}
		public void setContactName(String contactName) {
			this.contactName = contactName;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getContactEmail() {
			return contactEmail;
		}
		public void setContactEmail(String contactEmail) {
			this.contactEmail = contactEmail;
		}
		public String getContactPhone() {
			return contactPhone;
		}
		public void setContactPhone(String contactPhone) {
			this.contactPhone = contactPhone;
		}
		public int getContactIsActive() {
			return contactIsActive;
		}
		public void setContactIsActive(int contactIsActive) {
			this.contactIsActive = contactIsActive;
		}
		
		//constructors
		public VendorPerson() {
			super();
			// TODO Auto-generated constructor stub
		}
		public VendorPerson(int vendorId, String vendorName,
				String vendorAddress, String vendorLocation,
				String vendorService, int vendorPinCode, int vendorIsActive,
				int contactId, String contactName, String department,
				String contactEmail, String contactPhone, int contactIsActive) {
			super();
			this.vendorId = vendorId;
			this.vendorName = vendorName;
			this.vendorAddress = vendorAddress;
			this.vendorLocation = vendorLocation;
			this.vendorService = vendorService;
			this.vendorPinCode = vendorPinCode;
			this.vendorIsActive = vendorIsActive;
			this.contactId = contactId;
			this.contactName = contactName;
			this.department = department;
			this.contactEmail = contactEmail;
			this.contactPhone = contactPhone;
			this.contactIsActive = contactIsActive;
		}
		
		@Override
		public String toString() {
			return "VendorPerson [vendorId=" + vendorId + ", vendorName="
					+ vendorName + ", vendorAddress=" + vendorAddress
					+ ", vendorLocation=" + vendorLocation + ", vendorService="
					+ vendorService + ", vendorPinCode=" + vendorPinCode
					+ ", vendorIsActive=" + vendorIsActive + ", contactId="
					+ contactId + ", contactName=" + contactName
					+ ", department=" + department + ", contactEmail="
					+ contactEmail + ", contactPhone=" + contactPhone
					+ ", contactIsActive=" + contactIsActive + "]";
		}
		

}
