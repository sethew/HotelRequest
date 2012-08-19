package hotelrequest

class UserController {
	static scaffold = true

	def admin() {
		[users : User.list()]
	}

	def save() {
		def user = new User(userId:1, addr1:"", addr2:"", city:"", email:params.email, country:"", firstName:"", lastName:"", passwdHash:"", postalCode:"", state:"")
		if (!user.save()) {
			if(user.errors.hasFieldErrors("email")) {
				render(text:message(error:user.errors.getFieldError()), status:400)
			}
			else {
				render(text:"Unable to create user.", status:400)
			}
		}
		else {
			render "Created user: ${user.email}"
		}
	}

	def index() {
	}
}
