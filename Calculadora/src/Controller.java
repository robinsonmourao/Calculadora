import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;//Associa o atalho somente apos iniciar a aplicação

import javafx.application.Platform;//Acesso aos codigos das teclas

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;//Criação de combinaçoes de teclas
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
	private Slider percentSlider;

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

	BigDecimal valorInput, valorParcial = new BigDecimal(0), valorTotal = new BigDecimal(0);
	
	@FXML
	//ação do botão calcularButton	
	void calcularEvent(ActionEvent event) {
		try {
			//Pega o valor do input: "inputNumerosTextField" e salva na variável "valorInput"
			valorInput = new BigDecimal(inputNumerosTextField.getText());

			//Pega o "valorTotal" multiplica por "valorInput" e salva a variável "valorParcial"
			valorParcial = valorInput.multiply(valorTotal);

			//Pega o valor de "valorInput" e adiciona em "valorParcial" e salva em "valorTotal"
			valorTotal = valorParcial.add(valorInput);

			//formata "total" de moeda e o poe em "calcularTextField"
			calcularTextField.setText(moeda.format(valorTotal));
		}
		catch( NumberFormatException ex){
			inputNumerosTextField.setText("Nenhum número informado:");
			inputNumerosTextField.selectAll();
			inputNumerosTextField.requestFocus();

		}
		
	}
	public void initialize() {
		
		//evento do botão 1
		oneButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent evento) {
				inputNumerosTextField.setText(inputNumerosTextField.getText()+1);
			}
		});

		//evento dos numeros sao todos iguals, ou enxute para ficar um metodo so masi inteligente
		
		plusButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent evento) {
				//pega o texto do "inputNumerosTextField", converte para BigDecimal e salva em "valorParcial"
				//TEM QUE CONVERTER PRA BIGDECIMAL
				//valorParcial = BigDecimal.parseBigDecimal(inputNumerosTextField.getText());
			}
		});
		
		//arredonda para baixo de 0 a 4 e para cima de 5 a 9    	
		moeda.setRoundingMode(RoundingMode.HALF_UP);

		//listener para slider, calcular os %
		percentSlider.valueProperty().addListener(
				new ChangeListener<Number>(){
					@Override
					public void changed(ObservableValue<? extends Number> ov,Number valorAntigo, Number valorNovo) {

						//
						valorTotal = BigDecimal.valueOf(valorNovo.intValue()/100.0);

						//Altera "calcularTextField" para a porcentagem de "gorgetaPercent"
						calcularTextField.setText(percent.format(valorParcial));
					}
				}
				);
		Platform.runLater(() -> {
			calcularButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN),() -> {
				calcularButton.fire();
			});

		});

	}

}
