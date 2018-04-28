package Ld4_14_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

class GUI extends JFrame {

    private BinarySearchTree bt;


    GUI() {

        final String title = "Binary search tree";
        final int location = 300;
        final int width = 400;
        final int height = 150;

        JTextArea jTextArea = new JTextArea();
        JMenuBar menuBar = new JMenuBar();

        JMenu menu1 = new JMenu("Menu");
        JMenu menu2 = new JMenu("Edit");


        JMenuItem create = new JMenuItem("Create");
        JMenuItem close = new JMenuItem("Close");

        JMenuItem twoChildren = new JMenuItem("Peaks with two children");
        JMenuItem num = new JMenuItem("Count of paired elements");

        menu1.add(create);
        menu1.add(close);
        menu2.add(twoChildren);
        menu2.add(num);
        menuBar.add(menu1);
        menuBar.add(menu2);
        setJMenuBar(menuBar);

        jTextArea.setDisabledTextColor(Color.BLACK);
        jTextArea.setEnabled(false);
        add(jTextArea);

        setBounds(location, location, width, height);
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        create.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int size = Integer.parseInt(JOptionPane.showInputDialog("Size: "));

                if (size <= 0) {
                    JOptionPane.showMessageDialog(
                            null, "Izmeram jabut lielakam par 0",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (size > 15) {
                        JOptionPane.showMessageDialog(
                                null, "MaxSize: 15",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        bt = new BinarySearchTree(size);
                        int menu = JOptionPane.showConfirmDialog(null,
                                "Insert using random util", "Insert", JOptionPane.YES_NO_OPTION);
                        switch (menu) {
                            case 0:
                                Random rd = new Random();
                                jTextArea.setText("Elements: ");
                                while (!bt.isFull()) {
                                    int num = rd.nextInt(40) - 20;
                                    bt.add(num);
                                    jTextArea.append(String.valueOf(num) + " ");
                                }
                                jTextArea.append("\nTraversePostOrder: " + bt.postOrder());
                                break;
                            case 1:
                                String str = JOptionPane.showInputDialog(null,
                                        "Input " + size + " elements format El,El,...", "Insert",
                                        JOptionPane.QUESTION_MESSAGE);
                                try {
                                    String[] temp = str.split(",");
                                    if (temp.length != size)
                                        throw new IllegalStateException();
                                    jTextArea.setText("Elements: ");

                                    for (String aTemp : temp) {
                                        bt.add(Integer.parseInt(aTemp));
                                        jTextArea.append(aTemp + " ");
                                    }

                                    jTextArea.append("\nTraversePostOrder: " + bt.postOrder());

                                } catch (IllegalArgumentException il) {
                                    jTextArea.setText("");
                                    JOptionPane.showMessageDialog(null, "Wrong format",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                } catch (IllegalStateException is) {
                                    jTextArea.setText("");
                                    JOptionPane.showMessageDialog(null, "Wrong element count",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                        }
                    }
                }
            }
        });

        close.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        twoChildren.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bt == null) {
                    JOptionPane.showMessageDialog(null, "Tree is not created",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    jTextArea.append("\nNumber of peaks with two children: " +
                            bt.twoChildPerCount());
                }
            }
        });

        num.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bt == null) {
                    JOptionPane.showMessageDialog(null, "Tree is not created",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    jTextArea.append("\nNumber of paired elements: " +
                            bt.evenCount());
                }
            }
        });

    }
}
