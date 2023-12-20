package group25.calculator.sccalculator;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Stack;
import static java.lang.String.valueOf;



public class CalculatorController {

    //istanziamento di tutte le variabili necessarie
    private  static final ButtonStack<ComplexNumber> buttonStack = new ButtonStack<>();
    private static final Variable<Character, ComplexNumber> variables = new Variable<>();

    private boolean newNumber = true;
    private boolean nuovo = false;

    @FXML private VBox sideMenu;
    @FXML private Label lbl;
    @FXML private VBox stackContainer;

    private Parent root;
    private Stage stage;
    private Scene scene;


    //Cambi di scenari
    @FXML
    public void switchSceneButton(MouseEvent event){
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        switch(buttonText){
            case "Calculator":
                switchScene("Calculator.fxml",event, lbl.getText());
                break;
            case "Variables":
                switchScene("Variables.fxml",event, lbl.getText());
                break;
            case "StackView":
                switchScene("Stack.fxml",event, lbl.getText());
                break;
        }
    }
    @FXML
    private void switchScene(String fxmlFile, MouseEvent event, String labelText) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource(fxmlFile));
            root=loader.load();
            if(fxmlFile.equals("Stack.fxml")){
                CalculatorController newController = loader.getController();
                newController.setLabelText(labelText);
                newController.setStack(buttonStack.getStack());
                newController.setStage((Stage) ((Node) event.getSource()).getScene().getWindow());


                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                CalculatorController newController = loader.getController();
                newController.setLabelText(labelText);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    //Display Implementation
    private void setLabelText(String text) {
        lbl.setText(text);
    }
    @FXML
    public void toggleSideMenu() {
        // Cambia la visibilità del menu laterale
        sideMenu.setVisible(!sideMenu.isVisible());
    }

    public  void setStack(Stack<ComplexNumber> stack) {

        // Aggiungi stack elements al VBox come TextFields, partendo dall'alto
        for (int i = stack.size() - 1; i >= 0; i--) {
            ComplexNumber number = stack.elementAt(i);
            TextField textField = new TextField(number.toString());
            textField.setEditable(false); // Rendi il TextField di sola lettura
            textField.getStyleClass().add("text-field");
            stackContainer.getChildren().add(textField);
        }
    }
    private void updateStackView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Stack.fxml"));
        Parent root;
        try {
            root = loader.load();
            CalculatorController stackController = loader.getController();

            stackController.setStack(buttonStack.getStack());

            // Aggiorna la scena attuale con il nuovo stack visualizzato

            stage.setScene(new Scene(root));
            stackController.setStage(stage);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void OperationStack(MouseEvent event){
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText){
            case "Clear":
                buttonStack.clear();
                updateStackView();
                break;
            case "Drop":
                if(buttonStack.isEmpty())
                    lbl.setText("Stack Empty");
                else{
                    buttonStack.drop();
                    updateStackView();
                }
                break;
            case "Dup":
                if(buttonStack.isEmpty())
                    lbl.setText("Stack Empty");
                else{
                    buttonStack.dup();
                    updateStackView();
                }
                break;
            case "Swap":
                if(buttonStack.size() <2)
                    lbl.setText("Stack Empty");
                else {
                    buttonStack.swap();
                    updateStackView();
                }
                break;
            case "Over":
                if(buttonStack.size() <2)
                    lbl.setText("Stack Empty");
                else{
                    buttonStack.over();
                    updateStackView();
                }

                break;
        }

    }



    //LOGIC APPLICATION
    @FXML
    public void HandlePressButton(MouseEvent event) {
        boolean zero = false;
        if (nuovo) {
            lbl.setText("0");
            nuovo = false;
        }
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();

        if (buttonText.equals("0")) {
            newNumber = false;
            zero = true;
        }

        if (newNumber) {
            // Se stiamo inserendo un nuovo numero, imposta il testo direttamente
            lbl.setText(buttonText);
            newNumber = false;
        } else {
            if (buttonText.equals(",")) {
                // Gestisci il caso del punto decimale
                if (!( lbl.getText().endsWith("+") || lbl.getText().endsWith("-") || lbl.getText().endsWith("*") || lbl.getText().endsWith("/") || lbl.getText().endsWith(",") || lbl.getText().endsWith("<") || lbl.getText().endsWith(">") ) && !(lbl.getText().endsWith(","))) {

                    lbl.setText(lbl.getText() + buttonText);
                    if (zero && lbl.getText().equals("0"))
                        lbl.setText("0");
                }
            } else if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("*") || buttonText.equals("/")) {
                // Gestisci l'inserimento di un operatore
                if (!( lbl.getText().endsWith("+") || lbl.getText().endsWith("-") || lbl.getText().endsWith("*") || lbl.getText().endsWith("/") || lbl.getText().endsWith(",") || lbl.getText().endsWith("<") || lbl.getText().endsWith(">") )) {
                    lbl.setText(lbl.getText() + buttonText);
                }
            } else {
                if (zero && lbl.getText().equals("0"))
                    lbl.setText("0");
                else
                    lbl.setText(lbl.getText() + buttonText);
            }
        }
    }

    @FXML
    public void handleEnter() {
        newNumber = true;
        String text = lbl.getText();
        text = text.replace(',', '.');
        if(!nuovo) {
            if ( text.matches("[-+]?\\d*\\.?\\d+([-+]?\\d*\\.?\\d*)?") ) {
                String[] parts = text.split("[+\\-]");
                double realPart = 0;
                double imagPart = 0;
                if(text.charAt(0) == '-' || text.charAt(0) == '+') {    //numeri con segno
                    if (text.charAt(0) == '-')
                        realPart = -Double.parseDouble(parts[1]);
                    else
                        realPart = Double.parseDouble(parts[1]);
                    if (parts.length >2){
                        if (text.indexOf('-', 1) != -1)
                            imagPart = -Double.parseDouble(parts[2]);
                        else
                            imagPart = Double.parseDouble(parts[2]);
                    }

                } else if(text.contains("+") || text.contains("-")){
                    realPart = Double.parseDouble(parts[0]);
                    if (text.contains("-"))
                        imagPart = -Double.parseDouble(parts[1]);
                    else
                        imagPart = Double.parseDouble(parts[1]);
                } else
                    realPart = Double.valueOf(text);

                buttonStack.push(new ComplexNumber(realPart, imagPart));

            }else if (text.matches("[+\\-*/√]+")) {   // solo operazione
                FunctionButton(text);
            } else if (text.matches("[<>+\\-][a-z]")) {    // operazioni variabili
                FunctionVariables(text);
            } else if (text.matches("[a-z]")) {             // contenuto variabile
                FunctionVariables(text);
            }else{
                if (text.matches(".*[a-z].*"))
                    lbl.setText("Syntax Variable Error");
                else
                    lbl.setText("Syntax Error");
            }


            if(! (lbl.getText().contains("Syntax Error") ||lbl.getText().contains("Syntax Variable Error") || lbl.getText().contains("Stack Empty") || lbl.getText().contains("Insufficient Operators") ))
                lbl.setText("0");
        }
    }


    public void FunctionVariables(String text){
        ComplexNumber numb;
        if (text.charAt(0) >= 'a' && text.charAt(0) <= 'z'){
            lbl.setText(text.charAt(0) +"="+variables.pushVariableValue(text.charAt(0)));
            nuovo = true;

            updateStackView();

        }
        switch (text.charAt(0)){
            case '>':
                numb = buttonStack.pop();
                variables.saveToVariable(text.charAt(1), numb);
                lbl.setText(text.charAt(1) +"="+numb);
                nuovo = true;

                updateStackView();

                break;
            case '<':
                buttonStack.push(variables.pushVariableValue(text.charAt(1)));
                lbl.setText(text.charAt(1) +"="+null);
                nuovo = true;

                updateStackView();

                break;
            case '+':
                variables.addToVariable(text.charAt(1), buttonStack.pop());
                lbl.setText(text.charAt(1) +"="+variables.pushVariableValue(text.charAt(1)));
                nuovo = true;
                updateStackView();

                break;
            case '-':
                variables.subtractFromVariable(text.charAt(1), buttonStack.pop());
                lbl.setText(text.charAt(1) +"="+variables.pushVariableValue(text.charAt(1)));
                nuovo = true;
                updateStackView();
                break;

        }
    }
    public void FunctionButton(String text) {
        ComplexNumber result;
        if(buttonStack.isEmpty())
            lbl.setText("Stack Empty");
        else if (buttonStack.size()==1) {
            switch (text){
                case "√":
                    result = buttonStack.pop().sqrt();
                    lbl.setText(result.toString());
                    buttonStack.push(result);
                    nuovo = true;
                    updateStackView();
                    break;

                case "+/-":
                    result = buttonStack.pop().negate();
                    lbl.setText(result.toString());
                    buttonStack.push(result);
                    nuovo = true;
                    updateStackView();
                    break;
                default:
                    lbl.setText("Insufficient Operators");
            }
        } else{
            switch (text) {
                case "+":
                    result = buttonStack.pop().add(buttonStack.pop());
                    lbl.setText(result.toString());
                    buttonStack.push(result);
                    nuovo = true;
                    updateStackView();
                    break;

                case "-":
                    result = buttonStack.pop().subtract(buttonStack.pop());
                    lbl.setText(result.toString());
                    buttonStack.push(result);
                    nuovo = true;
                    updateStackView();
                    break;

                case "/":

                    result = buttonStack.pop().divide(buttonStack.pop());
                    lbl.setText(result.toString());
                    buttonStack.push(result);
                    nuovo = true;
                    updateStackView();
                    break;

                case "*":
                    result = buttonStack.pop().multiply(buttonStack.pop());
                    lbl.setText(result.toString());
                    buttonStack.push(result);
                    nuovo = true;
                    updateStackView();
                    break;

                case "√":
                    result = buttonStack.pop().sqrt();
                    lbl.setText(result.toString());
                    buttonStack.push(result);
                    nuovo = true;
                    updateStackView();
                    break;

                case "+/-":
                    result = buttonStack.pop().negate();
                    lbl.setText(result.toString());
                    buttonStack.push(result);
                    nuovo = true;
                    updateStackView();
                    break;
            }

        }


    }

    @FXML
    public void clearDisplay(){
        lbl.setText("0");
        newNumber = true;
    }
    @FXML
    public void delDisplay(){
        String text = lbl.getText();
        int length = text.length();
        if(length > 1){
            String newText = text.substring(0 , length-1);
            lbl.setText(newText);
            newNumber = false;
        } else{
            lbl.setText("0");
            newNumber = true;
        }

    }



}