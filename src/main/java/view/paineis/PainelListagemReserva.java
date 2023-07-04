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
	private ArrayList<Reserva> listaReservas;
	private ReservaSeletor reservaSeletor;
	private String[] nomesColunas = {"Hospede", "Quarto", "Check-in previsto", "Check-Out previsto", "Total da estadia"};
	private JTextField tfNomeHospede;
	private JTextField tfQuarto;
	private DatePickerSettings pickerInicial;
	private DatePickerSettings pickerFinal;
	private DatePicker dataInicio;
	private DatePicker dataFim;
	private JButton btnConsultar;
	private JButton btnLimpar;
	private JTable tabelaResultado;
	private JButton btnEditar;
	private Reserva reservaSelecionada;

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
		add(lblNome, "4, 4, 5, 1");
		
		tfNomeHospede = new JTextField();
		add(tfNomeHospede, "4, 6, 5, 1, fill, default");
		tfNomeHospede.setColumns(10);
		
		JLabel lblQuarto = new JLabel("Numero do quarto");
		add(lblQuarto, "4, 8, 5, 1");
		
		tfQuarto = new JTextField();
		tfQuarto.setColumns(10);
		add(tfQuarto, "4, 10, fill, default");
		
		pickerInicial = new DatePickerSettings();
		pickerFinal = new DatePickerSettings();
		
		JLabel lblInicioPeridodo = new JLabel("Inicio:");
		add(lblInicioPeridodo, "4, 12, 2, 1, left, default");
		
		JLabel lblFimPeridodo = new JLabel("Fim:");
		add(lblFimPeridodo, "6, 12, 3, 1");
		
		dataInicio = new DatePicker(pickerInicial);
		add(dataInicio, "4, 14");
		
		dataFim = new DatePicker(pickerFinal);
		add(dataFim, "6, 14, fill, default");
		
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
		add(tabelaResultado, "4, 16, 5, 1, fill, fill");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTabelaHospedes();
			}
		});
		add(btnLimpar, "4, 18, left, default");
		
		btnEditar = new JButton("Editar");
		add(btnEditar, "6, 18, center, default");
		btnEditar.setEnabled(false);
		
		consultarReservasComFiltro();
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarReservasComFiltro();
			}

		});
		add(btnConsultar, "8, 18, right, default");

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
	
	private void limparTabelaHospedes() {
		tabelaResultado.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
	
	public double calcularDuracaoReserva(LocalDate dataInicial, LocalDate dataFinal, double valor) {
        long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
        int diasFormatados = Math.toIntExact(dias);
        return diasFormatados * valor;
    }

}
