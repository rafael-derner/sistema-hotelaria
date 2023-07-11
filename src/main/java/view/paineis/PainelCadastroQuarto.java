package view.paineis;

import javax.swing.JPanel;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.google.protobuf.TextFormat.ParseException;
import com.privatejgoodies.forms.layout.ColumnSpec;
import com.privatejgoodies.forms.layout.FormLayout;
import com.privatejgoodies.forms.layout.FormSpecs;
import com.privatejgoodies.forms.layout.RowSpec;

import Util.Formatador;
import Util.JNumberFormatField;
import controller.QuartoController;
import model.exception.CampoInvalidoException;
import model.exception.QuartoJaUtilizadoException;
import model.vo.Quarto;
import model.vo.Usuario;

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
	private JNumberFormatField ftfValorDiaria;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JComboBox comboBox;
	private String[] tiposQuarto = {"B�sico","Intermedi�rio","Luxo"};
	private JLabel lblCadastroQuarto;
	private Quarto quartoVO;
	private QuartoController quartoController = new QuartoController();
	private int tipoDoQuarto;
	private MaskFormatter mascaraValorDiaria;

	/**
	 * Create the panel.
	 * @param quarto 
	 */
	public PainelCadastroQuarto(Quarto quarto) {
		if(quarto != null) {
			quartoVO = quarto;
		} else {
			quartoVO = new Quarto();
		}
		
		try {
			mascaraValorDiaria = new MaskFormatter("R$  ####,## ");
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
		
		ftfValorDiaria = new JNumberFormatField();
		add(ftfValorDiaria, "4, 14, fill, top");
		
		btnCancelar = new JButton("Cancelar");
		add(btnCancelar, "2, 16, fill, top");
		
		btnSalvar = new JButton("Salvar");
		add(btnSalvar, "6, 16, fill, top");
		

		if(this.quartoVO.getIdQuarto() != null) {
			preencherCamposDoFormulario();
		}
		
		

	}

	private void preencherCamposDoFormulario() {
		this.textNumeroQuarto.setText(this.quartoVO.getNumeroQuarto().toString());
		this.comboBox.setSelectedIndex(Formatador.formatarTipoQuarto(quartoVO.getTipoQuarto()));
		this.ftfValorDiaria.setText(this.quartoVO.getValorQuarto().toString().replace('.', ','));;
	}

	public boolean salvarQuarto() {
		// TODO Auto-generated method stub
		boolean retorno = false;
		String numeroQuarto = textNumeroQuarto.getText();
		if(!numeroQuarto.isEmpty()) {
			try {
				int numero = Integer.parseInt(numeroQuarto);
				quartoVO.setNumeroQuarto(numero);
			}catch (NumberFormatException numeroIncorreto) {
				JOptionPane.showMessageDialog(null,"Campo número do quarto deve receber caractéres numéricos.",
						"Erro",JOptionPane.ERROR_MESSAGE);
				
			}
		}
		quartoVO.setTipoQuarto((String) comboBox.getSelectedItem());
		
		String valorQuarto = ftfValorDiaria.getText().replace("R$", "").trim();
		if (!valorQuarto.isEmpty()) {
		    valorQuarto = valorQuarto.replace(',', '.');
		    try {
		        double valor = Double.parseDouble(valorQuarto);
		        quartoVO.setValorQuarto(valor);
		    } catch (NumberFormatException valorIncorreto) {
		        JOptionPane.showMessageDialog(null, "Campo valor da diária deve receber caracteres numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
		}
		
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

	public JButton getBtnSalvar() {
		return btnSalvar;
	}
	
	public JButton getBtnCancelar() {
		return btnCancelar;
	}


}
