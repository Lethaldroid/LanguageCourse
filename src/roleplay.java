import javax.naming.Context;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class roleplay {
    JFrame frame;
    JPanel panel;
    JComboBox<String> persons;
    JTextArea t = new JTextArea(" ");
    JLabel select = new JLabel("Select Person: ");
    JButton back = new JButton("Back");
    JButton hint = new JButton("Hint");
    JTextArea hintarea = new JTextArea("");
    public roleplay(String level, String context, String subcontext)
    {
        frame = new JFrame();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(900,500));
        String[] p = {"-select-", "personA", "personB"};
        persons = new JComboBox<>(p);

        hintarea.setVisible(false);
        t.setVisible(false);

        persons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadingDatabase read = new ReadingDatabase();
                String temp = read.gettext(level, context, subcontext, persons.getSelectedItem().toString());
                String fin = "";
                for (int i = 0;i < temp.length();i++){
                    if (temp.charAt(i) == '.'){
                        fin = fin + temp.charAt(i) + "\n";
                    }else{
                        fin = fin + temp.charAt(i);
                    }
                }
                t.setText(fin);
                t.setVisible(true);

            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Selection s = new Selection();
            }
        });
        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hintarea.getText().compareTo("") == 0)
                {
                    ArrayList<String> english = new ArrayList<String>();
                    ArrayList<String> spanish = new ArrayList<String>();
                    ReadingDatabase read = new ReadingDatabase();
                    String[] hints = read.getHints(level, context, subcontext);
                    String temp = "";
                    for (int i = 0;i < hints[0].length();i++){
                        if (hints[0].charAt(i) == ','){
                            english.add(temp);
                            temp = "";
                        }
                        else{
                            temp = temp + hints[0].charAt(i);
                        }
                    }
                    temp = "";
                    for (int i = 0;i < hints[1].length();i++){
                        if (hints[1].charAt(i) == ','){
                            spanish.add(temp);
                            temp = "";
                        }
                        else{
                            temp = temp + hints[1].charAt(i);
                        }
                    }
                    for (int i = 0;i < english.size() && i < spanish.size();i++)
                    {
                        hintarea.setText(hintarea.getText() + "\n" + english.get(i)  + "--" + spanish.get(i));
                    }
                    hintarea.setVisible(true);
                }
            }
        });
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(select, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(persons,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        panel.add(t, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        panel.add(hintarea,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(hint,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(back,gbc);
        frame.add(panel);
        frame.setTitle("RolePlay");
        frame.pack();
        frame.setVisible(true);
    }
}
