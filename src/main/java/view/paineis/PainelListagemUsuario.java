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

public class PainelListagemUsuario extends JPanel {

	private ArrayList<Usuario> clientes;
	private String[] nomesColunas = { "Nome", "CPF", "Telefone", "Perfil" };
	private JTable tblUsuarios;
	private JTextField tfNome;
	private JLabel lblCpf;
	private JTextField tfCpf;
	private JLabel lblPerfil;
	private JComboBox comboBox;
	private String[] tiposDePerfil = {"", "Recepcionista", "Gerente"};
	private JButton btnConsultar;
	private JButton btnNewButton;
	private JButton btnEditar;
	private JButton btnExcluir;
	
	public PainelListagemUsuario() {
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
		
		JLabel lblListagemUsuarios = new JLabel("Listagem de Usu\u00E1rios");
		lblListagemUsuarios.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblListagemUsuarios, "4, 4, 9, 1, center, default");
		
		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "4, 8");
		
		lblCpf = new JLabel("CPF:");
		add(lblCpf, "6, 8");
		
		lblPerfil = new JLabel("Perfil");
		add(lblPerfil, "8, 8");
		
		tfNome = new JTextField();
		add(tfNome, "4, 10, fill, default");
		tfNome.setColumns(10);
		
		tfCpf = new JTextField();
		add(tfCpf, "6, 10, fill, default");
		tfCpf.setColumns(10);
		
		comboBox = new JComboBox(tiposDePerfil);
		add(comboBox, "8, 10, fill, default");
		
		btnConsultar = new JButton("Consultar");
		add(btnConsultar, "10, 10");
		
		btnNewButton = new JButton("Limpar");
		add(btnNewButton, "12, 10");
		
		tblUsuarios = new JTable();
		add(tblUsuarios, "4, 12, 9, 5, fill, fill");
		this.limparTabelaClientes();
		
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(0, 128, 192));
		add(btnEditar, "10, 18");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(255, 0, 0));
		add(btnExcluir, "12, 18");
	} 
	
	private void limparTabelaClientes() {
		tblUsuarios.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
}
