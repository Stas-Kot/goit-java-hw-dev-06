package com.goit.feature.database;

import com.goit.feature.prefs.Prefs;
import com.goit.feature.utils.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public List<MaxProjectCountClient> findMaxProjectsClient() throws IOException {
        List<MaxProjectCountClient> clients = new ArrayList<>();
        String selectMaxProjectsClientFileName = new Prefs().getPref(Prefs.SELECT_MAX_PROJECTS_CLIENT_FILE_PATH);
        String selectSql = Files.readString(Paths.get(selectMaxProjectsClientFileName));

        Database database = Database.getInstance();

        try(Statement st = database.getConnection().createStatement()){
            try(ResultSet rs = st.executeQuery(selectSql)) {
                while(rs.next()) {
                    MaxProjectCountClient client = new MaxProjectCountClient();
                    String name = rs.getString("name");
                    Integer project_count = rs.getInt("project_count");
                    client.setName(name);
                    client.setProjectCount(project_count);
                    clients.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public List<LongestProject> findLongestProject() throws IOException {
        List<LongestProject> projects = new ArrayList<>();
        String longestProjectFileName = new Prefs().getPref(Prefs.SELECT_LONGEST_PROJECT_FILE_PATH);
        String selectSql = Files.readString(Paths.get(longestProjectFileName));

        Database database = Database.getInstance();

        try(Statement st = database.getConnection().createStatement()){
            try(ResultSet rs = st.executeQuery(selectSql)) {
                while(rs.next()) {
                    LongestProject project = new LongestProject();
                    Integer name = rs.getInt("name");
                    Integer month_count = rs.getInt("month_count");
                    project.setName(name);
                    project.setMonth_count(month_count);
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws IOException {
        List<MaxSalaryWorker> workers = new ArrayList<>();
        String maxSalaryWorkerFileName = new Prefs().getPref(Prefs.SELECT_MAX_SALARY_WORKER_FILE_PATH);
        String selectSql = Files.readString(Paths.get(maxSalaryWorkerFileName));

        Database database = Database.getInstance();

        try(Statement st = database.getConnection().createStatement()){
            try(ResultSet rs = st.executeQuery(selectSql)) {
                while(rs.next()) {
                    MaxSalaryWorker worker = new MaxSalaryWorker();
                    String name = rs.getString("name");
                    Integer salary = rs.getInt("salary");
                    worker.setName(name);
                    worker.setSalary(salary);
                    workers.add(worker);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws IOException {
        List<YoungestEldestWorker> workers = new ArrayList<>();
        String youngestEldestWorkersFileName = new Prefs().getPref(Prefs.SELECT_YOUNGEST_ELDEST_WORKERS_FILE_PATH);
        String selectSql = Files.readString(Paths.get(youngestEldestWorkersFileName));

        Database database = Database.getInstance();

        try(Statement st = database.getConnection().createStatement()){
            try(ResultSet rs = st.executeQuery(selectSql)) {
                while(rs.next()) {
                    YoungestEldestWorker worker = new YoungestEldestWorker();
                    String type = rs.getString("type");
                    String name = rs.getString("name");
                    LocalDate birthday = LocalDate.parse(rs.getString("birthday"));
                    worker.setName(name);
                    worker.setType(type);
                    worker.setBirthday(birthday);
                    workers.add(worker);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    public List<ProjectPrice> printProjectPrices() throws IOException {
        List<ProjectPrice> prices = new ArrayList<>();
        String projectPricesFileName = new Prefs().getPref(Prefs.SELECT_PROJECT_PRICES_FILE_PATH);
        String selectSql = Files.readString(Paths.get(projectPricesFileName));

        Database database = Database.getInstance();

        try(Statement st = database.getConnection().createStatement()){
            try(ResultSet rs = st.executeQuery(selectSql)) {
                while(rs.next()) {
                    ProjectPrice projectPrice = new ProjectPrice();
                    Integer id = rs.getInt("id");
                    Integer price = rs.getInt("price");
                    projectPrice.setId(id);
                    projectPrice.setPrice(price);
                    prices.add(projectPrice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prices;
    }
}
