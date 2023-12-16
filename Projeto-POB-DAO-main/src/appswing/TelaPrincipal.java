package appswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

import regras_negocio.Fachada;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frmEstacionamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmEstacionamento.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEstacionamento = new JFrame();
		frmEstacionamento.getContentPane().setBackground(Color.WHITE);

		frmEstacionamento.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});
		frmEstacionamento.setTitle("Estacionamento");
		frmEstacionamento.setBounds(100, 100, 450, 300);
		frmEstacionamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEstacionamento.setResizable(false);
		frmEstacionamento.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 22);
		frmEstacionamento.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Opções");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Tipo de veículo");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaTipos tela = new TelaTipos();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Veículo");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaVeiculos tela = new TelaVeiculos();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Registro");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRegistros tela = new TelaRegistros();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Sair");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmEstacionamento.dispose();
			}
		});
		
		JMenu mnNewMenu_2 = new JMenu("Consultas");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaConsulta tela = new TelaConsulta();
			}
		});
		menuBar.add(mnNewMenu_2);
		menuBar.add(mnNewMenu_1);
		
		JLabel lblNewLabel = new JLabel("ESTACIONAMENTO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(78, 61, 274, 31);
		frmEstacionamento.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(78, 90, 274, 149);
		frmEstacionamento.getContentPane().add(lblNewLabel_1);
		
		ImageIcon icon = new ImageIcon(TelaPrincipal.class.getResource("/arquivos/imagem.png"));
		icon.setImage(icon.getImage().getScaledInstance(
			lblNewLabel_1.getWidth(),
			lblNewLabel_1.getHeight(),
			Image.SCALE_DEFAULT
		));
		lblNewLabel_1.setIcon(icon);

	}
}