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
    private final char MULT = '×';
    private final char DIV = '÷';

    private final JTextPane  screen = new JTextPane ();
    char[] buttonSymbols = {'c' ,'C', 'd', DIV,
                            '7', '8', '9', MULT,
                            '4', '5', '6', '+',
                            '1', '2', '3', '-',
                            '(', '0', ')', '.'  };
    private String scnEqs = ""; //scnEqs - screen equation
    private String insEqs = ""; //scnEqs - inside equation
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
                    button.setText("00");
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
                    if(!isLastSymNum(scnEqs) && !isLastSym(scnEqs,'.', ')'))
                        scnEqs += buttonSym;
                    break;
                case ")":
                    if(hasOpened(scnEqs,'(', ')') && firstActionOF(scnEqs) != '?')
                    {
                        removeLastNotNum();
                        scnEqs += buttonSym;
                    }
                    break;
                case "C":
                    scnEqs = "";
                    break;
                case "CE":
                    int indexRemoving = scnEqs.lastIndexOf(lastStrNumOF(scnEqs));
                    scnEqs = scnEqs.substring(0, indexRemoving);
                    break;
                case "DEL":
                    removeLastChar();
                    break;
                case ".":
                    dotClicked(buttonSym);
                    break;
                default:
                    if(isNumeric(buttonSym.charAt(0)))
                    {
                        if(isLastSym(scnEqs,'0') && !hasLastNumberDot(scnEqs))
                            removeLastChar();
                        if (!isLastSym(scnEqs,')'))
                        scnEqs += buttonSym;
                    }
                    else if (hasNum(scnEqs))
                    {
                        removeLastNotNum();
                        scnEqs += buttonSym;
                    }
                    break;
            }
            createInsideEquation();
            screen.setText(scnEqs + "\n" + calc());
            info(buttonSym);
        }
    }
    private boolean isEmpty(String s)
{
    return s.length() == 0;
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
    private boolean hasNum(String s)
{
    return !lastStrNumOF(s).equals("");
}
    private char lastSymOf(String s)
    {
        return (s == null || isEmpty(s))
                ? '?'
                : s.charAt(s.length() - 1);
    }
    private String firstStrNumOf(String s)
    {
        int idLastSym = 0;
        //searching of the index last digit of the number
        for(int i = 0; i < s.length(); i++)
        {
            if(isNumeric(s.charAt(i)))
            {
                idLastSym = i;
                break;
            }
        }
        for(int i = idLastSym; i < s.length(); i++)
        {
            if (!isNumeric(s.charAt(i)) && s.charAt(i) != '.')
                return s.substring(idLastSym, i);
        }
        return s.substring(idLastSym);
    }
    private double firstNumOf(String s)
    {
        try{
            return Double.parseDouble(firstStrNumOf(s));
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    private String lastStrNumOF(String s)
    {
        int id = idLastNumOF(s);
        if(id == -1)
            return "";
        for(int i = id; i != -1; i--)
        {
            if(!isNumeric(s.charAt(i)) && s.charAt(i) != '.')
                return s.substring(i + 1, id + 1);
        }
        return s.substring(0, id + 1);

    }
    private double lastNumOF(String s)
    {
        try{
            return Double.parseDouble(lastStrNumOF(s));
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    private int idLastNumOF(String s)
    {
        for (int i = s.length() - 1; i != -1; i--)
        {
            if(isNumeric(s.charAt(i)))
                return i;
        }
        return -1;
    }
    private boolean isLastSym(String str, char ... sym){
        if(!(scnEqs == null) && !isEmpty(str))
        {
            for(char s: sym)
                if(lastSymOf(str) == s)
                    return true;
        }
        return false;
    }
    private  void createInsideEquation()
    {
        insEqs = scnEqs;
        while (hasOpened(insEqs,'(',')')) {
            insEqs += ")";
        }
        while (insEqs.contains("()")) {
            insEqs = insEqs.replace("()","");
        }
    }
    private void dotClicked(String buttonSym)
    {
        if(hasNum(scnEqs) && !hasLastNumberDot(scnEqs) && !isLastSym(scnEqs,')'))
        {
            removeLastNotNum();
            scnEqs += buttonSym;
        }
    }
    private void removeLastChar()
    {
        if(!(scnEqs == null) && !isEmpty(scnEqs))
            scnEqs = scnEqs.substring(0, scnEqs.length() - 1);
    }
    private void info(String buttonSym)
    {
        System.out.println
        ( "\n" + buttonSym + " is clicked"
            +"\nScreen equation: " + scnEqs
            + "\nLast number: " + lastNumOF(scnEqs)
            + "\nLast symbol: " + lastSymOf(scnEqs)
            + "\nHas opened brackets: " + hasOpened(scnEqs,'(', ')')
//            + "\nResult: " + calc(insEqs)
            + "\nFirst num: " + firstNumOf(scnEqs)
            + "\nId first action: " + idFirstActionOf(scnEqs)
            + "\nFirst action: " + firstActionOF(scnEqs)
            + "\nLast action: " + lastActionOf(scnEqs)
        );
    }
    private boolean hasLastNumberDot(String s)
    {
        return lastStrNumOF(s).contains(".");
    }
    private boolean isLastSymNum(String s)
    {
        return isNumeric(lastSymOf(s));
    }
    private int countOf(String s, char... sym)
    {
        int count = 0;
        for(char c: sym){
            for (char element : s.toCharArray())
                if (element == c) count++;
        }
        return count;
    }
    private boolean hasOpened(String s, char leftSym, char rightSym)
    {
        return countOf(s,leftSym) > countOf(s,rightSym);
    }
    private void removeLastNotNum()
    {
        if(!isLastSymNum(scnEqs) && !isLastSym(scnEqs,')'))
        {
            int indexLastNum = scnEqs.lastIndexOf(lastStrNumOF(scnEqs)) + lastStrNumOF(scnEqs).length() - 1;
            int indexLastCloseBracket = scnEqs.lastIndexOf(')');
            if(indexLastCloseBracket > indexLastNum)
                indexLastNum = indexLastCloseBracket;
            scnEqs = scnEqs.substring(0, indexLastNum + 1);
        }
    }
    private String calc()
    {
        if(countOf(insEqs,'(') > 0){
            while (countOf(insEqs,'(') > 0)
            {
                System.out.println("insEqs "+ insEqs);
                int idRightBracket = insEqs.indexOf(")");
                System.out.println("idRightBracket " + idRightBracket);
                int idLeftBracket = (insEqs.substring(0,idRightBracket)).lastIndexOf("(");
                String BracketEqs = insEqs.substring(idLeftBracket + 1, idRightBracket);
                String oldStr = insEqs.substring(idLeftBracket, idRightBracket + 1);
                System.out.println("oldStr" + oldStr);
                String newStr = String.valueOf(calcBrackets(BracketEqs));
                System.out.println("newStr" + newStr);
                if(newStr.equals("null"))
                    return "Error";
                insEqs = insEqs.replace(oldStr, newStr);
                System.out.println(insEqs);
            }
        }
            insEqs = String.valueOf(calcBrackets(insEqs));
        if(insEqs.equals("null"))
            return "∞";
        return insEqs;
    }
    private boolean isNumNeg(String s)
    {
        firstNumOf(s);
        return false;
    }
    private char lastActionOf (String s)
    {
        char[] str = s.toCharArray();
        for(int i = s.length() - 1; i != 0; i--)
            if(str[i] == '+' || str[i] == '-' || str[i]  == DIV || str[i]  == MULT)
                return str[i] ;
        return '?';

    }
    private Number calcBrackets(String s)
    {
        if(firstActionOF(s) != '?')
        {
            while(firstActionOF(s) != '?' || s.indexOf(String.valueOf(lastActionOf(s))) > 0)
            {
                double res = 0;
                int idFirstAction = idFirstActionOf(s);
                if(s.indexOf(MULT) > s.indexOf(DIV))
                    idFirstAction = s.indexOf(MULT);
                if(s.indexOf(MULT) < s.indexOf(DIV))
                    idFirstAction = s.indexOf(DIV);
                char act = s.charAt(idFirstAction);
                double firstNum = lastNumOF(s.substring(0,idFirstAction));
                double secondNum = firstNumOf(s.substring(idFirstAction));
                switch (act)
                {
                    case '+':
                        res = firstNum + secondNum;
                        break;
                    case '-':
                        res = firstNum - secondNum;
                        break;
                    case MULT:
                        res = firstNum * secondNum;
                        break;
                    case DIV:
                        res = firstNum / secondNum;
                        break;
                }
                System.out.println(String.valueOf(firstNum) + String.valueOf(act) + String.valueOf(secondNum) + " = " + res);
                int beginningOldStr = idFirstAction - lastStrNumOF(s.substring(0,idFirstAction)).length();
                int endOldSrt = idFirstAction + firstStrNumOf(s.substring(idFirstAction + 1)).length();
                String oldStr = s.substring(beginningOldStr, endOldSrt + 1);
                s = s.replace(oldStr, String.valueOf(res));
                if (s.contains("Infinity"))
                    return null;
            }
            System.out.println(s);
            return Double.parseDouble(s);
        }
        return firstNumOf(s);
    }
/*    private String getRes()
    {
        StringBuilder equation = new StringBuilder(scnEqs);
        int bracketCount = countOf('(');
        for(int i = 0; i < countOf('(') - countOf(')'); i++)
        {
            equation.append(")");
        }
        for(int i = 0; i < bracketCount; i++)
        {
            int startIndex = equation.lastIndexOf("(");
            int endIndex = equation.indexOf(")");
            String bracketEquation = equation.substring(startIndex + 1, endIndex);
            equation.replace(startIndex, endIndex, calculate(bracketEquation));
        }
        return calculate(String.valueOf(equation));
    }*/
/*
    private String calculate(String s)
    {
        double res = 0;
        do
        {
            switch (getFirstAction(s))
            {
                case '+':
                    res = firstNumOf(s) + getSecondNum(s);
                    break;
                case '-':
                    res = firstNumOf(s) - (getSecondNum(s));
                    break;
                case '÷':
                    res = toDouble(firstNumOf(s)) / toDouble(getSecondNum(s));
                    break;
                case '×':
                    res = toDouble(firstNumOf(s)) * toDouble(getSecondNum(s));
                    break;
            }

            int lastIndexSecondNum = s.indexOf(getSecondNum(s),s.indexOf(getFirstAction(s))) + getSecondNum(s).length();
            String oldStr = s.substring(0, lastIndexSecondNum);
            s = s.replace(oldStr,String.valueOf(res));
        }
        while (getFirstAction(s) != '?');
        return String.valueOf(res);
    }
*/

    private char firstActionOF(String s)
    {
        for(char sym: s.toCharArray())
            if(sym == '+' || sym == '-' || sym == DIV || sym == MULT)
                return sym;
        return '?';
    }
    private int idFirstActionOf(String s)
    {
        for(char sym: s.toCharArray())
            if(sym == '+' || sym == '-' || sym == DIV || sym == MULT)
                return s.indexOf(sym);
        return -1 ;
    }
/*
    private String getSecondNum(String s)
    {
        if(getFirstAction(s) == '?' || !isNumeric(s.charAt(s.length() - 1)))
            return "0";
        String shortEquation = s.substring(s.indexOf(getFirstAction(s)));
        return getFirstNum(shortEquation);
    }
    private double toDouble(String strNum)
    {
        return Double.parseDouble(strNum);
    }
*/
}
