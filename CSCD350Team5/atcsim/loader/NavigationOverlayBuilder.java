package atcsim.loader;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import atcsim.graphics.view.navigation.OverlayNavigation;
import atcsim.loader.navaid.*;
import atcsim.world.navigation.A_ComponentNavaid;
public class NavigationOverlayBuilder {
	//Private variables for the NavigationOverlayBuilder
	//String id is the id for the NavigationOverlayBuilder for identification
	protected String id;
	//hm is the global HashMap that all loaders called in the loadDefinition will recieve
	protected HashMap<String, A_ComponentNavaid<?>> hm;
	//on is the global OverlayNaviagtion that all loaders called will recieve.
	protected OverlayNavigation on;
	//create the HashMap and the OverlayNavigation that the loaders will use.
	protected Scanner scan;
	public NavigationOverlayBuilder(String str){
		id=str;
		hm=new HashMap<String, A_ComponentNavaid<?>>();
		on=new OverlayNavigation(str);
	}
	public String getID() {
		return this.id;
	}
	public OverlayNavigation loadDefinition(String filespec) throws java.io.IOException{
		//assign the file to the scanner
		//skip over the first line as the first line is the header.
		scan=new Scanner(new File(filespec));
		//first fix
		LoaderFix fix=new LoaderFix(hm,on);
		fix.Load(scan);
		//Second NDB
		LoaderNDB ndb=new LoaderNDB(hm,on);
		ndb.Load(scan);
		//Third VOR
		LoaderVOR vor=new LoaderVOR(hm,on);
		vor.Load(scan);
		//Fourth ILS
		LoaderILS ils=new LoaderILS(hm,on);
		ils.Load(scan);
		//Fifth AIRWAY
		LoaderAirway air=new LoaderAirway(hm,on);
		air.Load(scan);
		//close the scanner
		scan.close();
		return on;
	}
}

