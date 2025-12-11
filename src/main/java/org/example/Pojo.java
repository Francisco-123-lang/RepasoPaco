package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class User {
    private String nif;
    private String name;
    private String surname;
    private LocalDate birthdate;

    public User(String nif, String name, String surname, LocalDate birthdate) {
        this.nif = nif;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return  "nif= " + nif +
                ", name= " + name +
                ", surname= " + surname +
                ", birthdate= " + birthdate + '}';
    }
}

class Reader {
    public static User readReader() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce tu NIF: ");
        String nif = scanner.nextLine();
        System.out.println("Introduce tu nombre: ");
        String name = scanner.nextLine();
        System.out.println("Introduce tu apellido: ");
        String surname = scanner.nextLine();
        System.out.println("Introduce la fecha de nacimiento (formato yyyy-MM-dd)");
        String fechabr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaAnalizada = LocalDate.parse(fechabr,formatter);
        return new User(nif, name, surname, fechaAnalizada);
    }
}

class AgeChecker {
    public static User mayorMenor(User u1, User u2) {
        LocalDate user = u1.getBirthdate();
        LocalDate user2 = u2.getBirthdate();

        if (user.isBefore(user2)) {
            return u1;
        } else if (user2.isBefore(user)) {
            return u2;
        } else {
            return u1;
        }
    }
}
public class Pojo {

    public static void main(String[] args) {
        User u1 = Reader.readReader();
        System.out.println("Usuario creado completo: " + u1);
        User u2 = Reader.readReader();
        System.out.println("Usuario creado completo: " + u2);
        User usuarioMayor = AgeChecker.mayorMenor(u1, u2);

        if (usuarioMayor.equals(u1) && usuarioMayor.getBirthdate().isBefore(u2.getBirthdate())) {
            System.out.println("El usuario MAYOR es: " + usuarioMayor.getName() + " " + usuarioMayor.getSurname());
            System.out.println("Fecha de Nacimiento: " + usuarioMayor.getBirthdate());
        } else if (usuarioMayor.equals(u2) && usuarioMayor.getBirthdate().isBefore(u1.getBirthdate())) {
            System.out.println("El usuario MAYOR es: " + usuarioMayor.getName() + " " + usuarioMayor.getSurname());
            System.out.println("Fecha de Nacimiento: " + usuarioMayor.getBirthdate());
        } else {
            System.out.println("Ambos usuarios tienen la misma fecha de nacimiento.");
        }
    }
}
