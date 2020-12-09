package jdbc.daoimpl;

import jdbc.dao.RunDao;
import jdbc.utils.JdbcUtils;
import jdbc.entity.Run;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunDaoImplTest {

    private RunDao runDao = new RunDaoImpl();

    @BeforeEach
    void clearTable() {
        try {
            Statement statement = JdbcUtils
                    .getInstance()
                    .getConnection()
                    .createStatement();
            statement.executeUpdate("DELETE FROM runs");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void save() {
        try {
            //GIVEN
            Run run = new Run(1, "Bieg testowy", 99);

            //WHEN
            runDao.save(run);
            Run saved = runDao.findById(run.getId());

            //THEN
            assertNotNull(saved);
            assertEquals(run.getId(), saved.getId());
            assertEquals(run.getMemberslimit(), saved.getMemberslimit());
            assertEquals(run.getName(), saved.getName());

        } catch (SQLException throwables) {
            fail(throwables);
        }
    }

    @Test
    void findAll() {
        try {
            //GIVEN
            Run run1 = new Run(1, "Bieg testowy", 99);
            Run run2 = new Run(2, "Bieg testowy numer 2", 1898);
            runDao.save(run1);
            runDao.save(run2);

            //WHEN
            List<Run> list = runDao.findAll();

            //THEN
            assertEquals(2, list.size());
        } catch (SQLException throwables) {
            fail(throwables);
        }
    }

    @Test
    void findByNamePart() {
        try {
            //GIVEN
            Run run = new Run(1, "Bieg testowy", 99);
            runDao.save(run);

            //WHEN
            List<Run> runs = runDao.findByNamePart("Bieg");
            Run expectedRun = runs.get(0);

            //THEN
            assertNotNull(expectedRun);
            assertEquals(run.getId(), expectedRun.getId());
            assertEquals(run.getMemberslimit(), expectedRun.getMemberslimit());
            assertEquals(run.getName(), expectedRun.getName());
        } catch (SQLException throwables) {
            fail(throwables);
        }
    }

    @Test
    void update() {

        try {
            //GIVEN
            Run run = new Run(2, "Pierwsza nasza nazwa", 8787);
            runDao.save(run);

            //WHEN
            run.setName("Inna nazwa do update");
            run.setMemberslimit(878);
            runDao.update(run);
            Run updated = runDao.findById(run.getId());

            //THEN
            assertNotNull(updated);
            assertEquals(run.getName(), updated.getName());
            assertEquals(run.getMemberslimit(), updated.getMemberslimit());
        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void deleteById() {
        try {
            //GIVEN
            Run run = new Run(2, "Pierwsza nasza nazwa", 8787);
            runDao.save(run);

            //WHEN
            runDao.deleteById(run.getId());
            Run deleted = runDao.findById(run.getId());

            //THEN
            assertNull(deleted);

        } catch (SQLException e) {
           fail(e);
        }
    }
}
