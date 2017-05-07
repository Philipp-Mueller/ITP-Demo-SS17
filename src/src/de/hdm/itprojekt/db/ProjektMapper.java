package src.de.hdm.itprojekt.db;
import java.sql.*;

public class ProjektMapper {
	
private static ProjektMapper projektMapper = null;
	
	protected ProjektMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static ProjektMapper  projektMapper() {
        if (projektMapper == null) {
        	projektMapper = new ProjektMapper ();
        }
        return projektMapper;
	
    }
    
    public Projekt insert(Projekt p) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM projekt ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * p erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
        	p.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO projekt (id, ...) "
                + "VALUES (" + p.getId() + ",'"  "','"
                "')");
          }
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        /*
         * R�ckgabe, des evtl. korrigierten Projekts.
         * 
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte übergeben werden, wäre die Anpassung des Customer-Objekts auch
         * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
         * explizite Rückgabe von c ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
         */
        return p;
      }
    
    public Projekt update(Projekt p) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE profil " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + p.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Projekt p) zu wahren, geben wir p zur�ck
        return p;
      }

      /**
       * Löschen der Daten eines Projekt-Objekts aus der Datenbank.
       * 
       * @param c das aus der DB zu löschende "Objekt"
       */
      public void delete(Projekt p) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM projekt " + "WHERE id=" + p.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
