package proyectoLibretaDeNotas.asignaciones;

import proyectoLibretaDeNotas.modelo.Estudiante;

public class EstudianteRegular extends Estudiante {
    private String curso;

    public EstudianteRegular(String nombre, String curso) {
        super(nombre);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }
}
