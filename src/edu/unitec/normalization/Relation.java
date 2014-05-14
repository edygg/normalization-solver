package edu.unitec.normalization;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Edilson
 */
public class Relation {

    public static final String RELATION_REGEX = "[r|R][(]([A-Z][,])*[A-Z][)]";
    private List<Field> fields;
    private List<FunctionalDependency> functionalDependencies;
    private DirectedSparseGraph<String, Edge> graph;

    public Relation() {
        this.fields = new ArrayList();
        this.functionalDependencies = new ArrayList();
        this.graph = graph = new DirectedSparseGraph();
    }

    public boolean addField(Field field) {
        if (field == null) {
            return false;
        }

        if (this.fields.contains(field)) {
            return false;
        }
        return this.fields.add(field);
    }

    public boolean addFunctionalDependency(FunctionalDependency fd) {
        if (fd == null) {
            return false;
        }

        if (this.functionalDependencies.contains(fd)) {
            return false;
        }
        return this.functionalDependencies.add(fd);
    }

    public List<String> candidateKeys() {
        List<String> keys = new ArrayList();
        double[][] shortPaths = floyd();
        System.out.println(graph.getVertices());

        //Obtiene lo vértices del grafo y los ordena.
        Object[] vertices1 = graph.getVertices().toArray();
        Arrays.sort(vertices1);
        for (int i = 0; i < vertices1.length - 1; i++) {
            for (int j = i + 1; j < vertices1.length; j++) {
                if (((String) vertices1[i]).length() > ((String) vertices1[j]).length()) {
                    Object t = vertices1[j];
                    vertices1[j] = vertices1[i];
                    vertices1[i] = t;
                }
            }
        }
        for (int i = 0; i < vertices1.length; i++) {
            System.out.print(vertices1[i] + "\t");
        }
        System.out.println("");
        for (int i = 0; i < shortPaths.length; i++) {
            for (int j = 0; j < shortPaths.length; j++) {
                if (shortPaths[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("-1.0" + "\t");
                } else {
                    System.out.print("+" + shortPaths[i][j] + "\t");
                }

            }
            System.out.println("");
        }
        System.out.println("\n\n\n");
        for (int i = 0; i < shortPaths.length; i++) {
            if (validate(shortPaths, i)) {
                keys.add((String) vertices1[i]);
            }
        }

        /*Aqui comienza a combinar fields para poder crear llaves compuestas*/
        /*No debe estar vacia para que se corra este metodo, pero solo es una prueba*/
        int f_size = this.fields.size();
        ArrayList<String> add;
        System.out.println("");

        for (int i = f_size; i < shortPaths.length; i++) {
            add = shotgun(shortPaths, i, (String) vertices1[i]);
            System.out.println("");
            System.out.println("");
            for (int j = 0; j < add.size(); j++) {
                if (add.get(j).length() != 0 || add.get(j).length() != this.fields.size()) {
                    if (keys.indexOf(add.get(j)) == -1) {
                        keys.add(add.get(j));
                    }
                }

            }
        }

        return keys;
    }

    private ArrayList<String> shotgun(double[][] x, int k, String t1) {
        ArrayList<String> qwerty2 = new ArrayList();
        double[] temp = x[k];
        /*ArrayList<Integer> positions = new ArrayList();
         for (int j = 0; j < this.fields.size(); j++) {
         if (temp[j] == Double.POSITIVE_INFINITY) {
         positions.add(j);
         }
         }*/
        Object[] vertices1 = graph.getVertices().toArray();
        Arrays.sort(vertices1);
        for (int i = 0; i < vertices1.length - 1; i++) {
            for (int j = i + 1; j < vertices1.length; j++) {
                if (((String) vertices1[i]).length() > ((String) vertices1[j]).length()) {
                    Object t = vertices1[j];
                    vertices1[j] = vertices1[i];
                    vertices1[i] = t;
                }
            }
        }
        /*R(A,B,C,D,E,F,G,H,I,J)*/
        /*AB->C,BD->FE,AD->GH,A->I,H->J*/
        /*for (int j = 0; j < positions.size(); j++) {
         retval += (String) vertices1[positions.get(j)];
         }*/
        for (int j = 0; j < this.fields.size(); j++) {
            if (temp[j] == Double.POSITIVE_INFINITY) {
                temp[j] = -1.0;
            }
        }
        for (int i = k + 1; i < vertices1.length; i++) {
            double[] temp2 = x[i];

            for (int j = 0; j < this.fields.size(); j++) {
                if (temp2[j] == Double.POSITIVE_INFINITY) {
                    temp2[j] = -1.0;
                }
            }
            System.out.println("Temp1: ");
            for (int j = 0; j < this.fields.size(); j++) {
                System.out.print(temp[j] + "\t");
            }
            System.out.println("");
            System.out.println("Temp2: ");
            for (int j = 0; j < this.fields.size(); j++) {
                System.out.print(temp2[j] + "\t");
            }
            System.out.println("");
            double[] temp3 = new double[this.fields.size()];
            for (int j = 0; j < this.fields.size(); j++) {
                temp3[j] = temp[j] + temp2[j];
            }
            System.out.println("Temp3: ");
            for (int j = 0; j < this.fields.size(); j++) {
                System.out.print(temp3[j] + "\t");
            }
            System.out.println("");
            boolean simon = true;
            for (int j = 0; j < temp3.length; j++) {
                if (temp3[j] < 0) {
                    simon = false;
                    j = temp3.length;
                }
            }
            if (simon) {
                ArrayList<Character> qwerty = new ArrayList();
                for (int m = 0; m < t1.length(); m++) {
                    qwerty.add(t1.charAt(m));
                }
                String novo = (String) vertices1[i];
                for (int j = 0; j < novo.length(); j++) {
                    if (qwerty.indexOf(novo.charAt(j)) == -1) {
                        qwerty.add(novo.charAt(j));
                    }

                }
                Object[] asd = qwerty.toArray();
                Arrays.sort(asd);
                String y = "";
                for (int j = 0; j < qwerty.size(); j++) {
                    y += (Character) asd[j];
                }
                qwerty2.add(y);

            }
            if (i == vertices1.length - 1 && qwerty2.isEmpty()) {
                ArrayList<Character> qwerty = new ArrayList();
                for (int m = 0; m < t1.length(); m++) {
                    qwerty.add(t1.charAt(m));
                }
                for (int j = k + 1; j < vertices1.length; j++) {
                    String temporal = (String) vertices1[j];
                    for (int l = 0; l < temporal.length(); l++) {
                        if (qwerty.indexOf(temporal.charAt(l)) == -1) {
                            qwerty.add(temporal.charAt(l));
                        }
                    }
                }
                Object[] asd = qwerty.toArray();
                Arrays.sort(asd);
                String y = "";
                for (int j = 0; j < qwerty.size(); j++) {
                    y += (Character) asd[j];
                }
                qwerty2.add(y);
            }
        }
        return qwerty2;
    }

    private boolean validate(double[][] x, int i) {
        for (int j = 0; j < fields.size(); j++) {
            if (x[i][j] == Double.POSITIVE_INFINITY) {
                return false;
            }
        }

        return true;
    }

    private double[][] floyd() {
        double[][] shortPaths = createMatrix();
        //Corriendo las comparaciones del algoritmo de Floyd
        for (int k = 0; k < shortPaths.length; k++) {
            for (int i = 0; i < shortPaths.length; i++) {
                for (int j = 0; j < shortPaths.length; j++) {
                    if (shortPaths[i][k] + shortPaths[k][j] < shortPaths[i][j]) {
                        shortPaths[i][j] = shortPaths[i][k] + shortPaths[k][j];
                    }
                }
            }
        }
        return shortPaths;
    }

    private double[][] createMatrix() {
        //Adding vertices
        String temp;
        for (int i = 0; i < this.fields.size(); i++) {
            temp = this.fields.get(i).getFieldNames().get(0) + "";
            graph.addVertex(temp);
            graph.addEdge(new Edge(1), temp, temp);
        }
        //Adding edges
        String temp_vertex;
        FunctionalDependency temp_dependency;
        for (int i = 0; i < this.functionalDependencies.size(); i++) {
            temp_vertex = "";
            temp_dependency = this.functionalDependencies.get(i);
            for (int j = 0; j < temp_dependency.getLeftFields().size(); j++) {
                temp_vertex += temp_dependency.getLeftFields().get(j);
            }
            graph.addVertex(temp_vertex);

            for (int j = 0; j < temp_dependency.getLeftFields().size(); j++) {
                graph.addEdge(new Edge(1), temp_vertex, temp_dependency.getLeftFields().get(j) + "");
            }

            for (int j = 0; j < temp_dependency.getRightFields().size(); j++) {
                graph.addEdge(new Edge(1), temp_vertex, temp_dependency.getRightFields().get(j) + "");
            }
        }
        //Inicializa la matriz de caminos más cortos
        double[][] shortPaths = new double[graph.getVertexCount()][graph.getVertexCount()];

        //Rellena la matriz con infinitos para asegurar que en los vértices
        //donde no haya camino se represente correctamente.
        for (int i = 0; i < shortPaths.length; i++) {
            Arrays.fill(shortPaths[i], Double.POSITIVE_INFINITY);
            shortPaths[i][i] = 0;
        }

        //Obtiene lo vértices del grafo y los ordena.
        Object[] vertices = graph.getVertices().toArray();
        Arrays.sort(vertices);
        for (int i = 0; i < vertices.length - 1; i++) {
            for (int j = i + 1; j < vertices.length; j++) {
                if (((String) vertices[i]).length() > ((String) vertices[j]).length()) {
                    Object t = vertices[j];
                    vertices[j] = vertices[i];
                    vertices[i] = t;
                }
            }
        }

        //Verifica el camino hacia cada uno de los vértices.
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (i != j && graph.findEdge((String) vertices[i], (String) vertices[j]) != null) {
                    shortPaths[i][j] = graph.findEdge((String) vertices[i], (String) vertices[j]).getWeight();
                } else if (!((String) vertices[i]).equals((String) vertices[j])) {
                    shortPaths[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        return shortPaths;
    }

    public boolean hasField(Field field) {
        return this.fields.contains(field);
    }

    @Override
    public String toString() {
        String retVal = "Relation{";

        for (int i = 0; i < this.fields.size(); i++) {
            if (i == this.fields.size() - 1) {
                retVal += this.fields.get(i);
            } else {
                retVal += this.fields.get(i) + ",";
            }
        }

        return retVal + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Relation other = (Relation) obj;
        for (int i = 0; i < other.fields.size(); i++) {
            if (!this.fields.contains(other.fields.get(i))) {
                return false;
            }
        }
        return true;
    }

    public List<FunctionalDependency> getFunctionalDependencies() {
        return this.functionalDependencies;
    }
    
    public List<Field> getFields() {
        return this.fields;
    }
}
