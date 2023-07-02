package view.paineis;

import java.util.ArrayList;

import javax.swing.JPanel;

import model.exception.ExclusaoGerenteException;
import model.exception.UsuarioComReservaException;
import model.exception.UsuarioInativoException;
import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.UsuarioController;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class PainelListagemUsuario extends JPanel {

	private UsuarioController usuarioController = new UsuarioController();
	private UsuarioSeletor usuarioSeletor = new UsuarioSeletor();
	
	private ArrayList<Usuario> usuarios;
	private Usuario usuarioSelecionado;
	
	private String[] nomesColunas = { "Nome", "CPF", "Telefone", "Perfil", "Ativo" };
	private JTable tblUsuarios;
	private JTextField tfNome;
	private JLabel lblCpf;
	private JFormattedTextField tfCpf;
	private MaskFormatter mascaraCpf;
	private JLabel lblPerfil;
	private JComboBox comboBox;
	private String[] tiposDePerfil = {"", "Recepcionista", "Gerente"};
	
	private JButton btnConsultar;
	private JButton btnLimpar;
	private JButton btnEditar;
	private JButton btnInativar;

	private final int TAMANHO_PAGINA = 40;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private JLabel lblPaginacao = new JLabel();
	private JButton btnVoltarPagina;
	private JButton btnAvancarPagina;
	private JButton btnAdicionarNovoUsuario;
	
	public PainelListagemUsuario() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("20dlu:grow"),
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
				
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		btnInativar = new JButton("Inativar");
//		btnInativar.setEnabled(false);
//		btnInativar.setBackground(new Color(255, 0, 0));		
//		btnInativar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// COMPLETAR ACTION LISTENER
//			}
//		});
//		add(btnInativar, "12, 18");
		
		btnInativar = new JButton("Inativar");
		btnInativar.setBackground(new Color(255, 0, 0));
		btnInativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null, "Você deseja realmente inativar o usuário selecionado?");
				
				if(opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						usuarioController.inativar(usuarioSelecionado.getIdUsuario());
						JOptionPane.showMessageDialog(null, "Usuário inativado com sucesso");
						usuarios = (ArrayList<Usuario>) usuarioController.consultarTodos();
						atualizarTabelaUsuarios();
					} catch (UsuarioInativoException usuarioInativoException) {
						JOptionPane.showMessageDialog(null, usuarioInativoException.getMessage(), "Atenção", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
				
				btnAdicionarNovoUsuario = new JButton("Adicionar novo Usuário");
				btnAdicionarNovoUsuario.setBackground(new Color(128, 255, 128));
				add(btnAdicionarNovoUsuario, "12, 18");
		
				btnEditar = new JButton("Editar");
				btnEditar.setEnabled(false);
				btnEditar.setBackground(new Color(50, 204, 233));
				add(btnEditar, "14, 18");
		add(btnInativar, "16, 18");
		
		JLabel lblListagemUsuarios = new JLabel("Listagem de Usuários");
		lblListagemUsuarios.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblListagemUsuarios, "4, 4, 13, 1, center, default");
		
		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "4, 8, 3, 1");
		
		lblCpf = new JLabel("CPF:");
		add(lblCpf, "8, 8, 3, 1");
		
		lblPerfil = new JLabel("Perfil");
		add(lblPerfil, "12, 8");
		
		tfNome = new JTextField();
		add(tfNome, "4, 10, 3, 1, fill, default");
		tfNome.setColumns(10);
		
		tfCpf = new JFormattedTextField(mascaraCpf);
		add(tfCpf, "8, 10, 3, 1, fill, default");
		tfCpf.setColumns(10);
		
		comboBox = new JComboBox(tiposDePerfil);
		add(comboBox, "12, 10, fill, default");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			private UsuarioSeletor usuarioSeletor;

			public void actionPerformed(ActionEvent e) {
				buscarUsuarioComFiltro();
			}
		});
		add(btnConsultar, "14, 10");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNome.setText(null);
				tfCpf.setText(null);
				comboBox.setSelectedIndex(0);
			}
		});
		add(btnLimpar, "16, 10");
		
		tblUsuarios = new JTable();
		add(tblUsuarios, "4, 12, 13, 5, fill, fill");
		this.limparTabelaUsuarios();
		buscarUsuarioComFiltro();
		
		tblUsuarios.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int elementoSelecionado = tblUsuarios.getSelectedRow();
				
				if(elementoSelecionado > 0) {
					btnEditar.setEnabled(true);
					btnInativar.setEnabled(true);
					usuarioSelecionado = usuarios.get(elementoSelecionado - 1);
				}
			}
		});		
		
		btnVoltarPagina = new JButton("<<");
		btnVoltarPagina.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarUsuarioComFiltro();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnVoltarPagina, "4, 18");
		
		btnAvancarPagina = new JButton(">>");
		btnAvancarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				buscarUsuarioComFiltro();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnAvancarPagina, "8, 18");
				
		lblPaginacao.setText("1 / " + totalPaginas);
		add(lblPaginacao, "6, 18, center, default");

		atualizarQuantidadePaginas();
		
		buscarUsuarioComFiltro();
	} 
	
	protected void buscarUsuarioComFiltro() {
		usuarioSeletor = new UsuarioSeletor();		
		usuarioSeletor.setLimite(TAMANHO_PAGINA);
		usuarioSeletor.setPagina(paginaAtual);
		
		usuarioSeletor.setNome(tfNome.getText());
		usuarioSeletor.setPerfil((String) comboBox.getSelectedItem());

		if(!tfCpf.getText().contains("   .   .   -  ")) {
			try {
				String cpfSemMascara = (String) mascaraCpf.stringToValue(tfCpf.getText());
				usuarioSeletor.setCpf(cpfSemMascara);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		usuarios = (ArrayList<Usuario>) usuarioController.consultarComFiltro(usuarioSeletor);
		
		atualizarTabelaUsuarios();
		atualizarQuantidadePaginas();
	}
	
	private void atualizarTabelaUsuarios() {
		this.limparTabelaUsuarios();

		DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();

		for (Usuario usuario : usuarios) {
			Object[] novaLinhaDaTabela = new Object[5];
			novaLinhaDaTabela[0] = usuario.getNome();
			novaLinhaDaTabela[1] = usuario.getCpf();
			novaLinhaDaTabela[2] = usuario.getTelefone();
			novaLinhaDaTabela[3] = usuario.getPerfil();
			novaLinhaDaTabela[4] = usuario.isAtivo() ? "Sim" : "Não";

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void atualizarQuantidadePaginas() {
		int totalRegistros = usuarioController.contarTotalRegistrosComFiltros(usuarioSeletor);

		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		if(totalRegistros % TAMANHO_PAGINA > 0) { 
			totalPaginas++;
		}
		
		lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
	}
	
	private void limparTabelaUsuarios() {
		tblUsuarios.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		btnEditar.setEnabled(false);
		btnInativar.setEnabled(false);
	}
	
	public JButton getBtnEditar() {
		return this.btnEditar;
	}
	
	public Usuario getUsuarioSelecionado() {
		return this.usuarioSelecionado;
	}

	public JButton getBtnAdicionarNovoUsuario() {
		return this.btnAdicionarNovoUsuario;
	}
}
