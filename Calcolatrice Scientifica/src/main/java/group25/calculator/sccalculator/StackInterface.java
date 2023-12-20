package group25.calculator.sccalculator;

public interface StackInterface<T> {
    public void push(T element);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public void clear();
    public void drop();
    public void dup();
    public void swap();
    public void over();
}