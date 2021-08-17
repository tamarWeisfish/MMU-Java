package com.hit.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.Scanner;
/* Manages the graphical user interface sends the requests to CacheUnitClientObserver,
and receives input to display to the user*/
public class CacheUnitView extends java.lang.Object{
    JTextArea area;
    JFrame frame;
    JButton b1;
    JButton b2;
    Button button;
    private PropertyChangeSupport support;

    public  CacheUnitView(){
        frame = new JFrame("Cach unit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button=new Button();
        area=new JTextArea("Welcome to Cache unit");
        frame.add(button);
        frame.add(area);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setSize(400,400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(false);
       support = new PropertyChangeSupport(this);

    }
    public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }
    public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }
    public  void  start(){
        frame.setVisible(true);
    }

    public <T>void updateUIData(T t){
        System.out.println("updateUIData updateUIData updateUIData");
        area.setText((String) t);
    }

    public class Button  extends JPanel  implements ActionListener {
        protected JButton b1, b2, b3;
        public Button() {
            super(new FlowLayout());
            b1 = new JButton("load a Request");
            b1.setVerticalTextPosition(AbstractButton.CENTER);
            b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
            b1.setActionCommand("load");


            b3 = new JButton("show statistics");
            b3.setActionCommand("show");
            b3.setEnabled(true);
            b1.addActionListener(this);
            b3.addActionListener(this);

            add(b1);
            add(b3);
            support = new PropertyChangeSupport(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if ("show".equals(e.getActionCommand())) {
                support.firePropertyChange("request", null, "show statistics");
            } else {
                System.out.println("loading request");
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "json files", "json");
                chooser.setFileFilter(filter);
                chooser.showOpenDialog(frame);
                File file=chooser.getSelectedFile();
                try {
                    Scanner data = new Scanner(new FileReader(file.getAbsolutePath()));

                    support.firePropertyChange("request", null, data);

                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }catch (Exception exception){
                    System.out.println("File loading exception");
                }



            }
        }

    }
}







