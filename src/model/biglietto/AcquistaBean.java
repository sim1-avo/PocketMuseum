package model.biglietto;

import java.io.Serializable;
import java.sql.Date;

public class AcquistaBean  extends BigliettoBean implements Serializable {
  
  private static final long serialVersionUID = 1L;
  private String email;
  private Date dataAcquisto;
  
  public AcquistaBean() {}

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }



  public Date getDataAcquisto() {
    return dataAcquisto;
  }

  public void setDataAcquisto(Date dataAcquisto) {
    this.dataAcquisto = dataAcquisto;
  }
}
