package fr.rontho.aqs.infrastructure.database.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.rontho.aqs.infrastructure.database.DatabaseObject;
import fr.rontho.aqs.infrastructure.database.TableOperations;
import fr.rontho.aqs.infrastructure.database.model.OtherDataBaseData;
import fr.rontho.aqs.infrastructure.database.model.SimpleDatabaseData;

/**
 * Created by troncaglia on 06/02/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private final List<Class<? extends DatabaseObject>> tableList = new ArrayList<>();
    private final TableOperations tableOperations;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.tableOperations = new OrmLiteTableOperations();
        tableList.add(SimpleDatabaseData.class);
        tableList.add(OtherDataBaseData.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            for (Class<? extends DatabaseObject> dataBaseTable : tableList) {
                tableOperations.createTableIfNotExists(connectionSource, dataBaseTable);
            }
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            for (Class<? extends DatabaseObject> dataBaseTable : tableList) {
                tableOperations.dropTable(connectionSource, dataBaseTable, true);
                onCreate(db, connectionSource);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
