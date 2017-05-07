package src.de.hdm.itprojekt.db;
import java.sql.*;

public class ProjektmarktplatzMapper {

	private static ProjektmarktplatzMapper projektmarktplatzMapper = null;
	
	protected ProjektmarktplatzMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static ProjektmarktplatzMapper  projektmarktplatzMapper() {
        if (projektmarktplatzMapper == null) {
        	projektmarktplatzMapper = new ProjektmarktplatzMapper ();
        }
        return projektmarktplatzMapper;
	
    }
    
    public Projektmarktplatz insert(Projektmarktplatz projektmarkt) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM projektmarktplatz ");

          // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * projektmarkt erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
        	projektmarkt.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO projektmarktplatz (id, ...) "
                + "VALUES (" + projektmarkt.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * Rückgabe, des evtl. korrigierten Projektmarktplatzes.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte übergeben werden, wäre die Anpassung des Customer-Objekts auch
         * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
         * explizite Rückgabe von c ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return projektmarkt;
      }
	
    public Projektmarktplatz update(Projektmarktplatz projektmarkt) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE projektmarktplatz " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + projektmarkt.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Projektmarktplatz projektmarkt) zu wahren, geben wir projektmarkt zur�ck
        return projektmarkt;
      }

      /**
       * Löschen der Daten eines Projektmarktplatz-Objekts aus der Datenbank.
       * 
       * @param c das aus der DB zu löschende "Objekt"
       */
      public void delete(Projektmarktplatz projektmarkt) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM projektmarktplatz " + "WHERE id=" + projektmarkt.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
	
}
