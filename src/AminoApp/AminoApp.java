package AminoApp;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AminoApp extends JFrame {
    private final Container window;
    private final JTextField oneLetter, threeLetter, fullName, inputField;
    private final AminoMap aminoMap = new AminoMap();

    public AminoApp() {
        window = getContentPane();
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        inputField = new JTextField(20);
        fullName = new JTextField(13);
        threeLetter = new JTextField(3);
        oneLetter = new JTextField(1);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String inputText = inputField.getText();
            try{
                // Maakt eerste character uppercase en de rest lowercase.
                inputText = inputText.substring(0, 1).toUpperCase() + inputText.substring(1).toLowerCase();
                // Zoekt naam, 3 letter code en 1 letter code op in aminoMap.
                String[] result = aminoMap.getNames(inputText);
                System.out.println(Arrays.toString(result));
                // Plaatst resultaten in de juiste JTextFields.
                fullName.setText(result[0]);
                threeLetter.setText(result[1]);
                oneLetter.setText(result[2]);
            } catch (NullPointerException | StringIndexOutOfBoundsException nullPointerException) {
                JOptionPane.showMessageDialog(null, "Unable to find '" + inputText + "'");
            }
        });

        c.gridwidth = 5;
        window.add(inputField, c);
        c.gridx = 6;
        c.gridwidth = 0;
        window.add(searchButton, c);

        c.gridx = 0;
        c.gridy++;
        c.anchor = GridBagConstraints.WEST;
        window.add(new JLabel("Name:"));
        c.gridx++;
        window.add(fullName, c);
        c.gridy++;
        c.gridx = 0;
        window.add(new JLabel("3 letter:"));
        c.gridx++;
        window.add(threeLetter, c);
        c.gridy++;
        c.gridx = 0;
        window.add(new JLabel("1 letter:"));
        c.gridx++;
        window.add(oneLetter, c);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        AminoApp app = new AminoApp();
    }
}
