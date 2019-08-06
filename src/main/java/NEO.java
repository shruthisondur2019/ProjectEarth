import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.text.DateFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NEO {
	
	public ArrayList getNeoObjects() throws IOException, ParseException {
		File file = new File("neoobjects.json");
	 	FileReader reader = new FileReader(file.getAbsoluteFile());
	
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(reader);
		
		JSONObject jsonObj = (JSONObject)obj;
		JSONArray near_earth_objects =(JSONArray)jsonObj.get("near_earth_objects");
		
		
		ArrayList ary = new ArrayList();
		for(int i=0;i<near_earth_objects.size();i++) {
			JSONObject data1 =(JSONObject)near_earth_objects.get(i);		
			ary.add(data1.get("neo_reference_id"));		
		}
		
		return ary;

	}
	
	public ArrayList getSmallBigNeoObjects() throws IOException, ParseException {		
		File file = new File("neoobjects.json");
	 	FileReader reader = new FileReader(file.getAbsoluteFile());
	
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(reader);
		
		JSONObject jsonObj = (JSONObject)obj;
		JSONArray near_earth_objects =(JSONArray)jsonObj.get("near_earth_objects");	
		
		ArrayList arr = new ArrayList();
		Map<Integer,Double> hashmap1 = new HashMap<Integer,Double>();
		Map<Integer,Double> hashmap2 = new HashMap<Integer,Double>();
		
		for(int i=0;i<near_earth_objects.size();i++) {
			JSONObject data1 =(JSONObject)near_earth_objects.get(i);
			JSONArray close_approach_data =(JSONArray)data1.get("close_approach_data");
			JSONObject estimated_diameter = (JSONObject)data1.get("estimated_diameter");
			JSONObject feet = (JSONObject)estimated_diameter.get("feet");		
		
			for(int j=0;j<close_approach_data.size();j++) {
				JSONObject data2 =(JSONObject)close_approach_data.get(j);
				if(data2.get("orbiting_body").equals("Mars")) {
					hashmap1.put(Integer.parseInt((String)data1.get("neo_reference_id")),(Double) feet.get("estimated_diameter_max"));
				}else if(data2.get("orbiting_body").equals("Earth")) {
					hashmap2.put(Integer.parseInt((String)data1.get("neo_reference_id")),(Double) feet.get("estimated_diameter_max"));
				}		
			}
			
		}
		
		List <Entry<Integer,Double>> list1 = new LinkedList<Entry<Integer,Double>>(hashmap1.entrySet());
		Collections.sort(list1, new Comparator<Entry<Integer,Double>>() {		
			public int compare(Entry<Integer,Double> o1, Entry<Integer,Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		List <Entry<Integer,Double>> list2 = new LinkedList<Entry<Integer,Double>>(hashmap2.entrySet());
		Collections.sort(list2, new Comparator<Entry<Integer,Double>>() {		
			public int compare(Entry<Integer,Double> o1, Entry<Integer,Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
	
		arr.add(list1.get(0).getKey());
		arr.add(list2.get(list2.size()-1).getKey());
		return arr;

	}
	
	
public ArrayList getNeoOrbitEarth() throws IOException, ParseException{
	File file = new File("neoobjects.json");
	FileReader reader = new FileReader(file.getAbsoluteFile());

	JSONParser parser = new JSONParser();
	Object obj = parser.parse(reader);
	
	JSONObject jsonObj = (JSONObject)obj;
	JSONArray near_earth_objects =(JSONArray)jsonObj.get("near_earth_objects");
	
	
	ArrayList<Object> datelist = new ArrayList<Object>();
	ArrayList sub = new ArrayList();	
	Map<Integer,String> datemap = new HashMap<Integer,String>();		
	
	for(int i=0;i<near_earth_objects.size();i++) {
		JSONObject data1 =(JSONObject)near_earth_objects.get(i);
		JSONArray close_approach_data =(JSONArray)data1.get("close_approach_data");	
		for(int j=0;j<close_approach_data.size();j++) {
			JSONObject data2 =(JSONObject)close_approach_data.get(j);
			if(data2.get("orbiting_body").equals("Earth")) {
				datemap.put(Integer.parseInt((String)data1.get("neo_reference_id")),data2.get("close_approach_date").toString());
			}	
		}		
	}	
	
    List <Entry<Integer,String>> list = new LinkedList<Entry<Integer,String>>(datemap.entrySet());	
	Collections.sort(list, new Comparator<Entry<Integer,String>>() {		
		public int compare(Entry<Integer,String> date1, Entry<Integer,String> date2) {				
				return date1.getValue().compareTo(date2.getValue());
			}		
	});
	
	List <Entry<Integer,String>> sublist = list.subList(list.size()-3, list.size());
	ArrayList arr1 = null;
	for(int k=0;k<near_earth_objects.size();k++) {		
		JSONObject earthdata =(JSONObject)near_earth_objects.get(k);
		for(Entry<Integer,String> item : sublist) {			
			if(item.getKey().equals(Integer.parseInt((String)earthdata.get("neo_reference_id")))) {	
				arr1 = new ArrayList();
				arr1.add(earthdata.get("neo_reference_id"));
				arr1.add(earthdata.get("name"));
				arr1.add(earthdata.get("nasa_jpl_url"));
			}
		}
		if(arr1!=null) {
			datelist.add(arr1);
		}		
	}
	return datelist;
}
		

}
