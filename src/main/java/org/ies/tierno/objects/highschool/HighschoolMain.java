package org.ies.tierno.objects.highschool;
import java.util.Scanner;
import java.util.Arrays; // Necesario para Arrays.toString/hashCode/deepEquals en Highschool
import java.util.Objects; // Necesario para Objects.equals/hash en Highschool y Student

// CLASE PRINCIPAL (ENTRY POINT)
// Responsabilidad: Iniciar el programa, configurar los lectores y mostrar el resultado.

public class HighschoolMain {
    public static void main(String[] args) {
        // 1. Inicialización del recurso de entrada:
        Scanner scanner = new Scanner(System.in);

        // 2. Creación del lector de estudiantes:
        // Se le pasa el Scanner para que sepa de dónde leer los datos de CADA estudiante.
        StudentReader studentReader = new StudentReader(scanner);

        // 3. Creación del lector del instituto:
        // Se le pasa el Scanner y el StudentReader (dependencia),
        // porque el HighschoolReader usará al StudentReader para leer la lista de alumnos.
        HighschoolReader highschoolReader = new HighschoolReader(scanner, studentReader);

        // 4. Ejecución de la lectura deTODO el instituto:
        // Llama al metodo 'read', que pide el nombre, la cantidad de alumnos,
        // y luego ejecuta 'studentReader.read()' varias veces en un bucle 'for'.
        Highschool highschool = highschoolReader.read();

        // 5. Salida del resultado:
        // Imprime el objeto Highschool completo. Esto llama automáticamente al metodo toString()
        // de Highschool, el cual a su vez imprime todos los estudiantes de forma legible.
        System.out.println(highschool);
    }
}

// CLASE LECTORA DE ESTUDIANTES
// Responsabilidad: Encapsular la lógica de solicitar y crear UN SOLO objeto Student.

class StudentReader {
    // 'final' indica que la referencia a este objeto (Scanner) no cambiará.
    private final Scanner scanner;

    public StudentReader(Scanner scanner) {
        // Inyección de Dependencia: Recibe el Scanner ya creado en el main.
        this.scanner = scanner;
    }

    // Método principal de lectura:
    public Student read() {
        System.out.println("Introduce los datos del estudiante: ");

        System.out.println("Nombre:");
        String name = scanner.nextLine(); // Lee una línea completa como nombre

        System.out.println("Apellidos:");
        String surname = scanner.nextLine();

        System.out.println("Direción:");
        String address = scanner.nextLine();

        // Crea y devuelve el objeto POJO Student completamente inicializado.
        return new Student(name, surname, address);
    }
}

// ----------------------------------------------------------------------------------
// CLASE LECTORA DEL INSTITUTO
// Responsabilidad: Encapsular la lógica para solicitar el nombre, la cantidad,
//                   y ensamblar el objeto Highschool y su array de estudiantes.
// ----------------------------------------------------------------------------------
class HighschoolReader {
    private final Scanner scanner;
    private final StudentReader studentReader; // Dependencia para leer estudiantes individuales.

    public HighschoolReader(Scanner scanner, StudentReader studentReader) {
        this.scanner = scanner;
        this.studentReader = studentReader;
    }

    // Metodo principal de lectura:
    public Highschool read() {

        System.out.println("Nombre:");
        String name = scanner.nextLine();

        int numStudents;
        // Bucle DO-WHILE: Garantiza que se pida la cantidad al menos una vez,
        // y que no se acepte un número menor a 1 (no puede haber 0 o menos estudiantes).
        do {
            System.out.println("¿Cuántos estudiantes hay en el instituto?");
            numStudents = scanner.nextInt();
            // funcione correctamente en la siguiente llamada (evita un error común de Scanner).
            scanner.nextLine();
        } while(numStudents < 1);

        // Creación del ARRAY NATIVO:
        // Se crea el array Student[] con un tamaño fijo (numStudents) que el usuario acaba de introducir.
        Student[] students = new Student[numStudents];

        // Bucle FOR para llenar el array:
        // Itera desde 0 hasta la longitud máxima del array (students.length).
        for (int i = 0; i < students.length; i++) {
            // Delegación: Llama al StudentReader para que pida los datos del alumno i.
            // El resultado (el objeto Student) se guarda en la posición 'i' del array.
            students[i] = studentReader.read();
        }

        // Crea y devuelve el objeto POJO Highschool con el array lleno.
        return new Highschool(name, students);
    }
}

// ----------------------------------------------------------------------------------
// CLASE MODELO (POJO): Highschool
// Responsabilidad: Contener el nombre y el array fijo de objetos Student.
// ----------------------------------------------------------------------------------
class Highschool {
    private String name;
    private Student[] students; // Array nativo de tamaño fijo (como se requería estrictamente).

    public Highschool(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    // GETTERS Y SETTERS (Definición de un POJO estándar)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    // Sobrescritura de métodos de utilidad:
    // Permiten comparar si dos objetos Highschool son idénticos en valor.
    @Override
    public boolean equals(Object o) {
        // ... Lógica para comparar el nombre y el contenido del array...
        if (o == null || getClass() != o.getClass()) return false;
        Highschool that = (Highschool) o;
        // Objects.equals(name, that.name) compara los Strings.
        // Objects.deepEquals(students, that.students) es ESENCIAL para comparar el contenido de arrays.
        return Objects.equals(name, that.name) && Objects.deepEquals(students, that.students);
    }

    // Devuelve un código único basado en el contenido del objeto.
    @Override
    public int hashCode() {
        // Usa Arrays.hashCode para incluir el contenido del array en el cálculo del hash.
        return Objects.hash(name, Arrays.hashCode(students));
    }

    // Metodo clave llamado por System.out.println(highschool);
    @Override
    public String toString() {
        return "Highschool{" +
                "name='" + name + '\'' +
                // Arrays.toString(students) es VITAL. Si solo usas students.toString(),
                // solo imprimiría la dirección de memoria del array.
                ", students=" + Arrays.toString(students) +
                '}';
    }
}

// ----------------------------------------------------------------------------------
// CLASE MODELO (POJO): Student
// Responsabilidad: Contener los datos de un único estudiante.
// ----------------------------------------------------------------------------------
class Student {
    private String name;
    private String surname;
    private String address;

    public Student(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    // GETTERS Y SETTERS (Definición de un POJO estándar)
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

    // Sobrescritura de métodos de utilidad:
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        // Compara campo a campo los valores de los Strings.
        return Objects.equals(name, student.name) && Objects.equals(surname, student.surname) && Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, address);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
