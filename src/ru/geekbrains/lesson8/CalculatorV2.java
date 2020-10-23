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
    char[] buttonSymbols = {'(' ,')', 'c', 'C',
                            '7', '8', '9', 'd',
                            '4', '5', '6', '×',
                            '1', '2', '3', '-',
                            'm', '0', '.', '+'  };
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
        setBackground(Color.DARK_GRAY);
        setBounds(500, 200, 340, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
    }
    private void calculatorScreenSetting()
    {
        StyledDocument doc = screen.getStyledDocument();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), right, false);

        screen.setEditable(false);
        screen.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
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
            String buttonSym = e.getActionCommand();
            switch (e.getActionCommand())
            {
                case "(":
                    if(isScreenEmpty() || !isLastSymNum() && !isLastSym('.', ')', '|'))
                        expression += buttonSym;
                    break;
                case ")":
                    if(hasOpened('(', ')') && hasBracketsAction())
                    {
                        if(!isLastSymNum() && !isLastSym(')'))
                            removeLastChar();
                        expression += buttonSym;
                    }
                    break;
                case "C":
                    expression = "";
                    break;
                case "CE":
                    int indexRemoving = expression.indexOf(getLastNum());
                    expression = expression.substring(0, indexRemoving);
                    if(!isLastSymNum())
                        removeLastChar();
                    break;
                case "DEL":
                    removeLastChar();
                    break;
                case "||":
                    break;
                case ".":
                    dotClicked(buttonSym);
                    break;
                default:
                    if(isNumeric(buttonSym.charAt(0)))
                    {
                        if (!isLastSym(')'))
                        expression += buttonSym;
                    }
                    else if (!isScreenEmpty())
                    {
                        if(!isLastSymNum() && !isLastSym(')'))
                        {
                            removeLastChar();
                            if(!isLastSymNum())
                                removeLastChar();
                        }
                        expression += buttonSym;
                    }
                    break;
            }
            screen.setText(expression);
            info();
        }
    }
    private void dotClicked(String buttonSym)
    {
        if(!isScreenEmpty() && !hasLastNumberDot())
        {
            if(!isLastSymNum())
            {
                removeLastChar();
                if(!isLastSymNum())
                    removeLastChar();
            }
            expression += buttonSym;
        }
    }
    private boolean hasBracketsAction()
    {
        int actionIndex = expression.lastIndexOf(getLastNum()) - 1;
        char sym = expression.charAt(actionIndex);
        return sym != '(';
    }
    private boolean isScreenEmpty()
    {
        return expression.length() == 0;
    }
    private boolean isLastSym(char ... sym){
        if(!(expression == null) && !(expression.length() == 0))
        {
            for(char s: sym)
                if(getLastSym() == s)
                    return true;
        }
        return false;
    }
    private boolean isNumeric(char strNum)
    {
        try {
            Double.parseDouble(String.valueOf(strNum));
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    private void removeLastChar()
    {
        if(!(expression == null) && !isScreenEmpty())
            expression = expression.substring(0, expression.length() - 1);
    }
    private void info()
    {
        System.out.println
        (
            "\nExpression: " + expression
            + "\nIs last symbol numeric: " + isLastSymNum()
            + "\nHas last number dot: " + hasLastNumberDot()
            + "\nLast number: " + getLastNum()
            + "\nLast symbol: " + getLastSym()
            + "\nHas opened brackets: " + hasOpened('(', ')')
        );

    }
    private char getLastSym()
    {
        return (expression == null || isScreenEmpty())
                ? '='
                : expression.charAt(expression.length() - 1);
    }
    private String getLastNum()
    {
        if (isScreenEmpty() || expression.equals("("))
            return "";
        int index = expression.length() - 1;
        if(!isLastSymNum())
            index--;
        int i = index;
        do
        {
            if(!isNumeric(expression.charAt(i))
            && expression.charAt(i) != '.')
                return expression.substring(i + 1, index+1);
            if(i == 0)
                return expression.substring(i, index+1);
            i--;
        }
        while (true);
    }
    private boolean hasLastNumberDot()
    {
        return getLastNum().contains(".");
    }
    private boolean isLastSymNum()
    {
        return isNumeric(getLastSym());
    }
    private int countOf(char sym)
    {
        int count = 0;
        for (char element : expression.toCharArray())
            if (element == sym) count++;
        return count;
    }
    private boolean hasOpened(char leftSym, char rightSym)
    {
        return countOf(leftSym) > countOf(rightSym);
    }
}
