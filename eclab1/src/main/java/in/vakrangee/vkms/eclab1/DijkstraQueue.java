/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.vakrangee.vkms.eclab1;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author tanujv
 */
public class DijkstraQueue {

    private int distances[];
    private Queue<Integer> queue;
    private Set<Integer> settled;
    private int number_of_nodes;
    private int adjacencyMatrix[][];

    public DijkstraQueue(int number_of_nodes) {
        this.number_of_nodes = number_of_nodes;
        distances = new int[number_of_nodes + 1];
        settled = new HashSet<Integer>();
        queue = new LinkedList<Integer>();
        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
    }

    public void dijkstra_algorithm(int adjacency_matrix[][], int source) {
        int evaluationNode;
        for (int i = 1; i <= number_of_nodes; i++) {
            for (int j = 1; j <= number_of_nodes; j++) {
                adjacencyMatrix[i][j] = adjacency_matrix[i][j];
            }
        }

        for (int i = 1; i <= number_of_nodes; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        queue.add(source);
        distances[source] = 0;

        while (!queue.isEmpty()) {
            evaluationNode = getNodeWithMinimumDistanceFromQueue();
            settled.add(evaluationNode);
            evaluateNeighbours(evaluationNode);
        }
    }

    private int getNodeWithMinimumDistanceFromQueue() {
        int min;
        int node = 0;
        Iterator<Integer> iterator = queue.iterator();
        node = iterator.next();
        min = distances[node];

        for (int i = 1; i <= distances.length; i++) {
            if (queue.contains(i)) {
                if (distances[i] <= min) {
                    min = distances[i];
                    node = i;
                }
            }
        }
        queue.remove(node);
        return node;
    }

    private void evaluateNeighbours(int evaluationNode) {
        int edgeDistance = -1;
        int newDistance = -1;

        for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++) {
            if (!settled.contains(destinationNode)) {
                if (adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE) {
                    edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
                    newDistance = distances[evaluationNode] + edgeDistance;
                    if (newDistance < distances[destinationNode]) {
                        distances[destinationNode] = newDistance;
                    }
                    queue.add(destinationNode);
                }
            }
        }
    }

    public static void main(String... arg) {
        int adjacency_matrix[][];
        int numberOfVertices;
        int numberOfEdges;
        int source = 0;
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Enter the number of vertices and number of edges in the graph respectively.");
            numberOfVertices = scan.nextInt();
            numberOfEdges = scan.nextInt();
            adjacency_matrix = new int[numberOfVertices + 1][numberOfVertices + 1];

            System.out.println("Enter the Weighted Matrix for the graph");
            for (int i = 1; i <= numberOfVertices; i++) {
                for (int j = 1; j <= numberOfVertices; j++) {
                    adjacency_matrix[i][j] = scan.nextInt();
                    if (i == j) {
                        adjacency_matrix[i][j] = 0;
                        continue;
                    }
                    if (adjacency_matrix[i][j] == 0) {
                        adjacency_matrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            System.out.println("Enter the source ");
            source = scan.nextInt();
            DijkstraQueue dijkstrasQueue = new DijkstraQueue(numberOfVertices);
            dijkstrasQueue.dijkstra_algorithm(adjacency_matrix, source);

            System.out.println("The Shorted Path to all nodes are ");
            for (int i = 1; i <= dijkstrasQueue.distances.length - 1; i++) {
                System.out.println(source + " to " + i + " is " + dijkstrasQueue.distances[i]);
            }
        } catch (InputMismatchException inputMismatch) {
            System.out.println("Wrong Input Format");
        }
        scan.close();
    }
}
