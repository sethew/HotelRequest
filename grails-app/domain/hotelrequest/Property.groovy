package hotelrequest

class Property {

	static hasMany = [roomType:RoomType]
    static constraints = {
    }
	
	String event
	String name
	String Desc
	Boolean isAvalible
	String imageURL
	
}
