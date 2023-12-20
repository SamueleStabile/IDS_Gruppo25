package group25.calculator.sccalculator;

import java.util.Stack;

public class ButtonStack<T> implements StackInterface<T> {
    private Stack<T> stack;

    public ButtonStack(){
        stack = new Stack<T>();
    }



    public void push(T element){
        stack.push(element);
    }
    public T pop(){
        return stack.isEmpty() ? null : stack.pop();
    }
    public T peek(){
        return stack.isEmpty() ? null : stack.peek();
    }
    public boolean isEmpty(){
        return stack.empty();
    }
    public void clear(){
        stack.removeAllElements();
    }
    public void drop(){
        T numb = stack.pop();
    }
    public void dup(){
        stack.push(stack.peek());
    }
    public void swap(){
        if (stack.size() >= 2) {
            T app1;
            app1 = stack.pop();
            dup();
            stack.setElementAt(app1, (stack.size() - 2));
        }
    }
    public void over(){
        if (stack.size() >= 2)
            stack.push( stack.elementAt(stack.size()-2) );
    }

    public Stack<T> getStack() {
        return stack;
    }
    public int size(){
        return stack.size();
    }
}

