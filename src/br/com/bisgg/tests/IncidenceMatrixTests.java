package br.com.bisgg.tests;

import br.com.bisgg.algorithm.FloydWarshall;
import br.com.bisgg.graph.Graph;
import br.com.bisgg.graph.scene.IncidenceMatrix;

import java.io.IOException;
import java.util.List;

public class IncidenceMatrixTests {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Graph graph = new Graph("graph_data_4");
        List<String> rowsInEdges = graph.getEdgesFromFile();

        IncidenceMatrix incidenceMatrix = new IncidenceMatrix(graph.getNodes(), graph.getEdges());

        // Executa o método de Floyd
        //FloydWarshall floyd = new FloydWarshall(graph, graph.getNodes());



        for (int i = 0; i < rowsInEdges.size(); i++) {
            int from = Integer.parseInt(rowsInEdges.get(i).split(" ")[0]);
            int to = Integer.parseInt(rowsInEdges.get(i).split(" ")[1]);
            int weigth = Integer.parseInt(rowsInEdges.get(i).split(" ")[2]);

            System.out.println(from+" => "+to+" : "+weigth+"M");
        }
    }
}