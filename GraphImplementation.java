import java.lang.Exception;
import java.util.List;
import java.util.ArrayList;

public class GraphImplementation implements Graph //from class notes
{
	private int[][] adjacencyMatrix;
	private int[] incident;

	public GraphImplementation(int vertices)
	{
		//create adj matrix to keep track of where edges come from and go to
		adjacencyMatrix = new int[vertices][vertices];
		//also create incident array to keep track of numbers connected to vertex
		incident = new int[vertices];

	}

	public void addEdge(int v1, int v2) throws Exception
	{
		//if no vertex exists at v1 or v2, throw Exception
		if (v1 > adjacencyMatrix.length || v1 < 0 || v2 > adjacencyMatrix.length || v2 < 0)
		{
			throw new Exception("No vertex exists at one or both of these locations");
		}
		
		if (v1 != v2) //if v1 doesn't equal v2
		{
			//then add the edge between them
			adjacencyMatrix[v1][v2] = 1;
			//and increment vertex to have another incoming edge
			incident[v2]++;
		}
	}

	public List<Integer> topologicalSort()
	{
		List<Integer> schedule = new ArrayList<Integer>();
		
		for (int i = 0; i < adjacencyMatrix.length; i++)
		{
			int v = noIncidents(incident);
			if (v != -1)
			{
				schedule.add(v); //add vertex to list
				incident[v] = -1; //mark vertex as visited
				for (int j = 0; j < adjacencyMatrix.length; j++)
				{
					if (adjacencyMatrix[v][j] == 1) //if vertices are connected
					{
						incident[j] -= 1; //mark as visited by one more vertex
					}
				}
			}
		}

		return schedule;
	}

	private int noIncidents(int[] incidents)
	{
		for (int i = 0; i < incidents.length; i++)
		{
			if (incidents[i] == 0)
			{
				return i;
			}
		}

		return -1;
	}

	public List<Integer> neighbors(int vertex) throws Exception
	{
		List<Integer> neighbors = new ArrayList<Integer>();

		if (vertex > adjacencyMatrix.length || vertex < 0) //if no vertex exists at integer vertex, throw Exception
		{
			throw new Exception("No vertex exists at this location");
		}

		for (int i = 0; i < adjacencyMatrix.length; i++)
		{
			if (adjacencyMatrix[vertex][i] == 1) //if vertex has connection to other vertex i
			{
				neighbors.add(i); //add i to list of neighbors of vertex
			}
		}

		return neighbors;
	}
}