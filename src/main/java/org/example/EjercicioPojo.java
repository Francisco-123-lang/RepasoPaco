package org.example;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private String surname;
    private String address;

    public Student (String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

class Highschool {
    private String name;
    private ArrayList<Student> student;

    public Highschool(String name) {
        this.name = name;
        this.student = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudent() {
        return student;
    }

    public void setStudent(ArrayList<Student> student) {
        this.student = student;
    }

    public void agregarEstudiante(Student nuevoEstudiante) {
        this.student.add(nuevoEstudiante);
    }
}

public class EjercicioPojo {
    public static void main(String[] args) {

        Highschool insti1 = new Highschool("Enrique Tierno Galván");

        Student alumno1 = new Student("Folagor", "Fola", "Calle 23");
        Student alumno2 = new Student("Vegetta", "Deluque", "Calle 777");

        insti1.agregarEstudiante(alumno1);
        insti1.agregarEstudiante(alumno2);

        System.out.println("El nombre del instituto es" + insti1.getName());

        ArrayList<Student>  listaAlumnos = insti1.getStudent();
        System.out.println("Total estudiantes: " + listaAlumnos.size());

        if(listaAlumnos.size() > 0 ) {
            Student primerAlumno = listaAlumnos.get(1);
            System.out.println(" Nombre: " + primerAlumno.getName());
            System.out.println(" Apellido: " + primerAlumno.getSurname());
        }
    }
}

//Aunque esté bien el Arraylist no es lo que pidió en la tarea :(.