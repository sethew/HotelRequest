package hotelrequest

import groovy.util.slurpersupport.NodeChild
import groovyx.net.http.ContentType

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.protocol.BasicHttpContext

import spock.lang.Ignore
import spock.lang.Specification

class UserSpec extends Specification {
	def demographicParms = "&firstName=Seth&lastName=Sweep&addr1=1234+Morgan&addr2=Apt+33&city=GOLDEN+VALLEY&state=MN&postalCode=55444&country=USA&phone=555-555-5555"
	
	
	def userAdminPageShowsCreatedUsers() {
	
		
		setup:
		def userEmails = ["joe@example.com", "wilma@example.com", "magenta@example.com"]
		userEmails.each {
			URLConnection saveConnection = new URL("http://localhost:8080/HotelRequest/user/save?email=${it}${demographicParms}").openConnection()	
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
	
	def attendeeCanCreateUser() {
		when: 
		URLConnection urlConnection = new URL("http://localhost:8080/HotelRequest/user/save?email=seth@example.com${demographicParms}").openConnection()
		
		then:
		urlConnection.responseCode == 200
		urlConnection.contentType== "${ContentType.HTML.toString()};charset=utf-8"
		urlConnection.content.text == "Created user: seth@example.com"
	}
	
	
	
	def attendeeCannotCreateUserIfEmailAlreadyUsed() {
		setup:
		def httpClient = new DefaultHttpClient()
     	def httpContext = new BasicHttpContext()
		
		def url = "http://localhost:8080/HotelRequest/user/save?email=fifi@example.com${demographicParms}"
		URLConnection saveConnection = new URL(url).openConnection()
		assert saveConnection.responseCode == 200
		
		when:
		HttpGet httpGet = new HttpGet(url)
		HttpResponse httpResponse = httpClient.execute(httpGet, httpContext)
		def text = httpResponse.entity.content.text
		
		then:
		httpResponse.statusLine.statusCode == 400
		httpResponse.entity.contentType.value == "${ContentType.HTML.toString()};charset=utf-8"
		text == "Email fifi@example.com is already registered."
	}
	
	
	def attendeeCannotCreateUserIfEmailAddressIsInvalid() {
		setup:
		def httpClient = new DefaultHttpClient()
		def httpContext = new BasicHttpContext()
		
		when:
		HttpGet httpGet = new HttpGet("http://localhost:8080/HotelRequest/user/save?email=sethatexampledotcom${demographicParms}")
		HttpResponse httpResponse = httpClient.execute(httpGet, httpContext)
		def text = httpResponse.entity.content.text
		
		then:
		httpResponse.statusLine.statusCode == 400
		httpResponse.entity.contentType.value == "${ContentType.HTML.toString()};charset=utf-8"
		text == 'sethatexampledotcom is not a valid email address'
	}
}
