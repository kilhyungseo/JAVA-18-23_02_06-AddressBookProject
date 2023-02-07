import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinDoroList extends JDialog {
	public JList list;
	public String retAddress;
	
	public String getAddress() { //선택
		
		return retAddress;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinDoroList dialog = new WinDoroList();
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
	public WinDoroList() {
		setTitle("도로명 검색 결과");
		setBounds(100, 100, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				retAddress = list.getSelectedValue().toString();
				setVisible(false);
			}
		});
		scrollPane.setViewportView(list);

	}



	public WinDoroList(String doro) {
		this();
		showDoroList(doro);

	}

	private void showDoroList(String doro) {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
            System.out.println("DB 연결 완료");               
            Statement statement=conn.createStatement();
            
            //=============================================
            String sql = "select * from addresstbl where road= ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, doro);
            ResultSet rs = pstmt.executeQuery();
            Vector vector = new Vector<>();
            while(rs.next()) {  // 각 레코드의 필드들을 읽어서 벡터에 저장한 후, DefaultTableModel 에 추가한다.
				
				vector.add(rs.getString("si") + " " + rs.getString("gu") + " " + rs.getString("dong"));
            }
            list.setListData(vector);
            rs.close();
            pstmt.close();
            conn.close();
            //==============================================
        } catch (ClassNotFoundException e1) {
           System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e1) {
           System.out.println("DB 연결 오류");
        }
		
	}

}
