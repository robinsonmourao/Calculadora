import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Controller {

    @FXML
    private Label totalLabel;

    @FXML
    private GridPane mainGrindPane;

    @FXML
    private Button calcularButton;

    @FXML
    private Slider percentSlider;

    @FXML
    private TextField quantiaTextField;

    @FXML
    private Label gorjetaLabel;

    @FXML
    private TextField gorjetaTextField;

    @FXML
    private Label quantiaLabel;

    @FXML
    private Label percentLabel;

    @FXML
    private TextField totalTextField;

    @FXML
    
    private static final NumberFormat moeda =
    	NumberFormat.getPercentInstance();
    private static final NumberFormat percent = 
    		NumberFormat.getPercentInstance();
    private BigDecimal gorjetaPercent = new BigDecimal(10);
    
    void calcularEvent(ActionEvent event) {
    	try {
    		BigDecimal valor = new BigDecimal(quantiaTextField.getText());
    		BigDecimal gorjeta = valor.multiply(gorjetaPercent);
    		BigDecimal total = valor.add(gorjeta);
    		
    		gorjetaTextField.setText(moeda.format(gorjeta));
    		totalTextField.setText(moeda.format(total));
    	}
    	catch( NumberFormatException ex){
    		quantiaTextField.setText("Entre a quantia");
    		quantiaTextField.selectAll();
    		quantiaTextField.requestFocus();
    		    		
    	}
    }

}
