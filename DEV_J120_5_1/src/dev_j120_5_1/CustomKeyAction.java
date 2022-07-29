/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j120_5_1;

/**
 *
 * @author USER
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomKeyAction extends KeyAdapter {
    MainFrame frame;

    public CustomKeyAction(MainFrame frame){
        this.frame=frame;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER)
            frame.enterToDir();
    }
}
