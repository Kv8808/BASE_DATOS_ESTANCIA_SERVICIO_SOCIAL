package src;

import java.sql.SQLException;

public class TestDAO {
    public static void main(String[] args) {
        try {
            IngresoDiarioDAO dao = new IngresoDiarioDAO();
            System.out.println("DAO cargado correctamente!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
