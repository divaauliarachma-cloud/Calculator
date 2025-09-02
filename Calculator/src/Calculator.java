import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;
    
public class Calculator {
  int boardwidth = 350;
  int boardheight = 530;

  Color customLightGrey = new Color(212, 212, 210);
  Color customDarkGrey = new Color(82, 81, 80);
  Color customBlack = new Color(28, 28, 28);
  Color customOrange = new Color(255, 149, 0);

  String[] buttonValues ={
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0",".", "√", "="  
    };

  String[] rightSymbol = {"÷","×", "-", "+", "="};
  String[] topSymbols = {"AC","+/-", "%"};


  JFrame frame = new JFrame("Calculator");
  JLabel displayLabel = new JLabel();
  JPanel displayPanel = new JPanel();
  JPanel buttonPanel = new JPanel();
  
  //A+B, A-B, A/B, A*B
  String A = "0";
  String operator = null;
  String B = null;
    
  Calculator() {
    //frame.setVisible(true);
    frame.setSize(boardwidth, boardheight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    displayLabel.setBackground(customBlack);
    displayLabel.setForeground(Color.white);
    displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
    displayLabel.setHorizontalAlignment(JLabel.RIGHT);
    displayLabel.setText("0");
    displayLabel.setOpaque(true);

    displayPanel.setLayout(new BorderLayout());
    displayPanel.add(displayLabel);
    frame.add(displayPanel, BorderLayout.NORTH);

    buttonPanel.setLayout(new GridLayout(5, 4));
    buttonPanel.setBackground(customBlack);
    frame.add(buttonPanel);

        for (int i=0;i<buttonValues.length;i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));

            if(Arrays.asList(topSymbols).contains(buttonValue)){
              button.setBackground(customLightGrey);
              button.setForeground(customBlack);
            }
            else if (Arrays.asList(rightSymbol).contains(buttonValue)){
              button.setBackground(customOrange);
              button.setForeground(Color.white);
            }
            else{
              button.setBackground(customDarkGrey);
              button.setForeground(Color.white);
            }

            buttonPanel.add(button);
            
            button.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                JButton button = (JButton) e.getSource();
                String buttonValue = button.getText();
                  if (Arrays.asList(rightSymbol).contains(buttonValue)) {
                    if (buttonValue == "=") {
                      if (A != null) {
                          B = displayLabel.getText();
                          double numbA = Double.parseDouble(A);
                          double numbB = Double.parseDouble(B);

                          if (operator == "+") {
                              displayLabel.setText(removeZeroDecimal(numbA+numbB));
                          }
                          else if (operator  == "-") {
                              displayLabel.setText(removeZeroDecimal(numbA-numbB));
                          }
                          else if (operator == "×") {
                              displayLabel.setText(removeZeroDecimal(numbA*numbB));
                          }
                          else if (operator == "÷") {
                              displayLabel.setText(removeZeroDecimal(numbA/numbB));
                          }
                          clearAll();
                        }
                      }
                      else if ("+-×÷".contains(buttonValue)) {
                      if (operator == null) {
                        A = displayLabel.getText();
                        displayLabel.setText("0");
                        B = "0";
                      }
                      operator = buttonValue;
                    }
                  }
                  
                  else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                    if (buttonValue == "AC") {
                      clearAll();
                      displayLabel.setText("0");
                    }
                    else if (buttonValue == "+/-") {
                      double numbisplay = Double.parseDouble(displayLabel.getText());
                      numbisplay *= -1;
                      displayLabel.setText(removeZeroDecimal(numbisplay));
                    }
                    else if (buttonValue == "%") {
                      double numbisplay = Double.parseDouble(displayLabel.getText());
                      numbisplay /= 100;
                      displayLabel.setText(removeZeroDecimal(numbisplay));
                    }      
                  }
                else{ //digit or .
                  if (buttonValue == ".") {
                    if (!displayLabel.getText().contains(buttonValue)) {
                      displayLabel.setText(displayLabel.getText() + buttonValue);
                    }
                  }
                  else if ("0123456789".contains(buttonValue)) {
                    if (displayLabel.getText() == "0") {
                        displayLabel.setText(buttonValue);
                    }
                    else{
                      displayLabel.setText(displayLabel.getText() + buttonValue);
                    }
                  }
                }
              }
            });
          frame.setVisible(true);
        }
    }
    void clearAll(){
      A = "0";
      operator = null;
      B = null;
    }

    String removeZeroDecimal(double numbisplay){
      if (numbisplay % 1 == 0) {
        return Integer.toString((int) numbisplay);
      }
      return Double.toString(numbisplay);
  }     
}
