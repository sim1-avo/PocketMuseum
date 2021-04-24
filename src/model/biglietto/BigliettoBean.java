package model.biglietto;

import java.io.Serializable;
import java.sql.Timestamp;

public class BigliettoBean implements Serializable {
  private String codice;
  private float costo;
  private Timestamp inizioTurno;
  private Timestamp fineTurno;


  public BigliettoBean() {}
  /**
   * Costruttore Biglietto.
   * @param codice id biglietto
   * @param costo prezzo biglietto
   * @param inizioTurno inizio validit� biglietto
   * @param fineTurno fine validit� biglietto
   */
  
  public BigliettoBean(String codice, float costo, Timestamp inizioTurno, Timestamp fineTurno) {
    super();
    this.codice = codice;
    this.costo = costo;
    this.fineTurno = fineTurno;
    this.inizioTurno = inizioTurno;
  }

  public String getCodice() {
    return codice;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public float getCosto() {
    return costo;
  }

  public void setCosto(float costo) {
    this.costo = costo;
  }

  public Timestamp getInizioTurno() {
    return inizioTurno;
  }

  public void setInizioTurno(Timestamp inizioTurno) {
    this.inizioTurno = inizioTurno;
  }

  public Timestamp getFineTurno() {
    return fineTurno;
  }

  public void setFineTurno(Timestamp fineTurno) {
    this.fineTurno = fineTurno;
  }
}

