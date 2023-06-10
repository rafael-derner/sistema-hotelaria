package view.paineis;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class PainelCadastroQuarto extends JPanel {
	private JTextField textNumeroQuarto;
	private JLabel lblNumeroQuarto;
	private ButtonGroup buttonGroup;
	private JLabel lblTipoQuarto;
	private JRadioButton rdbtnBasico;
	private JRadioButton rdbtnIntermediario;
	private JRadioButton rdbtnLuxo;
	private JLabel lblValorDiaria;
	private JFormattedTextField ftfValorDiaria;
	private JButton btnCancelar;
	private JButton btnSalvar;

	/**
	 * Create the panel.
	 */
	public PainelCadastroQuarto() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(42dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblNumeroQuarto = new JLabel("Número do Quarto:");
		add(lblNumeroQuarto, "4, 4, fill, default");
		
		textNumeroQuarto = new JTextField();
		add(textNumeroQuarto, "6, 4");
		textNumeroQuarto.setColumns(10);
		
		buttonGroup = new ButtonGroup();
		
		lblTipoQuarto = new JLabel("Tipo do Quarto:");
		add(lblTipoQuarto, "4, 8, fill, default");
		
		rdbtnBasico = new JRadioButton("Básico");
		add(rdbtnBasico, "4, 10, fill, default");
		buttonGroup.add(rdbtnBasico);
		
		rdbtnIntermediario = new JRadioButton("Intermediário");
		add(rdbtnIntermediario, "6, 10, fill, default");
		buttonGroup.add(rdbtnIntermediario);
		
		rdbtnLuxo = new JRadioButton("Luxo");
		add(rdbtnLuxo, "8, 10, fill, default");
		buttonGroup.add(rdbtnLuxo);
		
		lblValorDiaria = new JLabel("Valor da Diária:");
		add(lblValorDiaria, "4, 14, fill, default");
		
		ftfValorDiaria = new JFormattedTextField();
		add(ftfValorDiaria, "6, 14, fill, default");
		
		btnCancelar = new JButton("Cancelar");
		add(btnCancelar, "4, 18, fill, default");
		
		btnSalvar = new JButton("Salvar");
		add(btnSalvar, "8, 18, fill, default");

	}

}
