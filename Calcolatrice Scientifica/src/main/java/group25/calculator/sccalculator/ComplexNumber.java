package group25.calculator.sccalculator;

public class ComplexNumber implements ArithmeticOperation<ComplexNumber>{
    private double real;
    private double imag;

    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }


    public  double getRealPart(){
        return this.real;
    }

    public double getImagPart(){return  this.imag;}
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imag + other.imag);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imag - other.imag);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(
                this.real * other.real - this.imag * other.imag,
                this.real * other.imag + this.imag * other.real
        );
    }

    public ComplexNumber divide(ComplexNumber other) {
        double denominator = other.real * other.real + other.imag * other.imag;
        return new ComplexNumber(
                (this.real * other.real + this.imag * other.imag) / denominator,
                (this.imag * other.real - this.real * other.imag) / denominator
        );
    }

    public ComplexNumber sqrt() {
        double magnitude = Math.sqrt(this.real * this.real + this.imag * this.imag);
        double phase = Math.atan2(this.imag, this.real);

        double newMagnitude = Math.sqrt(magnitude);
        double newPhase = phase / 2.0;

        return new ComplexNumber(newMagnitude * Math.cos(newPhase), newMagnitude * Math.sin(newPhase));
    }

    public ComplexNumber negate() {
        return new ComplexNumber(-this.real, -this.imag);
    }

    @Override
    public String toString() {
        if (imag == 0) {
            return String.format("%.2f", real);
        } else {
            return String.format("%.2f%+.2fj", real, imag);
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ComplexNumber other = (ComplexNumber) obj;
        return getRealPart() == other.getRealPart() && getImagPart() == other.getImagPart();
    }
}

