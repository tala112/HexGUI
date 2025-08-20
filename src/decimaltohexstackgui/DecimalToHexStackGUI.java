package decimaltohexstackgui;

// Tala Fahed
// 12219511

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Node {
    char data;
    Node next;

    public Node(char data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    private Node top;

    public Stack() {
        top = null;
    }

    public void push(char data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public char pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        char temp = top.data;
        top = top.next;
        return temp;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class DecimalToHexStackGUI extends JFrame {

    private JTextField inputField;
    private JButton convertButton;
    private JLabel resultLabel,enter;

    public DecimalToHexStackGUI() {
        setTitle("Decimal to Hex Converter - Stack");
        setSize(500, 200);
        setLocation(800,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        inputField = new JTextField();
        convertButton = new JButton("Convert to Hex");
        resultLabel = new JLabel("Enter a decimal number above.");
        enter=new JLabel("Enter Decimal Number:");
        add(inputField);
        inputField.setBounds(300,10,150,40);
        add(convertButton);
        convertButton.setBounds(300, 100,150,40);
        add(resultLabel);
        resultLabel.setBounds(60, 60,400,40);
        add(enter);
        enter.setBounds(60, 10,200,40);
        
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = inputField.getText().trim();
                    int decimalNumber = Integer.parseInt(inputText);

                    if (decimalNumber < 0) {
                        throw new NumberFormatException("Negative numbers not allowed.");
                    }

                    String hex = convertToHex(decimalNumber);
                    resultLabel.setText("Hexadecimal: " + hex);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input . Please enter a positive integer.");
                    
                } catch (Exception ex) {
                    resultLabel.setText(" Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static String convertToHex(int number) {
        Stack stack = new Stack();
        String hexChars = "0123456789ABCDEF";

        if (number == 0) {
            return "0";
        }

        StringBuilder hexString = new StringBuilder();

        while (number > 0) {
            int remainder = number % 16;
            stack.push(hexChars.charAt(remainder));
            number = number / 16;
        }

        while (!stack.isEmpty()) {
            hexString.append(stack.pop());
        }

        return hexString.toString();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DecimalToHexStackGUI();
            }
        });
    }
}
