import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selection {
    JFrame frame;
    JPanel panel;
    JLabel c,space;
    JLabel l;
    JLabel sc;
    JComboBox<String> level;
    JComboBox<String> context;
    JComboBox<String> subcontext;
    JButton next = new JButton("Next");
    String[] levels = {"-select-", "A1", "A2", "B1", "B2"};
    String[] contexts = {};
    String[] s_contexts = {};
    public Selection() {
        frame = new JFrame();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350,150));
        space = new JLabel("",SwingConstants.CENTER);
        l = new JLabel("Select Level: ",SwingConstants.CENTER);
        c = new JLabel("Select Context: ",SwingConstants.CENTER);
        sc = new JLabel("Select Sub-Context: ",SwingConstants.CENTER);

        level = new JComboBox<>(levels);
        context = new JComboBox<>(contexts);
        subcontext = new JComboBox<>(s_contexts);
        level.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadingDatabase read = new ReadingDatabase();
                String[] fin = read.getContext(level.getSelectedItem().toString());
                context.removeAllItems();
                context.addItem("-select-");
                for (int i = 0;i < fin.length;i++){
                    context.addItem(fin[i]);
                }
                context.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String sfin[] = read.getSub(context.getSelectedItem().toString(), level.getSelectedItem().toString());
                        subcontext.removeAllItems();
                        subcontext.addItem("-select-");
                        for (int i = 0;i < sfin.length;i++){
                            subcontext.addItem(sfin[i]);
                        }
                    }
                });
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                roleplay r = new roleplay(level.getSelectedItem().toString(), context.getSelectedItem().toString(), subcontext.getSelectedItem().toString());
            }
        });
        panel.setLayout(new GridLayout(4,2));
        panel.add(l);
        panel.add(level);
        panel.add(c);
        panel.add(context);
        panel.add(sc);
        panel.add(subcontext);
        panel.add(space);
        panel.add(next);
        frame.add(panel);
        frame.setTitle("Selection");
        frame.pack();
        frame.setVisible(true);
    }
}
