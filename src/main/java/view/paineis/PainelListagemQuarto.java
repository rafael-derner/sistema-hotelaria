package view.paineis;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JButton;

public class PainelListagemQuarto extends JPanel {
	private JTextField textField;
	private ButtonGroup grupo;
	private JRadioButton rdbtnIntermediario;
	private JRadioButton rdbtnLuxo;
	private JRadioButton rdbtnBasico;
	private Component lblOpcaoModelo;
	private JRadioButton rdbtnModeloQuarto;
	private JLabel lblConsulta;
	private JRadioButton rdbtnNumeroQuarto;
	private JTextField txtNumeroQuarto;
	private JLabel lblNumeroQuarto;
	private JTextField textField_1;
	private JTable table;
	private JButton btnConsultar;
	private JButton btnVoltar;
	private JButton btnLimpar;

	/**
	 * Create the panel.
	 */
	

	
	
	public PainelListagemQuarto() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(84dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		grupo = new ButtonGroup();
		
		lblConsulta = new JLabel("Consultar por:");
		add(lblConsulta, "4, 6");
		
		
		lblOpcaoModelo = new JLabel("Selecione o(s) modelo(s) de quarto:");
		add(lblOpcaoModelo, "4, 12");
		
		
		rdbtnBasico = new JRadioButton("Básico");
		add(rdbtnBasico, "4, 14");
		
		rdbtnIntermediario = new JRadioButton("Intermediário");
		add(rdbtnIntermediario, "6, 14");
		
		rdbtnLuxo = new JRadioButton("Luxo");
		add(rdbtnLuxo, "8, 14");
		
		lblNumeroQuarto = new JLabel("Digite o número do quarto:");
		add(lblNumeroQuarto, "4, 10, fill, default");
		
		txtNumeroQuarto = new JTextField();
		add(txtNumeroQuarto, "6, 10, fill, default");
		txtNumeroQuarto.setColumns(10);
		
		
		esconderTodosComponenstes();
		
		rdbtnNumeroQuarto = new JRadioButton("Número do Quarto");
		rdbtnNumeroQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderTodosComponenstes();
				mostarOpcaoNumeroQuarto();
			}
		});
		add(rdbtnNumeroQuarto, "4, 8");
		grupo.add(rdbtnNumeroQuarto);
		
		
		rdbtnModeloQuarto = new JRadioButton("Modelo do Quarto");
		rdbtnModeloQuarto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderTodosComponenstes();
				mostrarOpcaoModeloQuarto();
			}

			
		});
		add(rdbtnModeloQuarto, "6, 8");
		grupo.add(rdbtnModeloQuarto);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnLimpar, "4, 18");
		
		btnConsultar = new JButton("Consultar");
		add(btnConsultar, "8, 18");
		
		table = new JTable();
		add(table, "4, 20, 5, 1, fill, fill");
		
		btnVoltar = new JButton("Voltar");
		add(btnVoltar, "4, 22");
		

	}
	
	private void esconderTodosComponenstes() {
		lblOpcaoModelo.setVisible(false);
		rdbtnBasico.setVisible(false);
		rdbtnIntermediario.setVisible(false);
		rdbtnLuxo.setVisible(false);
		txtNumeroQuarto.setVisible(false);
		lblNumeroQuarto.setVisible(false);
	}
	
	private void mostarOpcaoNumeroQuarto() {
		txtNumeroQuarto.setVisible(true);
		lblNumeroQuarto.setVisible(true);
	}
	
	private void mostrarOpcaoModeloQuarto() {
		lblOpcaoModelo.setVisible(true);
		rdbtnBasico.setVisible(true);
		rdbtnIntermediario.setVisible(true);
		rdbtnLuxo.setVisible(true);
	}

}
