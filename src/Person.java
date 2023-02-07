import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Person extends JPanel {
	private JTextField textField;
	public Person() {
		setBackground(new Color(192, 192, 192));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Person.class.getResource("/images/memberSearch.png")));
		lblNewLabel.setBackground(new Color(255, 255, 0));
		lblNewLabel.setBounds(12, 10, 93, 122);
		lblNewLabel.setOpaque(true);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\uD074\uB9AD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnNewButton, "안녕하세요");
			}
		});
		btnNewButton.setBounds(202, 136, 97, 23);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(240, 47, 116, 21);
		add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"23132", "3", "243", "]324"}));
		comboBox.setBounds(324, 136, 32, 23);
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984 : ");
		lblNewLabel_1.setBounds(226, 10, 57, 15);
		add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(33, 200, 97, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(202, 200, 97, 23);
		add(btnNewButton_2);
	}
}
