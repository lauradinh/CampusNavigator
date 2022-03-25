  
import java.io.*;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Initialization {
    public static CS400Graph<String> loadData(String filepath) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(filepath));
        JSONArray places = (JSONArray) obj;
        Iterator<JSONObject> jitr = places.iterator();
        CS400Graph<String> madison = new CS400Graph<>();
        while (jitr.hasNext()) {
            JSONObject place = jitr.next();
            madison.insertVertex((String) place.get("name"));
        }
        jitr = places.iterator();
        while (jitr.hasNext()) {
            JSONObject place = jitr.next();
            String name = (String) place.get("name");
            JSONArray neighbors = (JSONArray) place.get("neighbors");
            JSONArray distances = (JSONArray) place.get("distances");
            int size = neighbors.size();
            for (int i = 0; i < size; i++) {
                madison.insertEdge(name, (String) neighbors.get(i), Math.toIntExact((long) distances.get(i)));
            }
        }

        return madison;
    }

    public static void main(String[] args) {
        CS400Graph<String> map;
        try {
            BackEnd back = new BackEnd();
            map = loadData("./src/madison.json");
            System.out.println(map.getPathCost("Asian Midway Foods", "Sterling Hall"));
            System.out.println(back.getDistanceCost("Asian Midway Foods", "Sterling Hall"));
            System.out.println(map.getPathCost("Asian Midway Foods", "Smith Residence Hall"));
            System.out.println(back.getDistanceCost("Asian Midway Foods", "Smith Residence Hall"));
            System.out.println(map.getPathCost("Asian Midway Foods", "Lucky Apartments"));
            System.out.println(back.getDistanceCost("Asian Midway Foods", "Lucky Apartments"));
            System.out.println(map.getPathCost("Asian Midway Foods", "Witte Residence Hall"));
            System.out.println(back.getDistanceCost("Asian Midway Foods", "Witte Residence Hall"));
            System.out.println(map.getPathCost("Asian Midway Foods", "Wendt Commons"));
            System.out.println(back.getDistanceCost("Asian Midway Foods", "Wendt Commons"));
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}