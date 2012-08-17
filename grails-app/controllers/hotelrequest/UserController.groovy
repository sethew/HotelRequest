package hotelrequest

class UserController {
	static scaffold = true

	def index() {
		[users : User.list()]
	}

	def save() {
		def user = new User(userId:1, addr1:"", addr2:"", city:"", email:params.email, country:"", firstName:"", lastName:"", passwdHash:"", postalCode:"", state:"")
		if (!user.save(validate:false)) {
			user.errors.each { println(it) }
			render "Unable to create user."
			return
		}
		render "Created user: ${user.email}"
	}
}
