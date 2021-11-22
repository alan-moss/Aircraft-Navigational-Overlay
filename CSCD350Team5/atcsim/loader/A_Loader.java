package atcsim.loader;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.world.navigation.A_ComponentNavaid;
import atcsim.datatype.Latitude;
import atcsim.datatype.Longitude;
import atcsim.datatype.Altitude;
import atcsim.datatype.CoordinateWorld3D;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
public abstract class A_Loader {
	//global HashMap and OverlayNavigation
	protected HashMap<String, A_ComponentNavaid<?>> hm;
	protected OverlayNavigation on;
	//global scanner
	protected Scanner scan;
	//set the global HashMap and OverlayNavigation so that we can add items to it in the load method
	public A_Loader(HashMap<String, A_ComponentNavaid<?>> hm,OverlayNavigation on) {
		this.hm=hm;
		this.on=on;
	}
	//create the Latitude object by giving the degree, minutes, and seconds
	public Latitude Load_Lat(String[] array) {
		int lat_deg = Integer.parseInt(array[0]);
		int lat_min = Integer.parseInt(array[1]);
		int lat_sec = Integer.parseInt(array[2]);
		return new Latitude(lat_deg,lat_min,lat_sec);
	}
	//create the Longitude object by giving the degree, minutes, and seconds
	public Longitude Load_Lon(String[] array) {
		int lon_deg = Integer.parseInt(array[0]);
		int lon_min = Integer.parseInt(array[1]);
		int lon_sec = Integer.parseInt(array[2]);
		return new Longitude(lon_deg,lon_min,lon_sec);
	}
	//create the Altitude object by giving the altitude
	public Altitude Load_Alt(String str) {
		return new Altitude(Double.parseDouble(str));
	}
	//create the position of the object in the navigation.
	public CoordinateWorld3D Load_Pos(Latitude lan,Longitude lon,Altitude alt) {
		return new CoordinateWorld3D(lan,lon,alt);
	}
	//Basic Load that all subclass will override.
	//load will take the scanner and scan through the file and grab one line at a time until there is either
	//a blank space or it is at the end of the file.
	//
	public void Load(Scanner scan) throws IOException {
		
	}
}
