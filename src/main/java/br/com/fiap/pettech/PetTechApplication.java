package br.com.fiap.pettech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetTechApplication {

//    public PetTechApplication()  throws SQLException {
//        final String url = "jdbc:postgresql://localhost:5432/pettech";
//        final String user = "user";
//        final String password="123456";
//
//        Connection connection = DriverManager.getConnection(url, user, password);
//
//        System.out.println("Conexão efetuada com sucesso!");
//
//        connection.close();
//    }

    public static void main(String[] args) {
        SpringApplication.run(PetTechApplication.class, args);
    }

}
