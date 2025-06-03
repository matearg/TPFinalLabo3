package Classes.Persona;

public class Administrador extends Persona {
    private float salario;
    private boolean admin = true;

    public Administrador() {
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
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
