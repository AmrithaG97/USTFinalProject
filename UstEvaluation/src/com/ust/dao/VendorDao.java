package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ust.model.Login;

import com.ust.model.VendorPerson;

public class VendorDao {

	JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	
	//verify login
	public Login validate(String userId, String userPassword) {
		String sql = "select userId,userPassword from LoginTable where userId='"
				+ userId + "' and userPassword='" + userPassword + "'";
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<Login>(Login.class));
	}


	// Retrieving all vendors
	public List<VendorPerson> getAllVendor() {
		return template.query("select vendorId,vendorName,vendorAddress,vendorLocation,vendorService,vendorPinCode from vendorTable where vendorIsActive=1",
				new RowMapper<VendorPerson>() 
		 {
			public VendorPerson mapRow(ResultSet rs, int row) throws SQLException {
				VendorPerson vendorPerson = new VendorPerson();
				vendorPerson.setVendorId(rs.getInt(1));
				vendorPerson.setVendorName(rs.getString(2));
				vendorPerson.setVendorAddress(rs.getString(3));
				vendorPerson.setVendorLocation(rs.getString(4));
				vendorPerson.setVendorService(rs.getString(5));
				vendorPerson.setVendorPinCode(rs.getInt(6));
//				vendorPerson.setVendorIsActive(rs.getInt(7));
				return vendorPerson;
			}
		});
	}

	
	// Retrieving vendors by name
		public List<VendorPerson> getVendorByName(String vendorName) {
			return template.query("select vendorId,vendorName,vendorAddress,vendorLocation,vendorService,vendorPinCode,vendorIsActive from vendorTable"
					+ " where vendorIsActive=1 and vendorName='"+vendorName+"'",new RowMapper<VendorPerson>() {
				public VendorPerson mapRow(ResultSet rs, int row) throws SQLException {
					VendorPerson vendorPerson = new VendorPerson();
					vendorPerson.setVendorId(rs.getInt(1));
					vendorPerson.setVendorName(rs.getString(2));
					vendorPerson.setVendorAddress(rs.getString(3));
					vendorPerson.setVendorLocation(rs.getString(4));
					vendorPerson.setVendorService(rs.getString(5));
					vendorPerson.setVendorPinCode(rs.getInt(6));
//					vendorPerson.setVendorIsActive(rs.getInt(7));
					return vendorPerson;
				}
			});
		}

		

		// Inserting vendor and contactPerson details
		public int insertVendorAndPerson(VendorPerson vendorperson) {
			String sql1 = "insert into vendorTable(vendorName,vendorAddress,vendorLocation,vendorService,vendorPinCode,vendorIsActive) values('"
					+ vendorperson.getVendorName()
					+ "','"
					+ vendorperson.getVendorAddress()
					+ "','"
					+ vendorperson.getVendorLocation()
					+ "','"
					+ vendorperson.getVendorService()
					+ "',"
					+ vendorperson.getVendorPinCode() + ",1)";
			template.update(sql1);

			Integer maxVendorId = getSequence();

			String sql2 = "insert into contactPersonTable(contactName,vendorId,department,contactEmail,contactPhone,contactIsActive) values('"
					+ vendorperson.getContactName()
					+ "',"
					+ maxVendorId
					+ ",'"
					+ vendorperson.getDepartment()
					+ "','"
					+ vendorperson.getContactEmail()
					+ "','"
					+ vendorperson.getContactPhone() + "',1)";
			return template.update(sql2);

		}

		// new vendorId
		private Integer getSequence() {
			Integer seq;
			String sql = "select MAX(vendorId)from vendorTable";
			seq = template.queryForObject(sql, new Object[] {}, Integer.class);
			return seq;
		}

		
		// Updating existing VendorPerson
				public int updateVendorPerson(int vendorId,VendorPerson vendorPerson) {
					String sql1 = "update vendorTable set vendorName = '"
							+ vendorPerson.getVendorName() + "'," + "vendorAddress = '"
							+ vendorPerson.getVendorAddress() + "',vendorLocation = '"
							+ vendorPerson.getVendorLocation() + "'," + "vendorService = '"
							+ vendorPerson.getVendorService() + "',vendorPinCode = "
							+ vendorPerson.getVendorPinCode() + ",vendorIsActive = 1"
							+ " where vendorId="
							+ vendorId;
					 template.update(sql1);
					 
					 String sql2="update contactPersonTable set vendorId="+vendorId+",contactName='" +vendorPerson.getContactName()
					+"',department='"+vendorPerson.getDepartment()+"',contactEmail='"
							 +vendorPerson.getContactEmail()+"',contactPhone='"+vendorPerson.getContactPhone()+"' where contactId="+vendorPerson.getContactId();
						return template.update(sql2);
				}
				
		//-----------------------Retrieving contactDetails by vendorId
				
		public List<VendorPerson> getContactById(int vendorId) {
			String sql = "select contactId,contactName,vendorId,department,contactEmail,contactPhone from contactPersonTable where vendorId="+vendorId+"";
					return template.query(sql, new RowMapper<VendorPerson>() {
						public VendorPerson mapRow(ResultSet rs, int row)
								throws SQLException {
							VendorPerson vendorPerson = new VendorPerson();
							vendorPerson.setContactId(rs.getInt(1));
							vendorPerson.setContactName(rs.getString(2));
							vendorPerson.setVendorId(rs.getInt(3));
							vendorPerson.setDepartment(rs.getString(4));
							vendorPerson.setContactEmail(rs.getString(5));
							vendorPerson.setContactPhone(rs.getString(6));
						//	vendorPerson.setContactIsActive(rs.getInt(7));
							return vendorPerson;
						}
					});
				}
		
		// disable vendor
		public int disableVendor(int vendorId) {

			String sql = "update vendorTable set vendorIsActive=0 where vendorId="
					+ vendorId;

			return template.update(sql);
		}
			
		
		//Retrieving by id
		public VendorPerson getVendorById(int vendorId){
			String sql = "select v1.vendorId,v1.vendorName,v1.vendorAddress,v1.vendorLocation,v1.vendorService,v1.vendorPinCode,c1.contactId,c1.contactName,c1.department,"
					+ "c1.contactEmail,c1.contactPhone from vendorTable v1 join contactPersonTable c1 on v1.vendorId=c1.vendorId where v1.vendorIsActive= 1 and v1.vendorId="+vendorId+"";
			return template.queryForObject(sql,new Object[] {},new BeanPropertyRowMapper<VendorPerson>(VendorPerson.class));
		}
	
//
//	// Inserting new vendor
//	public int insertVendor(Vendor vendor) {
//		String sql = "insert into vendorTable(vendorName,vendorAddress,vendorLocation,vendorService,vendorPinCode,vendorIsActive) values('"
//				+ vendor.getVendorName()
//				+ "','"
//				+ vendor.getVendorAddress()
//				+ "','"
//				+ vendor.getVendorLocation()
//				+ "','"
//				+ vendor.getVendorService()
//				+ "',"
//				+ vendor.getVendorPinCode()
//				+ ",1)";
//		return template.update(sql);
//	}

//	// Updating existing vendor
//	public int updateVendor(Vendor vendor) {
//		String sql = "update vendorTable set vendorName = '"
//				+ vendor.getVendorName() + "'," + "vendorAddress = '"
//				+ vendor.getVendorAddress() + "',vendorLocation = '"
//				+ vendor.getVendorLocation() + "'," + "vendorService = '"
//				+ vendor.getVendorService() + "',vendorPinCode = "
//				+ vendor.getVendorPinCode() + " where vendorId="
//				+ vendor.getVendorId();
//		return template.update(sql);
//	}

	

//	// ------------------vendor and contact person insertion
//	//Retrieving all
//	public VendorPerson getAllVendorPerson(int vendorId) {
//		String sql = "select * from vendorTable join contactPersonTable on vendorTable.vendorId=contactPersonTable.vendorId where vendorTable.vendorId=?";
//		return template.queryForObject(sql,new Object[] {},new BeanPropertyRowMapper<VendorPerson>(VendorPerson.class));
//	}

	
	
	
}
