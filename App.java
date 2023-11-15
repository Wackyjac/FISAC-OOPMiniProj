import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Enumeration;



public class App {

    private JFrame frame;

    private String[] correctAnswers = {"Paris", "Python", "Pacific Ocean", "William Shakespeare","Mars"};

    private int score = 0;



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new AdmissionTestAppSwing().createAndShowGUI());

    }



    private void createAndShowGUI() {

        frame = new JFrame("Admission Test");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        showLoginScreen();



        frame.setSize(400, 300);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }



    private void showLoginScreen() {

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JTextField usernameField = new JTextField();

        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");



        loginButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                validateLogin(usernameField.getText(), new String(passwordField.getPassword()));

            }

        });



        loginPanel.add(new JLabel("Username:"));

        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Password:"));

        loginPanel.add(passwordField);

        loginPanel.add(new JLabel()); // Empty label for spacing

        loginPanel.add(loginButton);



        frame.getContentPane().removeAll();

        frame.getContentPane().add(loginPanel, BorderLayout.CENTER);

        frame.revalidate();

        frame.repaint();

    }



    private void showMCQScreen() {

        frame.dispose(); // Dispose the current frame before creating a new one



        frame = new JFrame("Admission Test");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JPanel mcqPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        JRadioButton[] options = new JRadioButton[4];

        ButtonGroup[] toggleGroups = new ButtonGroup[5];



        String[] customQuestions = {

                "What is the capital of France?",

                "Which programming language is known for its simplicity?",

                "What is the largest ocean on Earth?",

                "Who is the author of Romeo and Juliet?",

                "Which planet is known as the Red Planet?"

        };



        String[][] customOptions = {

                {"Paris", "Berlin", "London", "Madrid"},

                {"Java", "C++", "Python", "Ruby"},

                {"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"},

                {"William Shakespeare", "Jane Austen", "Charles Dickens", "Mark Twain"},

                {"Mars", "Venus", "Jupiter", "Mercury"}

        };



        for (int i = 0; i < 5; i++) {

            JLabel questionLabel = new JLabel("Question " + (i + 1) + ": " + customQuestions[i]);

            options[0] = new JRadioButton(customOptions[i][0]);

            options[1] = new JRadioButton(customOptions[i][1]);

            options[2] = new JRadioButton(customOptions[i][2]);

            options[3] = new JRadioButton(customOptions[i][3]);



            toggleGroups[i] = new ButtonGroup();

            for (int j = 0; j < 4; j++) {

                toggleGroups[i].add(options[j]);

            }



            mcqPanel.add(questionLabel);

            for (int j = 0; j < 4; j++) {

                mcqPanel.add(options[j]);

            }

        }



        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                calculateScore(toggleGroups);

            }

        });



        mcqPanel.add(submitButton);



        frame.getContentPane().removeAll();

        frame.getContentPane().add(mcqPanel, BorderLayout.CENTER);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen

        frame.setUndecorated(true); // Remove window decorations for fullscreen

        frame.setVisible(true);

    }







    private void validateLogin(String username, String password) {

        // Implement your authentication logic here

        // For simplicity, let's assume username and password are both "admin"

        if (username.equals("admin") && password.equals("admin")) {

            showMCQScreen();

        } else {

            JOptionPane.showMessageDialog(frame, "Invalid login credentials. Please try again.");

        }

    }



    private void calculateScore(ButtonGroup[] toggleGroups) {

        score = 0; // Reset the score before recalculating



        for (int i = 0; i < 5; i++) {

            Enumeration<AbstractButton> buttons = toggleGroups[i].getElements();

            while (buttons.hasMoreElements()) {

                JRadioButton button = (JRadioButton) buttons.nextElement();

                if (button.isSelected() && button.getText().equals(correctAnswers[i])) {

                    score++;

                }

            }

        }



        // Display the result

        JOptionPane.showMessageDialog(frame, "Your total score is: " + score+"/5");

    }



}
