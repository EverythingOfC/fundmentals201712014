package iducs.javaweb.blog201712014.repository;

import iducs.javaweb.blog201712014.model.Blog;

import java.sql.*;
import java.util.List;

public class BlogDAOImpl extends OracleDAOImpl implements BlogDAO{

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public BlogDAOImpl() {
        this.conn = getConnection();
    }

    @Override
    public int create(Blog blog) {
        String query = "insert into blog201712014 values(seq_blog201712014.nextval, ?, ?, ?, ?)";
        int rows = 0; // 질의 처리 결과 영향받은 행의 수
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getAuthor());
            pstmt.setString(4, blog.getEmail());
            rows = pstmt.executeUpdate();// 1 이상이면 정상, 0 이하면 오류
        } catch(SQLException e){
            e.printStackTrace();
        }
        return rows;
    }


    @Override
    public Blog read(Blog blog) {
        return null;
    }

    @Override
    public List<Blog> readList() {
        return null;
    }

    @Override
    public int update(Blog blog) {
        return 0;
    }

    @Override
    public int delete(Blog blog) {
        return 0;
    }
}