package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TelaPrincipal extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
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
		mnQuarto.add(mnItemCadastrarQuarto);
		
		JMenuItem mnItemListarQuartos = new JMenuItem("Listar");
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
		mnReserva.add(mnItemCadastrarReserva);
		
		JMenuItem mnItemListarReservas = new JMenuItem("Listar");
		mnReserva.add(mnItemListarReservas);
		
		JMenu mnUsuario = new JMenu("Usuário");
		menuBar.add(mnUsuario);
		
		JMenuItem mnItemCadastrarUsuario = new JMenuItem("Cadastrar");
		mnUsuario.add(mnItemCadastrarUsuario);
		
		JMenuItem mnItemListarUsuarios = new JMenuItem("Listar");
		mnUsuario.add(mnItemListarUsuarios);

	}
}