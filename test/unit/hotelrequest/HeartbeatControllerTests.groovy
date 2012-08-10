package hotelrequest

import grails.test.mixin.TestFor

@TestFor(HeartbeatController)
class HeartbeatControllerTests {

	void testHeartbeat() {
		controller.index()

		assert response.status == 200
		assert response.text == 'Application is running.'
	}
}
