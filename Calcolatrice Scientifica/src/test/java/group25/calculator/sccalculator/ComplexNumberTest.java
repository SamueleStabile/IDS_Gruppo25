package group25.calculator.sccalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {
    @Test
    public void test_getRealPart(){
       ComplexNumber complexNumber = new ComplexNumber(1.0,6.0);
       double realPart= complexNumber.getRealPart();
       assertEquals(realPart,1.0,0.01);

    }
    @Test
    public void test_getImaginaryPart(){
        ComplexNumber complexNumber = new ComplexNumber(1.0,6.0);
        double imagPart= complexNumber.getImagPart();
        assertEquals(imagPart,6.0,0.01);

    }

    @Test
    public  void test_ComplexNumber_add(){
        ComplexNumber complexNumber1 = new ComplexNumber(2.0 , 3.0);
        ComplexNumber complexNumber2 = new ComplexNumber(4.0 , 5.0);
        ComplexNumber result = complexNumber1.add(complexNumber2);
        assertEquals(6.0, result.getRealPart() , 0.01);
        assertEquals(8.0, result.getImagPart() , 0.01);
    }

    @Test
    public  void test_ComplexNumber_subtract(){
        ComplexNumber complexNumber1 = new ComplexNumber(2.0 , 3.0);
        ComplexNumber complexNumber2 = new ComplexNumber(4.0 , 5.0);
        ComplexNumber result = complexNumber1.subtract(complexNumber2);
        assertEquals(-2.0, result.getRealPart() , 0.01);
        assertEquals(-2.0, result.getImagPart() , 0.01);
    }

    @Test
    public  void test_ComplexNumber_multiply(){
        ComplexNumber complexNumber1 = new ComplexNumber(2.0 , 3.0);
        ComplexNumber complexNumber2 = new ComplexNumber(4.0 , 5.0);
        ComplexNumber result = complexNumber1.multiply(complexNumber2);
        assertEquals(-7.0, result.getRealPart() , 0.01);
        assertEquals(22.0, result.getImagPart() , 0.01);
    }
    @Test
    public  void test_ComplexNumber_divide(){
        ComplexNumber complexNumber1 = new ComplexNumber(2.0 , 3.0);
        ComplexNumber complexNumber2 = new ComplexNumber(4.0 , 5.0);
        ComplexNumber result = complexNumber1.divide(complexNumber2);
        assertEquals(0.56, result.getRealPart() , 0.01);
        assertEquals(0.048, result.getImagPart() , 0.01);



    }

    @Test
    public  void test_ComplexNumber_sqrt(){
        ComplexNumber complexNumber1 = new ComplexNumber(2.0 , 3.0);
        ComplexNumber result = complexNumber1.sqrt();
        assertEquals(1.67, result.getRealPart() , 0.01);
        assertEquals(0.89, result.getImagPart() , 0.01);



    }
    @Test
    public  void test_ComplexNumber_negate(){
        ComplexNumber complexNumber1 = new ComplexNumber(2.0 , 3.0);
        ComplexNumber result = complexNumber1.negate();
        assertEquals(-2.0, result.getRealPart() , 0.01);
        assertEquals(-3.0, result.getImagPart() , 0.01);



    }


}