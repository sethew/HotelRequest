package hotelrequest

class UserController {
	static scaffold = false

	def admin() {
		[users : User.list()]
	}

	def save() {
		def user = new User( addr1:params.addr1, addr2:params.addr2, city:params.city, email:params.email, country:params.country, firstName:params.firstName, lastName:params.lastName, phone:params.phone, postalCode:params.postalCode, state:params.state, password:params.password, enabled: true)
		if (!user.save()) {
			if(user.errors.hasFieldErrors()) {
				render(text:message(error:user.errors.getFieldError()), status:400)
			}
			else {
				render(text:"Unable to create user." + message(error:user.errors.getFieldError()), status:400)
			}
		}
		else {
			session.user = user
			render "Created user: ${session.user.email}"
			
		}
	}
	
	def update() {
		if(params.id == session.user.id.toString()){
			// Update Okay, ID matches session value, the next two lines are a slick way of updating the 
			// an object from the params
			def user = User.findById(params.id)
			user.properties = params
			
			if (!user.save()) {
				render(view: "edit", model: [userInstance: user], status:400) 
			} else {
				// Save worked, make sure to update session and pass the model
				session.user = user
				render(view: "edit", model: [userInstance: user], status: 200)
			}
		} else {
			render(text:"Security Violation:" + params.id + ":" +session.user.id,status:400)
		}
	}
	
	def login() {
		render(view: "login")
	}
	
	def handleLogin() {
		User user = User.findByEmail(params.email)
			log.error(user)
		if(user == null || !user) {
			render(text:"Unable to find user." + params.email, status:400)
		} else {
			session.user = user
			render(view: "whoami")
		}
	}
	
	def edit() {
		User userFromSes = session.user
		render(view: "edit", model: [userInstance: userFromSes])
	}
	
	def whoami() {
		render(view: "whoami")
	}
	

	def index() {
	}
}
