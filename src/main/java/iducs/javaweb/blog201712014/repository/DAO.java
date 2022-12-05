package iducs.javaweb.blog201712014.repository;

import java.sql.Connection; // DBMS와 연결 관리를 담당하는 클래스
import java.sql.PreparedStatement; // 연결된 DBMS에 전달한 SQL문장을 조작하기 위한 클래스 ( sql문을 외부값으로 업데이트 가능 )
import java.sql.ResultSet; // 질의 결과를 조회하기 위한 클래스
import java.sql.Statement; // 연결된 DBMS에 전달한 SQL문장을 조작하기 위한 클래스

public interface DAO { // 인터페이스(DAO의 사용 방법을 정의한 타입)

    Connection getConnection() throws ClassNotFoundException,SecurityException;
    void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs); // 데이터베이스를 관리 메소드를 메모리에서 해제

}
