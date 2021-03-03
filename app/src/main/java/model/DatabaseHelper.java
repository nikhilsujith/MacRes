package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

//test
//this class is used to create the database
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MAC_database.db";

    public static final String USERS_TABLE_NAME = "users";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_FIRSTNAME = "first_name";
    public static final String COL_LASTNAME = "last_name";
    public static final String COL_UTAID = "utaID";
    public static final String COL_ROLE = "role";
    public static final String COL_PHONE = "phone";
    public static final String COL_EMAIL = "email";
    public static final String COL_ADDRESS = "street_address";
    public static final String COL_CITY = "city";
    public static final String COL_STATE = "state";
    public static final String COL_ZIPCODE = "zip_code";
    public static final String COL_NUMBER_NO_SHOWS = "no_shows";
    public static final String COL_REVOKED = "revoked";
    public static final String COL_VIOLATIONS = "violations";

    public static final String FACILITIES_TABLE_NAME = "facilities";
    public static final String COL_FACILITY_NAME = "facility_name";
    public static final String COL_FACILITY_TYPE = "facility_type";
    public static final String COL_TIME_INTERVAL = "time_interval";
    public static final String COL_DURATION = "duration";
    public static final String COL_VENUE = "venue";
    public static final String COL_DEPOSIT = "deposit";
    public static final String COL_AVAILABLE = "available";

    public static final String WORKING_HOURS_TABLE_NAME = "working_hours";
    public static final String COL_DAY = "day";
    public static final String COL_OPENING= "opening_hours";
    public static final String COL_CLOSING = "closing_hours";

    public static final String RESERVATIONS_TABLE_NAME = "reservations";
    public static final String COL_RESERVATION_ID = "reservation_id";
    public static final String COL_RESERVATION_USERNAME = "username";
    public static final String COL_RESERVATION_FACILITY_NAME = "facility_name";
    public static final String COL_DATE = "date";
    public static final String COL_TIME = "time";
    public static final String COL_RESERVATION_DEPOSIT = "deposit";

    public static final String NO_SHOWS_TABLE_NAME = "user_no_shows";
    public static final String COL_NO_SHOW_ID = "no_show_id";
    public static final String COL_NO_SHOW_USERNAME = "username";
    public static final String COL_NO_SHOW_FACILITY_NAME = "facility_name";
    public static final String COL_NO_SHOW_DATE = "date_of_no_show";
    public static final String COL_NO_SHOW_TIME = "time_of_no_show";

    public static final String VIOLATIONS_TABLE_NAME = "user_violations";
    public static final String COL_VIOLATION_ID = "violation_id";
    public static final String COL_VIOLAION_USERNAME = "username";
    public static final String COL_VIOLATION_FACILITY_NAME = "facility_name";
    public static final String COL_VIOLATION_DATE = "date_of_violation";
    public static final String COL_VIOLATION_TIME = "time_of_violation";
    public static final String COL_VIOLATION_DEPOSIT = "deposit";
    public static final String COL_VIOLATION_DESCRIPTION = "description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();  //to see the database created
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL() executes the query sent to it
        db.execSQL("CREATE TABLE " + USERS_TABLE_NAME + " ("
                + COL_USERNAME + " TEXT PRIMARY KEY NOT NULL, "
                + COL_PASSWORD + " TEXT NOT NULL, "
                + COL_FIRSTNAME + " TEXT NOT NULL, "
                + COL_LASTNAME + " TEXT NOT NULL, "
                + COL_UTAID + " TEXT NOT NULL, "    //uta id's are unique but if a person wants to register twice (once as a user and another as a facility or admin) he will use his same id twice
                + COL_ROLE + " TEXT NOT NULL DEFAULT 'USER', "
                + COL_PHONE + " TEXT NOT NULL, "
                + COL_EMAIL + " TEXT NOT NULL, "
                + COL_ADDRESS + " TEXT NOT NULL, "
                + COL_CITY + " TEXT NOT NULL DEFAULT 'ARLINGTON', "
                + COL_STATE + " TEXT NOT NULL DEFAULT 'TX', "
                + COL_ZIPCODE + " TEXT NOT NULL, "
                + COL_NUMBER_NO_SHOWS + " INTEGER NOT NULL DEFAULT '0', "
                + COL_REVOKED + " TEXT NOT NULL DEFAULT 'NO', "
                + COL_VIOLATIONS + " TEXT NOT NULL DEFAULT 'NO');");

        db.execSQL("CREATE TABLE " + FACILITIES_TABLE_NAME + " ("
                + COL_FACILITY_NAME + " TEXT PRIMARY KEY NOT NULL, "
                + COL_FACILITY_TYPE + " TEXT NOT NULL, "
                + COL_TIME_INTERVAL + " TEXT NOT NULL DEFAULT 'SAME DAY', "
                + COL_DURATION + " TEXT NOT NULL DEFAULT '1 HOUR', "
                + COL_VENUE + " TEXT NOT NULL DEFAULT 'INDOOR', "
                + COL_DEPOSIT + " TEXT NOT NULL DEFAULT 'NO', "
                + COL_AVAILABLE + "TEXT NOT NULL DEFAULT 'YES' );");

        db.execSQL("CREATE TABLE " + WORKING_HOURS_TABLE_NAME + " (" + COL_DAY
                + " TEXT PRIMARY KEY NOT NULL, "
                + COL_OPENING + " TEXT NOT NULL DEFAULT '6:00:00.00', "
                + COL_CLOSING + " TEXT NOT NULL DEFAULT '12:00:00.00');");

        db.execSQL("CREATE TABLE " + NO_SHOWS_TABLE_NAME + " ("
                + COL_NO_SHOW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COL_NO_SHOW_USERNAME + " TEXT NOT NULL, "
                + COL_NO_SHOW_FACILITY_NAME + " TEXT NOT NULL, "
                + COL_NO_SHOW_DATE + " TEXT NOT NULL, "
                + COL_NO_SHOW_TIME + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + RESERVATIONS_TABLE_NAME + " ("
                + COL_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL, "
                + COL_RESERVATION_USERNAME + " TEXT NOT NULL, "
                + COL_RESERVATION_FACILITY_NAME + " TEXT NOT NULL, "
                + COL_DATE + " TEXT NOT NULL DEFAULT CURRENT_DATE, "
                + COL_TIME + " TEXT NOT NULL DEFAULT CURRENT_TIME, "
                + COL_RESERVATION_DEPOSIT + " TEXT NOT NULL, "
                + "FOREIGN KEY(" + COL_RESERVATION_USERNAME + ") REFERENCES " + USERS_TABLE_NAME + "(" + COL_USERNAME + "), "
                + "FOREIGN KEY(" + COL_RESERVATION_FACILITY_NAME + ") REFERENCES " + FACILITIES_TABLE_NAME + "(" + COL_FACILITY_NAME + "));");

        db.execSQL("CREATE TABLE " + VIOLATIONS_TABLE_NAME + " ("
                + COL_VIOLATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL, "
                + COL_VIOLAION_USERNAME + " TEXT NOT NULL, "
                + COL_VIOLATION_FACILITY_NAME + " TEXT NOT NULL, "
                + COL_VIOLATION_DATE + " TEXT NOT NULL DEFAULT CURRENT_DATE, "
                + COL_VIOLATION_TIME + " TEXT NOT NULL DEFAULT CURRENT_TIME, "
                + COL_VIOLATION_DEPOSIT + " TEXT NOT NULL, "
                + COL_VIOLATION_DESCRIPTION + " TEXT NOT NULL, "
                + "FOREIGN KEY(" + COL_VIOLAION_USERNAME + ") REFERENCES " + USERS_TABLE_NAME + "(" + COL_USERNAME + "), "
                + "FOREIGN KEY(" + COL_VIOLATION_FACILITY_NAME + ") REFERENCES " + FACILITIES_TABLE_NAME + "(" + COL_FACILITY_NAME + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FACILITIES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORKING_HOURS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RESERVATIONS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NO_SHOWS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VIOLATIONS_TABLE_NAME);

        onCreate(db);
    }

    public boolean addFacility(String facilityName, String facilityType, String timeInterval, String duration, String venue, String deposit)
    {
        SQLiteDatabase db = this.getWritableDatabase();  //to see the database created

        //we need instance of the class ContentValue to insert values to the database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FACILITY_NAME, facilityName);
        contentValues.put(COL_FACILITY_TYPE, facilityType);
        contentValues.put(COL_TIME_INTERVAL, timeInterval);
        contentValues.put(COL_DURATION, duration);
        contentValues.put(COL_VENUE, venue);
        contentValues.put(COL_DEPOSIT, deposit);
        contentValues.put(COL_AVAILABLE, "YES");

        //now we insert the contentValues into the table
        long result = db.insert(FACILITIES_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insert_register(String username, String password, String firstName, String lastName, String utaID, String role, String phone, String email, String streetAddress, String city, String state, String zipCode)
    {
        SQLiteDatabase db = this.getWritableDatabase();  //to see the database created

        //we need instance of the class ContentValue to insert values to the database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_FIRSTNAME, firstName);
        contentValues.put(COL_LASTNAME, lastName);
        contentValues.put(COL_UTAID, utaID);
        contentValues.put(COL_ROLE, role);
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_ADDRESS, streetAddress);
        contentValues.put(COL_CITY, city);
        contentValues.put(COL_STATE, state);
        contentValues.put(COL_ZIPCODE, zipCode);

        //now we insert the contentValues into the table
        //if successful insert(...) returns the new data row inserted and it it failed it will return -1
        long result = db.insert(USERS_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    //Cursor will hold the read-write result of the query to be accessed by the code
    public Cursor login_query(String query)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor fetchSpecificUser(String query)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor fetchSpecificFacility(String query)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor fetchSpecificReservation(String query)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor fetchSpecificViolation(String query)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public boolean updateAdminProfile(String username, String firstName, String lastName, String utaID, String phone, String email, String streetAddress, String city, String state, String zipCode)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FIRSTNAME, firstName);
        contentValues.put(COL_LASTNAME, lastName);
        contentValues.put(COL_UTAID, utaID);
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_ADDRESS, streetAddress);
        contentValues.put(COL_CITY, city);
        contentValues.put(COL_STATE, state);
        contentValues.put(COL_ZIPCODE, zipCode);

        long result = db.update(USERS_TABLE_NAME, contentValues, "username = ?", new String[] {username});

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor admin_view_users()
    {
        Cursor cursor;
        String query = "SELECT * FROM users ORDER BY role DESC, last_name ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean admin_changeRole(String username, String role)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ROLE, role);

        long result = db.update(USERS_TABLE_NAME, contentValues, "username = ?", new String[] {username});

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean admin_revokeUser(String username, String revoke)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_REVOKED, revoke);

        long result = db.update(USERS_TABLE_NAME, contentValues, "username = ?", new String[]{username});

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean setNoShow(String username, String facilityName, String date, String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM users WHERE username LIKE '" + username + "'";
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.rawQuery(query, null);

        int numberOfNoShows;
        ContentValues contentValues1 = new ContentValues();

        while(cursor.moveToNext())
        {
            numberOfNoShows = Integer.parseInt(cursor.getString(12)) + 1;
            contentValues1.put(COL_NUMBER_NO_SHOWS, numberOfNoShows);
        }

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(COL_NO_SHOW_USERNAME, username);
        contentValues2.put(COL_NO_SHOW_FACILITY_NAME, facilityName);
        contentValues2.put(COL_NO_SHOW_DATE, date);
        contentValues2.put(COL_NO_SHOW_TIME, time);

        long result1 = db.update(USERS_TABLE_NAME, contentValues1, "username = ?", new String[]{username});
        long result2 = db.insert(NO_SHOWS_TABLE_NAME, null, contentValues2);

        if (result1 == -1 || result2 == -1)
            return false;
        else
            return true;
    }

    public boolean makeReport(String username, String facilityName, String date, String time, String description, String deposit)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COL_VIOLATIONS, "YES");

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(COL_VIOLAION_USERNAME, username);
        contentValues2.put(COL_VIOLATION_FACILITY_NAME, facilityName);
        contentValues2.put(COL_VIOLATION_DATE, date);
        contentValues2.put(COL_VIOLATION_TIME, time);
        contentValues2.put(COL_VIOLATION_DEPOSIT, deposit);
        contentValues2.put(COL_VIOLATION_DESCRIPTION, description);

        long result1 = db.update(USERS_TABLE_NAME, contentValues1, "username = ?", new String[]{username});
        long result2 = db.insert(VIOLATIONS_TABLE_NAME, null, contentValues2);

        if (result1 == -1 || result2 == -1)
            return false;
        else
            return true;
    }

    public boolean requestReservation(String username, String facilityName, String date, String time, String deposit)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_RESERVATION_USERNAME, username);
        contentValues.put(COL_RESERVATION_FACILITY_NAME, facilityName);
        contentValues.put(COL_DATE, date);
        contentValues.put(COL_TIME, time);
        contentValues.put(COL_RESERVATION_DEPOSIT, deposit);

        long result = db.insert(RESERVATIONS_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean fm_changeFacilityAvailability(String facilityName, String available)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_AVAILABLE, available);

        long result = db.update(FACILITIES_TABLE_NAME, contentValues, "facility_name = ?", new String[]{facilityName});

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor user_view_reservations(String username)
    {
        Cursor cursor;
        String query = "SELECT * FROM reservations WHERE username LIKE '" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor fm_view_reservations()
    {
        Cursor cursor;
        String query = "SELECT * FROM reservations ORDER BY reservation_id ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor view_facilities(String query)
    {
        Cursor cursor;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor view_violations(String username)
    {
        Cursor cursor;
        String query = "SELECT * FROM user_violations WHERE username LIKE '" + username + "' ORDER BY violation_id ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean cancelReservation(String reservationID)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        long result = db.delete(RESERVATIONS_TABLE_NAME, COL_RESERVATION_ID + "=" + reservationID, null);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean modifyReservation(String reservationID, String newDate, String newTime)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DATE, newDate);
        contentValues.put(COL_TIME, newTime);

        long result = db.update(RESERVATIONS_TABLE_NAME, contentValues, "reservation_id = ?", new String[]{reservationID});

        if (result == -1)
            return false;
        else
            return true;
    }
}
