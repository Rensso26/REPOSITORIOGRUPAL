package View;

import Actions.Contaible;
import Controlers.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {

    private JTextField usernameField;
    private JButton startButton;
    private JButton createButton;

    public LoginWindow() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        startButton = new JButton("Start");
        createButton = new JButton("Crear");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                dispose(); // Close the login window
                startBackground(username); // Start the background with the entered username
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                dispose(); // Close the login window
                crearUsuario(username); // Call the method to create a user
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(startButton);
        panel.add(createButton);

        add(panel);
        setVisible(true);
    }

    private void crearUsuario(String username) {
        Container container = new Container();
        container.CreateHero(username);
       BackGround background = new BackGround(username);
    }
    private void startBackground(String username) {
        BackGround background = new BackGround(username); // Pass the username to the BackGround constructor
    }

}
