package hotelrequest

class User {

	static constraints = {
		email(email:true, blank:false, unique: true)
		lastName(blank:false)
		firstName(blank:false)
		addr1(blank:false)
		addr2(blank:false)
		city(blank:false)
		state(blank:false)
		postalCode(blank:false)
		country(blank:false)
		email(blank:false)
		phone(blank:false)
		nullable:true
	}

	int userId
	String firstName
	String lastName
	String addr1
	String addr2
	String city
	String state
	String postalCode
	String country
	String email
	String phone
	String passwdHash
	int customerClass
	boolean current
}

