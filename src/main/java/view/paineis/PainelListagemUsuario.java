package view.paineis;

import java.util.ArrayList;

import javax.swing.JPanel;

import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.UsuarioController;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelListagemUsuario extends JPanel {

	private UsuarioController usuarioController = new UsuarioController();
	private UsuarioSeletor usuarioSeletor;
	private ArrayList<Usuario> usuarios;
	private String[] nomesColunas = { "Nome", "CPF", "Telefone", "Perfil" };
	private JTable tblUsuarios;
	private JTextField tfNome;
	private JLabel lblCpf;
	private JTextField tfCpf;
	private JLabel lblPerfil;
	private JComboBox comboBox;
	private String[] tiposDePerfil = {"", "Recepcionista", "Gerente"};
	private JButton btnConsultar;
	private JButton btnLimpar;
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
		btnConsultar.addActionListener(new ActionListener() {
			private UsuarioSeletor usuarioSeletor;

			public void actionPerformed(ActionEvent e) {
				buscarUsuarioComFiltro();
			}
		});
		add(btnConsultar, "10, 10");
		
		btnLimpar = new JButton("Limpar");
		add(btnLimpar, "12, 10");
		
		tblUsuarios = new JTable();
		add(tblUsuarios, "4, 12, 9, 5, fill, fill");
		this.limparTabelaUsuarios();
		
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(50, 204, 233));
		add(btnEditar, "10, 18");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(new Color(255, 0, 0));
		add(btnExcluir, "12, 18");
	} 
	
	protected void buscarUsuarioComFiltro() {
		usuarioSeletor = new UsuarioSeletor();
		
		usuarioSeletor.setNome(tfNome.getText());
		usuarioSeletor.setCpf(tfCpf.getText());
		usuarioSeletor.setPerfil((String) comboBox.getSelectedItem());
		
		usuarios = (ArrayList<Usuario>) usuarioController.consultarComFiltro(usuarioSeletor);
		
		atualizarTabelaUsuarios();
	}
	
	private void atualizarTabelaUsuarios() {
		this.limparTabelaUsuarios();

		DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();

		for (Usuario usuario : usuarios) {
			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = usuario.getNome();
			novaLinhaDaTabela[1] = usuario.getCpf();
			novaLinhaDaTabela[2] = usuario.getPerfil();

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaUsuarios() {
		tblUsuarios.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
}
