package DAO;

import javabean.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ray on 2017/5/21.
 */
public class ManagerDAO extends BaseDAO {
    Connection conn=null;
    public Manager login(long id,String password)  {
        Manager manager=null;
        try {
            conn=getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM Manager WHERE id=? AND password=?");
            ps.setLong(1,id);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                manager=new Manager();
                manager.setId(rs.getLong("id"));
                manager.setPassword(rs.getString("password"));
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return manager;
    }
}
