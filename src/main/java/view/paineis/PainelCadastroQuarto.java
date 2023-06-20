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
		setLayout(new com.jgoodies.forms.layout.FormLayout(new com.jgoodies.forms.layout.ColumnSpec[] {
				com.jgoodies.forms.layout.ColumnSpec.decode("32px:grow"),
				com.jgoodies.forms.layout.ColumnSpec.decode("121px"),
				com.jgoodies.forms.layout.FormSpecs.UNRELATED_GAP_COLSPEC,
				com.jgoodies.forms.layout.ColumnSpec.decode("130px"),
				com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
				com.jgoodies.forms.layout.ColumnSpec.decode("127px"),
				com.jgoodies.forms.layout.FormSpecs.UNRELATED_GAP_COLSPEC,
				com.jgoodies.forms.layout.ColumnSpec.decode("default:grow"),},
			new com.jgoodies.forms.layout.RowSpec[] {
				com.jgoodies.forms.layout.FormSpecs.UNRELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
				com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.RowSpec.decode("26px"),
				com.jgoodies.forms.layout.FormSpecs.UNRELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
				com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.RowSpec.decode("16px"),
				com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.RowSpec.decode("23px"),
				com.jgoodies.forms.layout.FormSpecs.UNRELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.RowSpec.decode("26px"),
				com.jgoodies.forms.layout.FormSpecs.UNRELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.RowSpec.decode("29px"),
				com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
				com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,}));
		
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
				quartoVO = new Quarto();
				String numeroQuarto = textNumeroQuarto.getText();
				int numero = Integer.parseInt(numeroQuarto);
				quartoVO.setNumeroQuarto(numero);
				quartoVO.setTipoQuarto((String) comboBox.getSelectedItem());
				String valorQuarto = ftfValorDiaria.getText();
				try {
					double valor = Double.parseDouble(valorQuarto);
					quartoVO.setValorQuarto(valor);
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Erro ao converter.","Erro",JOptionPane.ERROR_MESSAGE);
				}
				
				try {
				    try {
						quartoController.inserir(quartoVO);
					} catch (CampoInvalidoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    JOptionPane.showMessageDialog(null,"Quarto cadastrado com Sucesso!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
				} catch (QuartoJaUtilizadoException e2) {
				    JOptionPane.showMessageDialog(null,e2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		add(btnSalvar, "6, 16, fill, top");

	}

}
