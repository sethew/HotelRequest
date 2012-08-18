package hotelrequest

import groovy.util.slurpersupport.NodeChild
import groovyx.net.http.ContentType
import spock.lang.Ignore
import spock.lang.Specification

class UserSpec extends Specification {

	def "User admin page shows created users"() {
		setup:
		def userEmails = ["joe@example.com", "wilma@example.com", "magenta@example.com"]
		userEmails.each {
			URLConnection saveConnection = new URL("http://localhost:8080/HotelRequest/user/save?email=${it}").openConnection()	
			assert saveConnection.responseCode == 200;
		}

		when:
		URLConnection urlConnection = new URL("http://localhost:8080/HotelRequest/user/admin").openConnection()
		NodeChild html = new XmlSlurper().parseText(urlConnection.content.text)

		then:
		urlConnection.responseCode == 200
		urlConnection.contentType == "${ContentType.HTML.toString()};charset=UTF-8"
		html.h3.text() == 'Created Users'
		html.ul.eachWithIndex() { email, i ->
			email == userEmails[i]
		}
	}
	
	def "Attendee can create user"() {
		when: 
		URLConnection urlConnection = new URL("http://localhost:8080/HotelRequest/user/save?email=seth@example.com").openConnection()
		
		then:
		urlConnection.responseCode == 200
		urlConnection.contentType== "${ContentType.HTML.toString()};charset=utf-8"
		urlConnection.content.text == "Created user: seth@example.com"
	}
	
	@Ignore
	def "Attendee cannot create user if email already used"() {
		setup:
		def url = "http://localhost:8080/HotelRequest/user/save?email=seth@example.com"
		URLConnection saveConnection = new URL(url).openConnection()
		assert saveConnection.responseCode == 200
		
		when:
		URLConnection urlConnection = new URL(url).openConnection()
		
		then:
		urlConnection.responseCode == 400
		urlConnection.contentType== "${ContentType.HTML.toString()};charset=utf-8"
		urlConnection.content.text == "Email seth@example.com is already registered."
	}
	
	@Ignore
	def "Attendee cannot create user if email address is invalid"() {
		when:
		URLConnection urlConnection = new URL("http://localhost:8080/HotelRequest/user/save?email=sethatexampledotcom").openConnection()
		
		then:
		urlConnection.responseCode == 400
		urlConnection.contentType== "${ContentType.HTML.toString()};charset=utf-8"
		urlConnection.content.text == '"sethatexampledotcom" is not a valid email address.'
	}
}
