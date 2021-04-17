import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;

/**
 * Hello World!
 * 
 * This is the basic stub to start creating interactive maps.
 */
public class HelloUCSDWorld extends PApplet {
	
	UnfoldingMap googleMap;
	UnfoldingMap microsoftAerialMap;
	UnfoldingMap microsoftRoadMap;
	UnfoldingMap microsoftHybridMap;
	UnfoldingMap currentMap;


	public void setup() {
		size(800, 600, OPENGL);

		googleMap = new UnfoldingMap(this, new Google.GoogleTerrainProvider());
		
		microsoftAerialMap = new UnfoldingMap(this, new Microsoft.AerialProvider());
		
		microsoftRoadMap = new UnfoldingMap(this, new Microsoft.RoadProvider());
		
		microsoftHybridMap = new UnfoldingMap(this, new Microsoft.HybridProvider());
		
		currentMap = microsoftHybridMap;
		
		currentMap.zoomAndPanTo(14, new Location(32.881, -117.238)); // UCSD Location

		MapUtils.createDefaultEventDispatcher(this, currentMap);
	}

	public void draw() {
		background(0);
		currentMap.draw();
	}

}
