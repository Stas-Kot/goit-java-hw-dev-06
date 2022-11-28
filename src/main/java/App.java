import com.goit.feature.client.ClientService;
import com.goit.feature.database.Database;
import com.goit.feature.database.DatabaseInitService;
import com.goit.feature.database.DatabaseQueryService;
import com.goit.feature.utils.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        Database database = Database.getInstance();
        new DatabaseInitService().initDb(database);

        long id = new ClientService(database.getConnection()).create("Oleg");
        System.out.println("id = " + id);

//        DatabaseQueryService databaseQueryService = new DatabaseQueryService();
//
//        List<MaxProjectCountClient> maxProjectCountClients = databaseQueryService.findMaxProjectsClient();
//        maxProjectCountClients.forEach(System.out::println);
//
//        List<LongestProject> longestProjects = databaseQueryService.findLongestProject();
//        System.out.println("longestProjects = " + longestProjects);
//
//        List<MaxSalaryWorker> maxSalaryWorkers = databaseQueryService.findMaxSalaryWorker();
//        System.out.println("maxSalaryWorkers = " + maxSalaryWorkers);
//
//        List<YoungestEldestWorker> youngestEldestWorkers = databaseQueryService.findYoungestEldestWorkers();
//        youngestEldestWorkers.forEach(System.out::println);
//
//        List<ProjectPrice> projectPrices = databaseQueryService.printProjectPrices();
//        projectPrices.forEach(System.out::println);
    }
}
