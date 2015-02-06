package fr.cityway.tagvalidator.infrastructure.database;

import java.sql.SQLException;

/**
 * Created by troncaglia on 06/02/2015.
 */
public class UnrecoverableDbException extends Throwable {

    public UnrecoverableDbException() {}

    public UnrecoverableDbException(final String error, final SQLException exception) {
        super(error, exception);
    }
}
