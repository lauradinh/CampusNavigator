// --== CS400 File Header Information ==--
// Name: Laura Dinh
// Email: lmdinh@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import static java.util.stream.Collectors.toList;

/**
 * Creates back end portion of UW Campus Navigator which provides information
 * about the map
 * 
 * @author Laura Dinh
 *
 */
public class BackEnd implements IBackEnd {
	private CS400Graph<String> campusMap;

	/**
	 * Loads UW Campus information into a graph
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public BackEnd() throws ParseException, IOException {
			campusMap = Initialization.loadData("madison.json");
	}

	/**
	 * A list of all locations present on the graph
	 * 
	 * @return a list of strings corresponding to locations present in the graph
	 */
	@Override
	public List<String> getLocations() {
		List<String> buildingNames = campusMap.vertices.values().stream().map(v -> v.data).sorted().collect(toList());
		return buildingNames;
	}

	/**
	 * The path of between two a buildings
	 * 
	 * @return a string of the path with each building separated by "->"
	 */
	@Override
	public String shortestDistance(String startingBuilding, String destinationBuilding) {
		String shortestDistance = "";
		try {
			List<String> path = campusMap.shortestPath(startingBuilding, destinationBuilding);
			for (int i = 0; i < path.size() - 1; i++) {
				shortestDistance += path.get(i) + "-> ";
			}
			shortestDistance += path.get(path.size() - 1);
		} catch (NoSuchElementException e) {
			shortestDistance = "A path between " + startingBuilding + " and " + destinationBuilding + " does not exist";
		}

		return shortestDistance;
	}

	/**
	 * The cost of the shortest distance between two buildings
	 * 
	 * @return the shortest distance cost, returns a distance cost of -1 when path is not valid. 
	 */
	@Override
	public int getDistanceCost(String startingBuilding, String destinationBuilding) {
		int distanceCost;
		try {
			distanceCost = campusMap.getPathCost(startingBuilding, destinationBuilding);
		} catch (NoSuchElementException e) {
			distanceCost = -1;
		}
		return distanceCost;
	}

}
