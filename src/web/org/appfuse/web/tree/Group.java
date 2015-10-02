/*
 * project name : standardsql
 * package name : com.common.tree
 * file    name : Group.java
 * class   name : Group
 * Created on 2005-3-21 20:06:25
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.tree;

/**
 * 当前这个存在问题，不能够正常显示图标
 * Created on 2005-3-21 20:06:25
 * @author ---Joson Yuan
 * author comments:
 * 
 */

/**
 * 此类作为数据库目录树的参考 表中字段 id、parentid、name
 */
public class Group implements TreeNode
{
    private int id;

    private int parentId;

    private String name;

    public Group()
    {
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public static Group[] getGroups()
    {
        Group[] rs = new Group[5];
        for (int i = 0; i < rs.length; i++)
        {
            rs[i] = new Group();
            rs[i].setId(i + 1);
            rs[i].setName(Integer.toString(i + 1));
        }
        rs[0].setParentId(0);
        rs[1].setParentId(0);
        rs[2].setParentId(1);
        rs[3].setParentId(2);
        rs[4].setParentId(2);
        return rs;

        //        
        //        Connection conn = null;
        //        Statement stmt = null;
        //        ResultSet rs = null;
        //        try
        //        {
        //            String sql = "select * from linktone_forum";
        //            Class.forName("org.gjt.mm.mysql.Driver");
        //            //String url =
        //            // "jdbc:mysql://192.168.8.205/testgame?user=root&password=";
        //            String url =
        // "jdbc:mysql://127.0.0.1/linktone?user=root&password=&useUnicode=true&characterEncoding=gb2312";
        //            conn = DriverManager.getConnection(url);
        //            stmt = conn.createStatement();
        //            rs = stmt.executeQuery(sql);
        //            ArrayList list = new ArrayList();
        //            while (rs != null && rs.next())
        //            {
        //                Group g = new Group();
        //                g.setId(rs.getInt("id"));
        //                g.setParentId(rs.getInt("parentid"));
        //                g.setName(rs.getString("name"));
        //                list.add(g);
        //            }
        //            Group[] groups = new fly.tools.tree.Group[list.size()];
        //            for (int i = 0; i < list.size(); i++)
        //            {
        //                groups[i] = (Group) list.get(i);
        //            }
        //            return groups;
        //        } catch (ClassNotFoundException e)
        //        {
        //            e.printStackTrace();
        //        } catch (SQLException e)
        //        {
        //            e.printStackTrace();
        //        } finally
        //        {
        //            try
        //            {
        //                rs.close();
        //                stmt.close();
        //                conn.close();
        //            } catch (Exception e)
        //            {
        //                e.printStackTrace();
        //            }
        //        }
        //        throw new NullPointerException("Database error.");
        //
    }

}