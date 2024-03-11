package second;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
    
public class admin_login extends JFrame implements ActionListener{
        JLabel lab;
        JLabel username;
        JLabel password;
        JButton fp;
        JButton exit;
        JButton submit;
        JTextField userfield;
        JTextField passfield;

        public admin_login(){
            setTitle("welcome");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            lab = new JLabel("Admin Log-in Page");
            lab.setBounds(250,0,150,50);
            lab.setForeground(Color.red);

            fp = new JButton("Forget Password ?");
            fp.setBounds(50,280,150,30);
            fp.setBackground(Color.white);
            fp.setForeground(Color.BLUE);
            fp.addActionListener(this);

            exit = new JButton("EXIT");
            exit.setBounds(290,200,100,40);
            exit.setBackground(Color.black);
            exit.setForeground(Color.white);
            exit.addActionListener(this);

            username = new JLabel("USERNAME");
            username.setBounds(150,100,80,30);

            password = new JLabel("PASSWORD");
            password.setBounds(150,150,100,30);

            submit = new JButton("SUBMIT");
            submit.setBounds(160,200,100,40);
            submit.setBackground(Color.black);
            submit.setForeground(Color.white);
            submit.addActionListener(this);

            userfield = new JTextField();
            userfield.setBounds(300,100,80,30);

            passfield = new JTextField();
            passfield.setBounds(300,150,80,30);
           
            add(username);
            add(exit);
            add(password);
            add(submit);
            add(userfield);
            add(passfield);
            add(lab);
            add(fp);
            setLayout(null);
            setSize(600,400);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent a){
            if(a.getSource() == submit) {
                try {
                    String url = "jdbc:mysql://localhost:3306//oss";
                    String username = "root";
                    String password = "Hvish@115";
                    Connection connection;
                    connection = DriverManager.getConnection(url, username, password);
                    String username1 = userfield.getText();
                    String password1 = passfield.getText();
                    String sql = "SELECT * FROM authen WHERE username = ? AND password = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, username1);
                    statement.setString(2, password1);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(admin_login.this,
                                "Login Successful",
                                " ",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new four();
                    } else {
                        JOptionPane.showMessageDialog(admin_login.this,
                                "Invalid credentials",
                                " ",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }if(a.getSource() == fp){
                this.dispose();
                new forgetPass();
            }if(a.getSource() == exit){
                this.dispose();
                new zero();
            }
        }
    }

