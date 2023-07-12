package view.paineis;

import controller.ReservaController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import model.exception.CampoInvalidoException;
import model.exception.QuartoComReservaException;
import model.exception.QuartoInativoException;
import model.seletor.HospedeSeletor;
import model.seletor.ReservaSeletor;
import model.vo.Hospede;
import model.vo.Quarto;
import model.vo.Reserva;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Util.Formatador;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class PainelListagemReserva extends JPanel {
	private ReservaController controller = new ReservaController();
	private ReservaSeletor reservaSeletor = new ReservaSeletor();

	private ArrayList<Reserva> listaReservas;
	private Reserva reservaSelecionada;

	private String[] nomesColunas = { "Hospede", "Quarto", "Check-in previsto", "Check-Out previsto",
			"Total da estadia", "Ativo" };
	private JTextField tfNomeHospede;
	private JTextField tfQuarto;
	private DatePickerSettings pickerInicial;
	private DatePickerSettings pickerFinal;
	private DatePicker dataInicio;
	private DatePicker dataFim;
	private JTable tabelaResultado;
	private JButton btnEditar;
	private JButton btnConsultar;
	private JButton btnLimpar;

	private final int TAMANHO_PAGINA = 40;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private JLabel lblPaginacao = new JLabel();
	private JButton btnVoltarPagina;
	private JButton btnAvancarPagina;
	private JButton btnGerarRelatorio;
	private JButton btnInvalidar;
	private JLabel lblTitulo;
	private JButton btnExcluir;

	public PainelListagemReserva() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("37px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("37px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("37px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("37px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("150px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("150px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("150px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("150px:grow"),
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
				RowSpec.decode("fill:default:grow"),
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
		

		lblTitulo = new JLabel("Listagem de Reservas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblTitulo, "4, 4, 15, 1, center, default");

		JLabel lblNome = new JLabel("Nome do Hóspede:");
		add(lblNome, "4, 8, 3, 1");
		
		JLabel lblQuarto = new JLabel("Número do Quarto");
		add(lblQuarto, "8, 8, 3, 1");

		JLabel lblInicioPeridodo = new JLabel("Inicio:");
		add(lblInicioPeridodo, "12, 8");

		JLabel lblFimPeridodo = new JLabel("Fim:");
		add(lblFimPeridodo, "14, 8");

		tfNomeHospede = new JTextField();
		add(tfNomeHospede, "4, 10, 3, 1, fill, default");
		tfNomeHospede.setColumns(10);

		pickerInicial = new DatePickerSettings();
		pickerFinal = new DatePickerSettings();
		
		tfQuarto = new JTextField();
		tfQuarto.setColumns(10);
		add(tfQuarto, "8, 10, 3, 1, fill, default");

		dataInicio = new DatePicker(pickerInicial);
		add(dataInicio, "12, 10");

		dataFim = new DatePicker(pickerFinal);
		add(dataFim, "14, 10, fill, default");

		tabelaResultado = new JTable();
		tabelaResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = tabelaResultado.getSelectedRow();

				if (linhaSelecionada > 0) {
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					reservaSelecionada = listaReservas.get(linhaSelecionada - 1);
				}
			}
		});

				
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarReservasComFiltro();
			}

		});

		add(btnConsultar, "16, 10, fill, default");

		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTabelaHospedes();
			}
		});
		add(btnLimpar, "18, 10, fill, default");
		add(tabelaResultado, "4, 12, 15, 1, fill, fill");
				
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
						resultado = controller.gerarPlanilha(listaReservas, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (CampoInvalidoException campoInvalidoException) {
						JOptionPane.showConfirmDialog(null, campoInvalidoException.getMessage(), "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
						
		btnAvancarPagina = new JButton(">>");
		btnAvancarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				consultarReservasComFiltro();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});

										
		btnVoltarPagina = new JButton("<<");
		btnVoltarPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				consultarReservasComFiltro();
				lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
				btnVoltarPagina.setEnabled(paginaAtual > 1);
				btnAvancarPagina.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnVoltarPagina, "4, 14");
								
		lblPaginacao = new JLabel();
		lblPaginacao.setText("1 / " + totalPaginas);
		add(lblPaginacao, "6, 14, center, default");
		add(btnAvancarPagina, "8, 14");
		add(btnGerarRelatorio, "14, 14, fill, default");

		
		btnEditar = new JButton("Editar");
		btnEditar.setBackground(new Color(50, 204, 233));
		add(btnEditar, "16, 14, fill, default");
		btnEditar.setEnabled(false);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
						"Voce deseja realmente Inativar o Reserva selecionado?");
				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						controller.inativar(reservaSelecionada.getIdReserva());
						JOptionPane.showMessageDialog(null, "Reserva inativada com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						consultarReservasComFiltro();
					} catch (Exception ew) {
						System.out.println("Deu ruim");;
					}
			}
		}});
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.RED);
		add(btnExcluir, "18, 14");

		atualizarQuantidadePaginas();
		consultarReservasComFiltro();
	}

	public Reserva getReservaSelecionada() {
		return reservaSelecionada;
	}

	public void setReservaSelecionada(Reserva reservaSelecionada) {
		this.reservaSelecionada = reservaSelecionada;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	protected void consultarReservasComFiltro() {
		reservaSeletor = new ReservaSeletor();

		reservaSeletor.setNomeHospede(tfNomeHospede.getText());
		if (!tfQuarto.getText().isEmpty()) {
			reservaSeletor.setNumQuarto(Integer.parseInt(tfQuarto.getText()));
		}
		reservaSeletor.setDataEntrada(dataInicio.getDate());
		reservaSeletor.setDataSaida(dataFim.getDate());

		listaReservas = (ArrayList<Reserva>) controller.consultarComFiltro(reservaSeletor);

		atualizarTabela();
		atualizarQuantidadePaginas();
	}

	private void atualizarTabela() {
		this.limparTabelaHospedes();
		DefaultTableModel model = (DefaultTableModel) tabelaResultado.getModel();

		for (Reserva reserva : listaReservas) {
			Object[] novaLinha = new Object[6];
			novaLinha[0] = reserva.getHospede().getNome();
			novaLinha[1] = reserva.getQuarto().getNumeroQuarto();
			novaLinha[2] = reserva.getDtCheckIn();
			novaLinha[3] = reserva.getDtCheckOut();
			novaLinha[4] = Formatador.formatarValorQuartoParaView(reserva.calcularValorReserva(reserva));
			novaLinha[5] = reserva.getInvalido() ? "Não" : "Sim";

			model.addRow(novaLinha);
		}
	}

	private void atualizarQuantidadePaginas() {
		int totalRegistros = controller.contarTotalRegistrosComFiltros(reservaSeletor);

		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		if (totalRegistros % TAMANHO_PAGINA > 0) {
			totalPaginas++;
		}

		lblPaginacao.setText(paginaAtual + " / " + totalPaginas);
	}

	private void limparTabelaHospedes() {
		tabelaResultado.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	public double calcularDuracaoReserva(LocalDate dataInicial, LocalDate dataFinal, double valor) {
		long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
		int diasFormatados = Math.toIntExact(dias);
		return diasFormatados * valor;
	}


}
