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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;//Associa o atalho somente apos iniciar a aplica��o

import javafx.application.Platform;//Acesso aos codigos das teclas

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;//Cria��o de combina�oes de teclas
import javafx.scene.input.KeyCombination;//Acesso aos codigos de teclas com ctrl

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Controller {

	@FXML
	private Button calcularButton;

	@FXML
	private TextField calcularTextField;

	@FXML
	private Button clearButton;

	@FXML
	private Button divideButton;

	@FXML
	private Button eightButton;

	@FXML
	private Button fiveButton;

	@FXML
	private Button fourButton;

	@FXML
	private GridPane gridPane;

	@FXML
	private TextField inputNumerosTextField;

	@FXML
	private Text insiraNumerosText;

	@FXML
	private Button minusButton;

	@FXML
	private Button nineButton;

	@FXML
	private Button oneButton;

	@FXML
	private Button percentButton;

	@FXML
	private Button plusButton;

	@FXML
	private Button sevenButton;

	@FXML
	private Button sixButton;

	@FXML
	private Button threeButton;

	@FXML
	private Button timesButton;

	@FXML
	private Button twoButton;

	@FXML
	private Button zeroButton;	
    
	//cria "moeda" com padroes da regiao do sistema
    private static final NumberFormat moeda = NumberFormat.getPercentInstance(); 
    //cria "percent" com padroes da regiao do sistema
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    //cria "valorTotal" e inicia como um bigdecimal
    private BigDecimal valorTotal = new BigDecimal(0);
    
    
    @FXML
    //a��o do bot�o calcularButton
    void calcularEvent(ActionEvent event) {
    	try {
    		//cria "valor", inicia-o como BigDecimal e o associa com o valor de "inputNumerosTextField"
    		BigDecimal valor = new BigDecimal(inputNumerosTextField.getText());
    		
    		//cria "valorParcial" e multiplica "valor" por "valorTotal"
    		BigDecimal valorParcial = valor.multiply(valorTotal);
    		
    		//cria "total" e associa a aos (somat�rio de "valorTotal" com "valor") 
    		BigDecimal total = valor.add(valorTotal);
    		
    		//formata "total" de moeda e o poe em "calcularTextField"
    		calcularTextField.setText(moeda.format(total));
    	}
    	catch( NumberFormatException ex){
    		inputNumerosTextField.setText("Entre a quantia");
    		inputNumerosTextField.selectAll();
    		inputNumerosTextField.requestFocus();
    		    		
    	}
    }
    public void initialize() {//arredonda para baixo de 0 a 4 e para cima de 5 a 9
    	
    	moeda.setRoundingMode(RoundingMode.HALF_UP);
    	
    	//listener para slider
    	percentSlider.valueProperty().addListener(
    			new ChangeListener<Number>(){
    			@Override
    			public void changed(ObservableValue<? extends Number> ov,Number valorAntigo, Number valorNovo) {
    			gorjetaPercent = BigDecimal.valueOf(valorNovo.intValue()/100.0);
    			percentLabel.setText(percent.format(gorjetaPercent));
    			}
    		}
    		);
    	Platform.runLater(() -> {
    		calcularButton.getScene().getAccelerators().put
    		(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN),() -> {
    				calcularButton.fire();
    	});
    	
    });
    }

}
