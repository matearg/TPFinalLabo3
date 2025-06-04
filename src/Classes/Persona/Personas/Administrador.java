package Classes.Persona.Personas;

// Subclase de Persona, esta tendra accesos especiales al sistema
// podra agregar y eliminar productos, empleados y clientes
// asi como cambiar el valor de admin y salario de cada Empleado.
// Ademas podra cambiar precios de los productos.
public class Administrador extends Persona {
    private double salario;
    private boolean admin = true;

    public Administrador() {
        this.setTipo("Administrador");
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
