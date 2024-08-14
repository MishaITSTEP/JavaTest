package org.example;


import java.sql.*;

public class Main {

    private static final String URL = "jdbc:postgresql://ep-shrill-resonance-a2ewhu7l.eu-central-1.aws.neon.tech/jjj";
    private static final String USER = "jjj_owner";
    private static final String PASSWORD = "TQi2LaRf5uzl";
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        try {
            CreateTable();


        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
            ;
        } finally {
        }
    }

    private static void AddItem() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insert = "insert into item values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);

            Abonent abonent = new Abonent();
            abonent.setName("Mikhailo");
            abonent.setAddress("Konovalcia 32(2)");
            abonent.setEmail("mishakravchuk4004@gmail.com");
            abonent.setPhoneNumber("+380995024000");
            preparedStatement.setString(1, abonent.getName());
            preparedStatement.setString(2, abonent.getAddress());
            preparedStatement.setString(3, abonent.getEmail());
            preparedStatement.setString(4, abonent.getPhoneNumber());

            if (preparedStatement.executeUpdate() > 0) System.out.println("Executed👍");
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Помилка зяднання до БД!!!");
            ;
        } finally {
            if (connection != null) connection.close();
        }
    }

    // SQL-запит для створення таблиці
    private static void CreateTable() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Phonebook (ID SERIAL PRIMARY KEY, Name VARCHAR(100) NOT NULL, PhoneNumber VARCHAR(15) NOT NULL, Email VARCHAR(100), Address VARCHAR(255));";
            Statement command = connection.createStatement();

            // Виконання SQL-запиту на створення таблиці
            command.execute(createTableSQL);
            System.out.println("Успішно створено таблицю Phonebook :)");
        } catch (Exception e) {
            System.out.println("Помилка зяднання до БД!!!");
            ;
        } finally {
            if (connection != null) connection.close();
        }
    }
}