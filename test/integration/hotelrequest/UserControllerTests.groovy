package hotelrequest

import grails.test.mixin.*
import grails.test.mixin.support.*

import javax.servlet.http.HttpServletResponse

class UserControllerTests extends GroovyTestCase {

	Map mockParams

	void setUp() {
		mockParams = [:]
		mockParams.lastName = "Joe"
		mockParams.firstName = "Face"
		mockParams.addr1 = "123 Fake St"
		mockParams.addr2 = "Apt 1"
		mockParams.city = "Vancouver"
		mockParams.state = "BC"
		mockParams.postalCode = "ABC123"
		mockParams.country = "Canada"
		mockParams.email = "seth@example.com"
		mockParams.phone = "123456789"
	}

	void tearDown() {
		// Tear down logic here
		//user.delete()
	}

	void testSave() {	
		def controller = new UserController()
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "Created user: seth@example.com"
		assert controller.response.status == HttpServletResponse.SC_OK
		assert User.count == 1
	}
	
	void testSaveWithErrorInvalidEmail() {
		def controller = new UserController()
		mockParams.email = "notanemail"
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "notanemail is not a valid email address"
		assert controller.response.status == 400
		assert User.count == 0
	}

	void testSaveWithErrorBlankFirstName() {
		def controller = new UserController()
		mockParams.firstName = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "firstName cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testSaveWithErrorBlankLastName() {
		def controller = new UserController()
		mockParams.lastName = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "lastName cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testSaveWithErrorBlankEmail() {
		def controller = new UserController()
		mockParams.email = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "email cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testSaveWithErrorBlankAddr1() {
		def controller = new UserController()
		mockParams.addr1 = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "addr1 cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testSaveWithErrorBlankAddr2() {
		def controller = new UserController()
		mockParams.addr2 = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "addr2 cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testSaveWithErrorBlankCity() {
		def controller = new UserController()
		mockParams.city = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "city cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}	
	
	void testSaveWithErrorBlankState() {
		def controller = new UserController()
		mockParams.state = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "state cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}	
	
	void testSaveWithErrorBlankPostalCode() {
		def controller = new UserController()
		mockParams.postalCode = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "postalCode cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testSaveWithErrorBlankCountry() {
		def controller = new UserController()
		mockParams.country = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "country cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testSaveWithErrorBlankPhone() {
		def controller = new UserController()
		mockParams.phone = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "phone cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testLogin() {
		def controller = new UserController()
	    controller.params.putAll(mockParams)
		controller.save()
		controller.handleLogin()
		
		assert controller.session
		def sessUser = controller.session.user
		assert sessUser
		assertEquals("Expected emails to match",controller.params.email, sessUser.email)
	}
}
