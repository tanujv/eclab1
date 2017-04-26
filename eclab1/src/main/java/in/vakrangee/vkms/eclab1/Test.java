/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.vakrangee.vkms.eclab1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tanujv
 */
public class Test {

    private static List<Vertex> nodes;
    private static List<Edge> edges;

    public static void main(String[] args) {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        int numberOfVertices;
        int numberOfEdges;
        int source = 0;
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Enter the number of vertices and number of edges in the graph respectively.");
            numberOfVertices = scan.nextInt();
            numberOfEdges = scan.nextInt();
            System.out.println("numberOfVertices: " + numberOfVertices);
            System.out.println("numberOfEdges: " + numberOfEdges);

            for (int i = 1; i <= numberOfVertices; i++) {
                Vertex location = new Vertex("Node_" + i, "Node_" + i);
                nodes.add(location);
            }
            System.out.println("numberOfVertices: " + nodes.size());
            for (Vertex vertex : nodes) {
                System.out.println(vertex.getId() + "|" + vertex.getName());
            }

            for (int i = 1; i <= numberOfEdges; i++) {
                int sourceVertex = scan.nextInt();
                int destinationVertex = scan.nextInt();
                int duration = scan.nextInt();
                Edge lane = new Edge("Edge_" + i, new Vertex("Node_" + sourceVertex, "Node_" + sourceVertex),
                        new Vertex("Node_" + destinationVertex, "Node_" + destinationVertex), duration);
                edges.add(lane);
            }
            System.out.println("numberOfEdges: " + edges.size());
            for (Edge edge : edges) {
                System.out.println(edge.getId() + "|" + edge.getWeight());
            }
            int fromVertex = scan.nextInt();
            int toVertex = scan.nextInt();
            Graph graph = new Graph(nodes, edges);
            ECLab1 dijkstra = new ECLab1(graph);

            dijkstra.execute(new Vertex("Node_" + fromVertex, "Node_" + fromVertex));

            LinkedList<Vertex> path = dijkstra.getPath(new Vertex("Node_" + toVertex, "Node_" + toVertex));

            System.out.println("************");
            int cost = 0;
            int noOfEdges = path.size() - 1;

            int edgeNo = 0;
            Vertex newVertex = null;
            for (Vertex vertex : path) {

                if (edgeNo == 0) {
                    newVertex = vertex;
                } else {
                    System.out.println(vertex);
                    cost = cost + dijkstra.getDistance(newVertex, vertex);
                    newVertex = vertex;
                }
                edgeNo++;
            }

            System.out.println("************");
            System.out.println(cost + " " + noOfEdges);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
}
