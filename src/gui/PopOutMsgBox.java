package gui;

import javax.swing.JOptionPane;

public class PopOutMsgBox {
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }


}

