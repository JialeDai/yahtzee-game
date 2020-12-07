package dao;

import java.sql.ResultSet;

public interface Command {
    void insert();

    ResultSet selectAll();

    ResultSet selectOne(String name,String time);

    void updateOne(String name,String time);
}
