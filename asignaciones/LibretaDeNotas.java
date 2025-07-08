package proyectoLibretaDeNotas.asignaciones;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class LibretaDeNotas {

    private HashMap<String, ArrayList<Double>> calificacionesEstudiantes;
    private Scanner scanner; // Usamos el mismo scanner para toda la clase
    private static final double NOTA_APROBACION = 4.0;

    // Constructor: inicializa el HashMap y el Scanner
    public LibretaDeNotas(Scanner scanner) {
        this.calificacionesEstudiantes = new HashMap<>();
        this.scanner = scanner;
    }

    /**
     * Solicita al usuario la cantidad de alumnos, notas y luego los datos de cada uno.
     * Almacena todo en el HashMap. Incluye validaciones básicas.
     */
    public void cargarNotas() {
        int numAlumnos = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Ingrese la cantidad de alumnos: ");
                numAlumnos = scanner.nextInt();
                if (numAlumnos <= 0) {
                    System.out.println("La cantidad de alumnos debe ser un número positivo.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next();
            }
        }
        scanner.nextLine();

        int numNotasPorAlumno = 0;
        entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.print("Ingrese la cantidad de notas por alumno: ");
                numNotasPorAlumno = scanner.nextInt();
                if (numNotasPorAlumno <= 0) {
                    System.out.println("La cantidad de notas debe ser un número positivo.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next();
            }
        }
        scanner.nextLine();

        // Bucle para cada alumno
        for (int i = 0; i < numAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno " + (i + 1) + ": ");
            String nombreAlumno = scanner.nextLine();

            ArrayList<Double> notasAlumno = new ArrayList<>(); // Lista para las notas del alumno actual

            System.out.println("Ingrese las " + numNotasPorAlumno + " notas para " + nombreAlumno + ":");
            // Bucle para cada nota del alumno actual
            for (int j = 0; j < numNotasPorAlumno; j++) {
                double nota = -1; // Valor inicial para la nota
                boolean notaValida = false;
                while (!notaValida) {
                    try {
                        System.out.print("Nota " + (j + 1) + ": ");
                        nota = scanner.nextDouble();
                        // Validación de rango de notas (1.0 a 7.0)
                        if (nota >= 1.0 && nota <= 7.0) {
                            notasAlumno.add(nota); // Añade la nota a la lista del alumno
                            notaValida = true;
                        } else {
                            System.out.println("Nota inválida. Debe estar entre 1.0 y 7.0.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número válido para la nota.");
                        scanner.next();
                    }
                }
            }
            scanner.nextLine();

            // Almacena el nombre del alumno y su lista de notas en el HashMap
            calificacionesEstudiantes.put(nombreAlumno, notasAlumno);
            System.out.println("Notas de " + nombreAlumno + " añadidas.");
        }
    }

    /**
     * Calcula y retorna el promedio de una lista de notas.
     */
    private double calcularPromedio(ArrayList<Double> notas) {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    /**
     * Encuentra y retorna la nota máxima de una lista de notas.
     */
    private double encontrarNotaMaxima(ArrayList<Double> notas) {
        if (notas.isEmpty()) {
            return 0.0; // O un valor muy pequeño para manejar este caso
        }
        double max = notas.get(0);
        for (double nota : notas) {
            if (nota > max) {
                max = nota;
            }
        }
        return max;
    }

    /**
     * Encuentra y retorna la nota mínima de una lista de notas.
     */
    private double encontrarNotaMinima(ArrayList<Double> notas) {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double min = notas.get(0);
        for (double nota : notas) {
            if (nota < min) {
                min = nota;
            }
        }
        return min;
    }

    /**
     * Muestra el promedio, nota máxima y mínima para cada estudiante.
     * Implementa la Opción 1 del menú.
     */
    public void mostrarPromedioNotasPorEstudiante() {
        if (calificacionesEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.println("\n--- Promedio, Máxima y Mínima de Notas por Estudiante ---");

        for (Map.Entry<String, ArrayList<Double>> entry : calificacionesEstudiantes.entrySet()) {
            String nombreEstudiante = entry.getKey(); // Obtiene el nombre
            ArrayList<Double> notasEstudiante = entry.getValue(); // Obtiene la lista de notas

            double promedio = calcularPromedio(notasEstudiante);
            double maxima = encontrarNotaMaxima(notasEstudiante);
            double minima = encontrarNotaMinima(notasEstudiante);


            System.out.printf("Estudiante: %s | Promedio: %.2f | Máxima: %.1f | Mínima: %.1f%n",
                    nombreEstudiante, promedio, maxima, minima);
        }
        System.out.println("-------------------------------------------------------");
    }

    /**
     * Calcula el promedio general de todas las notas de todos los estudiantes en el curso.
     */
    private double calcularPromedioGeneralCurso() {
        if (calificacionesEstudiantes.isEmpty()) {
            return 0.0;
        }
        double sumaTotalNotas = 0;
        int cantidadTotalNotas = 0;
        // Itera sobre las listas de notas de cada estudiante
        for (ArrayList<Double> notas : calificacionesEstudiantes.values()) {
            for (double nota : notas) {
                sumaTotalNotas += nota;
                cantidadTotalNotas++;
            }
        }
        return cantidadTotalNotas == 0 ? 0.0 : sumaTotalNotas / cantidadTotalNotas;
    }

    /**
     * Implementa la Opción 2 del menú: verifica si una nota dada es aprobatoria o reprobatoria.
     */
    public void verificarNotaAprobatoria() {
        System.out.print("Ingrese el nombre del estudiante para verificar su nota: ");
        String nombre = scanner.nextLine();

        if (!calificacionesEstudiantes.containsKey(nombre)) {
            System.out.println("Estudiante '" + nombre + "' no encontrado.");
            return;
        }

        System.out.print("Ingrese la nota individual a verificar para " + nombre + ": ");
        double notaVerificar = -1;
        boolean notaValida = false;
        while (!notaValida) {
            try {
                notaVerificar = scanner.nextDouble();
                if (notaVerificar >= 1.0 && notaVerificar <= 7.0) {
                    notaValida = true;
                } else {
                    System.out.println("Nota inválida. Debe estar entre 1.0 y 7.0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                scanner.next();
            }
        }
        scanner.nextLine();

        if (notaVerificar >= NOTA_APROBACION) {
            System.out.println("La nota " + String.format("%.1f", notaVerificar) + " es APROBATORIA para " + nombre + ".");
        } else {
            System.out.println("La nota " + String.format("%.1f", notaVerificar) + " es REPROBATORIA para " + nombre + ".");
        }
    }

    /**
     * Implementa la Opción 3 del menú: compara el promedio de un estudiante con el promedio general del curso.
     */
    public void compararConPromedioCurso() {
        System.out.print("Ingrese el nombre del estudiante a comparar: ");
        String nombre = scanner.nextLine();

        if (!calificacionesEstudiantes.containsKey(nombre)) {
            System.out.println("Estudiante '" + nombre + "' no encontrado.");
            return;
        }

        ArrayList<Double> notasEstudiante = calificacionesEstudiantes.get(nombre);
        double promedioEstudiante = calcularPromedio(notasEstudiante);
        double promedioCurso = calcularPromedioGeneralCurso();

        System.out.printf("Promedio de %s: %.2f%n", nombre, promedioEstudiante);
        System.out.printf("Promedio general del curso: %.2f%n", promedioCurso);

        if (promedioEstudiante > promedioCurso) {
            System.out.println("El promedio de " + nombre + " está POR SOBRE del promedio del curso.");
        } else if (promedioEstudiante < promedioCurso) {
            System.out.println("El promedio de " + nombre + " está POR DEBAJO del promedio del curso.");
        } else {
            System.out.println("El promedio de " + nombre + " está IGUAL al promedio del curso.");
        }
    }
}