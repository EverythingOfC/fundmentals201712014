package iducs201712014a.javaweb.fundmentals.repository;

import iducs201712014a.javaweb.fundmentals.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MemberDAOImpl extends OracleDAO implements MemberDAO{ // MemberDAO의 구현 클래스

    // 연관 객체 선언
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public List<Member> readList(){

        List<Member> memberList = null;

        Member retMember = null;
        String sql = "select * from tab";

        try{

            conn = getConnection(); // DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            memberList = new ArrayList<Member>(); // Member객체들을 List로 담음

            while(rs.next()){

                retMember = new Member();
                retMember.setTname(rs.getString("tname")); // tname값만 계속 가져온다.
                memberList.add(retMember); // 계속 저장한다.
            }

        }catch(SQLException e){ // try문 오류처리
            System.out.println(e.getMessage());
        }finally {
            closeResources(conn,stmt,pstmt,rs); // 메모리 해제
            return memberList;
        }
    }

    @Override
    public int create(Member m) {
        return 0;
    }

    @Override
    public Member read(Member m) {
        return null;
    }

    @Override
    public int update(Member m) {
        return 0;
    }

    @Override
    public int delete(Member m) {
        return 0;
    }
}