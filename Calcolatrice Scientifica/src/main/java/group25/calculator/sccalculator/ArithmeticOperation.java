package group25.calculator.sccalculator;

public interface ArithmeticOperation<T> {
    public T add(T other);
    public T subtract(T other);
    public T multiply(T other);
    public T divide(T other);
    public T negate();
    public T sqrt();
}
