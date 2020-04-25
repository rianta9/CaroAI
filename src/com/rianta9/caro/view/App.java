/**
 * 
 */
package com.rianta9.caro.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.rianta9.caro.bean.CaroAI;
import com.rianta9.caro.bean.RoundedBorder;
import com.rianta9.caro.values.Value;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author rianta9
 * Datecreate: 30 thg 3, 2020 17:33:10
 */
public class App extends JFrame implements MouseListener{

	private JPanel contentPane;
	private JPanel TableCells; // Panel chứa ma trận cells
	private Border cellBorder; // tạo đường viền của mỗi cell
	private JLabel[][] cell; // Ma trận cells
	private JLabel userClickedCell; // cell được user click chọn
	private JLabel aiClickedCell; // cell được AI click chọn
	private JLabel lblUserScore; // điểm của User
	private JLabel lblAIScore; // điểm của AI
	
	private CaroAI caro;
	private Notification notification;
	
	public static final int SIZE = Value.TABLE_SIZE; // số hàng/cột của tablecells
	public static final int CELL_WIDTH = Value.CELL_WIDTH; // độ rộng của mỗi cell
	public static final int MARGIN = Value.MARGIN; // khoảng cách giữa các panel
	public static final int TEXT_CELL_SIZE = Value.TEXT_CELL_SIZE; // cỡ chữ trong mỗi cell
	
	private String currentPath; // đường dẫn hiện tại của project
	public Color userTextColor; // màu chữ X
	public Color aiTextColor; // màu chữ 0
	public Color cellBackgroundColor; // màu cell
	public Color backgroundColor; // màu nền
	private int mode; // chế độ chơi
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Tạo game mới, clear màn chơi cũ
	 */
	public void newGame() {
		caro = new CaroAI(SIZE, mode);
		userClickedCell = null;
		aiClickedCell = null;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				cell[i][j].setBackground(cellBackgroundColor);
				cell[i][j].setForeground(userTextColor);
				cell[i][j].setText("");
			}
		}
		if(mode == 1) {
			// cập nhật nước đi của AI
			int x = caro.getNextX();
			int y = caro.getNextY();
			aiClickedCell = cell[x][y];
			aiClickedCell.setForeground(aiTextColor);
			aiClickedCell.setText("O");
			aiClickedCell.setBackground(new Color(0, 139, 139)); // làm nổi bật cell được AI chọn
		}
	}
	
	
	public Notification getNotificationInstance() {
		if(notification == null) notification = new Notification();
		return notification;
	}
	
	/**
	 * Create the frame.
	 */
	public App() {
		/*--------------Set các giá trị mặc định--------------*/
		setResizable(false);
		currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
		setIconImage(Toolkit.getDefaultToolkit().getImage(currentPath+"\\file\\img\\icon.png"));
		setTitle("Cờ Caro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SIZE*CELL_WIDTH+3*MARGIN+280, SIZE*CELL_WIDTH+50);//kích thước của mỗi phần thay đổi tự động khi thay đổi SIZE(số hàng/số cột)
		setLocationRelativeTo(null);
		
		mode = Value.DEFAULT_MODE; // User chơi trước
		userTextColor = Value.USER_TEXT_COLOR; // màu magenta
		aiTextColor = Value.AI_TEXT_COLOR; // màu GREEN
		cellBackgroundColor = Value.CELL_BACKGROUND_COLOR; // màu trắng
		cellBorder = new LineBorder(Color.black, 1); // tạo border cho mỗi cell trong ma trận
		backgroundColor = Value.BACKGROUND_COLOR; // rbg(31, 31, 51)
		caro = new CaroAI(SIZE, mode); // khởi tạo CaroAI
		
		/*------------------Tạo các đối tượng------------------*/
		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TableCells = new JPanel();
		TableCells.setBackground(new Color(255, 255, 255));
		TableCells.setLayout(new GridLayout(SIZE, SIZE, 0, 0));
		TableCells.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TableCells.setBounds(MARGIN, MARGIN, SIZE*CELL_WIDTH, SIZE*CELL_WIDTH);
		contentPane.add(TableCells);
		
		// Tạo ma trận và add vào TableCells
		cell = new JLabel[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				cell[i][j] = new JLabel();
				cell[i][j].setSize(CELL_WIDTH, CELL_WIDTH); // kích cỡ mỗi cell
				cell[i][j].setOpaque(true);
				cell[i][j].setBorder(cellBorder);
				cell[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, TEXT_CELL_SIZE));
				cell[i][j].setBackground(cellBackgroundColor);
				cell[i][j].setForeground(userTextColor);
				cell[i][j].setHorizontalAlignment(SwingConstants.CENTER); // căn giữa chữ
				cell[i][j].addMouseListener(this); // add hàm bắt sự kiện click chuột
				TableCells.add(cell[i][j]); // add cell vào TableCells
			}
		}
		
		JPanel view = new JPanel();
		view.setBackground(new Color(250, 235, 215));
		view.setForeground(Color.BLACK);
		view.setBounds(SIZE*CELL_WIDTH+2*MARGIN, MARGIN, 274, SIZE*CELL_WIDTH);
		contentPane.add(view);
		view.setLayout(null);
		
		JLabel lbltitle = new JLabel("GAME CARO");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lbltitle.setBounds(10, 11, 254, 50);
		view.add(lbltitle);
		
		JLabel lblMode = new JLabel("Mode:");
		lblMode.setHorizontalAlignment(SwingConstants.LEFT);
		lblMode.setForeground(new Color(0, 0, 139));
		lblMode.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblMode.setBounds(10, 162, 254, 20);
		view.add(lblMode);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi mới?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) newGame();
			}
		});
		btnNewGame.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnNewGame.setBounds(30, 390, 89, 37);
		btnNewGame.setBackground(new Color(255, 20, 147));
		btnNewGame.setForeground(new Color(85, 107, 47));
		btnNewGame.setOpaque(false);
		btnNewGame.setBorder(new RoundedBorder(10));
		view.add(btnNewGame);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng trò chơi?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) System.exit(0); // thoát game
			}
		});
		btnExitGame.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnExitGame.setOpaque(false);
		btnExitGame.setForeground(new Color(85, 107, 47));
		btnExitGame.setBorder(new RoundedBorder(10));
		btnExitGame.setBackground(new Color(255, 20, 147));
		btnExitGame.setBounds(156, 390, 89, 37);
		view.add(btnExitGame);
		
		JRadioButton rdbtnUserPlaysFirst = new JRadioButton("User plays first");
		rdbtnUserPlaysFirst.setForeground(new Color(107, 142, 35));
		rdbtnUserPlaysFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 0; // cập nhật mode
				int result = JOptionPane.showConfirmDialog(null, "Xác nhận đổi chế độ chơi?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) newGame(); // clear màn chơi cũ
			}
		});
		rdbtnUserPlaysFirst.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		rdbtnUserPlaysFirst.setSelected(true);
		buttonGroup.add(rdbtnUserPlaysFirst);
		rdbtnUserPlaysFirst.setOpaque(false);
		rdbtnUserPlaysFirst.setBounds(26, 192, 232, 23);
		view.add(rdbtnUserPlaysFirst);
		
		JRadioButton rdbtnAiPlaysFirst = new JRadioButton("AI plays first");
		rdbtnAiPlaysFirst.setForeground(new Color(107, 142, 35));
		rdbtnAiPlaysFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 1; // cập nhật mode
				int result = JOptionPane.showConfirmDialog(null, "Xác nhận đổi chế độ chơi?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) newGame(); // clear màn chơi cũ
			}
		});
		rdbtnAiPlaysFirst.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		buttonGroup.add(rdbtnAiPlaysFirst);
		rdbtnAiPlaysFirst.setOpaque(false);
		rdbtnAiPlaysFirst.setBounds(26, 218, 232, 23);
		view.add(rdbtnAiPlaysFirst);
		
		JLabel lblSetting = new JLabel("Color:");
		lblSetting.setHorizontalAlignment(SwingConstants.LEFT);
		lblSetting.setForeground(new Color(0, 0, 139));
		lblSetting.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblSetting.setBounds(10, 254, 254, 20);
		view.add(lblSetting);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(10, 369, 254, 2);
		view.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.GRAY);
		separator_1.setBounds(10, 149, 254, 2);
		view.add(separator_1);
		
		JLabel lblUser = new JLabel("USER");
		lblUser.setForeground(new Color(220, 20, 60));
		lblUser.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(10, 91, 122, 20);
		view.add(lblUser);
		
		JLabel lblAI = new JLabel("AI");
		lblAI.setForeground(new Color(0, 139, 139));
		lblAI.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblAI.setHorizontalAlignment(SwingConstants.CENTER);
		lblAI.setBounds(142, 91, 122, 20);
		view.add(lblAI);
		
		lblUserScore = new JLabel("0");
		lblUserScore.setForeground(new Color(65, 105, 225));
		lblUserScore.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblUserScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserScore.setBounds(10, 122, 122, 20);
		view.add(lblUserScore);
		
		lblAIScore = new JLabel("0");
		lblAIScore.setForeground(new Color(0, 128, 0));
		lblAIScore.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblAIScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblAIScore.setBounds(142, 122, 122, 20);
		view.add(lblAIScore);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notification = getNotificationInstance();
				notification.show("Thông tin", "Thông Tin", Value.INFO_MESSAGE);
			}
		});
		btnInfo.setOpaque(false);
		btnInfo.setForeground(new Color(85, 107, 47));
		btnInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnInfo.setBorder(new RoundedBorder(10));
		btnInfo.setBackground(new Color(255, 20, 147));
		btnInfo.setBounds(156, 438, 89, 37);
		view.add(btnInfo);
		
		JButton btnIntroduce = new JButton("Introduce");
		btnIntroduce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				notification = getNotificationInstance();
				notification.show("Thông tin", "Giới Thiệu", Value.INTRODUCE_MESSAGE);
			}
		});
		btnIntroduce.setOpaque(false);
		btnIntroduce.setForeground(new Color(85, 107, 47));
		btnIntroduce.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnIntroduce.setBorder(new RoundedBorder(10));
		btnIntroduce.setBackground(new Color(255, 20, 147));
		btnIntroduce.setBounds(30, 438, 89, 37);
		view.add(btnIntroduce);
		
		JButton btnXColor = new JButton("Màu X");
		btnXColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(null, 
	                    "Chọn màu chữ", userTextColor); 
				if(color != null) {
					userTextColor = color;
					newGame();
				}
			}
		});
		btnXColor.setOpaque(false);
		btnXColor.setForeground(new Color(85, 107, 47));
		btnXColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXColor.setBorder(new RoundedBorder(10));
		btnXColor.setBackground(new Color(255, 20, 147));
		btnXColor.setBounds(30, 289, 89, 28);
		view.add(btnXColor);
		
		JButton btnBackgroundColor = new JButton("Màu Nền");
		btnBackgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, 
	                    "Chọn màu nền", backgroundColor); 
				if(color != null) {
					backgroundColor = color;
					contentPane.setBackground(backgroundColor);
				}
			}
		});
		btnBackgroundColor.setOpaque(false);
		btnBackgroundColor.setForeground(new Color(85, 107, 47));
		btnBackgroundColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBackgroundColor.setBorder(new RoundedBorder(10));
		btnBackgroundColor.setBackground(new Color(255, 20, 147));
		btnBackgroundColor.setBounds(30, 328, 89, 28);
		view.add(btnBackgroundColor);
		
		JButton btnOColor = new JButton("Màu O");
		btnOColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, 
	                    "Chọn màu chữ", aiTextColor); 
				if(color != null) {
					aiTextColor = color;
					newGame();
				}
			}
		});
		btnOColor.setOpaque(false);
		btnOColor.setForeground(new Color(85, 107, 47));
		btnOColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOColor.setBorder(new RoundedBorder(10));
		btnOColor.setBackground(new Color(255, 20, 147));
		btnOColor.setBounds(156, 289, 89, 28);
		view.add(btnOColor);
		
		JButton btnCellColor = new JButton("Màu Ô");
		btnCellColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, 
	                    "Chọn màu của mỗi cell", cellBackgroundColor); 
				if(color != null) {
					cellBackgroundColor = color;
					newGame();
				}
			}
		});
		btnCellColor.setOpaque(false);
		btnCellColor.setForeground(new Color(85, 107, 47));
		btnCellColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCellColor.setBorder(new RoundedBorder(10));
		btnCellColor.setBackground(new Color(255, 20, 147));
		btnCellColor.setBounds(156, 328, 89, 28);
		view.add(btnCellColor);
		
		
	}

	

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent element) {
		int x = -1, y = -1; // lưu tọa độ
		// lấy tọa độ user click
		for (int i = 0; i < SIZE; i++) {
			boolean fl = false;
			for (int j = 0; j < SIZE; j++) {
				if(cell[i][j] == element.getSource()) {
					if(userClickedCell != null) {
						userClickedCell.setBackground(cellBackgroundColor); // đặt lại màu clickedCell cũ
					}
					userClickedCell = cell[i][j]; // cập nhật clickedCell
					x = i;
					y = j;
					fl = true;
					break;
				}
			}
			if(fl) break; // dừng kiểm tra
		}
		
		/*kiểm tra số lần click của user*/
		
		if(element.getClickCount() == 1) { // người dùng click dạo(click 1 lần)
			userClickedCell.setBackground(new Color(0, 139, 139)); // làm nổi bật ô được click
		}
		
		else if(element.getClickCount() == 2) { // người dùng chọn đánh ô này
			userClickedCell.setBackground(cellBackgroundColor); // đặt lại màu clickedCell cũ
			if(caro.clickable(x, y)) {//nếu ô này chưa được đánh
				caro.update(x, y, CaroAI.USER_VALUE); // update matrix
				
				// Cập nhật bước đi của User
				userClickedCell.setText("X"); // đánh dấu đã đánh
				//System.out.println("x:" + x + " y:" + y + " double clicked!\n");
				
				// Kiểm tra sau khi đánh người dùng có thắng không
				int checkWinnerResult = caro.checkWinner();
				if(checkWinnerResult == CaroAI.USER_VALUE) {
					JOptionPane.showMessageDialog(null, "Bạn đã thắng!");
					int currentPoint = Integer.valueOf(lblUserScore.getText())+1;
					lblUserScore.setText(String.valueOf(currentPoint));
					newGame();
					return;
				}
				
				// Nếu không thì đến lượt AI đánh
				caro.nextStep();
				
				// Cập nhật bước đi của AI
				x = caro.getNextX();
				y = caro.getNextY();
				if(aiClickedCell != null) {
					aiClickedCell.setBackground(cellBackgroundColor); // đặt lại màu clickedCell cũ
				}
				aiClickedCell = cell[x][y];
				aiClickedCell.setForeground(aiTextColor);
				aiClickedCell.setText("O");
				aiClickedCell.setBackground(new Color(0, 139, 139)); // làm nổi bật cell được AI chọn
				
				// Kiểm tra sau khi đánh AI có thắng không
				checkWinnerResult = caro.checkWinner();
				if(checkWinnerResult == CaroAI.AI_VALUE) {
					JOptionPane.showMessageDialog(null, "AI đã thắng!");
					int currentPoint = Integer.valueOf(lblAIScore.getText())+1;
					lblAIScore.setText(String.valueOf(currentPoint));
					newGame();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
