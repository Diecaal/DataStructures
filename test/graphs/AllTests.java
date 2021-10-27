package graphs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ContainsCycleTest.class, GraphNodeTest.class, IsDrainNodeTest.class, IsSourceNodeTest.class,
		IsStronglyConnectedTest.class, L4_Graph_sampleTest.class, L5_Floyd_EvalTest.class,
		L5_Graph_Floyd_DFP_sampleTest.class, L6_TestDijkstra.class, L6B_Exercises_sampleTest.class,
		LecturesFloydTest.class, PrintFloydTest.class })
public class AllTests {

}
