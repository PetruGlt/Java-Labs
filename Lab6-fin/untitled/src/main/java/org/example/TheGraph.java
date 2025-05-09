package org.example;

import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.spanning.KruskalMinimumSpanningTree;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class TheGraph {
    private Graph graph;
     int value=0;
    TheGraph(List<Point> points){
        graph = GraphBuilder.empty()
                .estimatedNumVertices(points.size())
                .buildGraph();
        for (Point point : points) {
            graph.addLabeledVertex(point);
        }
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                    graph.addLabeledEdge(points.get(i), points.get(j), (int)sqrt(pow(points.get(i).x()-points.get(j).x(), 2)+pow(points.get(i).y()-points.get(j).y(), 2)));
            }
        }
        KruskalMinimumSpanningTree spanningTree = new KruskalMinimumSpanningTree(graph);
        List<Line> mstEdges = new ArrayList<>();
        Graph mst = spanningTree.getTree();

        for (Edge edge : mst.edges()) {
            int u = edge.source();
            int v = edge.target();
            Point from = (Point) graph.getVertexLabel(u);
            Point to = (Point) graph.getVertexLabel(v);
            mstEdges.add(new Line(from, to));
        }
        for (Line line : mstEdges) {
            value += (int)sqrt(pow(line.end().x()-line.start().x(), 2)+pow(line.end().y()-line.start().y(), 2));
        }
    }
    public void printGraph(){
        System.out.println("The best value possible : "+value);
    }
}
