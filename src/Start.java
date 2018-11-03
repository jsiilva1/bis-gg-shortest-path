import br.com.bisgg.graph.Graph;
import br.com.bisgg.graph.scene.IncidenceMatrix;
import br.com.bisgg.util.Helpers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Start {


    public static void main (String... args) throws ClassNotFoundException, IOException {

        // Instancia a classe mãe do sistema passando como parâmetro o arquivo que é pra ser gerado o grafo
        new Start("graph_data_1");
    }

    public Start(String graphFileName) throws ClassNotFoundException, IOException {

        // Instancia a super classe Graph passando como parâmetro o arquivo que é pra ser gerado o grafo
        Graph graph = new Graph(graphFileName);
        List<String> rowsInEdges = graph.getEdgesFromFile(); // obtem a lista de arestas do grafo

        Scanner in = new Scanner(System.in);

        // Exibição do menu principal
        Helpers.screenHomeStart();
        System.out.println("(Digite somente valores numéricos entre 1-"+graph.getNodes()+")");
        System.out.println("---------------------------------------------------------------\n");

    }
}
