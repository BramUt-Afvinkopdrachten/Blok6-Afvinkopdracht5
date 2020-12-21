package AminoApp;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AminoApp extends JFrame {
    private Container window;
    private JTextField oneLetter, threeLetter, fullName, inputField;
    private AminoMap aminoMap = new AminoMap();

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
            // Zorgt dat case niet uit maakt.
            try{
                inputText = inputText.substring(0, 1).toUpperCase() + inputText.substring(1).toLowerCase();
                String[] result = aminoMap.getNames(inputText);
                System.out.println(Arrays.toString(result));
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
        window.add(fullName, c);
        c.gridx++;
        c.anchor = GridBagConstraints.CENTER;
        window.add(threeLetter, c);
        c.gridx++;
        c.anchor = GridBagConstraints.EAST;
        window.add(oneLetter, c);


        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        AminoApp app = new AminoApp();
    }
}
