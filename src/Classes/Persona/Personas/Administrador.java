package Classes.Persona.Personas;

public class Administrador extends Persona {
    private double salario;
    private boolean admin = true;

    public Administrador() {
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
