package _65_Projects_Delete.dao;

import _65_Projects_Delete.vo.Project;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//@Component("projectDao")
public class MysqlProjectDao implements ProjectDao {

    DataSource ds;

    public void setDataSource(DataSource ds){
        this.ds = ds;
    }

    @Override
    public List<Project> selectList() throws Exception {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt
                    .executeQuery("SELECT PNO,PNAME,STA_DATE,END_DATE,STATE" + " FROM projects" + " ORDER BY PNO DESC");

            ArrayList<Project> projects = new ArrayList<Project>();

            while (rs.next()) {
                projects.add(new Project().setNo(rs.getInt("PNO")).setTitle(rs.getString("PNAME"))
                        .setStartDate(rs.getString("STA_DATE")).setEndDate(rs.getString("END_DATE"))
                        .setState(rs.getInt("STATE")));
            }

            return projects;

        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public int insert(Project project) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("INSERT INTO projects"
                    + "(PNAME,CONTENT,STA_DATE,END_DATE,STATE,CRE_DATE,TAGS)" + " VALUES (?,?,?,?,0,NOW(),?)");
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getContent());
            stmt.setString(3, project.getStartDate());
            stmt.setString(4, project.getEndDate());
            stmt.setString(5, project.getTags());

            return stmt.executeUpdate();

        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public int delete(int no) throws Exception {
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            return stmt.executeUpdate("DELETE FROM projects WHERE PNO=" + no);

        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Project selectOne(int no) throws Exception {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = ds.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT PNO,PNAME,CONTENT,STA_DATE,END_DATE,STATE,CRE_DATE,TAGS"
                    + " FROM projects WHERE PNO=" + no);
            if (rs.next()) {
                return new Project().setNo(rs.getInt("PNO")).setTitle(rs.getString("PNAME"))
                        .setContent(rs.getString("CONTENT")).setStartDate(rs.getString("STA_DATE"))
                        .setEndDate(rs.getString("END_DATE")).setState(rs.getInt("STATE"))
                        .setCreateDate(rs.getDate("CRE_DATE")).setTags(rs.getString("TAGS"));

            } else {
                throw new Exception("해당 번호의 프로젝트를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public int update(Project project) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ds.getConnection();
            stmt = connection.prepareStatement("UPDATE projects SET "
                    + " PNAME=?,"
                    + " CONTENT=?,"
                    + " STA_DATE=?,"
                    + " END_DATE=?,"
                    + " STATE=?,"
                    + " TAGS=?"
                    + " WHERE PNO=?");
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getContent());
            stmt.setString(3, project.getStartDate());
            stmt.setString(4, project.getEndDate());
            stmt.setInt(5, project.getState());
            stmt.setString(6, project.getTags());
            stmt.setInt(7, project.getNo());

            return stmt.executeUpdate();

        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
            }
        }
    }
}
