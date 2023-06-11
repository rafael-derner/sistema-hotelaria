package view.paineis;

import java.util.ArrayList;

import javax.swing.JPanel;

import model.vo.Usuario;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

public class PainelListagemHospede extends JPanel {

	private ArrayList<Usuario> clientes;
	private String[] nomesColunas = { "Nome", "CPF", "Telefone" };
	private JTable tblHospedes;
	private JTextField tfNome;
	private JLabel lblCpf;
	private JTextField tfCpf;
	private JButton btnConsultar;
	private JButton btnNewButton;
	private JButton btnEditar;
	private JButton btnExcluir;
	
	public PainelListagemHospede() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),},
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
				RowSpec.decode("default:grow"),
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblListagemHospede = new JLabel("Listagem de H\u00F3spedes");
		lblListagemHospede.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblListagemHospede, "4, 4, 9, 1, center, default");
		
		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "4, 8");
		
		lblCpf = new JLabel("CPF:");
		add(lblCpf, "6, 8");
		
		tfNome = new JTextField();
		add(tfNome, "4, 10, fill, default");
		tfNome.setColumns(10);
		
		tfCpf = new JTextField();
		add(tfCpf, "6, 10, fill, default");
		tfCpf.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		add(btnConsultar, "10, 10");
		
		btnNewButton = new JButton("Limpar");
		add(btnNewButton, "12, 10");
		
		tblHospedes = new JTable();
		add(tblHospedes, "4, 12, 9, 5, fill, fill");
		this.limparTabelaHospedes();
		
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(0, 128, 192));
		add(btnEditar, "10, 18");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(255, 0, 0));
		add(btnExcluir, "12, 18");
	} 
	
	private void limparTabelaHospedes() {
		tblHospedes.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
}
