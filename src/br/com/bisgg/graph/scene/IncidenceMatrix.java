package br.com.bisgg.graph.scene;

import java.util.ArrayList;

/*
*
* Classe responsável por gerar a matriz de incidência
*
* OBS: Comentários do métodos estão na interface principal
*
* */
public class IncidenceMatrix implements GraphSceneInterface {

    private int nodes;
    private int edges;
    private int[][] matrix;
    private int matrixSize;

    public IncidenceMatrix (int nodes, int edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.matrix = new int[nodes][edges];
        this.matrixSize = this.matrix.length;
    }

    public void set (int i, int j, int state) { this.matrix[i-1][j-1] = state; }

    @Override
    public void getLengthNode(int node) {

        int entryPoint = 0, exitPoint = 0;

        for (int i = 0; i < this.getMatrixSize(); i++) {
            if (this.matrix[node - 1][i] == 1)
                exitPoint++;

            if (this.matrix[i][node - 1] == -1 || this.matrix[i][node-1] == 1)
                entryPoint++;
        }

        System.out.println("\nGRAU DO VÉRTICE "+node+":");
        System.out.println("- Grau de entrada: "+entryPoint);
        System.out.println("- Grau de saída: "+exitPoint);
        System.out.println("-----------------------------------");
    }

    @Override
    public boolean isAdjacent(int n1, int n2) {
        return this.matrix[n1-1][n2-1] == 1 || this.matrix[n1-1][n2-1] == -1;
    }

    /*
    *
    * Procedimento helper para retornar, em tela, se um nó é adjacente a outro ou não, diferente do método acima, que retorna
      apenas um valor primitivo.
    *
    * */
    public void isAdjacentWithFullReturn (int n1, int n2) {

        if (this.isAdjacent(n1, n2)) {
            System.out.println("\nOs vértices "+n1+" e "+n2+" são adjacentes.");
            System.out.println("-----------------------------------");
        } else {
            System.out.println("\nOs vértices "+n1+" e "+n2+" não são adjacentes.");
            System.out.println("-----------------------------------");
        }
    }

    @Override
    public void getNodeAdjacency(int node) {

        ArrayList<Integer> adjacencyNodes = new ArrayList<>();

        for (int i = 0; i < this.getMatrixSize(); i++) {
            if (this.matrix[node - 1][i] == 1 || this.matrix[i][node-1] == 1 || this.matrix[i][node-1] == -1)
                adjacencyNodes.add(i + 1);
        }

        if (adjacencyNodes.size() != 0) {
            System.out.print("\nO conjunto de adjacências de αv("+node+") = {");

            for (int j = 0; j < adjacencyNodes.size(); j++) {
                System.out.print(adjacencyNodes.get(j));

                if (!(j == adjacencyNodes.size() - 1)) // Para tirar a vírgula depois do ultimo numero. Estética :)
                    System.out.print(", ");
            }

            System.out.print("}");
            System.out.println("\n-----------------------------------");
        } else {
            System.out.println("\nO conjunto de adjacências de αv("+node+") é nulo. {∅}");
            System.out.println("-----------------------------------");
        }

    }

    public int getMatrixSize () {
        return this.matrixSize;
    }

    public int[][] getMatrix () {
        return this.matrix;
    }

    public void show () {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.getMatrixSize(); i++) {
            for (int j = 0; j < this.getMatrixSize(); j++)
                s.append(this.matrix[i][j]).append(" ");
        }
        System.out.println(s.toString());
    }
}
