import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Languages {
    JFrame frame;
    JPanel panel;
    JLabel select,space;
    String[] lang = {"Spanish"};
    JComboBox<String> language;
    JButton next;
    public Languages() {
        frame = new JFrame();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 150));
        language = new JComboBox<>(lang);
        select = new JLabel("Select Language: ",SwingConstants.CENTER);
        space = new JLabel("",SwingConstants.CENTER);
        next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Selection s = new Selection();
            }
        });
        panel.setLayout(new GridLayout(4,1));
        panel.add(select);
        panel.add(space);
        panel.add(language);
        panel.add(space);
        panel.add(next);
        frame.add(panel);
        frame.setTitle("Languages");
        frame.pack();
        frame.setVisible(true);
    }
}
