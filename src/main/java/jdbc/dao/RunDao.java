package jdbc.dao;

import jdbc.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface RunDao {
    //CRUD
    void save(Run run) throws SQLException;                 //C         //klasa do przechowywania danych
    Run findById(Integer id) throws SQLException;           //R
    List<Run> findAll() throws SQLException;                //
    List<Run> findByNamePart(String part) throws SQLException;    //

    void update(Run run) throws SQLException;               //U
    void deleteById(Integer id) throws SQLException;        //D
}


//Dodać do RunDao metodę pozwalającą na wyszukiwanie biegów w zakresie limitu
//        uczestników (limitMax, limitMin). Utworzyć do niej test oraz implementację.