package iducs.javaweb.blog201712014.repository;

import iducs.javaweb.blog201712014.model.Blog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl extends OracleDAOImpl implements BlogDAO {

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
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getAuthor());
            pstmt.setString(4, blog.getEmail());
            rows = pstmt.executeUpdate();// 1 이상이면 정상, 0 이하면 오류
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }


    @Override
    public Blog read(Blog blog) {

        Blog retBlog = null;

        String sql = "select * from blog201712014 where id='" + blog.getId() + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                retBlog = new Blog();

                retBlog.setId(rs.getLong(1)); // rs.getString("<field_name>"); 필드이름로도 가능
                retBlog.setTitle(rs.getString(2)); // 생성 시 필드 순서
                retBlog.setContent(rs.getString(3));
                retBlog.setAuthor(rs.getString(4));
                retBlog.setEmail(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResources(conn, stmt, pstmt, rs);
        }
        return retBlog;
    }

    @Override
    public List<Blog> readList() {

        List<Blog> blogList = null;
        Blog retBlog = null;
        String sql = "select * from blog201712014 order by id desc";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            blogList = new ArrayList<>();
            while (rs.next()) { // 다음 record값을 접근
                // rs : record 집합, rs.getString(1) : 현재 레코드의 첫번재 필드 값
                retBlog = new Blog();
                retBlog.setId(rs.getLong(1)); // rs.getString("<field_name>"); 필드이름로도 가능
                retBlog.setTitle(rs.getString(2)); // 생성 시 필드 순서
                retBlog.setContent(rs.getString(3));
                retBlog.setAuthor(rs.getString(4));
                retBlog.setEmail(rs.getString(5));
                blogList.add(retBlog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResources(conn, stmt, pstmt, rs);
        }
        return blogList;
    }


    @Override
    public int update(Blog blog) {
        int rows = 0;
        String sql = "update blog set title=?, content=?, author=?, email=? where id=?";
        //TO_TIMESTAMP('2019-12-15 12:00:00.0', 'YYYY-MM-DD HH24:MI:SS.FF3')
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getAuthor());
            pstmt.setString(4, blog.getEmail());
            pstmt.setLong(5, blog.getId());
            rows = pstmt.executeUpdate();   // 쿼리 업데이트

            if(rows == 0)   // 쿼리가 없으면
                System.out.println(blog.getTitle() + "error : " + blog.getAuthor() + ":::" + blog.getId());
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }


    @Override
    public int delete(Blog blog) {

        int rows = 0;
        String sql = "delete from blog201712014 where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, blog.getId());
            rows = pstmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

}
