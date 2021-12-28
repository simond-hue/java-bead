package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    private static final String connectionString = "jdbc:sqlite:database/results.db";
    private Connection connection;
    public DBHelper() {

    }

    /**
     * Connects to the SQLite database
     * @throws SQLException
     */
    public void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.connection = DriverManager.getConnection(this.connectionString);
    }

    /**
     * Disconnects the SQLite database
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        if(this.connection != null){
            this.connection.close();
        }
    }

    /**
     * Gets the top ten player from the SQLite database
     * @return the top 10 player
     * @throws SQLException
     */
    public ArrayList<DBSchema> listTop() throws SQLException {
        this.connect();
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM players ORDER BY LEVEL DESC LIMIT 10;");
        ArrayList<DBSchema> resultProcessed = new ArrayList<DBSchema>();
        while(result.next()){
            int id = result.getInt("ID");
            String name = result.getString("NAME");
            int level = result.getInt("LEVEL");
            DBSchema tmp = new DBSchema(id,name,level);
            resultProcessed.add(tmp);
        }
        this.disconnect();
        return resultProcessed;
    }

    /**
     * Inserts new entry to the SQLite database
     * @param text the name of the player
     * @param layer the layer which the player has died on
     * @throws SQLException
     */
    public void addEntry(String text, int layer) throws SQLException {
        this.connect();
        PreparedStatement statement = this.connection.prepareStatement("INSERT INTO players (NAME, LEVEL) VALUES(?,?)");
        statement.setString(1,text);
        statement.setInt(2,layer);
        statement.executeUpdate();
        this.disconnect();
    }
}
