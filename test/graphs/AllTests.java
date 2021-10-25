package graphs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GraphNodeTest.class, IsDrainNodeTest.class, IsSourceNodeTest.class, L4_Graph_sampleTest.class,
		L5_Floyd_EvalTest.class, L5_Graph_Floyd_DFP_sampleTest.class, L6_TestDijkstra.class, LecturesFloydTest.class,
		PrintFloydTest.class })
public class AllTests {

}
