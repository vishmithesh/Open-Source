import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java_project extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = "";
    private double result = 0;
    private String operator = "";
    private boolean startNewInput = true;

    public java_project() {
        setTitle("Java Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField(''0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9\\.]")) {
            if (startNewInput) {
                currentInput = cmd;
                startNewInput = false;
            } else {
                currentInput += cmd;
            }
            display.setText(currentInput);
        } else if (cmd.matches("[\\+\\-\\*/]")) {
            calculate();
            operator = cmd;
            startNewInput = true;
        } else if (cmd.equals("=")) {
            calculate();
            operator = "";
            display.setText("" + result);
            startNewInput = true;
        } else if (cmd.equals("C")) {
            currentInput = "";
            operator = "";
            result = 0;
            display.setText("0");
            startNewInput = true;
        }
    }

    private void calculate() {
        double number = currentInput.isEmpty() ? 0 : Double.parseDouble(currentInput);
        switch (operator) {
            case "+": result += number; break;
            case "-": result -= number; break;
            case "*": result *= number; break;
            case "/":
                if (number != 0) {
                    result /= number;
                } else {
                    display.setText("Error");
                    result = 0;
                }
                break;
            default: result = number; break;
        }
        currentInput = "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new java_project().setVisible(true);
        });
    }
}

