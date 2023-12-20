package group25.calculator.sccalculator;

import java.util.HashMap;
import java.util.Map;

public class Variable<K,T extends ArithmeticOperation<T>> implements VariableInterface<K,T> {
    private Map<K,T> variables;

    public Variable(){
        variables = new HashMap<K,T>();
    }

    public boolean isNull(K variable){
        return ( ! variables.containsKey(variable) );
    }
    public void saveToVariable(K variable, T value){
        variables.put(variable, value);
    }

    public T pushVariableValue(K variable){
        //if(!isNull(variable)){
        return variables.get(variable);
        //}
    }

    public void addToVariable(K variable, T value){
        if(!isNull(variable)){
            T nuovoval = variables.get(variable).add(value);
            variables.put(variable, nuovoval);
        }


    }
    public void subtractFromVariable(K variable, T value){
        if(!isNull(variable)){
            T nuovoval = variables.get(variable).subtract(value);
            variables.put(variable, nuovoval);
        }

    }

}
