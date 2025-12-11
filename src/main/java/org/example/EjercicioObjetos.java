package org.example;

import java.time.LocalDate;

enum CivilState{
    Soltero, Casado, Divorciado, Viudo;
}

class Persona {
    private String name;
    private String surname;
    private String dni;
    private int age;
    private CivilState civilState;

    public Persona(String name, String surname, String dni, int age, CivilState civilState) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.dni = dni;
        this.civilState = civilState;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CivilState getCivilState() {
        return civilState;
    }

    public void setCivilState(CivilState civilState) {
        this.civilState = civilState;
    }

    public void sayHello() {
        System.out.println("Hola, soy " + name + " " + surname + " y mi dni es " + dni);
    }
    public void sayDespedida() {
        System.out.println("¡Hasta la próxima! Firmado " + name + " alias el " + civilState);
    }
}

public class EjercicioObjetos {
    public static void main(String[] args) {
        Persona st = new Persona("Tomás", "Rosa Melano", "50361783Q", 45, CivilState.Viudo);
        st.sayHello();
        st.sayDespedida();
    }
}

