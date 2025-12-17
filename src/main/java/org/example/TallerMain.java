package org.example;


import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TallerMain {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleReader vehicleReader = new VehicleReader(scanner);
        GarageReader garageReader = new GarageReader(scanner, vehicleReader);
        Garage garage = garageReader.read();
        System.out.println(garage);
    }
}

enum Vehicles {
    COCHE, MOTO, CAMION
        }
class Vehicle {
    private Vehicles vehicles;
    private int velocidad;
    private String color;
    private String matricula;

    public Vehicle(Vehicles vehicles, int velocidad, String color, String matricula) {
        this.vehicles = vehicles;
        this.velocidad = velocidad;
        this.color = color;
        this.matricula = matricula;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicles == vehicle.vehicles && Objects.equals(velocidad, vehicle.velocidad) && Objects.equals(color, vehicle.color) && Objects.equals(matricula, vehicle.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicles, velocidad, color, matricula);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicles=" + vehicles +
                ", velocidad='" + velocidad + '\'' +
                ", color='" + color + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}

class Garage {
    private String nombre;
    private String direccion;
    private Vehicle[] vehicles;

    public Garage (String nombre, String direccion, Vehicle[] vehicles) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.vehicles = vehicles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Garage garage = (Garage) o;
        return Objects.equals(nombre, garage.nombre) && Objects.equals(direccion, garage.direccion) && Objects.deepEquals(vehicles, garage.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, direccion, Arrays.hashCode(vehicles));
    }

    @Override
    public String toString() {
        return "Garage{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }
}

class VehicleReader {
    private final Scanner scanner;

    public VehicleReader (Scanner scanner) {
        this.scanner = scanner;
    }

    public Vehicle read() {
        System.out.println("Tipo de vehiculo(COCHE, MOTO, o CAMION): ");
        Vehicles vehicles = Vehicles.valueOf(scanner.nextLine());
        System.out.println("Velocidad Máxima: ");
        int velocidad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Color: ");
        String color = scanner.nextLine();
        System.out.println("Matricula: ");
        String matricula = scanner.nextLine();

        return new Vehicle(vehicles, velocidad, color, matricula);
    }
}

class GarageReader {
    private final Scanner scanner;
    private final VehicleReader vehicleReader;

    public GarageReader (Scanner scanner, VehicleReader vehicleReader) {
        this.scanner = scanner;
        this.vehicleReader = vehicleReader;
    }

    public Garage read() {
        System.out.println("Introduce los datos del garaje");
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Dirección: ");
        String direccion = scanner.nextLine();
        int num;
        do {
            System.out.println("¿Cuántos vehículos hay en el garaje?: ");
            num = scanner.nextInt();
            scanner.nextLine();
        } while (num < 1);
        Vehicle[] vehicles = new Vehicle[num];

        for (int i = 0; i < vehicles.length ; i++) {
            vehicles[i] = vehicleReader.read();
        }
        return new Garage(nombre, direccion, vehicles);
    }
}

