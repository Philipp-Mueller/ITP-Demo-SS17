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
           * Zun�chst schauen wir nach, welches der momentan n�hste
           * Prim�rschl�sselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM ausschreibung ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * a erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
        	a.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tats�chliche Einf�geoperation
            stmt.executeUpdate("INSERT INTO ausschreibung (id, ...) "
                + "VALUES (" + a.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * R�ckgabe, der evtl. korrigierten Ausschreibung.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte �bergeben werden, wäre die Anpassung des Auschreibung-Objekts auch
         * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
         * explizite R�ckgabe von a ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
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
       * Parameter a das aus der DB zu l�schende "Objekt"
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
