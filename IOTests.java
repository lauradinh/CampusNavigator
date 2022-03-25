 // --== CS400 File Header Information ==--
// Name: Letong Dai
// Email: ldai29@wisc.edu
// Team: ED
// TA: Keren Chen
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IOTests {
    @Test
    public void testLoadData() {
        try {
            String path = "./examples.json";
            String[] Vertices = {"Park Place", "Boardwalk",
                    "Sim City", "Isolation Town", "Riverdale"};
            int[][] Edges = {{0, 1}, {0, 2}, {0, 4}, {1, 2}, {1, 0}, {2, 1}, {2, 4}, {4, 0}};
            CS400Graph<String> test = Initialization.loadData(path);
            assertEquals(Vertices.length, test.getVertexCount(), 0); // check vertices number
            // check that each vertex is loaded
            for (int i = 0; i < Vertices.length; i++)
                assertTrue(test.containsVertex(Vertices[i]));
            assertEquals(Edges.length, test.getEdgeCount(), 0); // check edges number
            // check that each edge is loaded
            for (int i = 0; i < 6; i++)
                assertTrue(test.containsEdge(Vertices[Edges[i][0]], Vertices[Edges[i][1]]));
        } catch (Exception e) {
            fail();
        }
    }
}