package control.utente;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TemplateDM;
import model.utente.UtenteBean;
import model.utente.UtenteModelDM;

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public RegistrazioneServlet() {
    super();
  }
  /**
   * Servlet registrazione utente.
   */
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    TemplateDM model = new UtenteModelDM();
    String email;
    String password;
    String verificaPassword;
    email = request.getParameter("email");
    password = request.getParameter("Password");
    verificaPassword = request.getParameter("VerificaPassword");
    String nome;
    nome = request.getParameter("Nome");
    String cognome;
    cognome = request.getParameter("Cognome");
    String cf;
    cf = request.getParameter("CF");
    UtenteBean bean = new UtenteBean();
    if (email.length() == 0) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè"
          + " il campo e-mail è vuoto");
    } else if (email.length() <= 11) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo"
          + " e-mail non rispetta la lunghezza minima");
    } else if (email.length() >= 32) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo "
          + "e-mail non rispetta la lunghezza massima");
    } else if (!email.matches("^[A-Z a-z \\- \\_ 0-9 \\.]+@{1}[A-Z a-z \\- \\_ "
        + "0-9 \\.]+[\\.]{1}[a-z A-Z]{2,}$")) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè"
          + " il campo e-mail non rispetta il formato.");
    } else {
      bean.setEmail(email);
    }
    if (password.length() == 0) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo"
            + " password è vuoto");
    } else if (password.length() < 8) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo"
            + " password non rispetta la lunghezza minima");
    } else if (password.length() >= 32) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo "
            + "password non rispetta la lunghezza massima");
    } else {
      if (verificaPassword.length() == 0) {
        throw new IllegalArgumentException("La registrazione non va a buon fine poichè "
            + "il campo verifica password è vuoto");
      } else if (verificaPassword.length() < 8) {
        throw new IllegalArgumentException("La registrazione non va a buon fine poichè "
                + "il campo verifica password non rispetta la lunghezza minima");
      } else if (verificaPassword.length() >= 32) {
        throw new IllegalArgumentException("La registrazione non va a buon fine poichè "
                + "il campo verifica password non rispetta la lunghezza massima");
      } else if (!(verificaPassword.equals(password))) {
        throw new IllegalArgumentException("La registrazione non va a buon fine poichè "
                + "il campo verifica password e il campo password non corrispondono");
      } else {
        bean.setPassword(password);
      }
    }
    if (nome.length() == 0) {
      throw new IllegalArgumentException("La registrazione non va a"
          + " buon fine poichè il campo nome è vuoto");
    } else if (nome.length() >= 20) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo nome"
          + " non rispetta la lunghezza");
    } else if (!nome.matches("[A-Za-z \']+")) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo nome"
            + " non rispetta il formato");
    } else {
      bean.setNome(nome);
    }
    if (cognome.length() == 0) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè"
          + " il campo cognome è vuoto");
    } else if (cognome.length() >= 20) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè"
            + " il campo cognome non rispetta la lunghezza");
    }  else if (!cognome.matches("[A-Za-z']+")) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè "
          + "il campo cognome non rispetta il formato");
    } else {
      bean.setCognome(cognome);
    }
    if (cf.length() == 0) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè "
          + "il campo C.F. è vuoto");
    } else if (cf.length() < 16) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo C.F."
          + " non rispetta la lunghezza minima");
    } else if (cf.length() > 16) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo C.F."
          + " non rispetta la lunghezza massima");
    } else if (!cf.matches("[A-Z0-9]{16}")) {
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè il campo C.F. "
          + "non rispetta il formato");
    } else {
      bean.setCf(cf);  
    }
    bean.setTipo("reg");
    try {
      model.salva(bean);
    } catch (Exception  e) {
      System.out.println("Error: " + e.getMessage());
      request.setAttribute("error", e.getMessage());
      throw new IllegalArgumentException("La registrazione non va a buon fine poichè l'e-mail"
          + " utilizzata è gia presente nel sistema");
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher("./Log.jsp");
    dispatcher.forward(request, response);
  }
}
