package fr.cityway.tagvalidator.infrastructure.database.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.DataOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.cityway.tagvalidator.infrastructure.database.DatabaseObject;

/**
 * A simple demonstration object we are creating and persisting to the database.
 */
public class SimpleDatabaseData implements DatabaseObject {

    // id is generated by the database and set on the object automagically
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(index = true)
    String string;
    @DatabaseField
    long millis;
    @DatabaseField
    Date date;
    @DatabaseField
    boolean even;

    SimpleDatabaseData() {
        // needed by ormlite
    }

    public SimpleDatabaseData(long millis) {
        this.date = new Date(millis);
        this.string = (millis % 1000) + "ms";
        this.millis = millis;
        this.even = ((millis % 2) == 0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", ").append("string=").append(string);
        sb.append(", ").append("millis=").append(millis);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.S");
        sb.append(", ").append("date=").append(dateFormatter.format(date));
        sb.append(", ").append("even=").append(even);
        return sb.toString();
    }
}