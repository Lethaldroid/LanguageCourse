import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    JFrame frame;
    JPanel panel;
    JLabel username;
    JLabel password,space;
    TextField user;
    JPasswordField pass;
    JButton login;
    public String uname;
    public String pword;
    public GUI() {
        frame = new JFrame();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 150));
        username = new JLabel("Enter username: ",SwingConstants.CENTER);
        space = new JLabel("",SwingConstants.CENTER);
        password = new JLabel("Enter password: ",SwingConstants.CENTER);
        user = new TextField(15);
        pass = new JPasswordField(15);
        login = new JButton("LogIn");

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uname = user.getText();
                pword = pass.getText();
                ReadingDatabase read = new ReadingDatabase();
                if (read.authenticate(uname, pword)){
                    frame.dispose();
                    Languages l = new Languages();
                }
            }
        });
        panel.setLayout(new GridLayout(6,1));
        panel.add(username);
        panel.add(space);
        panel.add(user);
        panel.add(password);
        panel.add(pass);
        panel.add(space);
        panel.add(login);
        frame.add(panel,BorderLayout.CENTER);
        frame.setTitle("User Interface");
        frame.pack();
        frame.setVisible(true);
    }

}
