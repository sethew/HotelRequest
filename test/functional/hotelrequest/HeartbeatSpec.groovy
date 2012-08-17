package hotelrequest
import groovyx.net.http.RESTClient
import spock.lang.Specification

class HeartbeatSpec extends Specification {

	def "Application heartbeat shows app is alive."() {
		when:
		def response = new RESTClient("http://localhost:8080").get(path: "/HotelRequest/heartbeat")

		then:
		response.status == 200
		response.data == "Application is running."
	}
}
