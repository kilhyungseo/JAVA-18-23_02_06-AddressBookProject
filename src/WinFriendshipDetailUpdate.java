

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WinFriendshipDetailUpdate extends JDialog {
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
	private JButton btnFriendshipUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinFriendshipDetailUpdate dialog = new WinFriendshipDetailUpdate();
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
	public WinFriendshipDetailUpdate() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		setResizable(false);
		setTitle("\uCE5C\uAD6C\uC815\uBCF4\uBCC0\uACBD");
		setBounds(100, 100, 503, 300);
		getContentPane().setLayout(null);
		
		lblPic = new JLabel("");
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("????????????", "jpg", "png", "gif", "bmp");
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
		
		JLabel lblNID = new JLabel("?????? : ");
		lblNID.setBounds(137, 28, 57, 15);
		getContentPane().add(lblNID);
		
		JLabel lblName = new JLabel("?????? : ");
		lblName.setBounds(137, 57, 57, 15);
		getContentPane().add(lblName);
		
		JLabel lblMobile = new JLabel("???????????? :");
		lblMobile.setBounds(137, 86, 57, 15);
		getContentPane().add(lblMobile);
		
		JLabel lblAddress = new JLabel("??????: ");
		lblAddress.setBounds(34, 133, 57, 15);
		getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("????????? : ");
		lblEmail.setBounds(34, 164, 57, 15);
		getContentPane().add(lblEmail);
		
		JLabel lblGradYear = new JLabel("???????????? : ");
		lblGradYear.setBounds(34, 195, 69, 15);
		getContentPane().add(lblGradYear);
		
		JLabel lblBirth = new JLabel("???????????? : ");
		lblBirth.setBounds(34, 226, 67, 15);
		getContentPane().add(lblBirth);
		
		tfNID = new JTextField();
		tfNID.setEnabled(false);
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
		
		ckSLType = new JCheckBox("??????");
		ckSLType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					ckSLType.setText("??????");
				}else {
					ckSLType.setText("??????");
				}
			}
		});
		ckSLType.setBounds(225, 222, 57, 23);
		getContentPane().add(ckSLType);
		
		JButton btnAddressFinder = new JButton("??????...");
		btnAddressFinder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String doro = JOptionPane.showInputDialog("????????? ??????: ");
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
					if(tfBirth.getText().matches("^\\d{4}-(0[1-9]|[1-9]|1[0-2])-([1-9]|1[0-9]|2[0-9]|3[0-1])")) { //???????????? ???????????????
						btnFriendshipUpdate.requestFocus();
					}else {
						JOptionPane.showMessageDialog(tfBirth, "????????????????????? ?????????????????????\n???:1985-07-10");
						tfBirth.setSelectionStart(0);
						tfBirth.setSelectionEnd(tfBirth.getText().length());
					}
				}
			}
		});

		tfBirth.setBounds(103, 223, 116, 21);
		getContentPane().add(tfBirth);
		tfBirth.setColumns(10);
		
		JButton btnCalender = new JButton("??????...");
		btnCalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				tfBirth.setText(winCalendar.getDate()); //winCalendar getDate()?????? ????????? ????????? ??????
			}
		});
		btnCalender.setBounds(283, 222, 69, 23);
		getContentPane().add(btnCalender);
		
		btnFriendshipUpdate = new JButton("??????");
		btnFriendshipUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfName.getText().equals("")){
					JOptionPane.showMessageDialog(tfName, "????????? ???????????????");
					tfName.requestFocus();
				}else if(!tfMobile.getText().equals("^\\d{2,3}-\\d{3,4}-\\d{4}")) {
					JOptionPane.showMessageDialog(tfMobile, "??????????????? ???????????????");
					tfMobile.requestFocus();
				}else if(tfAddress.getText().equals("")) {
					JOptionPane.showMessageDialog(tfAddress, "????????? ???????????????");
					tfAddress.requestFocus();
				}else if(!tfEmail.getText().matches("\\w+@\\w+\\.\\w+(\\.\\w+)?")) {
					JOptionPane.showMessageDialog(tfEmail, "???????????? ???????????????");
					tfEmail.requestFocus();
				}else if(!tfBirth.getText().matches("^\\d{4}-(0[1-9]|[1-9]|1[0-2])-([1-9]|1[0-9]|2[0-9]|3[0-1])")) {
					JOptionPane.showMessageDialog(tfBirth, "???????????? ???????????????");
					tfBirth.requestFocus();
				}else if(filePath.equals("")){
					JOptionPane.showMessageDialog(lblPic, "??????????????? ???????????????");
				}else {
					updateRecord();
					dispose();
				}
				
			}
		});
		btnFriendshipUpdate.setBounds(375, 195, 97, 54);
		getContentPane().add(btnFriendshipUpdate);

	}

	
		protected void updateRecord() {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
	                           
	            
	            //=============================================
	            String sql = "UPDATE addrbooktbl SET name=?, mobile=?, Address=?, email=?, pic=?, GradYear=?, Birth=?, SLType=? WHERE Nid=?";
	           
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            
	            
	            pstmt.setString(1, tfName.getText());
	            pstmt.setString(2, tfMobile.getText());
	            pstmt.setString(3, tfAddress.getText());
	            pstmt.setString(4, tfEmail.getText());
	            pstmt.setString(5, lblPic.getText());
	            pstmt.setString(6, cbGradYear.getSelectedItem().toString());
	            pstmt.setString(7, tfBirth.getText());
	            if(ckSLType.isSelected()) {
	            	pstmt.setInt(8, 0);
	            }else {
	            	pstmt.setInt(8, 1);
	            }
	            pstmt.setString(9, tfNID.getText());
	            
	      
	            int result = pstmt.executeUpdate();
	            
	            if(result == 1) {
	            	System.out.println("?????? ??????");
	            }else {
	            	System.out.println("?????? ??????");
	            }
	            
	            pstmt.close();
	            conn.close();
	            // ?????? ????????? ????????? ???????????? ????????????	  	      	
				}catch (ClassNotFoundException e1) {
		            System.out.println("JDBC ???????????? ?????? ??????");
		         } catch (SQLException e1) {
		            System.out.println("DB ?????? ??????");
		            e1.printStackTrace();
		         }
		
	}

		public WinFriendshipDetailUpdate(String sNid) {
		this();
		showRecord(sNid);
	
	}

		protected void showRecord(String sNid) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
				//=============================================		
				String sql = "";
				if(sNid.equals("")) {
					sql = "select * from addrBookTBL";
				}else {
					sql = "select * from addrBookTBL where nid=" + sNid;
				}
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					tfNID.setText(sNid);
					tfName.setText(rs.getString("Name"));
					tfMobile.setText(rs.getString("mobile"));
					tfAddress.setText(rs.getString("address"));
					tfEmail.setText(rs.getString("email"));
					cbGradYear.setSelectedItem(rs.getInt("gradYear"));
					tfBirth.setText(rs.getString("birth"));
					if(rs.getInt("SLType") == 1) {
						ckSLType.setSelected(false);
						ckSLType.setText("??????");
					}else {
						ckSLType.setSelected(true);
						ckSLType.setText("??????");
					}
					String filePath = rs.getString("pic");
					ImageIcon icon = new ImageIcon(filePath);
					Image img = icon.getImage();
					img = img.getScaledInstance(80, 100, Image.SCALE_SMOOTH);
					ImageIcon image = new ImageIcon(img);
					lblPic.setIcon(image);
				}
				rs.close();
				stmt.close();			
				//==============================================
				con.close();
			} catch (ClassNotFoundException e1) {
				System.out.println("JDBC ???????????? ?????? ??????");
			} catch (SQLException e1) {
				System.out.println("DB ?????? ??????");
			} 
		
	}

		protected boolean isInteger(String sNumber) {
			//????????? true, ????????? ????????? false ???????????? ??????
			
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
            System.out.println("DB ?????? ??????");               
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
           System.out.println("JDBC ???????????? ?????? ??????");
           retType = false;
        } catch (SQLException e1) {
           System.out.println("DB ?????? ??????");
           retType = false;
        }finally {
        	return retType;
        }
		
	}
}
