package view.paineis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.privatejgoodies.forms.layout.ColumnSpec;
import com.privatejgoodies.forms.layout.FormLayout;
import com.privatejgoodies.forms.layout.FormSpecs;
import com.privatejgoodies.forms.layout.RowSpec;

import Util.Formatador;
import controller.QuartoController;
import model.exception.QuartoComReservaException;
import model.seletor.QuartoSeletor;
import model.vo.Quarto;
import model.vo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PainelListagemQuarto extends JPanel {

	private JPanel contentPane;
	private JTextField txtNumero;
	private JTable tableListagemQuartos;
	private JButton btnInativar;
	private JButton btnEditar;
	private JButton btnGerarPlanilha;
	private JButton btnLimpar;
	private JButton btnBuscar;
	private JComboBox cBValorQuarto;
	private JComboBox cBTipoQuarto;
	private String[] tipoDeQuarto = {"","Básico","Intermediário","Luxo"};
	private String[] valoresQuarto = {"Ativo","Inativo"};
	private JLabel lblValorQuarto;
	private JLabel lblTipoDeQuarto;
	private JLabel lblNumero;
	private String[] colunas = {"Número do Quarto","Tipo do Quarto","Valor do Quarto","Ativo"};
	private JLabel lblListagemQuartos;
	private ArrayList<Quarto> quartos;
	private Quarto quartoSelecionado;
	private QuartoSeletor quartoSeletor;
	
	private QuartoController quartoController = new QuartoController();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public PainelListagemQuarto() {
		
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
		
		lblValorQuarto = new JLabel("Condição do Quarto:");
		add(lblValorQuarto, "8, 4");
		
		txtNumero = new JTextField();
		add(txtNumero, "4, 6, fill, default");
		txtNumero.setColumns(10);
		
		cBTipoQuarto = new JComboBox(tipoDeQuarto);
		add(cBTipoQuarto, "6, 6, fill, default");
		
		cBValorQuarto = new JComboBox(valoresQuarto);
		cBValorQuarto.setSelectedIndex(-1);
		add(cBValorQuarto, "8, 6, fill, default");
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				buscarQuartos();
				
			}
		});
		add(btnBuscar, "10, 6");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText(null);
				cBTipoQuarto.setSelectedIndex(0);
				cBValorQuarto.setSelectedIndex(0);
			}
		});
		add(btnLimpar, "12, 6");
		
		tableListagemQuartos = new JTable();
		add(tableListagemQuartos, "4, 8, 9, 1, fill, fill");
		
		tableListagemQuartos.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int elementoSelecionado = tableListagemQuartos.getSelectedRow();
				if(elementoSelecionado > 0) {
					btnEditar.setEnabled(true);
					btnInativar.setEnabled(true);
					quartoSelecionado = quartos.get(elementoSelecionado - 1);
				}
				
			}
		});
		
		btnGerarPlanilha = new JButton("Gerar Planilha");
		add(btnGerarPlanilha, "4, 10");
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBackground(Color.BLUE);
		add(btnEditar, "10, 10");
		
		btnInativar = new JButton("Inativar");
		btnInativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, "Voce deseja realmente Inativar o Quarto selecionado?");
				if(opcaoSelecionada == JOptionPane.YES_OPTION){
					try {
						quartoController.inativar(quartoSelecionado.getIdQuarto());
						JOptionPane.showMessageDialog(null, "Quarto inativado com sucesso!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
						quartos = (ArrayList<Quarto>) quartoController.consultarTodos();
					}catch (QuartoComReservaException quartoComReservaException) {
						JOptionPane.showConfirmDialog(null, quartoComReservaException.getMessage(), "Atenção" , JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnInativar.setBackground(Color.RED);
		add(btnInativar, "12, 10");
	}
	
	private void buscarQuartos() {
		
		quartoSeletor = new QuartoSeletor();
		String numeroQuarto = txtNumero.getText();
		if (numeroQuarto.isEmpty()) {
		} else {
			try {
				int numero = Integer.parseInt(numeroQuarto);
				quartoSeletor.setNumeroQuarto(numero);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		quartoSeletor.setTipoQuarto((String) cBTipoQuarto.getSelectedItem());
		quartos = (ArrayList<Quarto>) quartoController.consultarComFiltro(quartoSeletor);
		atualizarTabelaQuartos();

	}

	private void atualizarTabelaQuartos() {
		this.limparTabelaQuartos();
		DefaultTableModel model = (DefaultTableModel) tableListagemQuartos.getModel();
		
		for (Quarto quarto : quartos) {
			Object[] novaLinhaTabela = new Object[4];
			novaLinhaTabela[0] = quarto.getNumeroQuarto();
			novaLinhaTabela[1] = quarto.getTipoQuarto();
			novaLinhaTabela[2] = quarto.getValorQuarto();
			novaLinhaTabela[3] = quarto.isAtivo() ? "Sim" : "Não";
			model.addRow(novaLinhaTabela);
		}
	}

	private void limparTabelaQuartos() {
		tableListagemQuartos.setModel(new DefaultTableModel(new Object[][] {colunas,},colunas));
		btnEditar.setEnabled(false);
		btnInativar.setEnabled(false);
	}
	
	
	
	public Quarto getQuartoSelecionado() {
		return this.quartoSelecionado;
	}

	public JButton getBtnExcluir() {
		return btnInativar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

}
