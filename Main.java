package proyectoLibretaDeNotas;
import java.util.Scanner;
import proyectoLibretaDeNotas.asignaciones.LibretaDeNotas;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LibretaDeNotas libreta = new LibretaDeNotas(scanner);
        int opcion;

        // Primero, pedimos al usuario que ingrese los datos de los estudiantes y sus notas.
        System.out.println("--- Inicio del Programa Libreta de notas ---");
        libreta.cargarNotas();

        // Bucle principal del menú
        do {
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Mostrar el Promedio de Notas, Máxima y Mínima por Estudiante.");
            System.out.println("2. Verificar si una Nota es Aprobatoria o Reprobatoria.");
            System.out.println("3. Comparar el Promedio de un Estudiante con el Promedio General del Curso.");
            System.out.println("0. Salir del Menú.");
            System.out.print("Ingrese su opción: ");

            // Validación para asegurar que la opción ingresada sea un número entero
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero para la opción.");
                scanner.next();
                System.out.print("Ingrese su opción: ");
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            // Ejecuta la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    libreta.mostrarPromedioNotasPorEstudiante();
                    break;
                case 2:
                    libreta.verificarNotaAprobatoria();
                    break;
                case 3:
                    libreta.compararConPromedioCurso();
                    break;
                case 0:
                    System.out.println("Saliendo de la Libreta de Notas. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}