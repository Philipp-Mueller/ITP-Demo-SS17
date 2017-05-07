package src.de.hdm.itprojekt.db;
import java.sql.*;

import de.hdm.thies.bankProjekt.server.db.DBConnection;
import de.hdm.thies.bankProjekt.shared.bo.Customer;


public class BewerbungMapper {
	
	private static BewerbungMapper bewerbungMapper = null;
	
	protected BewerbungMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static BewerbungMapper bewerbungMapper() {
        if (bewerbungMapper == null) {
            bewerbungMapper = new BewerbungMapper();
        }
        return bewerbungMapper;
	
}
    public Bewerbung insert(Bewerbung b) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM bewerbung ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * b erh�lt den bisher maximalen, nun um 1 inkrementierten
             * Prim�rschl�ssel.
             */
            b.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO bewerbung (id, ...) "
                + "VALUES (" + b.getId() + ",'"  "','"
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
         * explizite Rückgabe von b ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return b;
      }

    public Bewerbung update(Bewerbung b) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE bewerbung " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + );

        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Bewerbung b) zu wahren, geben wir c zurück
        return b;
      }

      /**
       * Löschen der Daten eines Bewerbung-Objekts aus der Datenbank.
       * 
       * @param c das aus der DB zu löschende "Objekt"
       */
      public void delete(Bewerbung b) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM bewerbung " + "WHERE id=" + b.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    
    
    
}
