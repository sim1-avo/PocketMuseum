package control.opera;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.opera.OperaBean;
import model.opera.OperaModelDM;

/**
 * Servlet implementation class GestioneOperaServlet.
 */
@WebServlet("/GestioneOperaServlet")
@MultipartConfig  
public class GestioneOperaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public GestioneOperaServlet() {
    super();
  }
  /**
   * Servlet gestione opera .
   */
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    final int id = Integer.parseInt(request.getParameter("id"));
    OperaModelDM model = new OperaModelDM();
    OperaBean opera = null;
    
    
    String nome = request.getParameter("nome");
    if (nome.length() < 3 || nome.length() >= 100) {
      throw new IllegalArgumentException("Lunghezza nome opera non rispettata");
    }
    if (!nome.matches("^(?![ .]+$)[a-zA-Z 0-9 , . ; : \" \\- \' à è ì í ò ù é "
        + "À, Á, È, É, Ì, Ò, Ù( )  ]*$")) {
      throw new IllegalArgumentException("Formato nome opera non rispettato");
    }
    
    String autore = request.getParameter("autore");
    if (autore.length() < 3 || autore.length() >= 50) {
      throw new IllegalArgumentException("Lunghezza autore opera non rispettata");
    }
    if (!autore.matches("^(?![ .]+$)[a-zA-Z 0-9 , . ; : \" \\- \' à è ì í ò ù é "
        + "À, Á, È, É, Ì, Ò, Ù ]*$")) {
      throw new IllegalArgumentException("Formato autore opera non rispettato");
    }
    
    String descrizione = request.getParameter("descrizione");
    if (descrizione.length() < 5 || descrizione.length() >= 5000) {
      throw new IllegalArgumentException("Lunghezza descrizione opera non rispettata");
    }

    if (!descrizione.matches("^[a-zA-Z 0-9 , . ; : \\n\\r \" \\- \' à è ì í ò ù é "
        + " À, Á, È, É, Ì, Ò, Ù ( ) ? ]*$")) {
      throw new IllegalArgumentException("Formato descrizione opera non rispettato");
    }
 
   
    
    String stato = request.getParameter("stato");
    
    Part immagine = request.getPart("immagine");
    if (immagine != null && !immagine.getSubmittedFileName().equals("")) {
      if (immagine.getSize() > 1024 * 1024) {
        throw new IllegalArgumentException("dimensioni immagine troppo grande");
      }
      if (!immagine.getSubmittedFileName().contains(".jpg") && !immagine.getSubmittedFileName()
          .contains(".jpeg") && !immagine.getSubmittedFileName().contains(".png")) {
        throw new IllegalArgumentException("formato immagine errato");
      }
    }

    Part copertina = request.getPart("copertina");
    if (copertina != null && !copertina.getSubmittedFileName().equals("")) {
      if (copertina.getSize() > 1024 * 1024) {
        throw new IllegalArgumentException("dimensioni copertina errato");
      }
      if (!copertina.getSubmittedFileName().contains(".jpg") && !copertina.getSubmittedFileName()
          .contains(".jpeg") && !copertina.getSubmittedFileName().contains(".png")) {
        throw new IllegalArgumentException("formato copertina errato");
      }
    }
    
    try {
      if (id != 0) {
        opera = model.doRetrieveByKey(id);
        opera.setNome(nome);
        opera.setAutore(autore);
        opera.setDescrizione(descrizione);
        opera.setStato(stato);
        if (immagine != null) {
          if (immagine.getSubmittedFileName() != "") {
            opera.setImmaginePart(immagine);
          } else {
            immagine = null;
          }
        }
        if (copertina != null) {
          if (copertina.getSubmittedFileName() != "") {
            opera.setCopertinaPart(copertina);
          } else {
            copertina = null;
          }
        }
        model.doUpdate(opera);
      } else {
        OperaBean opera2 = new OperaBean();
        opera2.setNome(nome);
        opera2.setAutore(autore);
        opera2.setDescrizione(descrizione);
        opera2.setStato(stato);
        if (immagine != null) {
          opera2.setImmaginePart(immagine);
        } else {
          opera2.setImmaginePart(null);
        }
        if (copertina != null) {
          opera2.setCopertinaPart(copertina);
        } else {
          opera2.setCopertinaPart(null);
        }
        model.doSave(opera2);
      }
      response.setContentType("text/html");
      request.removeAttribute("opere");
      response.sendRedirect("GestioneOpera.jsp");
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  
  }
}
