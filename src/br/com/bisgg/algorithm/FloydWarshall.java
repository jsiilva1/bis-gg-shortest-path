package br.com.bisgg.algorithm;

import br.com.bisgg.graph.Graph;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class FloydWarshall {

    private Graph graph;
    private int numberOfNodes;

    public FloydWarshall (Graph graph, int numberOfNodes) {
        this.graph = graph;
        this.numberOfNodes = numberOfNodes;
        this.execute();
    }

    public void execute () {

        double[][] dist = new double[this.numberOfNodes][this.numberOfNodes];
        List<String> rowsInEdges = this.graph.getEdgesFromFile();

        // Inicilizado a matriz com cada distancia sendo infinita
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);

        for (int i = 0; i < rowsInEdges.size(); i++) {
            int from = Integer.parseInt(rowsInEdges.get(i).split(" ")[0]);
            int to = Integer.parseInt(rowsInEdges.get(i).split(" ")[1]);
            int weigth = Integer.parseInt(rowsInEdges.get(i).split(" ")[2]);

            dist[from-1][to-1] = weigth;
        }

        int[][] next = new int[this.numberOfNodes][this.numberOfNodes];

        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }

        for (int k = 0; k < this.numberOfNodes; k++)
            for (int i = 0; i < this.numberOfNodes; i++)
                for (int j = 0; j < this.numberOfNodes; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }

        printResult(dist, next);
    }

    private static void printResult (double[][] dist, int[][] next) {
        System.out.println("Arestas:\t Distancia:  \tMenor caminho:");

        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;

                    String path = format("%d -> %d \t\t %2d \t\t\t %s", u, v,
                            (int) dist[i][j], u);
                    do {
                        u = next[u - 1][v - 1];
                        path += " -> " + u;
                    } while (u != v);

                    System.out.println(path);
                }
            }
        }
    }
}
