package dao;

import dbUtils.SqliteUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalScoreDao implements Command{
    private String name;
    private int score;

    public TotalScoreDao(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public void insert() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = SqliteUtils.getConnection();
            String sql = "insert into total_score(player_name,score) values(?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            int cnt = pstmt.executeUpdate();
            System.out.println("insert successfully "+cnt+" lines changed");
        }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                SqliteUtils.closeConnection(null,pstmt,conn);
            }
    }

    @Override
    public ResultSet selectAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String sql ="select * from total_score order by score;";
        return null;
    }

    @Override
    public ResultSet selectOne(String name, String time) {
        return null;
    }

    @Override
    public void updateOne(String name, String time) {

    }
}
