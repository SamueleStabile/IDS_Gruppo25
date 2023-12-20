package group25.calculator.sccalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;

class ButtonStackTest {

    private ButtonStack<ComplexNumber> buttonStack;
    private ComplexNumber numb1,numb2;

    @BeforeEach
    void setUp() {
        buttonStack = new ButtonStack<>();
        numb1 = new ComplexNumber(1,1);
        numb2 = new ComplexNumber(2,2);
    }

    @Test
    void testPush() {
        buttonStack.push(numb1);
        buttonStack.push(numb2);
        assertEquals(2, buttonStack.size());
    }

    @Test
    void testPop() {
        buttonStack.push(numb1);
        buttonStack.push(numb2);
        assertEquals(numb2, buttonStack.pop());
        assertEquals(numb1, buttonStack.pop());
        assertNull(buttonStack.pop());
    }

    @Test
    void testPeek() {
        buttonStack.push(numb1);
        buttonStack.push(numb2);
        assertEquals(numb2, buttonStack.peek());
        assertEquals(2, buttonStack.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(buttonStack.isEmpty());
        buttonStack.push(numb1);
        assertFalse(buttonStack.isEmpty());
    }

    @Test
    void testClear() {
        buttonStack.push(numb1);
        buttonStack.push(numb2);
        buttonStack.clear();
        assertTrue(buttonStack.isEmpty());
    }

    @Test
    void testDrop() {
        buttonStack.push(numb1);
        buttonStack.push(numb2);
        buttonStack.drop();
        assertEquals(1, buttonStack.size());
        assertEquals(numb1, buttonStack.pop());
    }

    @Test
    void testDup() {
        buttonStack.push(numb1);
        buttonStack.dup();
        assertEquals(2, buttonStack.size());
        assertEquals(numb1, buttonStack.pop());
        assertEquals(numb1, buttonStack.pop());
    }

    @Test
    void testSwap() {
        buttonStack.push(numb1);
        buttonStack.push(numb2);
        buttonStack.swap();
        assertEquals(2, buttonStack.size());
        assertEquals(numb1, buttonStack.pop());
        assertEquals(numb2, buttonStack.pop());
    }

    @Test
    void testOver() {
        buttonStack.push(numb1);
        buttonStack.push(numb2);
        buttonStack.over();
        assertEquals(3, buttonStack.size());
        assertEquals(numb1, buttonStack.pop());
        assertEquals(numb2, buttonStack.pop());
        assertEquals(numb1, buttonStack.pop());
    }
}