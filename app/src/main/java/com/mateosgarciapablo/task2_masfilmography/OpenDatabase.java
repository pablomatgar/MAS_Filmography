//Pablo Mateos García

package com.mateosgarciapablo.task2_masfilmography;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class OpenDatabase extends SQLiteOpenHelper{

    //Define database name and version

    private static final String DATABASE_NAME = "mas_filmography.db";
    private static final int DATABASE_VERSION = 1;

    OpenDatabase(Context context){
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public ArrayList<String> listAll(SQLiteDatabase sqdb, String table){

        //List all the records of a table

        ArrayList<String> list = new ArrayList<>();

        String result ="";

        Cursor c = sqdb.rawQuery("SELECT * FROM " + table, null);
        if (c != null){
            if (c.moveToFirst()){
                do{
                    String idFilm = c.getString(0);
                    result = result + idFilm + " - ";
                    String nameFilm = c.getString(1);
                    result = result + nameFilm;

                    list.add(result);

                    result="";
                }while (c.moveToNext());
            }
        }
        c.close();
        return list;
    }

    public ArrayList<String> findByID(SQLiteDatabase sqdb, String id, String table, String by){

        //Find a record from a table by its ID

        ArrayList<String> list = new ArrayList<>();

        Cursor c = sqdb.rawQuery("SELECT * FROM " + table + " WHERE " + by + " = '" + id + "'", null);
        if (c != null){
            if (c.moveToFirst()){
                do{
                    for(int i=0;i<c.getColumnCount();i++){
                        list.add(c.getString(i));
                    }
                }while (c.moveToNext());
            }
            else{
                //result = "There are no records.";
            }
        }
        c.close();
        return list;
    }

    public ArrayList<String> searchData(SQLiteDatabase sqdb, String searchData, String by, String table){

        //Search data with the search engine

        ArrayList<String> list = new ArrayList<>();

        String result ="";

        Cursor c = sqdb.rawQuery("SELECT * FROM " + table + " WHERE " + by + " like '%" + searchData + "%'", null);
        if (c != null){
            if (c.moveToFirst()){
                do{
                    String idFilm = c.getString(0);
                    result = result + idFilm + " - ";
                    String nameFilm = c.getString(1);
                    result = result + nameFilm;

                    list.add(result);

                    result="";
                } while (c.moveToNext());
            }
            else{
                result = "There are no records";
            }
        }
        c.close();
        return list;
    }


    public Boolean updateData(SQLiteDatabase sqdb, Context ctx, String id, String name, String originalName, String nameCharacter, String dateRelease, String dateEnd, String seasons, String episodes, String episodesCharacter, String creator, String runtime, String language, String genre){

        //Update record of an specific ID of the series table

        Boolean result = false;

        String modify = "";

        String[] fields = ctx.getResources().getStringArray(R.array.series_fields);

        modify = "UPDATE SERIES " +
                "SET " + translateIntoDB(fields[1]) + " = '" +name + "', " +
                translateIntoDB(fields[2]) + " = '" +originalName + "', " +
                translateIntoDB(fields[3]) + " = '" +nameCharacter + "', " +
                translateIntoDB(fields[4]) + " = '" +dateRelease + "', " +
                translateIntoDB(fields[5]) + " = '" +dateEnd + "', " +
                translateIntoDB(fields[6]) + " = '" +seasons + "', " +
                translateIntoDB(fields[7]) + " = '" +episodes + "', " +
                translateIntoDB(fields[8]) + " = '" +episodesCharacter + "', " +
                translateIntoDB(fields[9]) + " = '" +creator + "', " +
                translateIntoDB(fields[10]) + " = '" +runtime + "', " +
                translateIntoDB(fields[11]) + " = '" +language + "', " +
                translateIntoDB(fields[12]) + " = '" +genre + "' " +
                "WHERE " + translateIntoDB(fields[0]) + " = " + id;

        sqdb.execSQL(modify);

        return result;
    }

    public Boolean updateData(SQLiteDatabase sqdb, String table, Context ctx, String id, String name, String originalName, String nameCharacter, String dateRelease, String director, String runtime, String language, String genre){

        //Update record of an specific ID of the shorts or films table

        Boolean result = false;

        String modify = "";

        String[] fields;

        if(table.equals("films")){
            fields = ctx.getResources().getStringArray(R.array.film_fields);
        }
        else{
            fields = ctx.getResources().getStringArray(R.array.short_fields);
        }

        modify = "UPDATE " + table +
                " SET " + translateIntoDB(fields[1]) + " = '" +name + "', " +
                translateIntoDB(fields[2]) + " = '" +originalName + "', " +
                translateIntoDB(fields[3]) + " = '" +nameCharacter + "', " +
                translateIntoDB(fields[4]) + " = '" +dateRelease + "', " +
                translateIntoDB(fields[5]) + " = '" +director + "', " +
                translateIntoDB(fields[6]) + " = '" +runtime + "', " +
                translateIntoDB(fields[7]) + " = '" +language + "', " +
                translateIntoDB(fields[8]) + " = '" +genre + "' " +
                "WHERE " + translateIntoDB(fields[0]) + " = " + id;

        sqdb.execSQL(modify);

        return result;
    }

    public Boolean deleteData(SQLiteDatabase sqdb, String id, String table, Context ctx){

        //Delete an specific record by its ID

        Boolean result = false;

        String idName = "";

        switch (table){
            case "films": idName = "idFilm";
                break;
            case "series": idName = "idSeries";
                break;
            case "shorts": idName = "idShort";
                break;
        }

        String delete = "";

        delete = "DELETE FROM " + table + " WHERE " + idName + " = '" + id + "'";;

        sqdb.execSQL(delete);

        return result;
    }

    public Boolean addData(SQLiteDatabase sqdb, Context ctx, String name, String originalName, String nameCharacter, String dateRelease, String dateEnd, String seasons, String episodes, String episodesCharacter, String creator, String runtime, String language, String genre){

        //Add record to the series table

        Boolean result = false;

        String addNew = "";

        String[] fields = ctx.getResources().getStringArray(R.array.series_fields);

        addNew = "INSERT INTO SERIES(" +
                translateIntoDB(fields[1]) + ", " +
                translateIntoDB(fields[2]) + ", " +
                translateIntoDB(fields[3]) + ", " +
                translateIntoDB(fields[4]) + ", " +
                translateIntoDB(fields[5]) + ", " +
                translateIntoDB(fields[6]) + ", " +
                translateIntoDB(fields[7]) + ", " +
                translateIntoDB(fields[8]) + ", " +
                translateIntoDB(fields[9]) + ", " +
                translateIntoDB(fields[10]) + ", " +
                translateIntoDB(fields[11]) + ", " +
                translateIntoDB(fields[12]) + ") " +
                "VALUES (" +
                "'"+ name +
                "','"+ originalName +
                "','"+ nameCharacter +
                "','"+ dateRelease +
                "','"+ dateEnd +
                "','"+ seasons +
                "','"+ episodes +
                "','"+ episodesCharacter +
                "','"+ creator +
                "','"+ runtime +
                "','"+ language +
                "','"+ genre +
                "')";
        sqdb.execSQL(addNew);

        return result;
    }

    public Boolean addData(SQLiteDatabase sqdb, String table, Context ctx, String name, String originalName, String nameCharacter, String dateRelease, String director, String runtime, String language, String genre){

        //Add record to the films or shorts table

        Boolean result = false;

        String addNew = "";

        String[] fields;

        if(table.equals("films")){
            fields = ctx.getResources().getStringArray(R.array.film_fields);
        }
        else{
            fields = ctx.getResources().getStringArray(R.array.short_fields);
        }

        addNew = "INSERT INTO " + table + "(" +
                translateIntoDB(fields[1]) + ", " +
                translateIntoDB(fields[2]) + ", " +
                translateIntoDB(fields[3]) + ", " +
                translateIntoDB(fields[4]) + ", " +
                translateIntoDB(fields[5]) + ", " +
                translateIntoDB(fields[6]) + ", " +
                translateIntoDB(fields[7]) + ", " +
                translateIntoDB(fields[8]) + ") " +
                "VALUES (" +
                "'"+ name +
                "','"+ originalName +
                "','"+ nameCharacter +
                "','"+ dateRelease +
                "','"+ director +
                "','"+ runtime +
                "','"+ language +
                "','"+ genre +
                "')";
        sqdb.execSQL(addNew);

        return result;
    }

    private String translateIntoDB(String str){

        //Translates the 'user-friendly' strings into the database field names

        switch (str){
            case "Film ID": return "idFilm";
            case "Film Name": return "nameFilm";
            case "Original Film Name": return "originalNameFilm";
            case "Character Name": return "nameCharacter";
            case "Release Date": return "dateRelease";
            case "Director": return "director";
            case "Duration": return "duration";
            case "Language": return "language";
            case "Genre": return "genre";
            case "Series ID": return "idSeries";
            case "Series Name": return "nameSeries";
            case "Original Series Name": return "originalNameSeries";
            case "Seasons": return "seasons";
            case "Episodes": return "episodes";
            case "Episodes of Appareance of the Character": return "episodesAppareancesCharacter";
            case "Runtime": return "runtime";
            case "Creator": return "creator";
            case "End Date": return "dateEnd";
            case "Short ID": return "idShort";
            case "Short Name": return "nameShort";
            case "Original Short Name": return "originalNameShort";
        }
        return "";
    }
}
