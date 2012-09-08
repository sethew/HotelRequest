package hotelrequest

class RoomType {

	static constraints = {
		imageURL(nullable:true)
		price(nullable: false, min: 80.0, max: 9999.99, scale: 2)
	}

	int roomLimit
	String title
	String desc
	String imageURL
	Boolean isWaitList
	BigDecimal price

//	static mapping = {
//		limit column: 'room_limit'
//		desc column: 'room_desc'
//	}
}
