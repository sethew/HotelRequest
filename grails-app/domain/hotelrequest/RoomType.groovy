package hotelrequest

class RoomType {

	static constraints = {
		imageURL(nullable:true)
	}

	int roomLimit
	String title
	String desc
	String imageURL
	Boolean isWaitList

//	static mapping = {
//		limit column: 'room_limit'
//		desc column: 'room_desc'
//	}
}
