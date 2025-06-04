package Classes.Persona.Personas;

// Subclase de Persona tiene la capacidad de visualizar productos, clientes y empleados
// pero sin poder modificar sus atributos
public class Empleado extends Persona {
    private double salario;
    private boolean admin = false;

    public Empleado() {
        this.setTipo("Empleado");
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void ver() {
        super.ver();
        System.out.println("Salario: " + salario);
        System.out.println("Admin: " + admin);
    }
}
