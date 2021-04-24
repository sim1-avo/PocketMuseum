package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.opera.OperaBean;
import model.opera.OperaModelDM;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockPart;


public class OperaModelDMTC {

  @Test
  public void updateTestImmagineNull() throws SQLException, IOException {
    OperaBean opera = new OperaBean();
    OperaModelDM model;
    model = new OperaModelDM();
    opera.setId(1);
    opera.setNome("Il pagamento del tributo");
    opera.setDescrizione("Il Pagamento del tributo è un affresco di Masaccio facente parte della"
        + " decorazione della Cappella Brancacci nella chiesa di Santa Maria del Carmine a Firenze."
        + " Si tratta di uno dei dipinti più belli di tutti i tempi. L\'opera, databile al 1425 "
        + "circa (255x598 cm), ritrae una scena delle storie di San Pietro in cui Gesu\' lo invita"
        + " a pagare il tributo chiesto da un gabelliere per entrare nella citta\' di Cafarnao. Si "
        + "tratta della scena universalmente riconosciuta come una delle piu\' alte espressioni "
        + "dell\'arte masaccesca e del primo Rinascimento in generale.");
    opera.setAutore("Masaccio");
    opera.setStato("visibile");
    opera.setImmagine(null);
    byte[] b2 = new byte[20];
    MockPart part2 = new MockPart("copertina", "Copertina.jpg", b2);
    opera.setCopertinaPart(part2);
    model.doUpdate(opera);
  }

  @Test
  public void objectTest() throws SQLException, IOException {
    Object ob = null;
    OperaModelDM model = new OperaModelDM();
    model.doSave((OperaBean) ob);
    model.doUpdate((OperaBean) ob);
    model.doDelete((OperaBean) ob);
    model.doRetrieveByKey((Integer) ob);
    model.doRetrieveAll((String) ob);
    model.doRetrieveName((String) ob);
  }

  @Test
  public void TestDoRetrieveSenzaImmagini() throws SQLException, IOException {
    OperaBean opera = new OperaBean();
    OperaModelDM model;
    model = new OperaModelDM();
    opera.setId(0);
    opera.setNome("OperaTest");
    opera.setDescrizione("DescrizioneTest");
    opera.setAutore("Test");
    opera.setStato("visibile"); 
    opera.setImmaginePart(null);
    opera.setCopertinaPart(null);
    model.doSave(opera);
    ArrayList<OperaBean> temp =  (ArrayList<OperaBean>) model.doRetrieveName("OperaTest");
    model.doRetrieveByKey(temp.get(0).getId());
    model.doRetrieveAll("ASC");
    model.doDelete(temp.get(0));
  }
}
