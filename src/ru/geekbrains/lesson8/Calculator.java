package ru.geekbrains.lesson8;

import javax.swing.*;
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
    private double previousAction = 0;
    private JTextArea screen = new JTextArea ();
    private String expression = "";
    private String signAction = "";
    private double previousNumber = 0;
    private double result = 0;
    private String inputNumberTXT = "";
    private double inputNumber = 0;

    public Calculator()
    {
        GridLayout gridLayout = new GridLayout(5,4);
        JPanel panel = new JPanel(gridLayout);

        setWindow();

        screenSetting(screen);

        buttonSetting(panel);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    void buttonSetting(JPanel panel)
    {
        ActionListener buttonListener = new ButtonListener();
        JButton clear = new JButton("C");
        JButton division = new JButton("÷");
        JButton multiplication = new JButton("×");
        JButton delete = new JButton("⌫");
        JButton addition = new JButton("+");
        JButton subtraction = new JButton("-");
        JButton equal = new JButton("=");

        JButton[] btns = new JButton[17];
        btns[0] = clear;
        btns[1] = division;
        btns[2] = multiplication;
        btns[3] = delete;
        for (int i = 4; i < btns.length - 3; i++ )
        {
            btns[i] = new JButton("" + (i - 4));
        }
        btns[14] = addition;
        btns[15] = subtraction;
        btns[16] = equal;

        for(JButton button: btns)
        {
            button.addActionListener(buttonListener);
            panel.add(button);
        }
    }
    public class ButtonListener implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand())
            {
                case "C":
                    System.out.println("C is clicked");
                    expression = "";
                    signAction = "";
                    result = 0;
                    previousNumber = 0;
                    inputNumber = 0;
                    action = START;
                    break;
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
    public static String removeLastChar(String s)
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
        inputNumber = Integer.parseInt(inputNumberTXT);
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
    public void screenSetting(JTextArea screen )
    {
        screen.setLineWrap(true);
        screen.setPreferredSize(new Dimension(getWidth(),100));
        add(screen, BorderLayout.NORTH);
    }
    public void setWindow()
    {
        setTitle("Calculator");
        setBounds(500, 200, 400, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
