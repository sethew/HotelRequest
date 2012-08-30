package hotelrequest

import groovy.util.slurpersupport.NodeChild
import groovyx.net.http.ContentType

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.protocol.BasicHttpContext
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
	
	
	def attendeeCanUpdateUser() {
		setup:
		// Create user
		HttpClient client = new DefaultHttpClient()
		HttpPost post = new HttpPost("http://localhost:8080/HotelRequest/user/save")
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>()
		
		nameValuePairs.add(new BasicNameValuePair("email","seth2@example.com"))
		nameValuePairs.add(new BasicNameValuePair("firstName","Seth"))
		nameValuePairs.add(new BasicNameValuePair("lastName","Sweep"))
		nameValuePairs.add(new BasicNameValuePair("addr1","1234 Morgan"))
		nameValuePairs.add(new BasicNameValuePair("addr2","Apt 33"))
		nameValuePairs.add(new BasicNameValuePair("city","Golden Valley"))
		nameValuePairs.add(new BasicNameValuePair("state","MN"))
		nameValuePairs.add(new BasicNameValuePair("postalCode","55444"))
		nameValuePairs.add(new BasicNameValuePair("country","USA"))
		nameValuePairs.add(new BasicNameValuePair("phone","555-555-5555"))
		
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs))
		HttpResponse response = client.execute(post)
		response.getEntity().consumeContent()
	
		when:
		List<NameValuePair> nameValuePairsUpd = new ArrayList<NameValuePair>()
		HttpPost postUpd = new HttpPost("http://localhost:8080/HotelRequest/user/update")
		nameValuePairsUpd.add(new BasicNameValuePair("id","4"))
		nameValuePairsUpd.add(new BasicNameValuePair("email","seth2@example.com"))
		nameValuePairsUpd.add(new BasicNameValuePair("firstName","SethUPD"))
		nameValuePairsUpd.add(new BasicNameValuePair("lastName","SweepUPD"))
		nameValuePairsUpd.add(new BasicNameValuePair("addr1","1234 Morgan"))
		nameValuePairsUpd.add(new BasicNameValuePair("addr2","Apt 33"))
		nameValuePairsUpd.add(new BasicNameValuePair("city","Golden Valley"))
		nameValuePairsUpd.add(new BasicNameValuePair("state","MN"))
		nameValuePairsUpd.add(new BasicNameValuePair("postalCode","55444"))
		nameValuePairsUpd.add(new BasicNameValuePair("country","USA"))
		nameValuePairsUpd.add(new BasicNameValuePair("phone","555-555-5555"))
		postUpd.setEntity(new UrlEncodedFormEntity(nameValuePairsUpd))
		HttpResponse responseUpd = client.execute(postUpd);
		
		then:
		responseUpd.getStatusLine().getStatusCode() == 200
		responseUpd.getEntity().consumeContent()
		
	}
	
	
	def attendeeMessesUpUpdateUser() {
		setup:
		// Create user
		HttpClient client = new DefaultHttpClient()
		HttpPost post = new HttpPost("http://localhost:8080/HotelRequest/user/save")
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>()
		
		nameValuePairs.add(new BasicNameValuePair("email","seth3@example.com"))
		nameValuePairs.add(new BasicNameValuePair("firstName","Seth"))
		nameValuePairs.add(new BasicNameValuePair("lastName","Sweep"))
		nameValuePairs.add(new BasicNameValuePair("addr1","1234 Morgan"))
		nameValuePairs.add(new BasicNameValuePair("addr2","Apt 33"))
		nameValuePairs.add(new BasicNameValuePair("city","Golden Valley"))
		nameValuePairs.add(new BasicNameValuePair("state","MN"))
		nameValuePairs.add(new BasicNameValuePair("postalCode","55444"))
		nameValuePairs.add(new BasicNameValuePair("country","USA"))
		nameValuePairs.add(new BasicNameValuePair("phone","555-555-5555"))
		
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs))
		HttpResponse response = client.execute(post)
		response.getEntity().consumeContent()
	
		when:
		List<NameValuePair> nameValuePairsUpd = new ArrayList<NameValuePair>()
		HttpPost postUpd = new HttpPost("http://localhost:8080/HotelRequest/user/update")
		nameValuePairsUpd.add(new BasicNameValuePair("id","5"))
		nameValuePairsUpd.add(new BasicNameValuePair("email",""))
		nameValuePairsUpd.add(new BasicNameValuePair("firstName","SethUPD"))
		nameValuePairsUpd.add(new BasicNameValuePair("lastName","SweepUPD"))
		nameValuePairsUpd.add(new BasicNameValuePair("addr1","1234 Morgan"))
		nameValuePairsUpd.add(new BasicNameValuePair("addr2","Apt 33"))
		nameValuePairsUpd.add(new BasicNameValuePair("city","Golden Valley"))
		nameValuePairsUpd.add(new BasicNameValuePair("state","MN"))
		nameValuePairsUpd.add(new BasicNameValuePair("postalCode","55444"))
		nameValuePairsUpd.add(new BasicNameValuePair("country","USA"))
		nameValuePairsUpd.add(new BasicNameValuePair("phone","555-555-5555"))
		postUpd.setEntity(new UrlEncodedFormEntity(nameValuePairsUpd))
		HttpResponse responseUpd = client.execute(postUpd);
		
		then:
		responseUpd.getStatusLine().getStatusCode() == 400
		responseUpd.getEntity().consumeContent()
		
	}
	
	def attendeeCanCreateUser() {
		when: 
		URLConnection urlConnection = new URL("http://localhost:8080/HotelRequest/user/save?email=seth@example.com${demographicParms}").openConnection()
		
		then:
		urlConnection.responseCode == 200
		urlConnection.contentType== "${ContentType.HTML.toString()};charset=utf-8"
		urlConnection.content.text == "Created user: seth@example.com"
	}
	

	
	def attendeeCanLoginWithEmail() {
		when: URLConnection urlConnection = new URL("http://localhost:8080/HotelRequest/user/handleLogin?email=seth@example.com").openConnection()
		
		then:
		urlConnection.responseCode == 200
		urlConnection.contentType== "${ContentType.HTML.toString()};charset=utf-8"
		urlConnection.content.text == "logged in with: seth@example.com"
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
		text == "Email fifi@example.com is already registered"
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
