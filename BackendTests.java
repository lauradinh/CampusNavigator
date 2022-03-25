// --== CS400 File Header Information ==--
// Name: Letong Dai
// Email: ldai29@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BackendTests {
    private static IBackEnd test;
    private static String[] testData;

    @BeforeAll
    public static void init() {
        try {
            test = new BackEnd();
            Object[] data = Initialization.loadData("./madison.json").vertices.keySet().toArray();
            testData = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                testData[i] = (String) data[i];
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetLocation() {
        try {
            List<String> locations = test.getLocations();
            assertEquals(testData.length, locations.size(), 0);
            for (int i = 0; i < testData.length; i++)
                assertTrue(locations.contains(testData[i]));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testShortestDistancePath() {
        try {
            String result1 = "Chemistry Building-> Noland Zoology Building-> " +
                    "Sellery Residence Hall-> Ogg Residence Hall";
            String result2 = "Chemistry Building-> Union South";
            assertEquals(result1, test.shortestDistance(testData[0], testData[2]));
            assertEquals(result2, test.shortestDistance(testData[0], testData[3]));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testShorestDistanceCost() {
        try {
            int result1 = 6;
            int result2 = 3;
            assertEquals(result1, test.getDistanceCost(testData[0], testData[2]));
            assertEquals(result2, test.getDistanceCost(testData[0], testData[3]));
        } catch (Exception e) {
            fail();
        }
    }
}