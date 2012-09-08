import java.util.logging.Logger;

import hotelrequest.Property
import hotelrequest.RoomType

class BootStrap {
	
	// Refactor target.  An XML loader would be better though prod data is very light
    def init = { servletContext ->
			if (!Property.count()) {
				def southRoomsKing = new RoomType(roomLimit:3, title: "South (Short) Tower King Rooms",
					desc: "King Rooms in the South (Short) tower", isWaitList: false, price: 101,
					imageURL: "dbl_bloomington_king_north.png")
				def southRoomsDouble = new RoomType(roomLimit:3, title: "South (Short) Tower Double Rooms",
					desc: "Two Bed Rooms in the South (Short) tower", isWaitList: false, price: 101,
					imageURL: "dbl_bloomington_dbl_south.png")
				def northRoomsKing = new RoomType(roomLimit:4, title: "North (Tall) Tower King Rooms",
					desc: "One King Bed Rooms in the South (Tall) tower", isWaitList: false, price: 101,
					imageURL: "dbl_bloomington_king_south.png")
				def northRoomsDouble = new RoomType(roomLimit:4, title: "North (Tall) Tower Double Rooms",
					desc: "Two Bed Rooms in the South (Tall) tower", isWaitList: false, price: 101,
					imageURL: "dbl_bloomington_dbl_south.png")
				
				def waitlistRoomDbl = new RoomType(roomLimit:999, title: "Hotel Waitlist", 
					desc: "Waitlist for the DoubleTree by Hilton (Any room Type)", isWaitList: true, price: 101.0)
				
				def dblTree = new Property(event: "Convergence 2013", name: "DoubleTree By Hilton",
					desc: "Main Hotel for CONvergence<br>7800 Normandale Boulevard<br> Bloomington, MN  55439",
					isAvalible: true, imageURL: "dbl_tree_bloomington_small.png")
				    .addToRoomType(southRoomsKing)
					.addToRoomType(southRoomsDouble)
					.addToRoomType(northRoomsKing)
					.addToRoomType(northRoomsDouble)
					.addToRoomType(waitlistRoomDbl)
				//	dblTree.save()
				if(!dblTree.save())
					log.error("Error bootstraping hotel" + dblTree.errors.getFieldError())
				}
					
				
				
				
				def SofRoomsDouble = new RoomType(roomLimit:4, title: "Sofitel Double Rooms",
					desc: "Double Rooms in the Hotel Sofitel", isWaitList: false, price: 121.0)
				def SofRoomsKing = new RoomType(roomLimit:4, title: "Sofitel King Rooms",
					desc: "One King Bed Rooms in the Hotel Sofitel", isWaitList: false, price: 121.0)
				def waitlistRoomSof = new RoomType(roomLimit:999, title: "Hotel Waitlist",
					desc: "Waitlist for the DoubleTree by Hilton (Any room Type)", isWaitList: true, price: 121.0)
				
				def sofitel = new Property(event: "Convergence 2013", name: "Sofitel",
					desc: "Secondary Hotel for CONvergence<br>5601 W 78 St<br>Bloomington, MN 55439",
					isAvalible: true,imageURL: "sofitel_bloomington_small.png")
					.addToRoomType(SofRoomsKing)
					.addToRoomType(SofRoomsDouble)
					.addToRoomType(waitlistRoomSof)
				
				if(!sofitel.save()) {
					log.error("Error bootstraping hotel" + sofitel.errors.getFieldError())
				}
    }
    def destroy = {
    }
}
