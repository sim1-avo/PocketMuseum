
package model.evento;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EventoModel<T> {
    public void doSave(T evento) throws SQLException, IOException;

    public ArrayList<T> doRetrieveAll(String order) throws SQLException;

    public boolean doDelete(T evento) throws SQLException;

    public T doRetrieveByName(String nomeEvento) throws SQLException;

    public boolean doUpdate(T evento) throws SQLException, IOException;

}
