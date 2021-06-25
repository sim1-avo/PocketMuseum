package model;

import java.io.IOException; 
import java.sql.SQLException;
import java.sql.Timestamp;
import model.evento.EventoBean;
import model.evento.EventoModelDM;
import org.junit.jupiter.api.Test;

public class EventoModelDMTC {

  @Test
  public void objectTest() throws SQLException, IOException {
    EventoModelDM model = new EventoModelDM();
    Object ob = null;
    model.doDelete((EventoBean) ob);
    model.doRetrieveAll((String) ob);
    model.doRetrieveByName((String) ob);
    model.doSave((EventoBean) ob);
  }

  @Test
  public void TestDoRetrieveNoImage() throws SQLException, IOException {
    EventoModelDM model;
    model = new EventoModelDM();
    EventoBean evento = new EventoBean();
    evento.setNome("EventoTest");
    evento.setDescrizione("EventoTest");
    evento.setDataFine(new Timestamp(2025, 12, 12, 9, 9, 9, 9));
    evento.setDataInizio(new Timestamp(2020, 12, 12, 9, 9, 9, 9));
    evento.setImmaginePart(null);
    model.doSave(evento);
    model.doRetrieveAll("DESC");
    model.doDelete(evento);
  }
}
