package GeneFinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class GeneFinderGUI extends JFrame {
    private Container window;
    private JTextArea text1, text2, text3, commonArea;
    private JComboBox<String> selectionBox;
    private final JFileChooser fileChooser = new JFileChooser();

    public GeneFinderGUI() {
        window = getContentPane();
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;

        text1 = new JTextArea("1",7, 20);
        JScrollPane scroll1 = new JScrollPane(text1);
        text2 = new JTextArea("2",7, 20);
        JScrollPane scroll2 = new JScrollPane(text2);
        text3 = new JTextArea("3",7, 20);
        JScrollPane scroll3 = new JScrollPane(text3);

        JButton button1 = new JButton("Browse...");
        button1.addActionListener(e -> buttonListener(text1));
        JButton button2 = new JButton("Browse...");
        button2.addActionListener(e -> buttonListener(text2));
        JButton button3 = new JButton("Browse...");
        button3.addActionListener(e -> buttonListener(text3));

        commonArea = new JTextArea();
        commonArea.setRows(7);
        JScrollPane commonScroll = new JScrollPane(commonArea);

        String[] options = new String[] {"Algemeen", "1 & 2", "1 & 3", "2 & 3"};
        selectionBox = new JComboBox<>(options);
        selectionBox.addActionListener(this::boxListener);

        System.out.println(c.gridx);
        System.out.println(c.gridy);
        window.add(scroll1, c);
        c.gridx++;
        window.add(scroll2, c);
        c.gridx++;
        window.add(scroll3, c);

        c.gridx = 0;
        c.gridy++;
        window.add(button1, c);
        c.gridx++;
        window.add(button2, c);
        c.gridx++;
        window.add(button3, c);

        c.gridx = 0;
        c.gridy++;
        window.add(new Label("Common genes:"), c);
        c.gridx++;
        window.add(selectionBox, c);
        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        window.add(commonScroll, c);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        GeneFinderGUI app = new GeneFinderGUI();
    }

    /**
     * Maakt een HashSet van alle genen in een gegeven jTextArea
     * @param jTextArea TextArea met genen.
     * @return HashSet met genen uit TextArea.
     */
    public HashSet<String> readContents(JTextArea jTextArea){
        return new HashSet<>(Arrays.asList(jTextArea.getText().split("[ \n\t]+")));
    }

    /**
     * Action listener voor de JComboBox. Zet de overlappende genen van de geselecteerde JTextAreas in commonArea.
     * @param e ActionEvent
     */
    public void boxListener(ActionEvent e){
        HashSet<String> commonGenes;
        String selected = (String) selectionBox.getSelectedItem();

        assert selected != null;
        switch (selected) {
            case "Algemeen" -> {
                commonGenes = readContents(text1);
                commonGenes.retainAll(readContents(text2));
                commonGenes.retainAll(readContents(text3));
            }
            case "1 & 2" -> {
                commonGenes = readContents(text1);
                commonGenes.retainAll(readContents(text2));
            }
            case "1 & 3" -> {
                commonGenes = readContents(text1);
                commonGenes.retainAll(readContents(text3));
            }
            case "2 & 3" -> {
                commonGenes = readContents(text2);
                commonGenes.retainAll(readContents(text3));
            }
            default -> commonGenes = new HashSet<>(Arrays.asList("?", "??"));
        }
        commonArea.setText("");
        commonArea.setText(String.join("\n", commonGenes));
    }

    /**
     * Actionlistener voor filechooser buttons. OPent een filechoose en plaatst path in een JTextArea.
     * @param jTextArea Area waar de path van het bestand in moet komen staan.
     */
    public void buttonListener(JTextArea jTextArea) {
        // Opent filechooser.
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if (file != null) {
            try {
                // Plaats path in jTextArea.
                jTextArea.setText(readFile(file.getAbsolutePath()));
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, ioException.getMessage());
            }
        }
    }

    /**
     * Leest een bestand met per regel de naam van een gen, returnt deze als een enkelen String.
     * @param filepath Path naar de te lezen file.
     * @return String met genen.
     * @throws IOException Als de file niet gevonden/geopend kan worden.
     */
    public static String readFile(String filepath) throws IOException {
        String line;
        StringBuilder content = new StringBuilder();
        BufferedReader inFile = new BufferedReader(new FileReader(filepath));
        while ((line = inFile.readLine()) != null) {
            content.append(line.strip()).append("\n");
        }
        return content.toString();
    }

}
