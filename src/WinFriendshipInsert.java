

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.jdbc.ha.FailoverConnectionProxy;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinFriendshipInsert extends JDialog {
	private JTextField tfNID;
	private JTextField tfName;
	private JTextField tfMobile;
	private JTextField tfAddress;
	private JTextField tfEmail;
	private JTextField tfBirth;
	private JComboBox cbGradYear;
	private String filePath = "";
	private JLabel lblPic;
	private JCheckBox ckSLType;
	private JButton btnFriendshipInsert;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinFriendshipInsert dialog = new WinFriendshipInsert();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public WinFriendshipInsert() {
		setResizable(false);
		setTitle("\uCE5C\uAD6C \uCD94\uAC00");
		setBounds(100, 100, 503, 300);
		getContentPane().setLayout(null);
		
		lblPic = new JLabel("");
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("그림파일", "jpg", "png", "gif", "bmp");
					chooser.setFileFilter(filter);
					int ret = chooser.showOpenDialog(null);
					if(ret == JFileChooser.APPROVE_OPTION) {
						filePath = chooser.getSelectedFile().getPath();
						ImageIcon icon = new ImageIcon(filePath);
						Image img = icon.getImage();
						img = img.getScaledInstance(90, 110, Image.SCALE_SMOOTH);
						ImageIcon image = new ImageIcon(img);
						
						lblPic.setIcon(image);
						filePath = filePath.replaceAll("\\\\", "\\\\\\\\");
					}
				}
			}
		});
		lblPic.setToolTipText("\uB354\uBE14\uD074\uB9AD\uD558\uC5EC \uC0AC\uC9C4 \uC120\uD0DD");
		lblPic.setBackground(new Color(255, 255, 128));
		lblPic.setBounds(11, 10, 90, 110);
		lblPic.setOpaque(true);
		getContentPane().add(lblPic);
		
		JLabel lblNID = new JLabel("학번 : ");
		lblNID.setBounds(137, 28, 57, 15);
		getContentPane().add(lblNID);
		
		JLabel lblName = new JLabel("이름 : ");
		lblName.setBounds(137, 57, 57, 15);
		getContentPane().add(lblName);
		
		JLabel lblMobile = new JLabel("전화번호 :");
		lblMobile.setBounds(137, 86, 57, 15);
		getContentPane().add(lblMobile);
		
		JLabel lblAddress = new JLabel("주소: ");
		lblAddress.setBounds(34, 133, 57, 15);
		getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("이메일 : ");
		lblEmail.setBounds(34, 164, 57, 15);
		getContentPane().add(lblEmail);
		
		JLabel lblGradYear = new JLabel("졸업년도 : ");
		lblGradYear.setBounds(34, 195, 69, 15);
		getContentPane().add(lblGradYear);
		
		JLabel lblBirth = new JLabel("생년월일 : ");
		lblBirth.setBounds(34, 226, 67, 15);
		getContentPane().add(lblBirth);
		
		tfNID = new JTextField();
		tfNID.setBounds(216, 25, 136, 21);
		getContentPane().add(tfNID);
		tfNID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(216, 54, 136, 21);
		getContentPane().add(tfName);
		
		tfMobile = new JTextField();
		tfMobile.setColumns(10);
		tfMobile.setBounds(216, 83, 136, 21);
		getContentPane().add(tfMobile);
		
		tfAddress = new JTextField();
		tfAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					tfEmail.requestFocus();
				}
			}
		});
		tfAddress.setColumns(10);
		tfAddress.setBounds(103, 130, 249, 21);
		getContentPane().add(tfAddress);
		
		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tfEmail.getText().matches("\\w+@\\w+\\.\\w+(\\.\\w+)?")) {
						tfBirth.requestFocus();
					}else {
						tfEmail.setSelectionStart(0);
						tfEmail.setSelectionEnd(tfEmail.getText().length());
					}
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(103, 161, 249, 21);
		getContentPane().add(tfEmail);
		
		cbGradYear = new JComboBox();
		cbGradYear.setBounds(103, 191, 116, 23);
		getContentPane().add(cbGradYear);
		Calendar today = Calendar.getInstance();
		int lastYear = today.get(Calendar.YEAR);
		
		for(int y=1980; y<=lastYear;y++)
		cbGradYear.addItem(y);
		cbGradYear.setSelectedItem(lastYear);;
		
		ckSLType = new JCheckBox("양력");
		ckSLType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					ckSLType.setText("음력");
				}else {
					ckSLType.setText("양력");
				}
			}
		});
		ckSLType.setBounds(225, 222, 57, 23);
		getContentPane().add(ckSLType);
		
		JButton btnAddressFinder = new JButton("찾기...");
		btnAddressFinder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String doro = JOptionPane.showInputDialog("도로명 입력: ");
				WinDoroList doroList = new WinDoroList(doro);
				doroList.setModal(true);
				doroList.setVisible(true);
				tfAddress.setText(doroList.getAddress());
				tfAddress.requestFocus();
				
			}
		});
		btnAddressFinder.setBounds(375, 128, 97, 23);
		getContentPane().add(btnAddressFinder);
		
		tfBirth = new JTextField();
		tfBirth.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tfBirth.getText().matches("^\\d{4}-(0[1-9]|[1-9]|1[0-2])-([1-9]|1[0-9]|2[0-9]|3[0-1])")) { //생일날짜 정규표현식
						btnFriendshipInsert.requestFocus();
					}else {
						JOptionPane.showMessageDialog(tfBirth, "생일날짜형식이 잘못되었습니다\n예:1985-07-10");
						tfBirth.setSelectionStart(0);
						tfBirth.setSelectionEnd(tfBirth.getText().length());
					}
				}
			}
		});

		tfBirth.setBounds(103, 223, 116, 21);
		getContentPane().add(tfBirth);
		tfBirth.setColumns(10);
		
		JButton btnDup = new JButton("중복체크");
		btnDup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isInteger(tfNID.getText()) || isDuplicate(tfNID.getText())) { //숫자가 아니거나 중복되었다면
					JOptionPane.showMessageDialog(tfNID, "학번을 확인하세요");
					tfNID.setText("");
					tfNID.requestFocus();
					
				}else {
					tfName.requestFocus();
				}
			}
		});
		btnDup.setBounds(375, 24, 97, 23);
		getContentPane().add(btnDup);
		
		JButton btnCalender = new JButton("선택...");
		btnCalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				tfBirth.setText(winCalendar.getDate()); //winCalendar getDate()에서 선택한 날짜가 반환
			}
		});
		btnCalender.setBounds(283, 222, 69, 23);
		getContentPane().add(btnCalender);
		
		btnFriendshipInsert = new JButton("추가");
		btnFriendshipInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNID.getText().equals("")) {
					JOptionPane.showMessageDialog(tfNID, "학번을 확인하세요");
					tfNID.requestFocus();
				}else if(tfName.getText().equals("")){
					JOptionPane.showMessageDialog(tfName, "이름을 확인하세요");
					tfName.requestFocus();
				}else if(tfMobile.getText().equals("")) {
					JOptionPane.showMessageDialog(tfMobile, "전화번호를 확인하세요");
					tfMobile.requestFocus();
				}else if(tfAddress.getText().equals("")) {
					JOptionPane.showMessageDialog(tfAddress, "주소를 확인하세요");
					tfAddress.requestFocus();
				}else if(tfEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(tfEmail, "이메일을 확인하세요");
					tfEmail.requestFocus();
				}else if(tfBirth.getText().equals("")) {
					JOptionPane.showMessageDialog(tfBirth, "생년월일 확인하세요");
					tfBirth.requestFocus();
				}else if(filePath.equals("")){
					JOptionPane.showMessageDialog(lblPic, "사진파일을 확인하세요");
				}else {
					Insert();
				}
				
			}
		});
		btnFriendshipInsert.setBounds(375, 195, 97, 54);
		getContentPane().add(btnFriendshipInsert);

	}

	
		protected void Insert() {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
	            System.out.println("DB 연결 완료");               
	           
	            //=============================================
	            String sql = "INSERT INTO addrbooktbl VALUES(?,?,?,?,?,?,?,?,?)";
	            
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, tfNID.getText());
	            pstmt.setString(2, tfName.getText());
	            pstmt.setString(3, tfMobile.getText());
	            pstmt.setString(4, tfAddress.getText());
	            pstmt.setString(5, tfEmail.getText());
	            pstmt.setString(6, lblPic.getText());
	            pstmt.setString(7, cbGradYear.getSelectedItem().toString());
	            if(ckSLType.isSelected()) {
	            	pstmt.setString(8, tfBirth.getText());
	            	pstmt.setInt(9, 0);      //음력
	            }else {
	            	pstmt.setString(8, tfBirth.getText());
	            	pstmt.setInt(9, 1);    //양력
	            }
	   
	            int result = pstmt.executeUpdate();
	            if(result == 1 ) {
	            	System.out.println("입력성공");
	            	
	            }else {
	            	System.out.println("입력실패");
	            }
	            
	            pstmt.close();
	            conn.close();
	            //==============================================
	        } catch (ClassNotFoundException e1) {
	           System.out.println("JDBC 드라이버 로드 에러");
	           
	        } catch (SQLException e1) {
	        	e1.printStackTrace();
	           System.out.println("DB 연결 오류");
	           
	        }
		
	}

		protected boolean isInteger(String sNumber) {
			//숫자면 true, 숫자가 아니면 false 반환하는 함수
			
			String regExp = "^[0-9]+$";
				if(sNumber.matches(regExp))
					return true;
				else {
					return false;
				}
			}
		
	protected boolean isDuplicate(String SNID) {
		boolean retType = false;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
            System.out.println("DB 연결 완료");               
            Statement statement=conn.createStatement();
            
            //=============================================
            String sql = "select * from addrbooktbl where NID= ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, SNID);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	retType = true;
            	
            }else {
            	retType =  false;
            }
            
            rs.close();
            pstmt.close();
            conn.close();
            //==============================================
        } catch (ClassNotFoundException e1) {
           System.out.println("JDBC 드라이버 로드 에러");
           retType = false;
        } catch (SQLException e1) {
           System.out.println("DB 연결 오류");
           retType = false;
        }finally {
        	return retType;
        }
		
	}
}
