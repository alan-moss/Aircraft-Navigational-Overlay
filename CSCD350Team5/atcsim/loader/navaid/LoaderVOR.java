package atcsim.loader.navaid;
import java.io.IOException;
import java.util.*;
import atcsim.datatype.*;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.*;

public class LoaderVOR extends A_Loader{

	public LoaderVOR(HashMap<String, A_ComponentNavaid<?>> hm, OverlayNavigation on) {
		super(hm, on);
		System.out.println("This is Team 5 LoaderVOR executing...");
	}

	@Override
	public void Load(Scanner scan) throws IOException{
		//ID, vhf_frequency(int major, int minor), lat(deg,min,sec), lon(deg,min,sec), alt
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
				String[] tempArray = parts[1].split(",");
				int valueMajor = Integer.parseInt(tempArray[0]);
				int valueMinor = Integer.parseInt(tempArray[1]);
				VHFFrequency freq = new VHFFrequency(valueMajor, valueMinor);
				
				//Latitude
				Arrays.fill(tempArray, null);
				tempArray = parts[2].split(",");
				Latitude lat = Load_Lat(tempArray);

				//Longitude
				Arrays.fill(tempArray, null);
				tempArray = parts[3].split(",");
				Longitude lon = Load_Lon(tempArray);

				//Altitude
				Altitude alt = Load_Alt(parts[4]);

				//Create position
				CoordinateWorld3D position = Load_Pos(lat, lon, alt);
				//Create vor
				ComponentNavaidVOR vor = new ComponentNavaidVOR(id, position, freq);

				//Add to overlay and hashmap
				on.addNavaid(vor);
				hm.put(id, vor);
			}
			else{
				iterate = false;
			}
		}
	}
}
