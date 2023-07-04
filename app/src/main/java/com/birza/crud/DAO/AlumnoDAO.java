package com.birza.crud.DAO;

import com.birza.crud.Entity.Alumno;

import java.util.*;

public class AlumnoDAO {

    public static String[] vDistritos = {
            "Seleccione","Lima","La Molina","Puente Piedra","Los olivos","Comas","Santa Anita","SMP","Salamanca"
    };


    private static List<Alumno> lista = new ArrayList<>();

    // método que devuelva los datos de un alumno por su codigo
    // caso contrario devolverá null
    public Alumno BuscarAlumno(int codigo) {
        //foreach de java
        for (Alumno alumno : lista) {
            // si el valor codigo del alumno es igual al buscado
            if (alumno.getCodigo() == codigo) return alumno;
        }
        //si el codigo no es encontrado
        return null;
    }

    // Método AgregarAlumno
    public String AgregarAlumno(Alumno alumno) {
        // si el codigo de alumno no es encontrado
        if (BuscarAlumno(alumno.getCodigo()) == null) {
            lista.add(alumno);
            return "Alumno agregado correctamente";
        } else {
            // si fue encontrado
            return "Eror, Código del Alumno Duplicado";
        }
    }

    //ActualizarAlumno
    public String ActualizarAlumno(Alumno objalu) {

        Alumno alumno = BuscarAlumno(objalu.getCodigo());
        if (alumno != null) {
            // alumno encontrado por su codigo se actualiza
            // de esta forma los sobre-escribe y ya no se usa el add
            alumno.setNombre(objalu.getNombre());
            alumno.setPension(objalu.getPension());
            alumno.setCelular(objalu.getCelular());
            alumno.setDistrito(objalu.getDistrito());
            // lista.remove(alumno);
            // lista.add(objalu);
            return "Alumno actualizado correctamente";
        } else {
            // Si el alumno no existe en la lista
            return "Error, el código del alumno no existe";
        }
    }

    // Método EliminarAlumno
    public String EliminarAlumno(int codigo) {
        Alumno alumno = BuscarAlumno(codigo);
        if (alumno != null) {
            lista.remove(alumno);
            return "Alumno eliminado correctamente";
        } else {
            return "Error, código del alumno no existe";
        }

    }

    // método que devuelve la lista de alumnos como una lista de String
    public List<String> ListarAlumnosString() {
        List<String> lista_alumno = new ArrayList<>();
        //foreach
        for (Alumno alumno:lista) {
            lista_alumno.add("Codigo: " + alumno.getCodigo() +"\n" +
                             "Nombre: " + alumno.getNombre() +"\n" +
                             "Celular: " + alumno.getCelular() +"\n" +
                             "Pension: " + alumno.getPension() +"\n" +
                             "Distrito" + alumno.getDistrito());
        }
        return lista_alumno;
    }

    // método que devuelva la lista de alumnos
    public List<Alumno> ListarAlumnos() {
        return lista;
    }

}