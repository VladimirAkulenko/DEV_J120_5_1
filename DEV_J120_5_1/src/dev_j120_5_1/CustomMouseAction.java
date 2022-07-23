/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev_j120_5_1;

/**
 *
 * @author USER
 */
import java.awt.event.*;


public class CustomMouseAction extends MouseAdapter {
    MainFrame frame;

    public CustomMouseAction(MainFrame frame){
        this.frame=frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
            frame.enterToDir();
        }
    }
}
