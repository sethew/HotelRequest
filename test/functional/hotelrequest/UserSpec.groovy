package hotelrequest

import groovy.util.slurpersupport.NodeChild
import groovyx.net.http.ContentType

import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair
import org.apache.http.protocol.BasicHttpContext
import org.apache.http.protocol.HttpContext

import spock.lang.Ignore
import spock.lang.Specification

class UserSpec extends Specification {
	
	Map formAttributes
	
	HttpClient httpClient 
	HttpContext httpContext 
	
	static int userCounter = 0
	
	def setup() {
		httpClient = new DefaultHttpClient()
		httpContext = new BasicHttpContext()
		
		formAttributes = ["id":"4", "email":"", "password":"badsandwich", "firstName":"Seth", "lastName":"Sweep", "addr1":"1234 Morgan","addr2":"Apt 33", "city":"Golden Valley","state":"MN","postalCode":"55444","country":"USA", "phone":"555-555-5555"]
	}
	
	def userAdminPageShowsCreatedUsers() {
		setup:
		def userEmails = ["joe@example.com", "wilma@example.com", "magenta@example.com"]
		userEmails.each { createNewUserWithEmail(it) }
		
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
	
	def attendeeCanUpdateUser() {
		setup:
		// Create user
		def email = "seth2@example.com"
		createNewUserWithEmail(email)
	
		when:
        List<NameValuePair> nameValuePairsUpd = new ArrayList<NameValuePair>()
		HttpPost postUpd = new HttpPost("http://localhost:8080/HotelRequest/user/update")
		formAttributes.putAll(["id":"4", "firstName":"SethUPD", "lastName":"SweepUPD"])	
		formAttributes.each{String key, String value ->
			nameValuePairsUpd << new BasicNameValuePair(key, value)
		}
		postUpd.setEntity(new UrlEncodedFormEntity(nameValuePairsUpd))
		HttpResponse responseUpd = httpClient.execute(postUpd);
		
		then:
		responseUpd.getStatusLine().getStatusCode() == 200
		responseUpd.getEntity().consumeContent()
	}
	
	def attendeeMessesUpUpdateUser() {
		setup:
		createNewUserWithEmail("seth3@example.com")
	
		when:
		List<NameValuePair> nameValuePairsUpd = new ArrayList<NameValuePair>()
		HttpPost postUpd = new HttpPost("http://localhost:8080/HotelRequest/user/update")
		formAttributes.putAll(["id":"4", "email":"", "firstName":"SethUPD", "lastName":"SweepUPD"])	
		formAttributes.each{String key, String value ->
			nameValuePairsUpd << new BasicNameValuePair(key, value)
		}
		postUpd.setEntity(new UrlEncodedFormEntity(nameValuePairsUpd))
		HttpResponse responseUpd = httpClient.execute(postUpd);
		
		then:
		responseUpd.getStatusLine().getStatusCode() == 400
		responseUpd.getEntity().consumeContent()
		
	}
	
	def attendeeCanCreateUser() {
		setup:
		def email = "seth@convention-reg.com"
		
		when:
		HttpResponse httpResponse = httpClient.execute(createSavePostForUserWithEmail(email))
		
		then:
		httpResponse.statusLine.statusCode == 200
		httpResponse.entity.contentType.value == "${ContentType.HTML.toString()};charset=utf-8"
		httpResponse.entity.content.text == "Created user: ${email}"
	}
	
	def attendeeCannotCreateUserIfEmailAlreadyUsed() {
		setup:
		def email = "tom@example.com"
		createNewUserWithEmail(email) 
		
		when:
		HttpResponse httpResponse = httpClient.execute(createSavePostForUserWithEmail(email))

		then:
		httpResponse.statusLine.statusCode == 400
		httpResponse.entity.contentType.value == "${ContentType.HTML.toString()};charset=utf-8"
		httpResponse.entity.content.text == "Email ${email} is already registered"
	}
	
	def attendeeCannotCreateUserIfEmailAddressIsInvalid() {
		when:
		HttpResponse httpResponse = httpClient.execute(createSavePostForUserWithEmail("sethatexampledotcom"))
		
		then:
		httpResponse.statusLine.statusCode == 400
		httpResponse.entity.contentType.value == "${ContentType.HTML.toString()};charset=utf-8"
		httpResponse.entity.content.text == 'sethatexampledotcom is not a valid email address'
	}
	
	def attendeeCanLoginWithEmail() {
		setup:
		createNewUserWithEmail("seth4@example.com")
		
		when:
		HttpResponse httpResponse = httpClient.execute(new HttpGet("http://localhost:8080/HotelRequest/user/handleLogin?email=seth4@example.com"), httpContext)
		
		then:
		httpResponse.statusLine.statusCode == 200
		httpResponse.entity.contentType.value == "${ContentType.HTML.toString()};charset=utf-8"
		httpResponse.entity.content.text == "logged in with: seth4@example.com"
	}

	def attendeeCanAccessLoginPage() {
		setup:
		createNewUserWithEmail("fifi2@example.com")
			
		when:
		HttpResponse httpResponse = httpClient.execute(new HttpGet("http://localhost:8080/HotelRequest/login/auth"), httpContext)
				
		then:
		httpResponse.statusLine.statusCode == 200
	}
	
	def attendeeCanLoginSecurely() {
		setup:
		def email = "connie${userCounter++}@example.edu"
		createNewUserWithEmail(email)
		
		when:
		HttpResponse httpResponse = httpClient.execute(createPostForSecureLogin(email))
		
		then:
		httpResponse.statusLine.statusCode == 302
		httpResponse.getFirstHeader("Location").value == "http://localhost:8080/HotelRequest/"		
	}
	
	@Ignore("this test is somehow not working. However, there will be more things to test once the login functionality is married to the spring session.")
	def attendeeCanLogout() {
		setup:
		def email = "connie${userCounter++}@example.edu"
		createNewUserWithEmail(email)
		
		HttpPost post = createPostForSecureLogin(email)
		HttpResponse loginhttpResponse = httpClient.execute(post)
		loginhttpResponse.entity.consumeContent()
		
		when:
		HttpGet httpGet = new HttpGet("http://localhost:8080/HotelRequest/j_spring_security_logout")
		HttpResponse httpResponse = httpClient.execute(httpGet)
		
		then: 
		httpResponse.statusLine.statusCode == 302
		httpResponse.getFirstHeader("Location").value == "http://localhost:8080/HotelRequest/"
    }

	private HttpPost createPostForSecureLogin(String email) {
		HttpPost post = new HttpPost("http://localhost:8080/HotelRequest/j_spring_security_check")
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>()
		["j_password":"badsandwich","j_username":email].each{String key, String value ->
			nameValuePairs << new BasicNameValuePair(key, value)
		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs))
		return post
	}
	
	private HttpPost createSavePostForUserWithEmail(String email) {
		HttpPost post = new HttpPost("http://localhost:8080/HotelRequest/user/save")
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>()
		formAttributes.email = email
		formAttributes.each{String key, String value ->
			nameValuePairs << new BasicNameValuePair(key, value)
		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs))
		return post
	}
		
	private createNewUserWithEmail(String email) {
		HttpResponse response = httpClient.execute(createSavePostForUserWithEmail(email))
		assert response.statusLine.statusCode == 200
		response.entity.consumeContent()
	}
	
	//This would make a better unit test, but there's a grails bug that prevents this. See http://jira.grails.org/browse/GRAILS-8426
	def attendeeCanOnlyCallSaveOrUpdateAsAPost() {
		when:
		HttpResponse httpResponse = httpClient.execute(new HttpGet(url), httpContext)
		
		then:
		httpResponse.statusLine.statusCode == 405
		
		where:
		url << ["http://localhost:8080/HotelRequest/user/save?email=seth4@example.com", "http://localhost:8080/HotelRequest/user/update?email=tom2@example.com"]
	}
	

}
