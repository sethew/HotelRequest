package hotelrequest

class Property {

	static hasMany = [roomType:RoomType]
    static constraints = {
    }
	
	String event
	String name
	String desc
	Boolean isAvalible
	String imageURL
	
}
