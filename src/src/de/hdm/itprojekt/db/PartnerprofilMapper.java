package src.de.hdm.itprojekt.db;
import java.sql.*;

public class PartnerprofilMapper {
	
private static PartnerprofilMapper partnerprofilMapper = null;
	
	protected PartnerprofilMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static PartnerprofilMapper  partnerprofilMapper() {
        if (partnerprofilMapper == null) {
        	partnerprofilMapper = new PartnerprofilMapper();
        }
        return partnerprofilMapper;
	
    }
    public Partnerprofil insert(Partnerprofil profil) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM partnerprofil ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * c erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
        	profil.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO partnerprofil (id, ...) "
                + "VALUES (" + profil.getId() + ",'"  "','"
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
        return profil;
      }
    
    public Partnerprofil update(Partnerprofil profil) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE partnerprofil " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + profil.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Partnerprofil profil) zu wahren, geben wir bewertung zur�ck
        return profil;
      }

      /**
       * Löschen der Daten eines Profil-Objekts aus der Datenbank.
       * 
       * @param c das aus der DB zu löschende "Objekt"
       */
      public void delete(Partnerprofil profil) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM partnerprofil " + "WHERE id=" + profil.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
