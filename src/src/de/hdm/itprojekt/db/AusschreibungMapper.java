package src.de.hdm.itprojekt.db;
import java.sql.*;

import de.hdm.thies.bankProjekt.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.Customer;

public class AusschreibungMapper {
	
	private static AusschreibungMapper ausschreibungMapper = null;
	
	protected AusschreibungMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static AusschreibungMapper  ausschreibungMapper() {
        if (ausschreibungMapper == null) {
        	ausschreibungMapper = new AusschreibungMapper ();
        }
        return ausschreibungMapper;
	
    }
    public Ausschreibung insert(Ausschreibung a) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM ausschreibung ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * c erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
        	a.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO ausschreibung (id, ...) "
                + "VALUES (" + a.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * Rückgabe, des evtl. korrigierten Customers.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte übergeben werden, wäre die Anpassung des Customer-Objekts auch
         * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
         * explizite Rückgabe von c ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return a;
      }
    
    public Ausschreibung update(Ausschreibung a) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE ausschreibung " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + ); // Hier SQL Statement

        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Ausschreibung a) zu wahren, geben wir a zur�ck
        return a;
      }

      /**
       * L�schen der Daten eines Ausschreibung-Objekts aus der Datenbank.
       * 
       * @param c das aus der DB zu löschende "Objekt"
       */
      public void delete(Ausschreibung a) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM ausschreibung " + "WHERE id=" +);
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }

}
