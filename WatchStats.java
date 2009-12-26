import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class WatchStats extends JFrame {
// The Time Keeper

	public JLabel label;
	public JPanel panel;
	public JPanel labelStats;
	public GridBagConstraints GBC = new GridBagConstraints();
	public GridBagLayout gridLayout;
	public Employees employee;

	public WatchStats(String title) {
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setIconImage(new ImageIcon("sweetie2/png/16-clock.png").getImage());
		setTitle(title);

//		setUndecorated(true);
		int width = 300;
		int height = 200;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);

		panel = new JPanel();

//		JButton clockActionButton = new JButton("Clock In");
//		clockActionButton.setMnemonic(KeyEvent.VK_L);
//		panel.add(clockActionButton);

		JButton closeButton = new JButton("Close");
		closeButton.setMnemonic(KeyEvent.VK_C);
		closeButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			}
		);

		panel.add(closeButton);
	
		add(panel, BorderLayout.SOUTH);
		pack();
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
		labelStats = new LabelStats(employee);
		add(labelStats, BorderLayout.NORTH);
		pack();
	}

//	class menuItemToggle implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			JMenuItem item = (JMenuItem)e.getSource();
//			if(item.getText() == "Hello") {
//				item.setText("Bye");
//			} else if(item.getText() == "Bye") {
//				item.setText("Hello");
//			} else {
//				System.out.println("Error");
//			}
//		}	
//	}

	public static void main(String[] args) {
		WatchStats actionWatch = new WatchStats("Action Watch");
		actionWatch.setVisible(true);	
	}

}
