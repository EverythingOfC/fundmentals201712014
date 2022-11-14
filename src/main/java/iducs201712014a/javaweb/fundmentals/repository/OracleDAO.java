package iducs201712014a.javaweb.fundmentals.repository;

import java.sql.*;

public class OracleDAO implements DAO{ // DAO 인터페이스를 구현한 OracleDAO 구현 클래스

    @Override
    public Connection getConnection(){

        Connection conn = null;
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "system"; // 사용자
        String dbPass = "cometrue"; // 비밀번호 ( 학원에서는 다시 바꿔야 함. cometrue)

        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl , dbUser, dbPass);

        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void closeResources(Connection conn,Statement stmt,PreparedStatement pstmt,ResultSet rs){
        try {
            conn.close();
            stmt.close();
            pstmt.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
