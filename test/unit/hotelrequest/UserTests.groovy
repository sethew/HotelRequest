package hotelrequest



import grails.plugins.springsecurity.SpringSecurityService
import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

	void testUser() {
		def userPassword = "password"
		
		def mockSpringSecurityServiceControl = mockFor(SpringSecurityService)
		mockSpringSecurityServiceControl.demand.encodePassword {String password -> assert password == userPassword }
		
		def usersTest = new User(addr1: "123 Apple Street",addr2:"Apt 123",city:"Minneapolis",email:"jdoe@jd.com", country:"USA", firstName: "John",lastName:"Doe" ,postalCode: "55555", state: "MN", phone:"555-555-5555", password:userPassword)
		usersTest.springSecurityService = mockSpringSecurityServiceControl.createMock()
		
		if( !usersTest.save(flush:true,insert:true, validate:true) ) {
			usersTest.errors.each { println it }
		}

		assertEquals 1, User.list().size()
		mockSpringSecurityServiceControl.verify()
	}
}
