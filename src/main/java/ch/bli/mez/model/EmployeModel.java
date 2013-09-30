package ch.bli.mez.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import ch.bli.mez.model.classes.Employe;
import ch.bli.mez.model.database.DBManager;

public class EmployeModel {

  public Employe getEmployeById(int id) {
    ResultSet rs = DBManager.getInstance().query("SELECT * FROM Employe WHERE id=" + id);

    try {
      if (!rs.next()) {
        return null;
      } else {
        //return new Employe(rs.getString("name"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
