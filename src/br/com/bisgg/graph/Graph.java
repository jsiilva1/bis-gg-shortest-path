package br.com.bisgg.graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
*
* Classe Graph
*
* Responsável por conhecer e gerar o grafo a partir das informações do número de nós, arestas e das arestas formatadas
* vindas do arquivo
*
* */
public class Graph {

    private String graphFile;
    private int nodes;
    private int edges;

    public Graph (String graphFile) {
        this.graphFile = graphFile;
    }

    /*
    *
    * Método para criar a lógica das arestas vindo do arquivo, responsável por filtar ligações de origem e destino e identificar
      se há erros triviais nas ligações.
    *
    * */
    public List<String> getEdgesFromFile() {

        // Cria uma lista para armazenar e filtrar, mediante uma expressão Lambda, cada linha do arquivo
        List<String> rows;
        LinkedList<String> graph_data;

        try {

            // Instancia a classe intermediadora Eloquent para obter os dados, mesmo que ainda de forma crua, do arquivo do grafo;
            Eloquent eloquent = new Eloquent(this.graphFile);
            graph_data = eloquent.readGraphFile();

            /*
            *
            *
              Regra de negócio onde cada linha deve ter, necessariamente, depois de ter seu espaço removido, tamanho igual a dois,
              pois é ainda onde indica uma possibilida de existir uma aresta de entrada e saída; isso acontece enquanto a linha for -1 -1
            *
            *
            * */
            rows = graph_data
                    .stream()
                    .filter(line -> line.split(" ").length == 3 && !line.equals("-1 -1 -1"))
                    .collect(Collectors.toList());

            int graphSize = rows.size();

            // Número de nós e arestas do grafo
            int nodes = 0, edges = 0;

            int i = 0, j = 0;
            int indexOfError = 0;
            int lineOfError = 0;

            // Inicializa uma variável de controle de erro. Começa com a hipótese de um grafo válido, caso valor false há erro nas especificações das arestas
            boolean state = true;

            /*
            *
            * Verifica se o grafo contém o número de nós e arestas, respectivamente, na primeira e segunda linha do arquivo.
            *
            * Primeiro fragmento: verifica se a posição que é pra está o nó/aresta está vazia;
            * Segundo fragmento: verifica a quantidade de caracteres (retirando o espaço) que existe na linha, se for maior
              igual a 2 significa que aquela informação tende a ser de uma adjacência (V, A) origem/destino;
            *
            *
             * */
            if (graph_data.get(0).isEmpty() || graph_data.get(0).split(" ").length >= 2) {
                System.out.println("\bImpossível gerar o grafo.\nHá um erro nas especificações básicas de (V, A) na primeira linha do arquivo.");
            } else {

                // Obtém a quantidade de nós e arestas do grafo
                nodes = Integer.parseInt(graph_data.get(0));
                edges = graph_data.size() - 2;

                while (i < graphSize) {

                    /*
                     *
                     * Erro nas especificações das arestas
                     *
                     * Se o número de arestas (tamanho da lista) for menor que o numero de arestas esperadas dentro das ligações do arquivo,
                       retorna um erro, pois em algum momento a expressão lambda decidiu, por meio das especificações
                       da condição, não filtrar aquele item sem um vértice de destino e/ou origem, logo, para a condição ser satisfeita, o número de arestas
                       esperada no tamanho da lista de GraphData deve ser do tamanho da lista de Rows, onde as mesmas foram filtradas;
                     *
                     * */
                    if (rows.size() < edges) {
                        indexOfError = i - 1;
                        lineOfError = i;
                        state = false;
                    }
                    i++;
                }
            }

            if (!state)
                System.out.println("\bImpossível gerar o grafo.\nHá um erro na adjacência da posição " + indexOfError + " do arquivo.\n=> G(" + graph_data.get(lineOfError) + ", ?)");

            // Seta a quantidade de nós e arestas do grafo
            this.setNodes(nodes);
            this.setEdges(edges);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Exception in Classes: "+e);
        }

        return rows;
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    public int getEdges() {
        return edges;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }
}
