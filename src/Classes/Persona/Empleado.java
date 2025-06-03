package Classes.Persona;

public class Empleado extends Persona {
    private double salario;
    private boolean admin = false;

    public Empleado() {
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
