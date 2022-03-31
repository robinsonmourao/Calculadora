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

import javax.swing.event.ChangeListener;
import javafx.beans.value.ObservableValue;//Associa o atalho somente apos iniciar a aplicação

import javafx.application.Platform;//Acesso aos codigos das teclas
import javafx.scene.input.KeyCodeCombination;//Criação de combinaçoes de teclas
import javafx.scene.input.KeyCombination;//Acesso aos codigos de teclas com ctrl

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
    
    private static final NumberFormat moeda = NumberFormat.getPercentInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal gorjetaPercent = new BigDecimal(0.10);
    
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
    public void initialize() {
    	//arredonda para baixo de 0 a 4 e para cima de 5 a 9
    	moeda.setRoundingMode(RoundingMode.HALF_UP);
    	
    	//listener para slider
    	percentSlider.valueProperty().addListener(
    		new ChangeListener<Number>()){    		
    			@Override
    				public void changed(ObservevableValue<? extends Number> observableValue, Number valorAntigo, Number valorNovo){
    				gorjetaPercent = BigDecimal.valueOf(valorNovo.intValue()/100.0);
    				percentLabel.setText(percent.format(gorjetaPercent));
    			
    		
    		}
    	
    	}
    	
    }
    Plataform.runLater(() -> {
    	calculatButton.getScene().getAccelerators().put
    	(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN),() -> {
    			calcularButton.fire();
    	}
    	
    }

}
