/*
CREATE DATABASE school;

USE school;

CREATE TABLE studenti (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    cognome VARCHAR(50),
    eta INT
);

INSERT INTO studenti (nome, cognome, eta) VALUES
('Marco', 'Rossi', 20),
('Giulia', 'Verdi', 22),
('Luca', 'Bianchi', 21);
*/

import java.sql.*;

public class Esercizio2 {
    // Dati di connessione 
    private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USER = "root";           
    private static final String PASSWORD = "password";   

    public static void main(String[] args) {
        String query = "SELECT id, nome, cognome, eta FROM studenti";

        // Connessione, PreparedStatement e ResultSet gestiti con try-with-resources
        try (
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ) {
            System.out.println("Lista studenti:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                int eta = rs.getInt("eta");
                System.out.println(id + " - " + nome + " " + cognome + " (età: " + eta + ")");
            }
        } catch (SQLException e) {
            System.err.println("Errore durante l'accesso al database:");
            e.printStackTrace();
        }
        // Le risorse vengono chiuse automaticamente dal try-with-resources
    }
}
