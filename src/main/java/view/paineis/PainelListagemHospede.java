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
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
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
import model.seletor.HospedeSeletor;
import model.vo.Hospede;

public class PainelListagemHospede extends JPanel{

	private HospedeController hospedeController = new HospedeController();
	private HospedeSeletor hospedeSeletor;
	private ArrayList<Hospede> hospedes;
	private String[] nomesColunas = { "Nome", "CPF", "Telefone" };
	private JTable tblHospedes;
	private JTextField tfNome;
	private JLabel lblCpf;
	private JFormattedTextField tfCpf;
	private MaskFormatter mascaraCpf;
	private JButton btnConsultar;
	private JButton btnLimpar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private Hospede hospedeSelecionado;
	
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
				
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBackground(new Color(50, 204, 233));
		add(btnEditar, "10, 18");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(new Color(255, 0, 0));		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// COMPLETAR ACTION LISTENER
			}
		});
		add(btnExcluir, "12, 18");
		
		JLabel lblListagemHospedes = new JLabel("Listagem de Hóspedes");
		lblListagemHospedes.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblListagemHospedes, "4, 4, 9, 1, center, default");
		
		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "4, 8");
		
		lblCpf = new JLabel("CPF:");
		add(lblCpf, "6, 8");
		
		tfNome = new JTextField();
		add(tfNome, "4, 10, fill, default");
		tfNome.setColumns(10);
		
		tfCpf = new JFormattedTextField(mascaraCpf);
		add(tfCpf, "6, 10, fill, default");
		tfCpf.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			private HospedeSeletor HospedeSeletor;

			public void actionPerformed(ActionEvent e) {
				buscarHospedeComFiltro();
			}
		});
		add(btnConsultar, "10, 10");
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNome.setText(null);
				tfCpf.setText(null);
			}
		});
		add(btnLimpar, "12, 10");
		
		tblHospedes = new JTable();
		add(tblHospedes, "4, 12, 9, 5, fill, fill");
		this.limparTabelaHospedes();
		buscarHospedeComFiltro();
		
		tblHospedes.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int elementoSelecionado = tblHospedes.getSelectedRow();
				
				if(elementoSelecionado > 0) {
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					hospedeSelecionado = hospedes.get(elementoSelecionado - 1);
				}
			}
		});
		
		
	} 
	
	protected void buscarHospedeComFiltro() {
		hospedeSeletor = new HospedeSeletor();
		
		hospedeSeletor.setNome(tfNome.getText());

		if(!tfCpf.getText().contains("   .   .   -  ")) {
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
	
	private void limparTabelaHospedes() {
		tblHospedes.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
	
	public JButton getBtnEditar() {
		return this.btnEditar;
	}
	
	public Hospede getHospedeSelecionado() {
		return this.hospedeSelecionado;
	}
}
