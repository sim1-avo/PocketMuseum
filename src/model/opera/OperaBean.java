package model.opera;

import java.io.Serializable;
import javax.servlet.http.Part;


public class OperaBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private int id;
  private String nome;
  private String descrizione;
  private String autore;
  private String copertina;
  private String stato;
  private String immagine;
  private Part immaginePart;
  private Part copertinaPart;
  
  public OperaBean() {}
  
  /**
   * Costruttore OPERA.
   * @param id id opera
   * @param nome nome opera
   * @param descrizione descrizione opera
   * @param autore autore opera
   * @param copertina copertina opera
   * @param stato stato opera
   * @param immagine immagine opera
   */
  
  public OperaBean(int id, String nome, String descrizione, String autore, String copertina, 
      String stato, String immagine) {
    super();
    this.id = id;
    this.nome = nome;
    this.descrizione = descrizione;
    this.autore = autore;
    this.copertina = copertina;
    this.stato = stato;
    this.immagine = immagine;
  }
  
  public Part getImmaginePart() {
    return immaginePart;
  }
  
  public void setImmaginePart(Part immaginePart) {
    this.immaginePart = immaginePart;
  }

  public Part getCopertinaPart() {
    return copertinaPart;
  }

  public void setCopertinaPart(Part copertinaPart) {
    this.copertinaPart = copertinaPart;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }
  
  public String getAutore() {
    return autore;
  }

  public void setAutore(String autore) {
    this.autore = autore;
  }

  public String getCopertina() {
    return copertina;
  }

  public void setCopertina(String copertina) {
    this.copertina = copertina;
  }

  public String getStato() {
    return stato;
  }
  
  public void setStato(String stato) {
    this.stato = stato;
  }

  public String getImmagine() {
    return immagine;
  }

  public void setImmagine(String immagine) {
    this.immagine = immagine;
  }

}
