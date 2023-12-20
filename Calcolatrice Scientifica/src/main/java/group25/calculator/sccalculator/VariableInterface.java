package group25.calculator.sccalculator;

public interface VariableInterface<K,T> {
    public void saveToVariable(K variable, T value);
    public T pushVariableValue(K variable);
    public void addToVariable(K variable, T value);
    public void subtractFromVariable(K variable, T value);
}
