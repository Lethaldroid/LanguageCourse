import javax.swing.plaf.nimbus.State;
import java.io.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ReadingDatabase
{
    private Connection connet()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        String url = "jdbc:sqlite:data.db";
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url);
        } catch (SQLException error)
        {
            System.out.println(error.getMessage());
        }
        return conn;
    }

    public String[] getSub(String context, String level)
    {
        try
        {
            ArrayList<String> subcontext = new ArrayList<String>();
            String sql = "SELECT Subcontext FROM LanguageInfo WHERE Context = ? and Level = ?";
            Connection conn = this.connet();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, context);
            ps.setString(2, level);
            ResultSet result = ps.executeQuery();
            while (result.next())
            {
                subcontext.add(result.getString("Subcontext"));
            }
            int i, j, cond = 0;

            String[] fin = new String[subcontext.size()];
            for (i = 0; i < subcontext.size(); i++)
            {
                fin[i] = subcontext.get(i);
            }
            return fin;
        } catch (SQLException error)
        {
            System.out.println(error.getMessage());
        }
        return null;
    }

    public String gettext(String level, String context, String subcontext, String person)
    {
        try
        {
            String text = "";
            String sql = "SELECT personA, personB FROM LanguageInfo WHERE Level = ? and Context = ? and Subcontext = ?";
            Connection conn = this.connet();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, level);
            ps.setString(2, context);
            ps.setString(3, subcontext);
            ResultSet result = ps.executeQuery();
            while (result.next())
            {
                text = (result.getString(person));
            }
            int i, j, cond = 0;
            return text;

        } catch (SQLException error)
        {
            System.out.println(error.getMessage());
        }
        return null;
    }

    public String[] getHints(String level, String context, String subcontext)
    {
        try
        {
            String hints[] = new String[2];
            String sql = "SELECT keywords, spanish FROM LanguageInfo WHERE Level = ? and Context = ? and Subcontext = ?";
            Connection conn = this.connet();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, level);
            ps.setString(2, context);
            ps.setString(3, subcontext);
            ResultSet result = ps.executeQuery();
            while (result.next())
            {
                hints[0] = (result.getString("keywords"));
                hints[1] = result.getString("spanish");
            }
            int i, j, cond = 0;
            return hints;

        } catch (SQLException error)
        {
            System.out.println(error.getMessage());
        }
        return null;
    }

    public String[] getContext(String level)
    {
        try
        {
            ArrayList<String> context = new ArrayList<String>();
            String sql = "SELECT Context FROM LanguageInfo WHERE Level = ?";
            Connection conn = this.connet();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, level);
            ResultSet result = ps.executeQuery();
            while (result.next())
            {
                context.add(result.getString("Context"));
            }
            int i, j, cond = 0;

            String[] fin = new String[context.size()];
            for (i = 0; i < context.size(); i++)
            {
                for (j = 0; j < context.size(); j++)
                {
                    if (context.get(i).compareTo(context.get(j)) == 0 && i != j)
                    {
                        context.remove(j);

                    }
                }
            }
            for (i = 0; i < context.size(); i++)
            {
                fin[i] = context.get(i);
            }

            return fin;

        } catch (SQLException error)
        {
            System.out.println(error.getMessage());
        }
        return null;
    }

    public boolean authenticate(String username, String password)
    {
        try
        {
            String sql = "SELECT Username,Password FROM LoginInfo;";
            Connection conn = this.connet();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next())
            {
                if ((username.compareTo(result.getString("Username")) == 0) && password.compareTo(result.getString("Password")) == 0)
                {
                    return true;
                }
            }
        } catch (SQLException error)
        {
            System.out.println(error.getMessage());
        }
        return false;
    }
}
