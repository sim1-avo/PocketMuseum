package model.opera;

import java.io.Serializable;



public class RecensioneBean implements Serializable {
  
  private static final long serialVersionUID = 1L;
  private int valutazione;
  private String email;
  private int idOpera;
  private String commento;
  
  public RecensioneBean() {}
  
  /**
   * Costruttore Recensione.
   * @param idopera codice opera
   * @param valutazione valutazione recensione
   * @param email email dell'utente
   * @param commento commento recensione
   */
  
  
  public RecensioneBean(int valutazione, String email, int idopera, String commento) {
  
    super();
    this.valutazione = valutazione;
    this.email = email;
    this.idOpera = idopera;
    this.commento=commento;
  }

  public int getValutazione() {
    return valutazione;
  }


  public void setValutazione(int valutazione) {
    this.valutazione = valutazione;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public int getIDopera() {
    return idOpera;
  }


  public void setIDopera(int idopera) {
    idOpera = idopera;
  }

  public String getCommento() {
    return commento;
  }

  public void setCommento(String commento) {
    this.commento = commento;
  }
}
