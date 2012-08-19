package hotelrequest

import grails.test.mixin.*
import grails.test.mixin.support.*

import javax.servlet.http.HttpServletResponse


/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock(User)
class UserControllerTests {

	void setUp() {
		// Setup logic here
	}

	void tearDown() {
		// Tear down logic here
	}

	void testSave() {
		params.email = "seth@example.com"
		controller.save()
		assert response.text == "Created user: seth@example.com"
		assert response.status == HttpServletResponse.SC_OK
		assert User.count == 1
	}

	void testSaveWithErrorInvalidEmail() {
		params.email = "notanemail"
		controller.save()
		assert response.text == "notanemail is not a valid email address"
		assert response.status == 400
		assert User.count == 0
	}

	void testSaveWithErrorBlankEmail() {
		params.email = ""
		controller.save()
		assert response.text == "email cannot be blank"
		assert response.status == 400
		assert User.count == 0
	}
}
