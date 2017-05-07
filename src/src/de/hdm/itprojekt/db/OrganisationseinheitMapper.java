package src.de.hdm.itprojekt.db;
import java.sql.*;

public class OrganisationseinheitMapper {
	
private static OrganisationseinheitMapper organisationseinheitMapper = null;
	
	protected OrganisationseinheitMapper(){ //gesch�tzter Konstruktor, um zu verhindern dass mit "New" neue Instanzen erzeugt werden k�nnen.
		
	}
    public static OrganisationseinheitMapper  organisationseinheitMapper() {
        if (organisationseinheitMapper == null) {
        	organisationseinheitMapper = new OrganisationseinheitMapper();
        }
        return organisationseinheitMapper;
	
    }
    
    public Organisationseinheit insert(Organisationseinheit o) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          /*
           * Zunächst schauen wir nach, welches der momentan höchste
           * Primärschlüsselwert ist.
           */
          ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
              + "FROM organisationseinheit ");

          // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
          if (rs.next()) {
            /*
             * c erhält den bisher maximalen, nun um 1 inkrementierten
             * Primärschlüssel.
             */
            o.setId(rs.getInt("maxid") + 1);

            stmt = con.createStatement();

            // Jetzt erst erfolgt die tatsächliche Einfügeoperation
            stmt.executeUpdate("INSERT INTO organisationseinheit (id, ...) "
                + "VALUES (" + o.getId() + ",'"  "','"
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
        return o;
      }

    public Organisationseinheit update(Organisationseinheit o) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("UPDATE organisationseinheit " + "SET firstName=\""
              +  + "\", " + "lastName=\"" +  + "\" "
              + "WHERE id=" + o.getId()
        }
        catch (SQLException e) {
          e.printStackTrace();
        }

        // Um Analogie zu insert(Organisationseinheit o) zu wahren, geben wir bewertung zur�ck
        return o;
      }

      /**
       * Löschen der Daten eines Orga-Objekts aus der Datenbank.
       * 
       * @param c das aus der DB zu löschende "Objekt"
       */
      public void delete(Organisationseinheit o) {
        Connection con = DBConnection.connection();

        try {
          Statement stmt = con.createStatement();

          stmt.executeUpdate("DELETE FROM organisationseinheit " + "WHERE id=" + o.getId());
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
}
