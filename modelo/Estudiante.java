package proyectoLibretaDeNotas.modelo;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private List<Double> notas;

    // Constructor que recibe solo el nombre, la lista de notas se inicializa vacía
    public Estudiante(String nombre) {
        this.nombre = nombre;
        this.notas = new ArrayList<>(); // Inicializa la lista de notas vacía
    }


    public String getNombre() {
        return nombre;
    }

    public List<Double> getNotas() {
        return notas; // Retorna la lista de notas
    }

    // Método para añadir una nota individual al estudiante
    public void agregarNota(double nota) {
        this.notas.add(nota);
    }


    public double calcularPromedio() {
        if (notas.isEmpty()) { // Correcto: verifica si la lista está vacía
            return 0.0;
        }
        double suma = 0.0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    // Calcula y retorna la nota máxima del estudiante
    public double obtenerNotaMaxima() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double max = notas.get(0); // Se asume que hay al menos una nota
        for (double nota : notas) {
            if (nota > max) {
                max = nota;
            }
        }
        return max;
    }

    // Calcula y retorna la nota mínima del estudiante
    public double obtenerNotaMinima() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double min = notas.get(0); // Se asume que hay al menos una nota
        for (double nota : notas) {
            if (nota < min) { // La condición correcta para encontrar el mínimo
                min = nota;
            }
        }
        return min;
    }

    @Override
    public String toString() {
        return "Estudiante: " + nombre + ", Promedio: " + String.format("%.2f", calcularPromedio()) + ", Notas: " + notas;
    }
}