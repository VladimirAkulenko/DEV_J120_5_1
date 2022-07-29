/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j120_5_1;

/**
 *
 * @author USER
 */
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MainFrame extends JFrame {
    File [] fileDir;
    JSplitPane splitPane;
    JTextArea textArea = new JTextArea();
    private final JList fileList;

    public MainFrame(){
        setBounds(300,100,800,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        fileList = new JList();
        fileList.addListSelectionListener(e -> onFiledListItemSelection());
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        fileList.addMouseListener(new CustomMouseAction(this));
        fileList.addKeyListener(new CustomKeyAction(this));

        textArea.setEditable(false);
        addSplitPane();
    }

    private void addSplitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(fileList),
                new JScrollPane(textArea));
        add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(150);
        
        goToDir(new File(System.getProperty("user.dir")));
    }

    private void goToDir (File file){
        File[] ff = file.listFiles();
        if (ff == null) {
            textArea.setText("Error reading file list of " + file.getAbsolutePath() + ".");
            return;
        }
        setTitle(file.getAbsolutePath());
        fileDir = ff;
        Arrays.sort(fileDir, MainFrame::compareFiles);
        File parent = file.getParentFile();
        if (parent != null) {
            File[] af = new File[fileDir.length+1];
            af[0] = parent;
            System.arraycopy(fileDir, 0, af,1, fileDir.length);
            fileDir = af;
        }
        String[] names = new String[fileDir.length];
        for (int i = 0; i < fileDir.length; i++) {
            names[i] = fileDir[i].getName();
        }
        if (parent != null)
            names[0] = "...";
        fileList.setListData(names);
        }

private static int compareFiles(File f1, File f2) {
    if(f1.isDirectory() && f2.isFile())
        return -1;
    if (f1.isFile() && f2.isDirectory())
        return 1;
    return f1.getName().compareTo(f2.getName());
    }

    private void onFiledListItemSelection() {
        int seldNdx = fileList.getSelectedIndex();
        if (seldNdx == -1)
            return;

        if(fileDir[seldNdx].isDirectory()) {
            textArea.setText("");
            return;
        }

        try (FileReader fr = new FileReader(fileDir[seldNdx])){
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[4096];
            int n;
            while ((n = fr.read(buf)) >= 0) {
                sb.append(buf, 0, n);
            }
            textArea.setText(sb.toString());
            textArea.setCaretPosition(0);
        } catch (IOException e) {
            textArea.setText("Error reading file: " + e.getMessage() + ".");
        }
    }

    protected void enterToDir() {
        int seldNdx = fileList.getSelectedIndex();
        if (seldNdx == -1)
            return;

        File f = fileDir[seldNdx];

        if (!f.isDirectory())
            return;
        goToDir(f);
    }
}
