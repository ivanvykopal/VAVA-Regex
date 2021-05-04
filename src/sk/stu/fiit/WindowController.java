/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan Vykopal
 */
public final class WindowController {

    private final Window window;
    
    public WindowController(Window window) {
        this.window = window;
        
        window.setVisible(true);
        
        initController();
    }
    
    void initController() {
        this.window.getBtnCheck().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
               checkRegex();
            }
        });
        
        this.window.getBtnCheetsheet().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
               new CheetSheetWindow().setVisible(true);
            }
        });
    }
    
    
    void checkRegex() {
        String regex = window.getTfRegex();
        String text = window.getTaString();
        try {
            Pattern p;
            if (window.getChbCase().isSelected()) {
                p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            } else {
                p = Pattern.compile(regex);
            }
            Matcher m = p.matcher(text);

            if (m.find()) {
                JOptionPane.showMessageDialog(window, "OK");
            } else {
                JOptionPane.showMessageDialog(window, "NG");
            }
        } catch (PatternSyntaxException e) {
            JOptionPane.showMessageDialog(window, "Synktatikcá chyba regexu!");
        }
    }
    
}
