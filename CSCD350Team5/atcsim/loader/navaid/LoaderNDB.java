package atcsim.loader.navaid;
import java.io.IOException;
import java.util.*;
import atcsim.datatype.*;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.world.navigation.*;

public class LoaderNDB extends A_Loader{

	public LoaderNDB(HashMap<String, A_ComponentNavaid<?>> hm, OverlayNavigation on) {
		super(hm, on);
		System.out.println("This is Team 5 LoaderNDB executing...");
	}

	@Override
	public void Load(Scanner scan) throws IOException {
		//ID, frequency, lat(deg,min,sec), lon(deg,min,sec), alt
		boolean iterate = true;
		scan.nextLine();
		while(iterate)
		{
			//ID
			String csv = scan.nextLine();
			if(!csv.equals(""))
			{
				String[] parts = csv.split(", ");
				String id = parts[0];
				
				//Frequency
				UHFFrequency freq = new UHFFrequency(Integer.parseInt(parts[1]));

				//Latitude
				String[] tempArray = parts[2].split(",");
				Latitude lat = Load_Lat(tempArray);

				//Longitude
				Arrays.fill(tempArray, null);
				tempArray = parts[3].split(",");
				Longitude lon = Load_Lon(tempArray);

				//Altitude
				Altitude alt = Load_Alt(parts[4]);

				//Create position
				CoordinateWorld3D position = Load_Pos(lat, lon, alt);
				//Create ndb
				ComponentNavaidNDB ndb = new ComponentNavaidNDB(id, position, freq);

				//Add to overlay and hashmap
				on.addNavaid(ndb);
				hm.put(id, ndb);
			}
			
			else{
				iterate = false;
			}
		}
	}
}
