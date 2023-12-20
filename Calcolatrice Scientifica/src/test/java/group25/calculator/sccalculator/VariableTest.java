package group25.calculator.sccalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {
    private Variable<Character, ComplexNumber> variable;
    private ComplexNumber numb1;

    @BeforeEach
    void setUp() {
        variable = new Variable<>();
        numb1 = new ComplexNumber(1,1);
    }

    @Test
    void testIsNull() {
        assertTrue(variable.isNull('x'));
        variable.saveToVariable('x', numb1);
        assertFalse(variable.isNull('x'));
    }

    @Test
    void testSaveToVariable() {
        variable.saveToVariable('x', numb1);
        assertFalse(variable.isNull('x'));
        assertEquals(numb1, variable.pushVariableValue('x'));
    }

    @Test
    void testPushVariableValue() {
        variable.saveToVariable('x', numb1);
        assertEquals(numb1, variable.pushVariableValue('x'));
    }

    @Test
    void testAddToVariable() {
        variable.saveToVariable('x', numb1);
        variable.addToVariable('x', new ComplexNumber(2, 2));
        assertEquals(new ComplexNumber(3, 3), variable.pushVariableValue('x'));
    }

    @Test
    void testSubtractFromVariable() {
        variable.saveToVariable('x', numb1);
        variable.subtractFromVariable('x', new ComplexNumber(2, 2));
        assertEquals(new ComplexNumber(-1, -1), variable.pushVariableValue('x'));
    }
}