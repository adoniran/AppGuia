package model;

/**
 * Created by adoniran on 28/11/17.
 */

public class Usuario {
    private String name;
    private String email;
    private String sexo;

    public Usuario() {}

    public Usuario(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public Usuario(String name, String email, String sexo) {
        this.name = name;
        this.email = email;
        this.sexo = sexo;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getSexo() {
        return sexo;
    }
}
