package hotelrequest.gebpages

import geb.Page
import groovy.lang.MetaClass;

class PropertyListPage extends Page {

	static url = "property/list"
	
		static at = { title == "Hotel Request - List Hotels and Rooms" }
	
		static content = {
			//properties = { $(".hotelBlock") }
			
		    propertyForm { $("form") }
//		    loginButton { $("input", value: "Login") }
		}
}
