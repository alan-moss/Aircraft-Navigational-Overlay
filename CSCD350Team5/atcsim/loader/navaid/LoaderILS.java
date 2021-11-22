package atcsim.loader.navaid;
import java.io.IOException;
import java.util.*;
import atcsim.datatype.*;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.A_Loader;
import atcsim.world.navigation.*;

public class LoaderILS extends A_Loader{

	public LoaderILS(HashMap<String, A_ComponentNavaid<?>> hm, OverlayNavigation on) {
		super(hm, on);
		System.out.println("This is Team 5 LoaderILS executing...");
	}

	@Override
	public void Load(Scanner scan) throws IOException{
		//ID, vhf_freq, lat, lon, alt, azimuth(int), beacon, beacon, beacon
		//AngleNavigational(double)
		//NavaidILSBeaconDescriptor(Distance distance, Altitude altitude)
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

				//Azimuth
				AngleNavigational azimuth = new AngleNavigational(Double.parseDouble(parts[5]));

				//Beacon markerOuter
				Arrays.fill(tempArray, null);
				tempArray = parts[6].split(",");
				Distance outerDist = new Distance(Double.parseDouble(tempArray[0]));
				Altitude outerAlt = new Altitude(Double.parseDouble(tempArray[1]));
				NavaidILSBeaconDescriptor markerOuter = new NavaidILSBeaconDescriptor(outerDist, outerAlt);

				//Beacon markerMiddle
				Arrays.fill(tempArray, null);
				tempArray = parts[7].split(",");
				Distance middleDist = new Distance(Double.parseDouble(tempArray[0]));
				Altitude middleAlt = new Altitude(Double.parseDouble(tempArray[1]));
				NavaidILSBeaconDescriptor markerMiddle = new NavaidILSBeaconDescriptor(middleDist, middleAlt);

				//Beacon markerInner
				Arrays.fill(tempArray, null);
				tempArray = parts[8].split(",");
				Distance innerDist = new Distance(Double.parseDouble(tempArray[0]));
				Altitude innerAlt = new Altitude(Double.parseDouble(tempArray[1]));
				NavaidILSBeaconDescriptor markerInner = new NavaidILSBeaconDescriptor(innerDist, innerAlt);

				//Create position
				CoordinateWorld3D position = Load_Pos(lat, lon, alt);
				//Create ndb
				ComponentNavaidILS ils = new ComponentNavaidILS(id, position, azimuth, freq, markerOuter, markerMiddle, markerInner);
				
				//Add to overlay and hashmap
				on.addNavaid(ils);
				hm.put(id, ils);
			}
			else{
				iterate = false;
			}
		}
	}
}
