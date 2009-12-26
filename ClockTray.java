import org.jdesktop.jdic.tray.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ClockTray extends org.jdesktop.jdic.tray.TrayIcon {

	public ImageIcon imageIcon;
	public JPopupMenu menu;
	public JMenuItem quitMenuItem;
	public Employees employee;
	public TimeLog employeeAction;

    public ClockTray(String title) {
		super(new ImageIcon(ClockTray.class.getResource("sweetie2/png/16-clock.png")), title);
		this.employee = null;
        menu = new JPopupMenu("Clock Menu");
//		setPopupMenu(getMenu());
        // "Quit" menu item
		menu.insert(new JPopupMenu.Separator(), 0);
        this.quitMenuItem = new JMenuItem("Quit");
        menu.add(this.quitMenuItem);
	}

	public JPopupMenu getMenu() {
        JMenuItem menuItem;
        JCheckBoxMenuItem cbMenuItem;
		ImageIcon menuIcon;
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if( Integer.parseInt(System.getProperty("java.version").substring(2,3)) >=5 )
            System.setProperty("javax.swing.adjustPopupLocationToFit", "false");
	
		// due to having the Quit option added first for all conditions
		// we have to insert the remaining menu items in at the top and
		// therefore... backwards; to get them where we want.
		addOptionsSubmenu(menu);

        menuIcon = new ImageIcon(ClockTray.class.getResource("sweetie2/png/16-clock.png"));

		String action = "In";
		boolean selectCheckbox = false;
		try {
			if(employee.isClockedOn())
				action = "Off";
			if(employee.isOnBreak())
				selectCheckbox = true;
		} catch (java.sql.SQLException sql_e) {
			System.out.println("Error " + sql_e.toString());
			sql_e.printStackTrace();
		} catch (ClassNotFoundException nocls_e) {
			System.out.println("Error " + nocls_e.toString());
			nocls_e.printStackTrace();
		} catch (NonexistantValueException nev_e) {
			System.out.println("Error " + nev_e.toString());
			nev_e.printStackTrace();
		}

//        menuItem = new JMenuItem("Take Break", menuIcon);
        cbMenuItem = new JCheckBoxMenuItem("Take Break");
		cbMenuItem.setSelected(selectCheckbox);
        cbMenuItem.setMnemonic(KeyEvent.VK_B);
        menu.insert(cbMenuItem, 0);

        menuItem = new JMenuItem("Clock " + action, menuIcon);
        menuItem.setMnemonic(KeyEvent.VK_C);
        menu.insert(menuItem, 0);

		menu.insert(new JPopupMenu.Separator(), 0);
       
		if(employee.isManager) {
			menuIcon = new ImageIcon(ClockTray.class.getResource("sweetie2/png/16-tag-pencil.png"));
			menuItem = new JMenuItem("Add/Edit User", menuIcon);
			menuItem.setMnemonic(KeyEvent.VK_U);
			menu.insert(menuItem, 0);

			menuIcon = new ImageIcon(ClockTray.class.getResource("sweetie2/png/16-archive.png"));
			menuItem = new JMenuItem("Reports", menuIcon);
			menuItem.setMnemonic(KeyEvent.VK_R);
			menu.insert(menuItem, 0);

			menu.insert(new JPopupMenu.Separator(), 0);
		}

		return menu;
    }
	public JMenuItem getQuitItem() {
		return this.quitMenuItem;
	}

	protected void addOptionsSubmenu(JPopupMenu menu) {
        // a submenu
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
		
        JMenu submenu = new JMenu("Options");
        submenu.setMnemonic(KeyEvent.VK_O);

		menuItem = new JMenuItem("Show time in");
		submenu.add(menuItem);

        // a group of radio button menu items
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("    ...minutes");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_M);
        group.add(rbMenuItem);
        submenu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("    ...decimal");
        rbMenuItem.setMnemonic(KeyEvent.VK_D);
        group.add(rbMenuItem);
        submenu.add(rbMenuItem);

		if(employee.isManager) {
			submenu.addSeparator();
			menuItem = new JMenuItem("Time Server Settings");
			submenu.add(menuItem);
		}
        menu.insert(submenu, 0);
	}

	public void setEmployee(Employees employee) { this.employee = employee; }
	public void getEmployee(Employees employee) { this.employee = employee; }
	public boolean hasEmployee() { return (this.employee != null); }

    public static void main(String[] args) {
        new ClockTray("Tray Item");
    }

}
