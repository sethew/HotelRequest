package hotelrequest

class RoomType {

	static constraints = {
	}

	int limit
	String title
	String desc
	String imageURL
	Boolean isWaitList

	static mapping = {
		limit column: 'room_limit'
		desc column: 'room_desc'
	}
}
