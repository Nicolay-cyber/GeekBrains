package ru.geekbrains.lesson8;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame
{
    private static final int START = 0;
    private static final int ADDITION = 1;
    private static final int SUBTRACTION = 2;
    private static final int MULTIPLICATION = 3;
    private static final int DIVISION = 4;
    private static final int SHOW_RESULT = 5;
    private int action = START;
    private int previousAction = 0;
    private JTextPane  screen = new JTextPane ();
    private String expression = "";
    private String signAction = "";
    private double previousNumber = 0;
    private double result = 0;
    private String inputNumberTXT = "";
    private double inputNumber = 0;

    public Calculator()
    {
        GridLayout gridLayout = new GridLayout(4,4);
        JPanel panel = new JPanel(gridLayout);

        setWindow();

        screenSetting();

        buttonSetting(panel);

        add(panel);
        setVisible(true);
    }

    private void buttonSetting(JPanel panel)
    {
        ActionListener buttonListener = new ButtonListener();
        JButton clear = new JButton("C");
        JButton division = new JButton("÷");
        JButton multiplication = new JButton("×");
        JButton delete = new JButton("⌫");
        JButton addition = new JButton("+");
        JButton subtraction = new JButton("-");
        JButton equal = new JButton("=");

        JButton[] btns = new JButton[16];
        btns[0] = clear;
        btns[1] = division;
        btns[2] = multiplication;
        //btns[3] = delete;
        for (int i = 3; i < btns.length - 3; i++ )
        {
            btns[i] = new JButton("" + (i - 3));
        }
        btns[13] = addition;
        btns[14] = subtraction;
        btns[15] = equal;

        for(JButton button: btns)
        {
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.LIGHT_GRAY);
            button.setFont(new Font("Calibri Light", Font.PLAIN, 25));
            button.addActionListener(buttonListener);
            panel.add(button);
        }
    }
    private class ButtonListener implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if ("C".equals(e.getActionCommand())) {
                System.out.println("C is clicked");
                expression = "";
                signAction = "";
                result = 0;
                previousNumber = 0;
                inputNumber = 0;
                action = START;
            }
            if(isNumeric(e.getActionCommand()))
            {
                System.out.println("is number");
                numberIsClicked(e);
            }
            else if(e.getActionCommand().equals("⌫"))
            {
                System.out.println("⌫");
                /*if(!signAction.equals(""))
                {
                    signAction = "";
                    inputNumber = previousNumber;
                }*/
            }
            else if(!expression.equals("") && signAction.equals(""))
            {
                System.out.println("is action");
                actionIsClicked(e);
            }
            screen.setText(expression + signAction);
            if (action == SHOW_RESULT)
            {
                expression = "";
                result = 0;
                previousNumber = 0;
                action = START;
            }
            System.out.println(expression + signAction);
            System.out.println("result: " + result);
            System.out.println("previous number: " + previousNumber);
            System.out.println("inputNumber: " + inputNumber);
            System.out.println("sign action: " + signAction);
            System.out.println();
        }
    }
    private static String removeLastChar(String s)
    {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }
    private static boolean isNumeric(String strNum)
    {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    private void numberIsClicked(ActionEvent e)
    {
        expression += signAction;
        signAction = "";
        expression += e.getActionCommand();
        inputNumberTXT += e.getActionCommand();
        inputNumber = Integer.parseInt(inputNumberTXT.trim());
        if(action == START)
        {
            result = inputNumber;
        }
    }
    private void actionIsClicked(ActionEvent e)
    {

        calculate();
        inputNumberTXT = "";
        previousNumber = inputNumber;
        //inputNumber = 0;

        switch (e.getActionCommand())
        {
            case "+":
                action = ADDITION;
                signAction = " + ";
                break;
            case "-":
                action = SUBTRACTION;
                signAction = " - ";
                break;
            case "×":
                previousAction = action;
                action = MULTIPLICATION;
                signAction = " × ";
                break;
            case "÷":
                previousAction = action;
                action = DIVISION;
                signAction = " / ";
                break;

            case "=":
                if(signAction.equals("") && action != START) {
                    expression += " = " + result;
                    action = SHOW_RESULT;
                    break;
                }
        }

    }
    private void screenSetting()
    {
        screen.setAutoscrolls(true);
        screen.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
        StyledDocument doc = screen.getStyledDocument();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), right, false);
        screen.setEditable(false);

        screen.setFont(new Font("Calibri Light", Font.PLAIN, 30));
        screen.setBackground(Color.getHSBColor(0,0,0.9f));
        screen.setPreferredSize(new Dimension(getWidth(),250));
        add(screen, BorderLayout.NORTH);
    }
    private void setWindow()
    {
        setTitle("Calculator");

        setBackground(Color.DARK_GRAY);
        setBounds(500, 200, 340, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
    private void correctionOfResult()
    {
        if (previousAction == ADDITION)
        {
            result -= previousNumber;
            result += inputNumber;
        }
        if (previousAction == SUBTRACTION)
        {
            result += previousNumber;
            result -= inputNumber;
        }
        if ( previousAction == START)
        {
            result = inputNumber;
        }
        previousAction = START;

    }
    private void calculate()
    {
        switch (action)
        {
            case ADDITION:
                result += inputNumber;
                break;
            case SUBTRACTION:
                result -= inputNumber;
                break;
            case MULTIPLICATION:
                inputNumber *= previousNumber;
                correctionOfResult();
                break;
            case DIVISION:
                inputNumber = previousNumber/inputNumber;
                correctionOfResult();
                break;
        }
    }
}
