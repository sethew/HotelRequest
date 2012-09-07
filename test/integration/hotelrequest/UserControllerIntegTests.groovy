package hotelrequest

import grails.test.mixin.*
import grails.test.mixin.support.*

import javax.servlet.http.HttpServletResponse

class UserControllerIntegTests extends GroovyTestCase {

	Map mockParams

	void setUp() {
		mockParams = ["lastName":"Joe", "firstName":"Face", "addr1":"123 Fake St", "addr2":"Apt 1", "city":"Vancouver", "state":"BC", "postalCode":"ABC123", country:"Canada", email:"seth@convention-reg.com", phone:"123456789", password:"password"]
	}

	void tearDown() {
		// Tear down logic here
		//user.delete()
	}

	void testSave() {	
		def controller = new UserController()
		def sendMailCalled = false
		controller.metaClass.sendMail = { Closure c -> 
			sendMailCalled = true
		}
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "Created user: seth@convention-reg.com"
		assert controller.response.status == HttpServletResponse.SC_OK
		assert User.count == 1
		assert sendMailCalled
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
	
	void testSaveWithNotErrorBlankAddr2() {
		def controller = new UserController()
		def sendMailCalled = false
		controller.metaClass.sendMail = { Closure c ->
			sendMailCalled = true
		}
		mockParams.addr2 = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "Created user: seth@convention-reg.com"
		assert controller.response.status == HttpServletResponse.SC_OK
		assert User.count == 1
		assert sendMailCalled
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
	
	void testSaveWithErrorBlankPassword() {
		def controller = new UserController()
		mockParams.password = ""
		controller.params.putAll(mockParams)
		controller.save()
		assert controller.response.text == "password cannot be blank"
		assert controller.response.status == 400
		assert User.count == 0
	}
	
	void testLogin() {
		def controller = new UserController()
		def sendMailCalled = false
		controller.metaClass.sendMail = { Closure c ->
			sendMailCalled = true
		}
	    controller.params.putAll(mockParams)
		controller.save()
		controller.handleLogin()
		
		assert controller.session
		def sessUser = controller.session.user
		assert sessUser
		assertEquals("Expected emails to match",controller.params.email, sessUser.email)
		assert sendMailCalled
	}

}
