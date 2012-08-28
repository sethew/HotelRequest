package hotelrequest

import grails.test.mixin.*
import grails.test.mixin.support.*

import javax.servlet.http.HttpServletResponse


/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerTests {
	User user
	UserController uc
	
	void setUp() {
		user = new User(email: "foo@foo.com", firstName:"connie", lastName:"robot", addr1:"123 Somestreet", addr2:"apt2", city:"Mpls", state:"MN", country:"USA", phone:"555-555-5555",postalCode:"55555")
		if (!user.save()) {
			if(user.errors.hasFieldErrors("email")) {
				println("Setup Create Failed")
			} else {
				println("Setup Create Completed")
			}
		}
		// Setup logic here
		//uc = new UserController()
	}

	void tearDown() {
		// Tear down logic here
		user.delete()
	}


	
	void testLogin() {
		user = new User(email: "foo@foo.com", firstName:"connie", lastName:"robot", addr1:"123 Somestreet", addr2:"apt2", city:"Mpls", state:"MN", country:"USA", phone:"555-555-5555",postalCode:"55555")
		if (!user.save()) {
			if(user.errors.hasFieldErrors("email")) {
				println("Setup Create Failed")
			} else {
				println("Setup Create Completed")
			}
		}
		controller.params.email = user.email
		controller.handleLogin()
		
		def sessUser = controller.session.user
		assert sessUser
		assertEquals("Expected ids to match",user.id, sessUser.id)
		
		user.delete()
	}
}
