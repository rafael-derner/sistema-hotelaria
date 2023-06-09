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
import model.exception.CampoInvalidoException;
import model.exception.QuartoComReservaException;
import model.exception.QuartoInativoException;
import model.seletor.QuartoSeletor;
import model.vo.Quarto;
import model.vo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JCheckBox;

public class PainelListagemQuarto extends JPanel {

	private JPanel contentPane;
	private JTextField txtNumero;
	private JTable tableListagemQuartos;
	private JButton btnInativar;
	private JButton btnEditar;
	private JButton btnGerarPlanilha;
	private JButton btnLimpar;
	private JButton btnBuscar;
	private JComboBox cBTipoQuarto;
	private String[] tipoDeQuarto = { "", "Básico", "Intermediário", "Luxo" };
	private String[] valoresQuarto = { "", "Ativo", "Inativo" };
	private String[] filtroValorDiaria = { "", "Crescente", "Decrescente" };
	private JLabel lblTipoDeQuarto;
	private JLabel lblNumero;
	private String[] colunas = { "Número do Quarto", "Categoria", "Valor do Quarto", "Ativo" };
	private JLabel lblListagemQuartos;
	private ArrayList<Quarto> quartos;
	private Quarto quartoSelecionado;
	private QuartoSeletor quartoSeletor;

	private QuartoController quartoController = new QuartoController();

	private final int TAMANHO_PAGINA = 38;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private JButton btnVoltarPagina;
	private JButton btnAvancarPagina;
	private JLabel lblPaginacao = new JLabel();
	private JLabel lblValorFiltro;
	private JComboBox cBFiltroValor;
	private JButton btnAdicionarNovoQuarto;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public PainelListagemQuarto() {
		setLayout(new com.jgoodies.forms.layout.FormLayout(
				new com.jgoodies.forms.layout.ColumnSpec[] { com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("max(100dlu;default)"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("37px:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("37px:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("37px:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("37px:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("150px:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("150px:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("150px:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("150px:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_COLSPEC,
						com.jgoodies.forms.layout.ColumnSpec.decode("max(100dlu;default)"), },
				new com.jgoodies.forms.layout.RowSpec[] { com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.RowSpec.decode("default:grow"),
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.RELATED_GAP_ROWSPEC,
						com.jgoodies.forms.layout.FormSpecs.DEFAULT_ROWSPEC, }));
		
		lblListagemQuartos = new JLabel("Listagem de Quartos");
		lblListagemQuartos.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblListagemQuartos, "4, 4, 15, 1, center, top");

		lblNumero = new JLabel("Número do Quarto:");
		add(lblNumero, "4, 8, 3, 1, fill, top");

		lblTipoDeQuarto = new JLabel("Categoria:");
		add(lblTipoDeQuarto, "12, 8, fill, top");

		lblValorFiltro = new JLabel("Valor da Diária");
		add(lblValorFiltro, "14, 8");
		txtNumero = new JTextField();
		add(txtNumero, "4, 10, 7, 1, fill, center");
		txtNumero.setColumns(10);

		btnBuscar = new JButton("Consultar");
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				buscarQuartos();

			}
		});

		cBTipoQuarto = new JComboBox(tipoDeQuarto);
		add(cBTipoQuarto, "12, 10, fill, center");

		cBFiltroValor = new JComboBox(filtroValorDiaria);
		add(cBFiltroValor, "14, 10, fill, default");
		add(btnBuscar, "16, 10, fill, top");

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText(null);
				cBTipoQuarto.setSelectedIndex(0);
			}
		});
		add(btnLimpar, "18, 10, fill, top");
		
		tableListagemQuartos = new JTable();
		add(tableListagemQuartos, "4, 12, 15, 5, fill, fill");
		tableListagemQuartos.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int elementoSelecionado = tableListagemQuartos.getSelectedRow();
				if (elementoSelecionado > 0) {
					btnEditar.setEnabled(true);
					btnInativar.setEnabled(true);
					quartoSelecionado = quartos.get(elementoSelecionado - 1);
				}

			}
		});

		btnVoltarPagina = new JButton("<<");
		btnVoltarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarQuartos();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		btnVoltarPagina.setEnabled(false);
		add(btnVoltarPagina, "4, 18, fill, top");

		lblPaginacao = new JLabel("1 / " + totalPaginas);
		add(lblPaginacao, "6, 18, center, center");

		btnAvancarPagina = new JButton(">>");
		btnAvancarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				buscarQuartos();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		// btnAvancarPagina.setEnabled(false);
		add(btnAvancarPagina, "8, 18, fill, top");
		
				btnGerarPlanilha = new JButton("Gerar Relatório");
				btnGerarPlanilha.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
						janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para o relatório...");
						int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
						if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
							String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();
							String resultado;
							try {
								resultado = quartoController.gerarPlanilha(quartos, caminhoEscolhido);
								JOptionPane.showMessageDialog(null, resultado);
							} catch (CampoInvalidoException e1) {
								JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);

							}
						}
					}
				});
				
						add(btnGerarPlanilha, "12, 18, fill, top");
		
		btnAdicionarNovoQuarto = new JButton("Adicionar novo Quarto");
		btnAdicionarNovoQuarto.setEnabled(false);
		btnAdicionarNovoQuarto.setBackground(new Color(128, 255, 128));
		add(btnAdicionarNovoQuarto, "14, 18");

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBackground(new Color(50, 204, 233));
		add(btnEditar, "16, 18, fill, top");
		btnInativar = new JButton("Inativar");
		btnInativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
						"Voce deseja realmente Inativar o Quarto selecionado?");
				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						quartoController.inativar(quartoSelecionado.getIdQuarto());
						JOptionPane.showMessageDialog(null, "Quarto inativado com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						quartos = (ArrayList<Quarto>) quartoController.consultarTodos();
					} catch (QuartoComReservaException quartoComReservaException) {
						JOptionPane.showConfirmDialog(null, quartoComReservaException.getMessage(), "Atenção",
								JOptionPane.WARNING_MESSAGE);
					} catch (QuartoInativoException quartoInativoException) {
						JOptionPane.showConfirmDialog(null, quartoInativoException.getMessage(), "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				buscarQuartos();
				atualizarTabelaQuartos();
			}

		});
		btnInativar.setBackground(Color.RED);
		add(btnInativar, "18, 18, fill, top");
		buscarQuartos();
		atualizarQuantidadePaginas();
	}

	private void atualizarQuantidadePaginas() {
		int totalRegistros = quartoController.contarTotalRegistrosComFiltros(quartoSeletor);

		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		if (totalRegistros % TAMANHO_PAGINA > 0) {
			totalPaginas++;
		}
		lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
	}

	private void buscarQuartos() {

		quartoSeletor = new QuartoSeletor();
		quartoSeletor.setLimite(TAMANHO_PAGINA);
		quartoSeletor.setPagina(paginaAtual);

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
		quartoSeletor.setFiltroValor((String) cBFiltroValor.getSelectedItem());
		quartos = (ArrayList<Quarto>) quartoController.consultarComFiltro(quartoSeletor);
		atualizarTabelaQuartos();
		atualizarQuantidadePaginas();

	}

	private void atualizarTabelaQuartos() {
		this.limparTabelaQuartos();
		DefaultTableModel model = (DefaultTableModel) tableListagemQuartos.getModel();

		for (Quarto quarto : quartos) {
			Object[] novaLinhaTabela = new Object[4];
			novaLinhaTabela[0] = quarto.getNumeroQuarto();
			novaLinhaTabela[1] = quarto.getTipoQuarto();
			novaLinhaTabela[2] = Formatador.formatarValorQuartoParaView(quarto.getValorQuarto());
			novaLinhaTabela[3] = quarto.isAtivo() ? "Sim" : "Não";
			model.addRow(novaLinhaTabela);
		}
	}

	private void limparTabelaQuartos() {
		tableListagemQuartos.setModel(new DefaultTableModel(new Object[][] { colunas, }, colunas));
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
