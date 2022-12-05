package iducs.javaweb.blog201712014.repository;

import java.sql.*;

public class OracleDAOImpl implements DAO{ // DAO 인터페이스를 구현한 OracleDAO 구현 클래스

    @Override
    public Connection getConnection(){

        Connection conn = null;
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "system"; // 사용자
        String dbPass = "cometrue"; // 비밀번호 ( 학교에서는 다시 바꿔야 함. cometrue)

        try{
            Class.forName("oracle.jdbc.OracleDriver"); // 해당 클래스 이름의 객체를 받아온다.
            conn = DriverManager.getConnection(jdbcUrl , dbUser, dbPass);  // 드라이버 관리자가 데이터베이스를 연결함.(오라클 드라이버를 메모리에 적재)

        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public void closeResources(Connection conn,Statement stmt,PreparedStatement pstmt,ResultSet rs){
        try {
            if (rs!= null) try {rs.close(); } catch(Exception e){}
            if (pstmt!= null) try {pstmt.close(); } catch(Exception e){}
            if (stmt!= null) try {stmt.close(); } catch(Exception e){}
            if (conn!= null) try {conn.close(); } catch(Exception e){}

            // NULL이 아닐 때만 자원을 반환한다. ( 호출한 역순으로 반환 )

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
