package hotelrequest



import grails.test.mixin.*

import org.bouncycastle.bcpg.UserIDPacket;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Users)
class UsersTests {

    void testUser() {
		def usersTest = new Users(userId:1,addr1: "123 Apple Street",addr2:"Apt 123",city:"Minneapolis",email:"jdoe@jd.com", country:"USA", firstName: "John",lastName:"Doe", passwdHash:"xxxx",postalCode: "55555", state: "MN")
		
		if( !usersTest.save(flush:true,insert:true, validate:true) ) {
			usersTest.errors.each {
				 println it
			}
		 }
		println Users.list()
		
		assertEquals 1, Users.list().size()
	}
}
