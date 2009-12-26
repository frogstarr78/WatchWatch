import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LabelStats extends JPanel {
	Employees employee;
	TimeLog employeeAction;
	String action;
	GridBagLayout gridLayout = new GridBagLayout();
	GridBagConstraints GBC = new GridBagConstraints();
	JLabel label;

	public LabelStats(Employees employee) {
		setLayout(gridLayout);

		try {
			employeeAction = employee.getLastAction();
			action = employeeAction.getActionName();
//			action = "out";
		} catch (java.sql.SQLException sql_e) {
			System.out.println("Error " + sql_e.toString());
			sql_e.printStackTrace();
		} catch (ClassNotFoundException nocls_e) {
			System.out.println("Error " + nocls_e.toString());
		} catch (NonexistantValueException nev_e) {
			System.out.println("Error " + nev_e.toString());
		}

		GBC.fill = GridBagConstraints.BOTH;
		GBC.gridwidth = GridBagConstraints.RELATIVE;
		label = new JLabel("You are currently clocked: ");
		gridLayout.setConstraints(label, GBC);
		add(label);

		label = new JLabel(action);
		GBC.fill = GridBagConstraints.BOTH;
		GBC.gridwidth = GridBagConstraints.REMAINDER;
		GBC.weightx = 1.0;
		gridLayout.setConstraints(label, GBC);
		add(label);

		GBC.fill = GridBagConstraints.BOTH;
		GBC.gridwidth = GridBagConstraints.RELATIVE;
		label = new JLabel("You last clocked " + action + " at: ");
		gridLayout.setConstraints(label, GBC);
		add(label);

		GBC.fill = GridBagConstraints.BOTH;
		GBC.gridwidth = GridBagConstraints.REMAINDER;
		label = new JLabel(employeeAction.getTime());
		gridLayout.setConstraints(label, GBC);
		add(label);
		// encase in JPanel w/ tableLayout
//		pack();
	}
}
