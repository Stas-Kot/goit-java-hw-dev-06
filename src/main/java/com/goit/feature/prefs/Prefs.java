package com.goit.feature.prefs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Prefs {
    public static final String DB_JDBC_CONNECTION_URL = "dbUrl";
    public static final String DEFAULT_PREFS_FILENAME = "prefs.json";
    public static final String INIT_DB_SQL_FILE_PATH = "initDbSql";
    public static final String INSERT_SQL_FILE_PATH = "insertSql";
    public static final String SELECT_MAX_PROJECTS_CLIENT_FILE_PATH = "selectMaxProjectsClientSql";
    public static final String SELECT_LONGEST_PROJECT_FILE_PATH = "selectLongestProjectSql";
    public static final String SELECT_MAX_SALARY_WORKER_FILE_PATH = "selectMaxSalaryWorkerSql";
    public static final String SELECT_YOUNGEST_ELDEST_WORKERS_FILE_PATH = "selectYoungestEldestWorkersSql";
    public static final String SELECT_PROJECT_PRICES_FILE_PATH = "selectProjectPricesSql";

    private Map<String, Object> prefs = new HashMap<>();

    public Prefs() {
        this(DEFAULT_PREFS_FILENAME);
    }

    public Prefs(String fileName) {
        try {
            String json = Files.readString(Paths.get(fileName));

            TypeToken<?> typeToken = TypeToken.getParameterized(Map.class, String.class, Object.class);
            prefs = new Gson().fromJson(json, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPref(String key) {
        return prefs.get(key).toString();
    }
}
