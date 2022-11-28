import com.goit.feature.client.Client;
import com.goit.feature.client.ClientService;
import com.goit.feature.database.Database;
import com.goit.feature.database.DatabaseInitService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        Database database = Database.getInstance();
        new DatabaseInitService().initDb();
        ClientService clientService = new ClientService(database.getConnection());

//        long id = clientService.create("Oleg");
//        System.out.println("id = " + id);

//        clientService.setName(7, "Vasja");
//        clientService.deleteById(8);

//        Client byId = clientService.getById(9);
//        System.out.println("byId = " + byId);

        List<Client> res = clientService.listAll();
        System.out.println("res = " + res);

    }
}
