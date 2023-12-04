package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Registro;
import regras_negocio.Fachada;
import javax.swing.JRadioButton;

public class TelaRegistros {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_2;
	private JButton button;
	private JButton btnCriarNovoRegistro;
	private JButton button_2;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_6;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JLabel label_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRegistros tela = new TelaRegistros();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaRegistros() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setModal(true);
		frame.setResizable(false);
		frame.setTitle("Registros");
		frame.setBounds(100, 100, 729, 419);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_6.setText("selecionado="+ (int) table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(new Color(232, 244, 244));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(12, 355, 688, 14);
		frame.getContentPane().add(label);

		label_6 = new JLabel("resultados:");
		label_6.setBounds(31, 202, 431, 14);
		frame.getContentPane().add(label_6);

		label_1 = new JLabel("Data:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(41, 252, 29, 14);
		frame.getContentPane().add(label_1);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(77, 249, 195, 20);
		frame.getContentPane().add(textField);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(431, 321, 171, 23);
		frame.getContentPane().add(button);

		button_2 = new JButton("Apagar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0) {	
						int id = (int) table.getValueAt( table.getSelectedRow(), 0);

						Fachada.excluirRegistro(id);
						label.setText("registro apagado" );
						listagem();

					}
					else
						label.setText("nao selecionado");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(431, 287, 171, 23);
		frame.getContentPane().add(button_2);


		textField_2 = new JTextField();
		textField_2.setBounds(77, 277, 195, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(47, 308, 1, 16);
		frame.getContentPane().add(textPane);

		label_3 = new JLabel("Placa:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(41, 281, 89, 16);
		frame.getContentPane().add(label_3);
		
		radioButton = new JRadioButton("Entrada");
		radioButton.setBackground(Color.WHITE);
		radioButton.setBounds(446, 260, 80, 23);
		frame.getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("Saída");
		radioButton_1.setBackground(Color.WHITE);
		radioButton_1.setBounds(528, 260, 109, 23);
		frame.getContentPane().add(radioButton_1);
		
		ButtonGroup group = new ButtonGroup();
        group.add(radioButton);
        group.add(radioButton_1);
		
		label_2 = new JLabel("Operação:");
		label_2.setBounds(493, 239, 64, 14);
		frame.getContentPane().add(label_2);

		btnCriarNovoRegistro = new JButton("Criar novo registro");
		btnCriarNovoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() || textField_2.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					
					// Pegando o valor dos botões
					String opcao = null;
	                if (radioButton.isSelected()) {
	                    opcao = "entrada";
	                } else if (radioButton_1.isSelected()) {
	                    opcao = "saida";
	                    }
	                    
					String data = textField.getText();
					String placa = textField_2.getText();

					Fachada.criarRegistro(data, placa, opcao);
					label.setText("Registro criado");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCriarNovoRegistro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCriarNovoRegistro.setBounds(92, 321, 153, 23);
		frame.getContentPane().add(btnCriarNovoRegistro);
	}

	public void listagem() {
		try{
			//ler os carros do banco
			List<Registro> lista = Fachada.listarRegistros();

			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("id");
			model.addColumn("placa");
			model.addColumn("data");
			model.addColumn("operação");

			//adicionar linhas no model
			for(Registro reg : lista) {
				model.addRow(new Object[]{reg.getId(), reg.getVeiculo().getPlaca(), reg.getDatahora(), reg.getOperacao()});
			}


			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_6.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
