package model.utente;

import java.io.Serializable;

public class UtenteBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private String nome;
  private String cognome;
  private String email;
  private String password;
  private String tipo;
  private String cf;

  public UtenteBean() {}
  
  /**
   * Questo è il costruttore per l'UtenteBean.
   */
  public UtenteBean(String email, String password,  String nome, String cognome,
      String tipo, String cf) {

    email = this.email;
    password = this.password;
    nome = this.nome;
    cognome = this.cognome;
    tipo = this.tipo;
    cf = this.cf;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getCf() {
    return cf;
  }

  public void setCf(String cf) {
    this.cf = cf;
  }

  @Override
  public String toString() {
    return email + " , " + password + " , " + nome + " , " + cognome + " , " + tipo + " , " + cf;
  }
}
