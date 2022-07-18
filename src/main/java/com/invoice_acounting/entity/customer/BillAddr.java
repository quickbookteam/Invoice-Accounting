package com.invoice_acounting.entity.customer;


public class BillAddr {

	private Long id;
	private String city ;
	private String line1 ;
	private String postalCode ;
	private String lat ;
	private String _long;
	private String countrySubDivisionCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String get_long() {
		return _long;
	}
	public void set_long(String _long) {
		this._long = _long;
	}
	public String getCountrySubDivisionCode() {
		return countrySubDivisionCode;
	}
	public void setCountrySubDivisionCode(String countrySubDivisionCode) {
		this.countrySubDivisionCode = countrySubDivisionCode;
	}
	

}
