package atcsim.loader.navaid;
import java.io.IOException;
import java.util.*;
import atcsim.datatype.*;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.*;

public class LoaderAirway extends A_Loader{

	public LoaderAirway(HashMap<String, A_ComponentNavaid<?>> hm, OverlayNavigation on) {
		super(hm, on);
		System.out.println("This is Team 5 LoaderAirway executing...");
	}
	@Override
	public void Load(Scanner scan) throws IOException {
		
		boolean iterate = true;
		scan.nextLine();
		while(iterate && scan.hasNext())
		{
			//ID
			String csv = scan.nextLine();
			if(!csv.equals(""))
			{
				String[] parts = csv.split(", ");
				String id = parts[0];
				
				String type = parts[1];
				
				switch(type){
					//Coordinate to coordinate
					case "CC":
						//First Latitude
						String[] ccArray = parts[2].split(",");
						Latitude lat_1 = Load_Lat(ccArray);
						//First Longitude
						Arrays.fill(ccArray, null);
						ccArray = parts[3].split(",");
						Longitude lon_1 = Load_Lon(ccArray);
						//First Altitude
						Arrays.fill(ccArray, null);
						Altitude alt_1 = Load_Alt(parts[4]);

						//Second Latitude
						Arrays.fill(ccArray, null);
						ccArray = parts[5].split(",");
						Latitude lat_2 = Load_Lat(ccArray);
						//Second Longitude
						Arrays.fill(ccArray, null);
						ccArray = parts[6].split(",");
						Longitude lon_2 = Load_Lon(ccArray);
						//Second Altitude
						Arrays.fill(ccArray, null);
						Altitude alt_2 = Load_Alt(parts[7]);

						//Create first position
						CoordinateWorld3D position_1 = Load_Pos(lat_1, lon_1, alt_1);
						//Create second  position
						CoordinateWorld3D position_2 = Load_Pos(lat_2, lon_2, alt_2);

						//Create ccAir
						ComponentNavaidAirway ccAir = new ComponentNavaidAirway(id, position_1, position_2);

						//Add to overlay and hashmap
						on.addNavaid(ccAir);
						hm.put(id, ccAir);
						break;

					//Navaid to coordinate
					case "NC":
						//Latitude
						String[] ncArray = parts[3].split(",");
						Latitude lat = Load_Lat(ncArray);

						//Longitude
						Arrays.fill(ncArray, null);
						ncArray = parts[4].split(",");
						Longitude lon = Load_Lon(ncArray);

						//Altitude
						Altitude alt = Load_Alt(parts[5]);
						//Create position
						CoordinateWorld3D position = Load_Pos(lat, lon, alt);
						//Create ncAir
						ComponentNavaidAirway ncAir = new ComponentNavaidAirway(id, hm.get(parts[2]), position);

						//Add to overlay and hashmap
						on.addNavaid(ncAir);
						hm.put(id, ncAir);
						break;
						
					//Navaid to navaid
					case "NN":
						ComponentNavaidAirway nnAir = new ComponentNavaidAirway(id, hm.get(parts[2]), hm.get(parts[3]));
						//Add to overlay and hashmap
						on.addNavaid(nnAir);
						hm.put(id, nnAir);
						break;
				}
			}
			else{
				iterate = false;
			}
		}
	}
}
