package iducs.javaweb.blog201712014.repository;

import iducs.javaweb.blog201712014.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MemberDAOImpl extends OracleDAOImpl implements MemberDAO{ // MemberDAO의 구현 클래스

    // DB 연관 객체 선언
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public List<Member> sortList(String sort) {

        List<Member> memberList = null;

        Member retMember = null;
        String sql = "select * from member1 order by " + sort; // 해당 정렬 방식으로 정렬

        try{
            conn = getConnection(); // DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            memberList = new ArrayList<Member>(); // Member객체들을 List로 담음

            while(rs.next()){
                retMember = new Member(); // Member형 객체
                retMember.setName(rs.getString("name"));
                retMember.setEmail(rs.getString("email"));
                retMember.setPw(rs.getString("pw"));
                retMember.setPhone(rs.getString("phone"));
                retMember.setAddress(rs.getString("address"));
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
    public List<Member> readList(){

        List<Member> memberList = null;

        Member retMember = null;
        String sql = "select * from member1";

        try{
            conn = getConnection(); // DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            memberList = new ArrayList<Member>(); // Member객체들을 List로 담음

            while(rs.next()){
                retMember = new Member(); // Member형 객체
                retMember.setName(rs.getString("name"));
                retMember.setEmail(rs.getString("email"));
                retMember.setPw(rs.getString("pw"));
                retMember.setPhone(rs.getString("phone"));
                retMember.setAddress(rs.getString("address"));
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
    public int create(Member m) { // 등록

        int ret = 0;

        String sql = "insert into member1 values(seq_member.nextval,?,?,?,?,?)";

        try{
            conn = getConnection(); // DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,m.getEmail());
            pstmt.setString(2,m.getPw());
            pstmt.setString(3,m.getName());
            pstmt.setString(4,m.getPhone());
            pstmt.setString(5,m.getAddress());

            ret = pstmt.executeUpdate(); // 쿼리 업데이트( 성공시 1반환)

        }catch(SQLException e){ // try문 오류처리
            System.out.println(e.getMessage());
        }finally {
            closeResources(conn,stmt,pstmt,rs); // 메모리 해제
            return ret;
        }

    }

    @Override
    public Member read(Member m) {

        List<Member> memberList = null;

        Member retMember = null;
        String sql = "select * from member1 where email = '" + m.getEmail() +"'";

        try{
            conn = getConnection(); // DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                retMember = new Member(); // Member형 객체
                retMember.setName(rs.getString("name"));
                retMember.setEmail(rs.getString("email"));
                retMember.setAddress(rs.getString("address"));
                retMember.setPw(rs.getString("pw"));
                retMember.setPhone(rs.getString("phone"));
            }

        }catch(SQLException e){ // try문 오류처리
            System.out.println(e.getMessage());
        }finally {
            closeResources(conn,stmt,pstmt,rs); // 메모리 해제
            return retMember;
        }

    }

    @Override
    public int update(Member m) {

        int ret = 0;

        String sql = "update member1 set email = ?, pw = ?, name = ?, phone =?, address =? where email ='" + m.getEmail() +"'";

        try{
            conn = getConnection(); // DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,m.getEmail());
            pstmt.setString(2,m.getPw());
            pstmt.setString(3,m.getName());
            pstmt.setString(4,m.getPhone());
            pstmt.setString(5,m.getAddress());

            ret = pstmt.executeUpdate(); // 쿼리 업데이트( 성공시 1 반환)

        }catch(SQLException e){ // try문 오류처리
            System.out.println(e.getMessage());
        }finally {
            closeResources(conn,stmt,pstmt,rs); // 메모리 해제
            return ret;
        }

    }

    @Override
    public int delete(Member m) {

        int ret = 0;

        String sql = "delete from member1 where pw = '" + m.getPw() + "'";

        try{
            conn = getConnection(); // DB 연결 객체 생성

            stmt = conn.createStatement();
            ret = stmt.executeUpdate(sql); // 쿼리 업데이트( 성공시 1반환)

        }catch(SQLException e){ // try문 오류처리
            System.out.println(e.getMessage());
        }finally {
            closeResources(conn,stmt,pstmt,rs); // 메모리 해제
            return ret;
        }
    }

    @Override
    public Member login(Member m) {

        String sql = "select * from member1 where email = ? and pw = ?"; // email과 pw가 일치하는 결과갓반환

        Member retMember = null;

        try{
            conn = getConnection(); // DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,m.getEmail());
            pstmt.setString(2,m.getPw());

            rs = pstmt.executeQuery();

            if(rs.next()){ // 아이디가 있다면

                retMember = new Member();
                retMember.setName(rs.getString("name"));
                retMember.setEmail(rs.getString("email"));
            }

        }catch(SQLException e){ // try문 오류처리
            System.out.println(e.getMessage());
        }finally {
            closeResources(conn,stmt,pstmt,rs); // 메모리 해제
            return retMember;
        }

    }
}