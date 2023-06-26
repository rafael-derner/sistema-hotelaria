package view.paineis;

import javax.swing.JPanel;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.google.protobuf.TextFormat.ParseException;
import com.privatejgoodies.forms.layout.ColumnSpec;
import com.privatejgoodies.forms.layout.FormLayout;
import com.privatejgoodies.forms.layout.FormSpecs;
import com.privatejgoodies.forms.layout.RowSpec;

import controller.QuartoController;
import model.exception.CampoInvalidoException;
import model.exception.QuartoJaUtilizadoException;
import model.vo.Quarto;

import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class PainelCadastroQuarto extends JPanel {
	private JTextField textNumeroQuarto;
	private JLabel lblNumeroQuarto;
	private ButtonGroup buttonGroup;
	private JLabel lblTipoQuarto;
	private JLabel lblValorDiaria;
	private JFormattedTextField ftfValorDiaria;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JComboBox comboBox;
	private String[] tiposQuarto = {"Básico","Intermediário","Luxo"};
	private JLabel lblCadastroQuarto;
	private Quarto quartoVO;
	private QuartoController quartoController = new QuartoController();

	/**
	 * Create the panel.
	 */
	public PainelCadastroQuarto() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("32px:grow"),
				ColumnSpec.decode("121px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("130px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("127px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("16px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("29px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		buttonGroup = new ButtonGroup();
		
		lblCadastroQuarto = new JLabel("Cadastro de Quarto");
		lblCadastroQuarto.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblCadastroQuarto, "2, 2, 5, 1, center, default");
		
		lblNumeroQuarto = new JLabel("Número do Quarto:");
		add(lblNumeroQuarto, "4, 4, left, center");
		
		textNumeroQuarto = new JTextField();
		add(textNumeroQuarto, "4, 6, left, top");
		textNumeroQuarto.setColumns(10);
		
		lblTipoQuarto = new JLabel("Tipo do Quarto:");
		add(lblTipoQuarto, "4, 8, fill, top");
		
		comboBox = new JComboBox(tiposQuarto);
		add(comboBox, "4, 10, fill, default");
		
		lblValorDiaria = new JLabel("Valor da Diária:");
		add(lblValorDiaria, "4, 12, fill, center");
		
		ftfValorDiaria = new JFormattedTextField();
		add(ftfValorDiaria, "4, 14, fill, top");
		
		btnCancelar = new JButton("Cancelar");
		add(btnCancelar, "2, 16, fill, top");
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarQuarto();
			}
		});
		add(btnSalvar, "6, 16, fill, top");

	}

	protected boolean salvarQuarto() {
		// TODO Auto-generated method stub
		quartoVO = new Quarto();
		String numeroQuarto = textNumeroQuarto.getText();
		if(!numeroQuarto.isEmpty()) {
			try {
				int numero = Integer.parseInt(numeroQuarto);
				quartoVO.setNumeroQuarto(numero);
			}catch (NumberFormatException numeroIncorreto){
				JOptionPane.showMessageDialog(null,"Campo número do quarto deve receber caractéres numéricos.",
						"Erro",JOptionPane.ERROR_MESSAGE);
				
			}
		}
		quartoVO.setTipoQuarto((String) comboBox.getSelectedItem());			
		String valorQuarto = ftfValorDiaria.getText();
		if (!valorQuarto.isEmpty()) {
		    valorQuarto = valorQuarto.replace(',', '.'); // Replace comma with a point
		    try {
		        double valor = Double.parseDouble(valorQuarto);
		        DecimalFormat valorFormatado = new DecimalFormat("#.00");
		        quartoVO.setValorQuarto(valor);
		    } catch (NumberFormatException valorIncorreto) {
		        JOptionPane.showMessageDialog(null, "Campo valor da diária deve receber caracteres numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}

		boolean retorno = false;
		try {
			if(quartoVO.getIdQuarto()!= null) {
				if(quartoController.atualizar(quartoVO)) {
					JOptionPane.showMessageDialog(null, "Quarto atualizado com sucesso!",
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
					retorno = true;
				}else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o quarto. Verifique os dados e tente novamente",
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				quartoController.inserir(quartoVO);
				JOptionPane.showMessageDialog(null, "Quarto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				retorno = true;
			}
		}catch (QuartoJaUtilizadoException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (CampoInvalidoException exceptionCampoInvalido) {
			JOptionPane.showMessageDialog(null, exceptionCampoInvalido.getMessage(), 
					"Erro", JOptionPane.ERROR_MESSAGE); 
		}
		return retorno; 
		
	}

}
