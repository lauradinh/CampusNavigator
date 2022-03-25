// --== CS400 File Header Information ==--
// Name: Laura Dinh
// Email: lmdinh@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import java.util.List;

public interface IBackEnd {

	/**
	 * Gets a list of strings corresponding to locations present in the graph
	 * 
	 * @return a list of building names
	 */
	public List<String> getLocations();

	/**
	 * The shortest path between two buildings as a string separated by arrows
	 * 
	 * @param startingBuilding    the first building the person begins at
	 * @param destinationBuilding the second building the person ends at
	 * @return the shortest path between two buildings
	 */
	public String shortestDistance(String startingBuilding, String destinationBuilding);

	/**
	 * The shortest distance between two buildings
	 * 
	 * @param startingBuilding    the first building the person begins at
	 * @param desintationBuilding the second building the person ends at
	 * @return the shortest distance between two buildings
	 */
	public int getDistanceCost(String startingBuilding, String desintationBuilding);

}
