package hotelrequest

import groovy.util.slurpersupport.NodeChild
import groovyx.net.http.ContentType
import spock.lang.Specification

class UserSpec extends Specification {

	def "User page shows created users"() {
		setup:
		def userEmails = ["joe@sethlab.com", "wilma@sethlab.com", "magenta@sethlab.com"]
		userEmails.each {
			URLConnection saveConnection = new URL("http://localhost:8080/HotelRequest/user/save?email=${it}").openConnection()	
			assert saveConnection.responseCode == 200;
		}

		when:
		URLConnection response = new URL("http://localhost:8080/HotelRequest/user").openConnection()
		NodeChild html = new XmlSlurper().parseText(response.content.text)

		then:
		response.responseCode == 200
		response.contentType == "${ContentType.HTML.toString()};charset=UTF-8"
		html.h3.text() == 'Created Users'
		html.ul.eachWithIndex() { email, i ->
			email == userEmails[i]
		}
	}
}
