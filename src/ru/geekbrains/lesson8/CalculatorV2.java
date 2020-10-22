package ru.geekbrains.lesson8;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorV2 extends JFrame
{
    private JTextPane  screen = new JTextPane ();
    char[] buttonSymbols = {'(' ,'C', 'c', 'd',
                            '7', '8', '9', '×',
                            '4', '5', '6', '-',
                            '1', '2', '3', '+',
                            'm', '0', '.', '='  };
    private String expression = "";



    public CalculatorV2()
    {
        setGUI();

        setVisible(true);
    }

    private void setGUI()
    {
        windowSetting();
        calculatorScreenSetting();
        calculatorKeyboardSetting();
    }

    private void windowSetting()
    {
        setBounds(500, 200, 340, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    private void calculatorScreenSetting()
    {
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
    private void calculatorKeyboardSetting()
    {
        ActionListener buttonListener = new ButtonListener();
        JPanel keyBoard = new JPanel(new GridLayout(5,4));
        for(char buttonSymbols: buttonSymbols)
        {
            JButton button = new JButton();

            switch (buttonSymbols){
                case 'd':
                    button.setText("DEL");
                    break;
                case 'c':
                    button.setText("CE");
                    break;
                case 'm':
                    button.setText("||");
                    break;
                case '(':
                    button.setText("( )");
                    break;
                default:
                    button.setText(String.valueOf(buttonSymbols));
                    break;
            }
            button.setBackground(Color.DARK_GRAY);
            button.setForeground(Color.LIGHT_GRAY);
            button.setFont(new Font("Calibri Light", Font.PLAIN, 25));
            button.addActionListener(buttonListener);
            keyBoard.add(button);

        }
        add(keyBoard, BorderLayout.CENTER);
    }
    private class ButtonListener implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand())
            {
                case "( )":
                    break;
                case "C":
                    expression = "";
                    break;
                case "CE":
                    break;
                case "DEL":
                    expression = removeLastChar(expression);
                    break;
                case "||":
                    break;
                case "=":
                    break;
                case ".":
                    if(!expression.equals("")&& !hasLastNumberDot())
                    {
                        if (!isNumeric(getLastSymbol()))
                            expression = removeLastChar(expression);
                        expression += e.getActionCommand();
                    }
                    break;
                default:
                    if(isNumeric(e.getActionCommand()))
                    {
                        expression += e.getActionCommand();
                    }
                    else if (!expression.equals(""))
                    {
                        if(!isNumeric(getLastSymbol()))
                            expression = removeLastChar(expression);
                        expression += e.getActionCommand();
                    }
                    break;
            }
            screen.setText(expression);
            info();
        }
    }
    private boolean isNumeric(String strNum)
    {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    private boolean isCharNumeric(char symbol)
    {
        String symb = String.valueOf(symbol);
        return isNumeric(symb);
    }
    private String removeLastChar(String s)
    {
        if(!(s == null) && !(s.length() == 0))
            return s.substring(0, s.length() - 1);
        return "";
    }
    private void info()
    {
        System.out.println
        (
            "\nExpression: " + expression
            + "\nIs last symbol numeric: " + isNumeric(getLastSymbol())
            + "\nHas number dot: " + hasLastNumberDot()
            + "\nLast number: " + getLastNumber()
            + "\nLast symbol: " + getLastSymbol()
//            + "\nHas Previous Number Dot: "
        );

    }
    private boolean hasPreviousNumberDot()
    {
        int dotIndex = expression.lastIndexOf(".");
        char symbol = 0;
        int index = expression.length() - 2;
        do {
            symbol = expression.charAt(index);
            if(!isCharNumeric(symbol) && index == dotIndex)
                return true;
            if(!isCharNumeric(symbol) && index > dotIndex)
                return false;
            if(index <= 0)
                return false;
            index--;
        }
        while (true);
    }
    private String getLastSymbol()
    {
        return (expression == null || expression.length() == 0)
                ? null
                : String.valueOf(expression.charAt(expression.length() - 1));

    }
    private String getLastNumber()
    {

        if (expression.length() == 0)
            return "";
        int index = expression.length() - 1;
        if(!isNumeric(getLastSymbol()))
            index--;
        int i = index;
        do
        {
            if(i == 0 || !isCharNumeric(expression.charAt(i - 1)) && expression .charAt(i - 1) != '.'){
                return expression.substring(i, index + 1);
            }
            i--;
        }
            while (true);
    }
    private boolean hasLastNumberDot()
    {
        return getLastNumber().contains(".");
    }
}
