package correctorExamenes;

public class Par {
    private double numero1;
    private double numero2;

    public Par(double numero1, double numero2) {
	this.numero1 = numero1;
	this.numero2 = numero2;
    }

    public double getNumero1() {
	return numero1;
    }

    public double getNumero2() {
	return numero2;
    }

    @Override
    public String toString() {
	return "{" + numero1 + ", " + numero2 + "}";
    }
}
