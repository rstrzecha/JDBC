package jdbc.daoimpl;

import jdbc.dao.RunDao;
import jdbc.utils.JdbcUtils;
import jdbc.entity.Run;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RunDaoImpl implements RunDao {
    @Override
    public void save(Run run) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO runs (id, name, members_limit) VALUES (?, ?, ?)");
        statement.setInt(1, run.getId());
        statement.setString(2, run.getName());
        statement.setInt(3, run.getMemberslimit());

        statement.executeUpdate();
        statement.close();
    }

    @Override
    public Run findById(Integer id) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("SELECT * FROM runs WHERE id=?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        Run run = null;

        if(resultSet.next()) {
            run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setName(resultSet.getString("name"));
            run.setMemberslimit(resultSet.getInt("members_limit"));
        }
        statement.close();

        return run;
    }

    @Override
    public List<Run> findByNamePart(String name) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("SELECT * FROM runs WHERE name LIKE ?");
        statement.setString(1, "%" + name + "%");

        ResultSet resultSet = statement.executeQuery();
        ArrayList<Run> result = new ArrayList<>();

        while (resultSet.next()) {
            Run run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setName(resultSet.getString("name"));
            run.setMemberslimit(resultSet.getInt("members_limit"));

            result.add(run);
        }
        statement.close();

        return result;
    }

    @Override
    public List<Run> findAll() throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("SELECT * FROM runs");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Run> result = new ArrayList<>();

        while (resultSet.next()) {
            Run run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setName(resultSet.getString("name"));
            run.setMemberslimit(resultSet.getInt("members_limit"));

            result.add(run);
        }
        statement.close();

        return result;
    }

    @Override
    public void update(Run run) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("UPDATE runs SET name=?, members_limit=? WHERE id=?");
        statement.setString(1, run.getName());
        statement.setInt(2, run.getMemberslimit());
        statement.setInt(3, run.getId());

        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("DELETE FROM runs WHERE id=?");
        statement.setInt(1, id);

        statement.executeUpdate();
        statement.close();
    }
}