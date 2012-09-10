package hotelrequest

import geb.spock.GebReportingSpec
import spock.lang.*
import hotelrequest.gebpages.PropertyListPage

@Stepwise
class PropertySpec extends GebReportingSpec {
	

	
	def "pageLoads"() {
		when: "I am at the propertyList page"
		to PropertyListPage
		then:
		$().text().contains("Place Holder for some event description text")
	}
	
	def "pageContainsTwoProperties"() {
		when: "I am at the propertyList page"
		to PropertyListPage
		then:
		$().text().contains("DoubleTree By Hilton")
		$().text().contains("Secondary Hotel for CONvergence")
	}
	

	
	

}
