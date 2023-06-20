package view.paineis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.privatejgoodies.forms.layout.ColumnSpec;
import com.privatejgoodies.forms.layout.FormLayout;
import com.privatejgoodies.forms.layout.FormSpecs;
import com.privatejgoodies.forms.layout.RowSpec;

import model.vo.Quarto;

import javax.swing.JLabel;
import java.awt.Font;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PainelListagemQuarto extends JPanel {

	private JPanel contentPane;
	private JTextField txtNumero;
	private JTable tableListagemQuartos;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnGerarPlanilha;
	private JButton btnLimpar;
	private JButton btnBuscar;
	private JComboBox cBValorQuarto;
	private JComboBox cBTipoQuarto;
	private String[] tipoDeQuarto = {"","Básico","Intemediário","Luxo"};
	private String[] valoresQuarto = {"","Até R$ 249,99","De R$ 250,00 a R$ 499,99","De R$ 500,00 a R$ 1.000,00"};
	private JLabel lblValorQuarto;
	private JLabel lblTipoDeQuarto;
	private JLabel lblNumero;
	private JLabel lblListagemQuartos;
	private ArrayList<Quarto> quartos;
	
	private Controller controller;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public PainelListagemQuarto(Quarto quarto) {
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("100dlu"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblListagemQuartos = new JLabel("Listagem de Quartos");
		lblListagemQuartos.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblListagemQuartos, "4, 2, 9, 1, center, default");
		
		lblNumero = new JLabel("Número:");
		add(lblNumero, "4, 4");
		
		lblTipoDeQuarto = new JLabel("Tipo de Quarto:");
		add(lblTipoDeQuarto, "6, 4");
		
		lblValorQuarto = new JLabel("Valor do Quarto:");
		add(lblValorQuarto, "8, 4");
		
		txtNumero = new JTextField();
		add(txtNumero, "4, 6, fill, default");
		txtNumero.setColumns(10);
		
		cBTipoQuarto = new JComboBox(tipoDeQuarto);
		add(cBTipoQuarto, "6, 6, fill, default");
		
		cBValorQuarto = new JComboBox(valoresQuarto);
		add(cBValorQuarto, "8, 6, fill, default");
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				quartos = (ArrayList<Quarto>) controller.consultarTodos();
				
			}
		});
		add(btnBuscar, "10, 6");
		
		btnLimpar = new JButton("Limpar");
		add(btnLimpar, "12, 6");
		
		tableListagemQuartos = new JTable();
		add(tableListagemQuartos, "4, 8, 9, 1, fill, fill");
		
		btnGerarPlanilha = new JButton("Gerar Planilha");
		add(btnGerarPlanilha, "4, 10");
		
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(Color.BLUE);
		add(btnEditar, "10, 10");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(Color.RED);
		add(btnExcluir, "12, 10");
	}

}
