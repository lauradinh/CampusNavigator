// --== CS400 File Header Information ==--
// Name: Letong Dai
// Email: ldai29@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FrontendTests {
    private FrontEnd test;
    private ByteArrayOutputStream testOutput;

    @Test
    public void testFrontEnd() {
        // this test should stop after at most 5s
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            try {
                BackEnd backend = new BackEnd();
                String[] locations = locations = backend.getLocations().toArray(String[]::new);
                test = new FrontEnd();
                testOutput = new ByteArrayOutputStream(); // contains the output of the program
                System.setOut(new PrintStream(testOutput)); // set the output to testOutput
                // test input:
                // 1-single trip, 3-from the third location, 5-to the fifth location
                // <enter>
                // 2-multiple trips, 2-two trips
                // the first trip: 2-from the second location, 1-to the first location
                // the second trip: 4-from the fourth location, 3-to the third location
                // <enter>
                // q-quit, yes
                String testData = "1\n3\n5\n\n2\n2\n2\n1\n\n4\n3\n\n\nq\nyes\n";
                ByteArrayInputStream testInput = new ByteArrayInputStream(testData.getBytes());
                System.setIn(testInput); // set the input to testInput
                FrontEnd.main(null); // run program
                String result = testOutput.toString().replaceAll("[\\f\\n\\r\\t\\v]", "");
                // check no error message
                assertFalse(result.matches(".*ERROR: File wasn't able to be read.*"));
                // check single trip result
                assertTrue(result.matches(".*Path:" + backend.shortestDistance(locations[2], locations[4]) +
                        "Distance: " + backend.getDistanceCost(locations[2], locations[4]) + " blocks.*"));
                // check multiple trips result (first trip)
                assertTrue(result.matches(".*Path:" + backend.shortestDistance(locations[2], locations[4]) +
                        "Distance: " + backend.getDistanceCost(locations[2], locations[4]) + " blocks.*"));
                // check multiple trips result (second trip)
                assertTrue(result.matches(".*Path:" + backend.shortestDistance(locations[2], locations[4]) +
                        "Distance: " + backend.getDistanceCost(locations[2], locations[4]) + " blocks.*"));
            } catch (Exception e) {
                fail();
            }
        });
    }

    @AfterAll
    public static void restoreStream() {
        System.setIn(System.in);
        System.setOut(System.out);
    }
}