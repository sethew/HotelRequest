package hotelrequest

class UserController {
	static scaffold = true

	def admin() {
		[users : User.list()]
	}

	def save() {
		def user = new User( addr1:params.addr1, addr2:params.addr2, city:params.city, email:params.email, country:params.country, firstName:params.firstName, lastName:params.lastName, passwdHash:"", phone:params.phone, postalCode:params.postalCode, state:params.state)
		if (!user.save()) {
			if(user.errors.hasFieldErrors("email")) {
				render(text:message(error:user.errors.getFieldError()), status:400)
			}
			else {
				
				render(text:"Unable to create user." + message(error:user.errors.getFieldError()), status:400)
			}
		}
		else {
			render "Created user: ${user.email}"
		}
	}

	def index() {
	}
}
