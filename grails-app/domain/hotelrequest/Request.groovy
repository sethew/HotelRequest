package hotelrequest

class Request {

    static constraints = {
    }
	Boolean wheelchair
	Boolean crib
	Boolean rollaway
	Boolean doNotCare
	Calendar checkIn
	Calendar checkOut
	int adults
	int children
	String altPhone
	String sharingWith
	String additionalRequests
	Boolean volunteer
	Calendar timestamp
	RoomType roomType
}
