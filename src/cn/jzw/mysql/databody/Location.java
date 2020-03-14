package cn.jzw.mysql.databody;

public class Location {
	private String country;
	private String province;
	private String city;
	private String street;
	private String fullLocation;
	
	public Location(String full) {
		// TODO Auto-generated constructor stub
		this.fullLocation=full;
		
	}
	public Location(String count,String pro,String cit,String stre) {
		// TODO Auto-generated constructor stub
		this.country=count;
		this.province=count;
		this.city=cit;
		this.street=stre;
	}
	
	public String getCountry() {
		return country;
	}
	public String getFullLocation() {
		return fullLocation;
	}
	public void setFullLocation(String fullLocation) {
		this.fullLocation = fullLocation;
	}
	public void setCountry(String country) {
		this.country = country;
	}



	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	@Override
	public String toString() {
		return "Location [country=" + country + ", province=" + province + ", city=" + city + ", street=" + street
				+ ", fullLocation=" + fullLocation + "]";
	}

	
	
}
