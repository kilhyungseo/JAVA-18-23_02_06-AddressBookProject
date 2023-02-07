import java.awt.EventQueue;

import javax.swing.JDialog;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinMain extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMain dialog = new WinMain();
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
	public WinMain() {
		setTitle("동창회 주소록 관리 프로그램");
		setBounds(100, 100, 334, 300);
		getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				WinFriendshipInsert winFriendshipInsert = new WinFriendshipInsert();
				winFriendshipInsert.setModal(true);
				winFriendshipInsert.setVisible(true);
			}
		});
		btnAdd.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberAdd.png")));
		getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinFriendshipDelete friendshipDelete = new WinFriendshipDelete();
				friendshipDelete.setModal(true);
				friendshipDelete.setVisible(true);
				
			}
		});
		btnDelete.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberRemove.png")));
		getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinFriendshipUpdate winFriendshipUpdate = new WinFriendshipUpdate();
				winFriendshipUpdate.setModal(true);
				winFriendshipUpdate.setVisible(true);
				
			}
		});
		btnUpdate.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberUpdate.png")));
		getContentPane().add(btnUpdate);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberSearch.png")));
		getContentPane().add(btnSearch);

	}

}
