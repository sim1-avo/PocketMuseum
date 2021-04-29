
package model.evento;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Part;


public class EventoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private String descrizione;
    private Part immaginePart;
    private String immagine;
    private Timestamp dataInizio;
    private Timestamp dataFine;

    /**
     * Costruttore Eavento.
     * @param id id Eavento
     * @param nome nomeEavento
     * @param descrizione descrizione Eavento
     * @param immaginePart immagine Eavento
     * @param immagine immagine Eavento
     * @param dataInizio data inizioEavento
     * @param dataFine data fine Eavento
     */
    public EventoBean(int id, String nome, String descrizione, Part immaginePart,
                      String immagine, Timestamp dataInizio, Timestamp dataFine) {
        super();
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.immaginePart = immaginePart;
        this.immagine = immagine;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public EventoBean() {}

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

    public Part getImmaginePart() {
        return immaginePart;
    }

    public void setImmaginePart(Part immaginePart) {
        this.immaginePart = immaginePart;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public Timestamp getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Timestamp dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Timestamp getDataFine() {
        return dataFine;
    }

    public void setDataFine(Timestamp dataFine) {
        this.dataFine = dataFine;
    }

}
