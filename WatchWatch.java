import org.jdesktop.jdic.tray.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class WatchWatch {
// The Time Keeper

	// views
	public JLabel label;
	public Logique loginView;
	public WatchStats watchStats;
	public ClockTray trayItem;
    public org.jdesktop.jdic.tray.SystemTray systemTray;

	// models
	public Employees employee;	

	public WatchWatch(String title) { 
		this.employee = null;
	}

	public void setLogin(Logique login){
		this.loginView = login;
		this.loginView.getLoginButton().addActionListener(new AuthenticateAction());
		this.loginView.setVisible(true);
	}

	public void setWatchAction(WatchStats watchStats) {
		this.watchStats = watchStats;
	}

	public void setTrayItem(ClockTray trayItem) {
		this.systemTray = org.jdesktop.jdic.tray.SystemTray.getDefaultSystemTray();
		this.trayItem = trayItem;
		this.trayItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	watchStats.setVisible(!watchStats.isVisible());
            }
        });
		this.trayItem.getQuitItem().addActionListener(new QuitAction());
	}

	// ActionListeners
	class QuitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			systemTray.removeTrayIcon(trayItem);
			watchStats.dispose();
			System.exit(0);
		}
	}

	class AuthenticateAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Boolean isAuthorized;
			String encodedPassword = MD5Password.encode(loginView.getPassword());
			try {
				employee = Employees.findOneByName(loginView.getUsername());
				if(employee.getPassword().equals(encodedPassword)) {
					loginView.setVisible(false);
					showStats();
					showTray();
				} else {
					// show error label
					loginView.setErrorString("Login failed. Please try again.");
					loginView.pack();
				}
			} catch (java.sql.SQLException sql_e) {
				System.out.println("Error " + sql_e.toString());
				loginView.setErrorString("Login failed. Please try again.");
				loginView.pack();
			} catch (ClassNotFoundException nocls_e) {
				System.out.println("Error " + nocls_e.toString());
			} catch (NonexistantValueException user_e) {
				loginView.setErrorString(user_e.toString());
				loginView.pack();
			}
		}
	}
	class BreakAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JCheckBoxMenuItem checkBoxItem;

			try {
				checkBoxItem = (JCheckBoxMenuItem)e.getSource();
				employee.setToBreak(checkBoxItem.isSelected());
			} catch (java.sql.SQLException sql_e) {
				System.out.println("Error " + sql_e.toString());
				loginView.setErrorString("Login failed. Please try again.");
				loginView.pack();
			} catch (ClassNotFoundException nocls_e) {
				System.out.println("Error " + nocls_e.toString());
			} catch (NonexistantValueException user_e) {
				loginView.setErrorString(user_e.toString());
				loginView.pack();
			}
		}
	}

	class ClockingAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JMenuItem menuItem = (JMenuItem)e.getSource();
			String sourceText = menuItem.getText();

			try {
				menuItem = (JMenuItem)e.getSource();
				if (sourceText.equals("Clock In")) {
					employee.clockOff();
					menuItem.setText("Clock Off");
				} else if (sourceText.equals("Clock Off")) {
					employee.clockOn();
					menuItem.setText("Clock In");
				}
			} catch (java.sql.SQLException sql_e) {
				System.out.println("Error " + sql_e.toString());
				loginView.setErrorString("Login failed. Please try again.");
				loginView.pack();
			} catch (ClassNotFoundException nocls_e) {
				System.out.println("Error " + nocls_e.toString());
			} catch (NonexistantValueException user_e) {
				loginView.setErrorString(user_e.toString());
				loginView.pack();
			}
		}
	}

	// wrappers for ui initializations
	public void showStats() {
		watchStats.setEmployee(this.employee);
		watchStats.setVisible(true);	
	}

	public void showTray() {
		trayItem.setEmployee(this.employee);
		trayItem.setPopupMenu(trayItem.getMenu());
		systemTray.addTrayIcon(trayItem);
	}

	// entry to it all
	public static void main(String[] args) {
		WatchWatch watchWatch = new WatchWatch("Watch Login");
		watchWatch.setLogin(new Logique());
		watchWatch.setWatchAction(new WatchStats("Watch Watch"));
		watchWatch.setTrayItem(new ClockTray("Tray Item"));
	}

}
