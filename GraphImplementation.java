import java.util.*;
import java.lang.Exception;

public class GraphImplementation implements Graph
{
	//Instance data
	private int vertices;
	private int matrix[][];
	
	//Default constructor that defines instance data and creates a matrix
	public GraphImplementation(int vertices)
	{
		this.vertices = vertices;
		this.matrix = new int[this.vertices][this.vertices];
	}

	//Adds an edge between two vertices
	public void addEdge(int src, int tar) throws Exception
	{
        //Throws an error if any of these cases are breached
		if (src >= vertices || src < 0 || tar >= vertices || tar < 0)
		{
            throw new Exception();
        }
		
		//Creates an edge
		matrix[src][tar] = 1;
	}

	//Returns a list of neighboring vertex IDs
	public List<Integer> neighbors(int vertex) throws Exception
	{
        //Throws an error if the vertex in question does not exist
		if (vertex >= vertices || vertex < 0)
		{
            throw new Exception();
		}
		
		//Creates a list to track neighbors that will be returned
		List<Integer> neighbors = new ArrayList<Integer>();
        
		//Populates the list
		for (int i = 0; i < vertices; i++)
		{
            if (matrix[vertex][i] > 0)
            {
                neighbors.add(i);
            }
        }

        //Returns the list
		return neighbors;
	}

	//Prints a sorted list of vertices
	public List<Integer> topologicalSort()
	{
		System.out.print("Solution: ");
        List<Integer> solution = new ArrayList<>();

        int[] sum = new int[matrix.length];

        for (int i = 0; i < vertices; i++)
        {
            for (int j = 0; j < vertices; j++)
            {
                sum[i] += matrix[j][i];
            }
        }

        for (int count = 0; count < vertices; count++)
        {
            int n = findZero(sum);
			
			if (n == -1)
			{
                System.out.println("\nAn ordering of this graph is not possible.");

                return solution;
            }

            solution.add(n);
            System.out.print(n + " ");
            sum[n] = -1;
			
			for (int i = 0; i < vertices; i++)
            {
                sum[i] -= matrix[n][i];
            }
        }

        System.out.println();
        return solution;
	}

	//Finds the next zero in the sum
	int findZero(int[] sum)
	{
		for (int i = 0; i < vertices; i++)
        {
            if (sum[i] == 0)
            {
				return i;
            }
        }

        return -1;
	}
}