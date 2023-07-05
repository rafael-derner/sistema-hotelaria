package view.paineis;

import controller.ReservaController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import model.seletor.HospedeSeletor;
import model.seletor.ReservaSeletor;
import model.vo.Hospede;
import model.vo.Reserva;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelListagemReserva extends JPanel {
	private ReservaController controller = new ReservaController();
	private ReservaSeletor reservaSeletor = new ReservaSeletor();
	
	private ArrayList<Reserva> listaReservas;
	private Reserva reservaSelecionada;
	
	private String[] nomesColunas = {"Hospede", "Quarto", "Check-in previsto", "Check-Out previsto", "Total da estadia"};
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

	public PainelListagemReserva() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNome = new JLabel("Nome do hospede:");
		add(lblNome, "4, 4, 7, 1");
		
		tfNomeHospede = new JTextField();
		add(tfNomeHospede, "4, 6, 7, 1, fill, default");
		tfNomeHospede.setColumns(10);
		
		JLabel lblQuarto = new JLabel("Numero do quarto");
		add(lblQuarto, "4, 8, 7, 1");
		
		tfQuarto = new JTextField();
		tfQuarto.setColumns(10);
		add(tfQuarto, "4, 10, 3, 1, fill, default");
		
		pickerInicial = new DatePickerSettings();
		pickerFinal = new DatePickerSettings();
		
		JLabel lblInicioPeridodo = new JLabel("Inicio:");
		add(lblInicioPeridodo, "4, 12, 4, 1, left, default");
		
		JLabel lblFimPeridodo = new JLabel("Fim:");
		add(lblFimPeridodo, "8, 12, 3, 1");
		
		dataInicio = new DatePicker(pickerInicial);
		add(dataInicio, "4, 14, 3, 1");
		
		dataFim = new DatePicker(pickerFinal);
		add(dataFim, "8, 14, fill, default");
		
		tabelaResultado = new JTable();
		tabelaResultado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = tabelaResultado.getSelectedRow();
				
				if(linhaSelecionada > 0) {
					btnEditar.setEnabled(true);
					reservaSelecionada = listaReservas.get(linhaSelecionada - 1);
				}
			}
		});
		add(tabelaResultado, "4, 16, 7, 1, fill, fill");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTabelaHospedes();
			}
		});
		add(btnLimpar, "4, 18, 3, 1, left, default");
		
		btnEditar = new JButton("Editar");
		add(btnEditar, "8, 18, center, default");
		btnEditar.setEnabled(false);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarReservasComFiltro();
			}

		});
		add(btnConsultar, "10, 18, right, default");
		
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
		add(btnVoltarPagina, "4, 20");
		
		lblPaginacao = new JLabel();
		lblPaginacao.setText("1 / " + totalPaginas);
		add(lblPaginacao, "6, 20, center, default");
		
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
		add(btnAvancarPagina, "8, 20");
		

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
		if(!tfQuarto.getText().isEmpty()) {
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

		for (Reserva reserva: listaReservas) {
			Object[] novaLinha = new Object[5];
			novaLinha[0] = reserva.getHospede().getNome();
			novaLinha[1] = reserva.getQuarto().getNumeroQuarto();
			novaLinha[2] = reserva.getDtCheckIn();
			novaLinha[3] = reserva.getDtCheckOut();
			novaLinha[4] = calcularDuracaoReserva(reserva.getDtCheckIn(), reserva.getDtCheckOut(), reserva.getQuarto().getValorQuarto());

			model.addRow(novaLinha);
		}
	}
	
	private void atualizarQuantidadePaginas() {
		int totalRegistros = controller.contarTotalRegistrosComFiltros(reservaSeletor);

		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		if(totalRegistros % TAMANHO_PAGINA > 0) { 
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
