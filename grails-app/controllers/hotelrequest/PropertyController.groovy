package hotelrequest

class PropertyController {

	static scaffold = true
	
	def list() {
		// Filter out Properties 
		def properties = Property.findAllByIsAvalible(true)
		def fullRooms = []
		def fullRoomsMap = [:]
		
		// Get the full rooms for each property
		properties.each {Property it ->
			fullRooms.addAll( findFullRoomsByRoomType(it.roomType))
		}
		
		// Convert List to Map for View
		fullRooms.each {
			RoomType it -> fullRoomsMap.put(it.id, it )
		}
		
		render(view: "listProperties", model: [properties: properties, fullRoomsTypesMap: fullRoomsMap])
		
	}
	
	
	def findFullRoomsByRoomType(roomTypes) {

		roomTypes.findAll{ RoomType room -> 
			(Request.countByRoomType(room) + 
				RoomRequestToken.countByRoomTypeAndIsActive(room, true)) >= room.roomLimit }
	}

}
