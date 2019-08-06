import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NEOTest {	
NEO neo = new NEO();
@Test
public void NeoOjectsTest()
{
	try {
		ArrayList neoobjects = neo.getNeoObjects();
		boolean flag;
		Assert.assertNotNull(neoobjects, "List should have NEO Objects");
		Assert.assertEquals(neoobjects.size(), 21);		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Test
public void SmallBigNeoObjectsTest()
{
	try {
		ArrayList neoobjects = neo.getSmallBigNeoObjects();
		boolean flag;
		Assert.assertNotNull(neoobjects, "List should have NEO Objects");
		Assert.assertEquals(neoobjects.size(), 2);		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Test
public void NeoOrbitEarthTest()
{
	try {
		ArrayList neoobjects = neo.getNeoOrbitEarth();
		boolean flag;
		Assert.assertNotNull(neoobjects, "List should have NEO Objects");
		Assert.assertEquals(neoobjects.size(), 4);		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
