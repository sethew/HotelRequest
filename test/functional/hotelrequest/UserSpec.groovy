package hotelrequest

import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import spock.lang.Specification

class UserSpec extends Specification {

	def "User page shows created users"() {
		setup:
		def httpClient = new RESTClient("http://localhost:8080/")
		def userEmails = ["joe@sethlab.com", "wilma@sethlab.com", "magenta@sethlab.com"]
		userEmails.each {
			httpClient.get(path: "/HotelRequest/user/save", query:[email:it])
		}

		when:
		HttpResponseDecorator response = (HttpResponseDecorator) new RESTClient("http://localhost:8080/").get(path:"/HotelRequest/user/")

		then:
		response.status == 200
		response.contentType == ContentType.HTML.toString()
		response.data.BODY.H3.text() == 'Created Users'
		response.data.BODY.UL.eachWithIndex() { email, i ->
			email == userEmails[i]
		}
	}
}
