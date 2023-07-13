package view.paineis;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.HospedeController;
import model.exception.CampoInvalidoException;
import model.seletor.HospedeSeletor;
import model.vo.Hospede;

public class PainelListagemHospede extends JPanel {
	private HospedeController hospedeController = new HospedeController();
	private HospedeSeletor hospedeSeletor = new HospedeSeletor();

	private ArrayList<Hospede> hospedes;
	private Hospede hospedeSelecionado;

	private String[] nomesColunas = { "Nome", "CPF", "Telefone" };
	private JTable tblHospedes;
	private JTextField tfNome;
	private JLabel lblCpf;
	private JFormattedTextField tfCpf;
	private MaskFormatter mascaraCpf;
	private JButton btnConsultar;
	private JButton btnLimpar;
	private JButton btnEditar;
	private JButton btnAdicionarNovoHospede;
	private JButton btnGerarRelatorio;

	private final int TAMANHO_PAGINA = 40;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private JLabel lblPaginacao = new JLabel();
	private JButton btnVoltarPagina;
	private JButton btnAvancarPagina;

	public PainelListagemHospede() {
		setLayout(new FormLayout(
				new ColumnSpec[] { 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(100dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("37px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("37px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("37px:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("37px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("150px:grow"), 
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(100dlu;default)"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lblCpf = new JLabel("CPF:");
		add(lblCpf, "12, 8");

		btnVoltarPagina = new JButton("<<");
		btnVoltarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarHospedeComFiltro();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnVoltarPagina, "4, 18");

		lblPaginacao.setText("1 / " + totalPaginas);
		add(lblPaginacao, "6, 18, center, default");

		btnAvancarPagina = new JButton(">>");
		btnAvancarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				buscarHospedeComFiltro();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnAvancarPagina, "8, 18");

		btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para o relatório...");
				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();
					String resultado;
					try {
						resultado = hospedeController.gerarPlanilha(hospedes, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (CampoInvalidoException campoInvalidoException) {
						JOptionPane.showConfirmDialog(null, campoInvalidoException.getMessage(), "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		add(btnGerarRelatorio, "12, 18");

		btnAdicionarNovoHospede = new JButton("Adicionar novo Hóspede");
		btnAdicionarNovoHospede.setBackground(new Color(128, 255, 128));
		add(btnAdicionarNovoHospede, "14, 18");

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBackground(new Color(50, 204, 233));
		add(btnEditar, "16, 18");

		JLabel lblListagemHospedes = new JLabel("Listagem de Hóspedes");
		lblListagemHospedes.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblListagemHospedes, "4, 4, 15, 1, center, default");

		JLabel lblNome = new JLabel("Nome do Hóspede:");
		add(lblNome, "4, 8, 7, 1");

		tfNome = new JTextField();
		add(tfNome, "4, 10, 7, 1, fill, default");
		tfNome.setColumns(10);

		tfCpf = new JFormattedTextField(mascaraCpf);
		add(tfCpf, "12, 10, fill, default");
		tfCpf.setColumns(10);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			private HospedeSeletor HospedeSeletor;

			public void actionPerformed(ActionEvent e) {
				buscarHospedeComFiltro();
			}
		});
		add(btnConsultar, "16, 10");

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNome.setText(null);
				tfCpf.setText(null);
			}
		});
		add(btnLimpar, "18, 10");

		tblHospedes = new JTable();
		add(tblHospedes, "4, 12, 15, 5, fill, fill");
		this.limparTabelaHospedes();

		tblHospedes.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int elementoSelecionado = tblHospedes.getSelectedRow();

				if (elementoSelecionado > 0) {
					btnEditar.setEnabled(true);
					hospedeSelecionado = hospedes.get(elementoSelecionado - 1);
				}
			}
		});

		atualizarQuantidadePaginas();
		buscarHospedeComFiltro();
	}

	protected void buscarHospedeComFiltro() {
		hospedeSeletor = new HospedeSeletor();

		hospedeSeletor.setNome(tfNome.getText());

		if (!tfCpf.getText().contains("   .   .   -  ")) {
			try {
				String cpfSemMascara = (String) mascaraCpf.stringToValue(tfCpf.getText());
				hospedeSeletor.setCpf(cpfSemMascara);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		hospedes = (ArrayList<Hospede>) hospedeController.consultarComFiltro(hospedeSeletor);

		atualizarTabelaHospedes();
		atualizarQuantidadePaginas();
	}

	private void atualizarTabelaHospedes() {
		this.limparTabelaHospedes();

		DefaultTableModel model = (DefaultTableModel) tblHospedes.getModel();

		for (Hospede hospede : hospedes) {
			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = hospede.getNome();
			novaLinhaDaTabela[1] = hospede.getCpf();
			novaLinhaDaTabela[2] = hospede.getTelefone();

			model.addRow(novaLinhaDaTabela);
		}
	}

	private void atualizarQuantidadePaginas() {
		int totalRegistros = hospedeController.contarTotalRegistrosComFiltros(hospedeSeletor);

		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		if (totalRegistros % TAMANHO_PAGINA > 0) {
			totalPaginas++;
		}

		lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
	}

	private void limparTabelaHospedes() {
		tblHospedes.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		btnEditar.setEnabled(false);
	}

	public JButton getBtnEditar() {
		return this.btnEditar;
	}

	public Hospede getHospedeSelecionado() {
		return this.hospedeSelecionado;
	}

	public JButton getBtnAdicionarNovoHospede() {
		return btnAdicionarNovoHospede;
	}
}
