package hotelrequest



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

	void testUser() {
		def usersTest = new User(userId:1,addr1: "123 Apple Street",addr2:"Apt 123",city:"Minneapolis",email:"jdoe@jd.com", country:"USA", firstName: "John",lastName:"Doe", passwdHash:"xxxx",postalCode: "55555", state: "MN")

		if( !usersTest.save(flush:true,insert:true, validate:true) ) {
			usersTest.errors.each { println it }
		}
		println User.list()

		assertEquals 1, User.list().size()
	}
}
