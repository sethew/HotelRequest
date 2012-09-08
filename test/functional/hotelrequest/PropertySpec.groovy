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

class PropertySpec extends Specification {
	
	Map formAttributes
	
	HttpClient httpClient 
	HttpContext httpContext 
	
	static int userCounter = 0
	
	def setup() {
		httpClient = new DefaultHttpClient()
		httpContext = new BasicHttpContext()
		
		
	}
	
	
	
	def attendeeCanBrowsePropertiesAndRooms() {
		when:
		HttpResponse httpResponse = httpClient.execute(new HttpGet("http://localhost:8080/HotelRequest/property/listFancy"), httpContext)
		
		then:
		httpResponse.statusLine.statusCode == 200
		httpResponse.entity.contentType.value == "${ContentType.HTML.toString()};charset=utf-8"
		httpResponse.entity.
		
		}
	

}
