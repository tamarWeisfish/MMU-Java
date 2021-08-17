//package com.hit.view;
//
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeSupport;
//import java.io.*;
//import java.util.Scanner;
//
//
//public class Button  extends JPanel  implements ActionListener {
//    protected JButton b1, b2, b3;
//    JFrame frame;
//    private PropertyChangeSupport support;
//    public Button() {
//        super(new FlowLayout());
//        b1 = new JButton("load a Request");
//        b1.setVerticalTextPosition(AbstractButton.CENTER);
//        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
//        b1.setActionCommand("load");
//
//
//        b3 = new JButton("show statistics");
//        b3.setActionCommand("show");
//        b3.setEnabled(true);
//
//        ButtonActionListener buttonActionListener;
//        buttonActionListener = new ButtonActionListener();
//        //Listen for actions on buttons 1 and 3.
//        b1.addActionListener(this);
//        b1.addActionListener(buttonActionListener);
//        b3.addActionListener(this);
//        b3.addActionListener(buttonActionListener);
//
//        add(b1);
//        add(b3);
//        support = new PropertyChangeSupport(this);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println(e.getActionCommand());
//        if ("show".equals(e.getActionCommand())) {
//       // ???????????
//
//        } else {
//            System.out.println("loading request");
//            JFileChooser chooser = new JFileChooser();
//            FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                    "json files", "json");
//            chooser.setFileFilter(filter);
//            chooser.showOpenDialog(frame);
//            File file=chooser.getSelectedFile();
////            System.out.println(file.getAbsolutePath());
//            try {
//                Scanner data = new Scanner(new FileReader(file.getAbsolutePath()));
////                String datalin;
////                while (data.hasNextLine()) {
////                    datalin = data.nextLine();
////                    System.out.println(datalin);
////                }
//                support.firePropertyChange("json", null, data);
//
//            } catch (FileNotFoundException fileNotFoundException) {
//                fileNotFoundException.printStackTrace();
//            }
//
//
//
//        }
//    }
//
//    public class ButtonActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if ("disable".equals(e.getActionCommand())) {
//                System.out.println("Click on disable");
//            } else {
//                System.out.println("click on enable");
//            }
//        }
//    }
//}
