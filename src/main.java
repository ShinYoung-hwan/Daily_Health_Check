import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.awt.GridBagLayout;
import javax.swing.JProgressBar;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class main extends JFrame {

	private JPanel contentPane;
	private Panel panel;
	private JProgressBar progressBar;
	private JButton checkButton;
	private JLabel explainLabel;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JLabel nameLabel;
	private JLabel ageLabel;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JLabel feverLabel;
	private JCheckBox feverCheckBox;
	private JLabel LossOfAppetiteLabel;
	private JCheckBox LossOfAppetiteCheckBox;
	private JLabel coughLabel;
	private JCheckBox coughCheckBox;
	private JLabel LossOfSmellabel;
	private JCheckBox lossOfSmellCheckBox;
	private JLabel shortnessOfBreathLabel;
	private JCheckBox shortnessOfBreathCheckBox;
	private JLabel fatigueLabel;
	private JCheckBox fatigueCheckBox;
	private JLabel CoughingUpSputumLabel;
	private JCheckBox CoughingUpSputumCheckBox;
	private JLabel MuscleAchesAndPainLabel;
	private JCheckBox MuscleAchesAndPainCheckBox;
	private JButton historyButton;
	private JButton feverExplainButton;
	private JButton LossOfAppetiteExplainButton;
	private JButton coughExplainButton;
	private JButton LossOfSmellExplainButton;
	private JButton ShortnessOfBreathExplainButton;
	private JButton fatigueExplainButton;
	private JButton coughingUpSputumExplainButton;
	private JButton MuscleAchesAndPainButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setTitle("Daily Health Check");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new Panel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 89, 146, 95, 91, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 23, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridwidth = 7;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 0;
		panel.add(progressBar, gbc_progressBar);

		// This is default object to use inner method.
		Person defaultPerson = new Person("", 0);
		// This part is action of check button.
		checkButton = new JButton("Check!");
		checkButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (nameTextField.getText().equals(""))
						throw new blankFileNameException();

					Person user = new Person(nameTextField.getText(), Integer.parseInt(ageTextField.getText()));

					// Use the username.txt file.
					PrintWriter writer = new PrintWriter(new FileOutputStream(nameTextField.getText() + ".txt", true));

					writer.printf("%s\nName: %s\nAge: %d\n", getTodayDate(), user.getUserName(), user.getUserAge());
					String printout = String.format("%s\nName: %s\nAge: %d\n", getTodayDate(), user.getUserName(),
							user.getUserAge());
					progressBar.setValue(10);

					// get all symptoms that the user has.
					{
						String tmpStr = getSymptomString(user);
						writer.printf("Number of symptoms: %d\n", user.getNumOfSymptoms());
						writer.printf("Symptoms:\n" + tmpStr);
						printout += "Symptoms:\n";
						printout += tmpStr;
					}
					progressBar.setValue(60);
					// If the user has more than or equal 15, then, show "do not go out!!";
					int selectedWeight = getSelectedWeight(user.getSymptoms());
					if (selectedWeight >= 15) {
						writer.println("Result: ");
						writer.println(" - You must stay at home!!\n");
						printout += "Result: \n - You must stay at home!!\n";
					} else {
						writer.println("Result: ");
						writer.println("You can go out today!!\n");
						printout += "Result: \n - You can go out today!!\n";
					}
					progressBar.setValue(100);
					writer.close();
					JOptionPane.showMessageDialog(null, printout);
				} catch (NumberFormatException e1) {
					// this part is for catch whether the user set char in ageTextField.
					JOptionPane.showMessageDialog(null, "Only integer is valid in age textfield.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (blankFileNameException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Please use 'Name' textfield!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "The file is not exist!", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		GridBagConstraints gbc_checkButton = new GridBagConstraints();
		gbc_checkButton.insets = new Insets(0, 0, 0, 5);
		gbc_checkButton.gridx = 7;
		gbc_checkButton.gridy = 0;
		panel.add(checkButton, gbc_checkButton);

		// This part is for action of history button.
		historyButton = new JButton("History");
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (nameTextField.getText().equals(""))
						throw new blankFileNameException();
					// use the username txt file.
					Scanner reader = new Scanner(new FileInputStream(nameTextField.getText() + ".txt"));
					String str = new String();

					while (reader.hasNext()) {
						str += reader.nextLine() + "\n";
					}

					progressBar.setValue(100);
					// Use jDialog to show the history.
					ScrollDialog historyDialog = new ScrollDialog();
					historyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					historyDialog.historyEditorPane.setText(str);
					historyDialog.setVisible(true);

					reader.close();
				} catch (blankFileNameException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Please use 'Name' textfield!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "The file is not exist!", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		GridBagConstraints gbc_historyButton = new GridBagConstraints();
		gbc_historyButton.gridx = 8;
		gbc_historyButton.gridy = 0;
		panel.add(historyButton, gbc_historyButton);

		explainLabel = new JLabel(
				"<html>\rThis app is for check daily health check!<br>Let's check it is okay to go out today!\r<html>");
		explainLabel.setFont(explainLabel.getFont().deriveFont(explainLabel.getFont().getStyle() | Font.BOLD, 15f));
		explainLabel.setForeground(Color.BLACK);
		explainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		explainLabel.setOpaque(true);
		explainLabel.setBackground(Color.WHITE);
		contentPane.add(explainLabel, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		nameLabel = new JLabel("Name");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 0;
		panel_1.add(nameLabel, gbc_nameLabel);

		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 13;
		gbc_nameTextField.gridy = 0;
		panel_1.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		ageLabel = new JLabel("Age");
		GridBagConstraints gbc_ageLabel = new GridBagConstraints();
		gbc_ageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ageLabel.gridx = 0;
		gbc_ageLabel.gridy = 1;
		panel_1.add(ageLabel, gbc_ageLabel);

		ageTextField = new JTextField();
		GridBagConstraints gbc_ageTextField = new GridBagConstraints();
		gbc_ageTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageTextField.gridx = 13;
		gbc_ageTextField.gridy = 1;
		panel_1.add(ageTextField, gbc_ageTextField);
		ageTextField.setColumns(10);

		feverLabel = new JLabel("Fever");
		GridBagConstraints gbc_feverLabel = new GridBagConstraints();
		gbc_feverLabel.insets = new Insets(0, 0, 5, 5);
		gbc_feverLabel.gridx = 0;
		gbc_feverLabel.gridy = 2;
		panel_1.add(feverLabel, gbc_feverLabel);

		feverCheckBox = new JCheckBox("Yes");
		GridBagConstraints gbc_feverCheckBox = new GridBagConstraints();
		gbc_feverCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_feverCheckBox.gridx = 12;
		gbc_feverCheckBox.gridy = 2;
		panel_1.add(feverCheckBox, gbc_feverCheckBox);

		feverExplainButton = new JButton("Explain");
		feverExplainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						defaultPerson.getSymptoms()[Person.symptomType.Fever.ordinal()].getExplainationOfSymptom());
			}
		});
		GridBagConstraints gbc_feverExplainButton = new GridBagConstraints();
		gbc_feverExplainButton.insets = new Insets(0, 0, 5, 0);
		gbc_feverExplainButton.gridx = 13;
		gbc_feverExplainButton.gridy = 2;
		panel_1.add(feverExplainButton, gbc_feverExplainButton);

		LossOfAppetiteLabel = new JLabel("Loss Of Appetite");
		GridBagConstraints gbc_LossOfAppetiteLabel = new GridBagConstraints();
		gbc_LossOfAppetiteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_LossOfAppetiteLabel.gridx = 0;
		gbc_LossOfAppetiteLabel.gridy = 3;
		panel_1.add(LossOfAppetiteLabel, gbc_LossOfAppetiteLabel);

		LossOfAppetiteCheckBox = new JCheckBox("Yes");
		GridBagConstraints gbc_LossOfAppetiteCheckBox = new GridBagConstraints();
		gbc_LossOfAppetiteCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_LossOfAppetiteCheckBox.gridx = 12;
		gbc_LossOfAppetiteCheckBox.gridy = 3;
		panel_1.add(LossOfAppetiteCheckBox, gbc_LossOfAppetiteCheckBox);

		LossOfAppetiteExplainButton = new JButton("Explain");
		LossOfAppetiteExplainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						defaultPerson.getSymptoms()[Person.symptomType.Loss_Of_Appetite.ordinal()]
								.getExplainationOfSymptom());
			}
		});
		GridBagConstraints gbc_LossOfAppetiteExplainButton = new GridBagConstraints();
		gbc_LossOfAppetiteExplainButton.insets = new Insets(0, 0, 5, 0);
		gbc_LossOfAppetiteExplainButton.gridx = 13;
		gbc_LossOfAppetiteExplainButton.gridy = 3;
		panel_1.add(LossOfAppetiteExplainButton, gbc_LossOfAppetiteExplainButton);

		coughLabel = new JLabel("Cough");
		GridBagConstraints gbc_coughLabel = new GridBagConstraints();
		gbc_coughLabel.insets = new Insets(0, 0, 5, 5);
		gbc_coughLabel.gridx = 0;
		gbc_coughLabel.gridy = 4;
		panel_1.add(coughLabel, gbc_coughLabel);

		coughCheckBox = new JCheckBox("Yes");
		GridBagConstraints gbc_coughCheckBox = new GridBagConstraints();
		gbc_coughCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_coughCheckBox.gridx = 12;
		gbc_coughCheckBox.gridy = 4;
		panel_1.add(coughCheckBox, gbc_coughCheckBox);

		coughExplainButton = new JButton("Explain");
		coughExplainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						defaultPerson.getSymptoms()[Person.symptomType.Cough.ordinal()].getExplainationOfSymptom());
			}
		});
		GridBagConstraints gbc_coughExplainButton = new GridBagConstraints();
		gbc_coughExplainButton.insets = new Insets(0, 0, 5, 0);
		gbc_coughExplainButton.gridx = 13;
		gbc_coughExplainButton.gridy = 4;
		panel_1.add(coughExplainButton, gbc_coughExplainButton);

		LossOfSmellabel = new JLabel("Loss of smell");
		GridBagConstraints gbc_LossOfSmellabel = new GridBagConstraints();
		gbc_LossOfSmellabel.insets = new Insets(0, 0, 5, 5);
		gbc_LossOfSmellabel.gridx = 0;
		gbc_LossOfSmellabel.gridy = 5;
		panel_1.add(LossOfSmellabel, gbc_LossOfSmellabel);

		lossOfSmellCheckBox = new JCheckBox("Yes");
		GridBagConstraints gbc_lossOfSmellCheckBox = new GridBagConstraints();
		gbc_lossOfSmellCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_lossOfSmellCheckBox.gridx = 12;
		gbc_lossOfSmellCheckBox.gridy = 5;
		panel_1.add(lossOfSmellCheckBox, gbc_lossOfSmellCheckBox);

		LossOfSmellExplainButton = new JButton("Explain");
		LossOfSmellExplainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						defaultPerson.getSymptoms()[Person.symptomType.Loss_Of_Smell.ordinal()]
								.getExplainationOfSymptom());
			}
		});
		GridBagConstraints gbc_LossOfSmellExplainButton = new GridBagConstraints();
		gbc_LossOfSmellExplainButton.insets = new Insets(0, 0, 5, 0);
		gbc_LossOfSmellExplainButton.gridx = 13;
		gbc_LossOfSmellExplainButton.gridy = 5;
		panel_1.add(LossOfSmellExplainButton, gbc_LossOfSmellExplainButton);

		shortnessOfBreathLabel = new JLabel("Shortness of breath");
		GridBagConstraints gbc_shortnessOfBreathLabel = new GridBagConstraints();
		gbc_shortnessOfBreathLabel.insets = new Insets(0, 0, 5, 5);
		gbc_shortnessOfBreathLabel.gridx = 0;
		gbc_shortnessOfBreathLabel.gridy = 6;
		panel_1.add(shortnessOfBreathLabel, gbc_shortnessOfBreathLabel);

		shortnessOfBreathCheckBox = new JCheckBox("Yes");
		GridBagConstraints gbc_shortnessOfBreathCheckBox = new GridBagConstraints();
		gbc_shortnessOfBreathCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_shortnessOfBreathCheckBox.gridx = 12;
		gbc_shortnessOfBreathCheckBox.gridy = 6;
		panel_1.add(shortnessOfBreathCheckBox, gbc_shortnessOfBreathCheckBox);

		ShortnessOfBreathExplainButton = new JButton("Explain");
		ShortnessOfBreathExplainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						defaultPerson.getSymptoms()[Person.symptomType.Shortness_Of_breath.ordinal()]
								.getExplainationOfSymptom());
			}
		});
		GridBagConstraints gbc_ShortnessOfBreathExplainButton = new GridBagConstraints();
		gbc_ShortnessOfBreathExplainButton.insets = new Insets(0, 0, 5, 0);
		gbc_ShortnessOfBreathExplainButton.gridx = 13;
		gbc_ShortnessOfBreathExplainButton.gridy = 6;
		panel_1.add(ShortnessOfBreathExplainButton, gbc_ShortnessOfBreathExplainButton);

		fatigueLabel = new JLabel("Fatigue");
		GridBagConstraints gbc_fatigueLabel = new GridBagConstraints();
		gbc_fatigueLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fatigueLabel.gridx = 0;
		gbc_fatigueLabel.gridy = 7;
		panel_1.add(fatigueLabel, gbc_fatigueLabel);

		fatigueCheckBox = new JCheckBox("Yes");
		GridBagConstraints gbc_fatigueCheckBox = new GridBagConstraints();
		gbc_fatigueCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_fatigueCheckBox.gridx = 12;
		gbc_fatigueCheckBox.gridy = 7;
		panel_1.add(fatigueCheckBox, gbc_fatigueCheckBox);

		fatigueExplainButton = new JButton("Explain");
		fatigueExplainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						defaultPerson.getSymptoms()[Person.symptomType.Fatigue.ordinal()].getExplainationOfSymptom());
			}
		});
		GridBagConstraints gbc_fatigueExplainButton = new GridBagConstraints();
		gbc_fatigueExplainButton.insets = new Insets(0, 0, 5, 0);
		gbc_fatigueExplainButton.gridx = 13;
		gbc_fatigueExplainButton.gridy = 7;
		panel_1.add(fatigueExplainButton, gbc_fatigueExplainButton);

		CoughingUpSputumLabel = new JLabel("Coughing up sputum");
		GridBagConstraints gbc_CoughingUpSputumLabel = new GridBagConstraints();
		gbc_CoughingUpSputumLabel.insets = new Insets(0, 0, 5, 5);
		gbc_CoughingUpSputumLabel.gridx = 0;
		gbc_CoughingUpSputumLabel.gridy = 8;
		panel_1.add(CoughingUpSputumLabel, gbc_CoughingUpSputumLabel);

		CoughingUpSputumCheckBox = new JCheckBox("Yes");
		GridBagConstraints gbc_CoughingUpSputumCheckBox = new GridBagConstraints();
		gbc_CoughingUpSputumCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_CoughingUpSputumCheckBox.gridx = 12;
		gbc_CoughingUpSputumCheckBox.gridy = 8;
		panel_1.add(CoughingUpSputumCheckBox, gbc_CoughingUpSputumCheckBox);

		coughingUpSputumExplainButton = new JButton("Explain");
		coughingUpSputumExplainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						defaultPerson.getSymptoms()[Person.symptomType.Coughing_Up_sputum.ordinal()]
								.getExplainationOfSymptom());
			}
		});
		GridBagConstraints gbc_coughingUpSputumExplainButton = new GridBagConstraints();
		gbc_coughingUpSputumExplainButton.insets = new Insets(0, 0, 5, 0);
		gbc_coughingUpSputumExplainButton.gridx = 13;
		gbc_coughingUpSputumExplainButton.gridy = 8;
		panel_1.add(coughingUpSputumExplainButton, gbc_coughingUpSputumExplainButton);

		MuscleAchesAndPainLabel = new JLabel("Muscle aches and pain");
		GridBagConstraints gbc_MuscleAchesAndPainLabel = new GridBagConstraints();
		gbc_MuscleAchesAndPainLabel.insets = new Insets(0, 0, 0, 5);
		gbc_MuscleAchesAndPainLabel.gridx = 0;
		gbc_MuscleAchesAndPainLabel.gridy = 9;
		panel_1.add(MuscleAchesAndPainLabel, gbc_MuscleAchesAndPainLabel);

		MuscleAchesAndPainCheckBox = new JCheckBox("Yes");
		GridBagConstraints gbc_MuscleAchesAndPainCheckBox = new GridBagConstraints();
		gbc_MuscleAchesAndPainCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_MuscleAchesAndPainCheckBox.gridx = 12;
		gbc_MuscleAchesAndPainCheckBox.gridy = 9;
		panel_1.add(MuscleAchesAndPainCheckBox, gbc_MuscleAchesAndPainCheckBox);

		MuscleAchesAndPainButton = new JButton("Explain");
		MuscleAchesAndPainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						defaultPerson.getSymptoms()[Person.symptomType.Muscle_Aches_and_Pain.ordinal()]
								.getExplainationOfSymptom());
			}
		});
		GridBagConstraints gbc_MuscleAchesAndPainButton = new GridBagConstraints();
		gbc_MuscleAchesAndPainButton.gridx = 13;
		gbc_MuscleAchesAndPainButton.gridy = 9;
		panel_1.add(MuscleAchesAndPainButton, gbc_MuscleAchesAndPainButton);
	}

	// This method is for get today's Date.
	public String getTodayDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
		String today = sdf.format(date);
		return today;
	}

	// This is method for get total weight that the checkbox is selected.
	public int getSelectedWeight(Symptoms[] symptomList) {
		int weightSum = 0;

		if (feverCheckBox.isSelected())
			weightSum += symptomList[Person.symptomType.Fever.ordinal()].getWeightOfSymptom();
		if (LossOfAppetiteCheckBox.isSelected())
			weightSum += symptomList[Person.symptomType.Loss_Of_Appetite.ordinal()].getWeightOfSymptom();
		if (coughCheckBox.isSelected())
			weightSum += symptomList[Person.symptomType.Cough.ordinal()].getWeightOfSymptom();
		if (lossOfSmellCheckBox.isSelected())
			weightSum += symptomList[Person.symptomType.Loss_Of_Smell.ordinal()].getWeightOfSymptom();
		if (shortnessOfBreathCheckBox.isSelected())
			weightSum += symptomList[Person.symptomType.Shortness_Of_breath.ordinal()].getWeightOfSymptom();
		if (fatigueCheckBox.isSelected())
			weightSum += symptomList[Person.symptomType.Fatigue.ordinal()].getWeightOfSymptom();
		if (CoughingUpSputumCheckBox.isSelected())
			weightSum += symptomList[Person.symptomType.Coughing_Up_sputum.ordinal()].getWeightOfSymptom();
		if (MuscleAchesAndPainCheckBox.isSelected())
			weightSum += symptomList[Person.symptomType.Muscle_Aches_and_Pain.ordinal()].getWeightOfSymptom();

		return weightSum;
	}

	// This method is for return string that contains all selected symptoms
	public String getSymptomString(Person user) {
		String str = "";

		if (feverCheckBox.isSelected()) {
			str += " - Fever\n";
			user.plusNumOfSymptoms();
		}
		if (LossOfAppetiteCheckBox.isSelected()) {
			str += " - Loss of appetite\n";
			user.plusNumOfSymptoms();
		}
		if (coughCheckBox.isSelected()) {
			str += " - Cough\n";
			user.plusNumOfSymptoms();
		}
		if (lossOfSmellCheckBox.isSelected()) {
			str += " - Loss of smell\n";
			user.plusNumOfSymptoms();
		}
		if (shortnessOfBreathCheckBox.isSelected()) {
			str += " - Shortness of breath\n";
			user.plusNumOfSymptoms();
		}
		if (fatigueCheckBox.isSelected()) {
			str += " - fatigue\n";
			user.plusNumOfSymptoms();
		}
		if (CoughingUpSputumCheckBox.isSelected()) {
			str += " - Coughing up sputum\n";
			user.plusNumOfSymptoms();
		}
		if (MuscleAchesAndPainCheckBox.isSelected()) {
			str += " - Muscle aches and pain\n";
			user.plusNumOfSymptoms();
		}

		return str;
	}
}
