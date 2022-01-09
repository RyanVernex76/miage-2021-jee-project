package fr.pantheonsorbonne.ufr27.miage.dto;

public class ConnectionDTO {
    private String email;

    private String password;

    public ConnectionDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
