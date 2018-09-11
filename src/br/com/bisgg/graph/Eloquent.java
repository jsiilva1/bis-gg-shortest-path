package br.com.bisgg.graph;

import br.com.bisgg.persistencia.Persistencia;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Eloquent extends Persistencia {

    private String graph_file;

    public Eloquent (String graphFile) {
        this.graph_file = graphFile;
    }

    protected LinkedList<String> readGraphFile () throws IOException, ClassNotFoundException {

        LinkedList<String> objects = new LinkedList<>();

        try {
            // Se o arquivo de dados do grafo não é vazio, ele obtem os objetos, caso inverso, retorna o valor padrão nulo
            if (!this.isEmpty(this.graph_file))
                objects = this.get(this.graph_file);

        } catch (IOException e) {
            e.printStackTrace();
        }

       return objects;
    }
}
