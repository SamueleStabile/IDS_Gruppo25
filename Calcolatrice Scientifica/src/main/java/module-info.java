module group25.calculator.sccalculator.calculatrice {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens group25.calculator.sccalculator to javafx.fxml;
    exports group25.calculator.sccalculator;
}