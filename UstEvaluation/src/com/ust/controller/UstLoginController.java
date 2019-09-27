package com.ust.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.ust.dao.LoginDao;
import com.ust.dao.VendorDao;

import com.ust.model.Login;

import com.ust.model.VendorPerson;

@RestController
public class UstLoginController {

	@Autowired
	LoginDao ldao;

	@Autowired
	VendorDao vdao;


	// verify login
	@RequestMapping(value = "/api/login/{userId}/{userpassword}", method = RequestMethod.GET)
	@ResponseBody
	public Login selectUser(@PathVariable("userId") String userId,
			@PathVariable("userpassword") String userPassword) {
		return vdao.validate(userId, userPassword);
	}

	// -------------------------------------VENDOR-----------------------------------------

	// Retrieving all vendors who are active
	@RequestMapping(value = "/api/vendors/selectallVendors/{vendorName}", method = RequestMethod.GET)
	@ResponseBody
	public List selectAllVendor(Model m,@PathVariable("vendorName") String vendorName) {
		List list ;
		if(vendorName.equals("null")) {
			list = vdao.getAllVendor();
		}
		else
		{
			list = vdao.getVendorByName(vendorName);
		}
		return list;
		
	}

	// Insert new vendor
		@RequestMapping(value = "/api/vendors/insert", method = {
				RequestMethod.POST, RequestMethod.GET })
		public void saveVendor(@RequestBody VendorPerson vendorPerson) throws ParseException {

			vdao.insertVendorAndPerson(vendorPerson);
		}
	
	// update VendorPerson details
	@RequestMapping(value = "/api/VendorPerson/update", method = {
			RequestMethod.PUT, RequestMethod.GET })
	public void updateVendorPerson(@RequestBody VendorPerson vendorPerson)throws ParseException {
		int vendorId=vendorPerson.getVendorId();
		vdao.updateVendorPerson(vendorId,vendorPerson);
	}
	
	// disable vendor
		@RequestMapping(value = "/api/vendors/disable/{vendorId}", method = {
				RequestMethod.PUT, RequestMethod.GET })
		@ResponseBody
		public void disableVendor(@PathVariable("vendorId") int vendorId) {
			vdao.disableVendor(vendorId);
		}
		
	//Retrieve contact by vendorId
		@RequestMapping(value = "/api/vendors/contactDetails/{id}", method = RequestMethod.GET)
		@ResponseBody
		public List getContactById(Model m,@PathVariable("id") int vendorId) {
			List list;
			list = vdao.getContactById(vendorId);
			return list;
		}
		
	// Retrieve vendor by id
	@RequestMapping(value = "/api/vendors/selectByid/{id}", method = RequestMethod.GET)
	@ResponseBody
	public VendorPerson findVendorById(Model m,@PathVariable("id") int vendorId) {
		return vdao.getVendorById(vendorId);
	}

	

//	// update vendor details
//	@RequestMapping(value = "/api/vendors/update", method = {
//			RequestMethod.PUT, RequestMethod.GET })
//	public void updateVendor(@RequestBody Vendor vendor) {
//
//		vdao.updateVendor(vendor);
//	}

	
//
//	// ------------------------------Contactperson----------------------------------
//
//	// Retrieving all contact who are active and under that vendor
//	@RequestMapping(value = "/api/contactPerson/selectall/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public List<ContactPerson> selectAllPerson(Model m,@PathVariable("id") int vendorId) {
//		List list;
//		list = cpdao.getAllPerson(vendorId);
//		return list;
//	}
//
//	// Retrieve person by id
//	@RequestMapping(value = "/api/contactPerson/selectByid/{id}", method = RequestMethod.GET)
//	public ContactPerson findPersonById(@PathVariable("id") int contactId) {
//		return cpdao.selectPersonById(contactId);
//	}
//
//	// Insert new person
//	@RequestMapping(value = "/api/contactPerson/insert", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	public void savePerson(@RequestBody ContactPerson contact) {
//
//		cpdao.insertContact(contact);
//	}
//
//	// update person details
//	@RequestMapping(value = "/api/contactPerson/update", method = {
//			RequestMethod.PUT, RequestMethod.GET })
//	public void updatePerson(@RequestBody ContactPerson contact) {
//
//		cpdao.updatePerson(contact);
//	}
//
//	// disable person
//	@RequestMapping(value = "/api/contactPerson/disable/{contactId}", method = {
//			RequestMethod.PUT, RequestMethod.GET })
//	@ResponseBody
//	public void disablePerson(@PathVariable("contactId") int contactId) {
//		cpdao.disablePerson(contactId);
//	}
//
//	// ----------------------VENDOR AND CONTACT PERSON
//
//	//Retrieve all
//	@RequestMapping(value = "/api/vendorPerson/selectall/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public VendorPerson getAllVendorPerson(Model m,@PathVariable("id") int vendorId) {
//		VendorPerson vendorPerson= vdao.getAllVendorPerson(vendorId);
//		return vendorPerson;
//	}
//	
//	
//	// Inserting vendor and contactPerson details
//	@RequestMapping(value = "/api/VendorPerson/insert", method = {
//			RequestMethod.POST, RequestMethod.GET })
//	@ResponseBody
//	public void insertVendorAndPerson(@RequestBody VendorPerson vendorperson) {
//		vdao.insertVendorAndPerson(vendorperson);
//	}
//	
//	//Retrieve vendor by id
//	@RequestMapping(value = "/api/VendorPerson/{vendorId}", method = RequestMethod.GET )
//	@ResponseBody
//	public VendorPerson getVendorById(Model m,@PathVariable("vendorId") int vendorId) {
//		return vdao.getVendorById(vendorId);
//	}
//	
	
}
