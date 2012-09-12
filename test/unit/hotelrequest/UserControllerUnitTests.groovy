package hotelrequest

import grails.test.mixin.*
import grails.test.mixin.support.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock(User)
class UserControllerUnitTests {

	Map mockParams
	
	@Before
    void setUp() {
        mockParams = ["lastName":"Joe", "firstName":"Face", "addr1":"123 Fake St", "addr2":"Apt 1", "city":"Vancouver", "state":"BC", "postalCode":"ABC123", country:"Canada", email:"seth@example.com", phone:"123456789", password:"password"]
    }

    void tearDown() {
        // Tear down logic here
    }
	
	void testSaveSetsSessionUser() {
		request.method = "POST"
		controller.params.putAll(mockParams)
		def sendMailCalled = false
		
		controller.metaClass.sendMail = { Closure c ->
		
		sendMailCalled = true
		
		}
		
	
		
		
		
		controller.save()
		assert sendMailCalled
		assert session.user != null
		assert session.user.email == mockParams.email
	}
}
