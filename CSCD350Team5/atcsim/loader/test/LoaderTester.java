package atcsim.loader.test;
import java.io.IOException;
import atcsim.loader.*;

public class LoaderTester {
	public static void main(String[] args) throws IOException {
		//create the NavigationOverlayBuilder nb so that we can load in the file.
		NavigationOverlayBuilder nb=new NavigationOverlayBuilder("test id");
		//call the loadDefinition method so that we can test the method.
		nb.loadDefinition("definition1.txt");
	}
}