package db.connection;

import java.sql.*;

public class DBOperations {
    Connection conn;
    public void doConnectDB() {
        String connectionStr = "jdbc:mysql://localhost:3306/"+Utilities.DB_NAME;
        String userName = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(connectionStr, userName, password);
            System.out.println("DB Connection is seccussful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+ Utilities.TABLE_PRODUCTS +
                    " AS p JOIN " + Utilities.TABLE_CATEGORY + " AS c ON p.category = c.id");

            while (rs.next()) {
                String products_name = rs.getString(Utilities.PRODUCTS_NAME);
                int products_category = rs.getInt(Utilities.PRODUCTS_CATEGORY);
                float products_price = rs.getInt(Utilities.PRODUCTS_PRICE);
                float products_quantity = rs.getFloat(Utilities.PRODUCTS_QUANTITY);
                String products_manufacturer = rs.getString(Utilities.PRODUCTS_MANUFACTURER);

                String category_name = rs.getString(Utilities.CATEGORY_NAME);
                System.out.println("Name: " + products_name + ", Category Id: "
                        + products_category + ", Category: " + category_name + ", Price: "
                        + products_price + ", Quantity: " + products_quantity + ", Manufacturer: "
                        + products_manufacturer);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
