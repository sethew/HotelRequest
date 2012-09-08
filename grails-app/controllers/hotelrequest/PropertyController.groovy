package hotelrequest

class PropertyController {

	static scaffold = true
	
	def listFancy() {
		
		render(view: "listProperty", model: [properties: Property.all])
		
	}
	
 
}
