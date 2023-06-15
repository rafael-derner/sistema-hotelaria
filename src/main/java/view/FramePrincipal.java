package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.paineis.PainelCadastroQuarto;
import view.paineis.PainelCadastroReserva;
import view.paineis.PainelCadastroUsuario;
import view.paineis.PainelListagemQuarto;
import view.paineis.PainelListagemUsuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FramePrincipal extends JFrame {

	private PainelCadastroUsuario painelCadastroUsuario;
	private PainelListagemUsuario painelListagemUsuario;
	private PainelCadastroReserva cadastroReserva;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setTitle("Sistema de Hotelaria");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnQuarto = new JMenu("Quarto");
		menuBar.add(mnQuarto);
		
		JMenuItem mnItemCadastrarQuarto = new JMenuItem("Cadastrar");
		mnItemCadastrarQuarto.addActionListener(new ActionListener() {
			private PainelCadastroQuarto painelCadastroQuarto;

			public void actionPerformed(ActionEvent e) {
				painelCadastroQuarto = new PainelCadastroQuarto();
				painelCadastroQuarto.setVisible(true);
				setContentPane(painelCadastroQuarto);
				revalidate();
			}
		});
		mnQuarto.add(mnItemCadastrarQuarto);
		
		JMenuItem mnItemListarQuartos = new JMenuItem("Listar");
		mnItemListarQuartos.addActionListener(new ActionListener() {
			private PainelListagemQuarto painelListagemQuarto;

			public void actionPerformed(ActionEvent e) {
				painelListagemQuarto = new PainelListagemQuarto();
				painelListagemQuarto.setVisible(true);
				setContentPane(painelListagemQuarto);
				revalidate();
			}
		});
		mnQuarto.add(mnItemListarQuartos);
		
		JMenu mnHospede = new JMenu("Hóspede");
		menuBar.add(mnHospede);
		
		JMenuItem mnItemCadastrarHospede = new JMenuItem("Cadastrar");
		mnHospede.add(mnItemCadastrarHospede);
		
		JMenuItem mnItemListarHospedes = new JMenuItem("Listar");
		mnHospede.add(mnItemListarHospedes);
		
		JMenu mnReserva = new JMenu("Reserva");
		menuBar.add(mnReserva);
		
		JMenuItem mnItemCadastrarReserva = new JMenuItem("Cadastrar");
		mnItemCadastrarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastroReserva = new PainelCadastroReserva();
				cadastroReserva.setVisible(true);
				setContentPane(cadastroReserva);
				revalidate();
			}
		});
		mnReserva.add(mnItemCadastrarReserva);
		
		JMenuItem mnItemListarReservas = new JMenuItem("Listar");
		mnReserva.add(mnItemListarReservas);
		
		JMenu mnUsuario = new JMenu("Usuário");
		menuBar.add(mnUsuario);
		
		JMenuItem mnItemCadastrarUsuario = new JMenuItem("Cadastrar");		
		mnItemCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario();
				painelCadastroUsuario.setVisible(true);
				// registra action do botao cancelar de dentro do painel
				registrarCliqueBtnCancelarPainelCadastroUsuario();
				
				//Atualiza a tela principal
				setContentPane(painelCadastroUsuario);
				revalidate();
			}
		});
		mnUsuario.add(mnItemCadastrarUsuario);
		
		JMenuItem mnItemListarUsuarios = new JMenuItem("Listar");
		mnItemListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemUsuario = new PainelListagemUsuario();
				painelListagemUsuario.setVisible(true);
				
				//Atualiza a tela principal
				setContentPane(painelListagemUsuario);
				revalidate();
			}
		});
		mnUsuario.add(mnItemListarUsuarios);

	}
	
	/*
	 * Clique no bot�o de cancelar do PainelCadastroUsuario
	 */
	protected void registrarCliqueBtnCancelarPainelCadastroUsuario() {
		if(painelCadastroUsuario == null) {
			painelCadastroUsuario = new PainelCadastroUsuario();
		}
		
		painelCadastroUsuario.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelListagemUsuario = new PainelListagemUsuario();
				painelListagemUsuario.setVisible(true);
				registrarCliqueBotaoEditarDoPainelListagemUsuario();
				setContentPane(painelListagemUsuario);
				revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoEditarDoPainelListagemUsuario() {
		// TODO Auto-generated method stub
		
	}
}
