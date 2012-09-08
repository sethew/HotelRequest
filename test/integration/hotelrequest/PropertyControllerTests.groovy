package hotelrequest


import org.junit.Before;
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PropertyController)

class PropertyControllerTests {


	// Validates properties are loaded into system via Bootstrap
    void testPreLoadedPropertiesPopulated() {
       assert Property.count() == 2
    }
	

}
