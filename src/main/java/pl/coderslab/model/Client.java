package pl.coderslab.model;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
public class Client {
    // variables.

    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String phone;

// java.sql.Date sqlDate = new java.sql.Date(now.getTime()); date util to date sql
    // constructors - id by default=0;
    public Client() {
    }

    public Client(String name, String surname, LocalDate birthDate, String phone) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Dao Create/Update
    // Zapisz do  BD zapisuje nowy element do BD ub zmodyfikowany element. Poznajemy po id==0
    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO client(name,surname,birthdate,phone) VALUES (?, ?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.surname);
            preparedStatement.setString(3, java.sql.Date.valueOf(this.birthDate).toString());
            preparedStatement.setString(4, this.phone);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE client SET name=?,surname=?,birthdate=?,phone=? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.surname);
            preparedStatement.setString(3, java.sql.Date.valueOf(this.birthDate).toString());
            preparedStatement.setString(4, this.phone);
            preparedStatement.setInt(5, this.getId());
            preparedStatement.executeUpdate();
        }
    }
    // DAO READ
    // Wczytaj 1 client po id. Metoda statyczna dlatego przekazujemy dodatkowo id
    static public Client loadClientById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM client where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Client loadedClient = new Client();
            loadedClient.id = resultSet.getInt("id");
            loadedClient.name = resultSet.getString("name");
            loadedClient.surname = resultSet.getString("surname");
            loadedClient.birthDate = resultSet.getDate("birthdate").toLocalDate();

            loadedClient.phone = resultSet.getString("phone");
            return loadedClient;}
        return null;}

    // Wczytaj wszystkich z BD
    static public List<Client> loadAllClients(Connection conn) throws SQLException {
        ArrayList<Client> clients = new ArrayList<Client>();
        String sql = "SELECT * FROM client";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Client loadedClient = new Client();
            loadedClient.id = resultSet.getInt("id");
            loadedClient.name = resultSet.getString("name");
            loadedClient.surname = resultSet.getString("surname");
            loadedClient.birthDate = resultSet.getDate("birthdate").toLocalDate();
            loadedClient.phone = resultSet.getString("phone");
            clients.add(loadedClient);
        }
        return clients;
    }
    // DAO DELETE
    // usuń client zBD
    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM client WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

}
