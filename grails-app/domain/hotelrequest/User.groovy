package hotelrequest

class User {

	static constraints = {
		email(email:true, blank:false)
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
	String passwdHash
	int customerClass
	boolean current
}

