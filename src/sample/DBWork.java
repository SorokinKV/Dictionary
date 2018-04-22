package sample;

import java.sql.*;
import java.util.ArrayList;

public class DBWork {
    private static Connection connection;
    private static Statement statement;


    public static String dbWork(String word) {
        try {
            connect();
            //  String res =  showTable(word);
            return showTable(word);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return "Не найдено";
    }

    public static ArrayList getArr(){
        ArrayList arr = new ArrayList();
        try {
            connect();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT name FROM sqlite_master WHERE type='table';");
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                String testString = (result.getString(1));
                if(testString.contains("Caches")) {
                    System.out.println(result.getString(1));
                    PreparedStatement preparedStatementL = null;
                    preparedStatementL = connection.prepareStatement("SELECT * FROM "+ result.getString(1) +" WHERE type='table';");
                    ResultSet resultL = preparedStatement.executeQuery();
                    System.out.println();

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            disconnect();
        }
        return arr;

    }

    private static String showTable (String value) throws SQLException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementExt = null; //перевод со стилями
        String firstLetter = "Caches" + (value.substring(0, 1).toUpperCase());
        preparedStatement = connection.prepareStatement("SELECT * FROM " + firstLetter + " WHERE word = ?;");
        preparedStatement.setString(1, value);
        ResultSet result = preparedStatement.executeQuery();
        //расширенный перевод со стилями
        String idTrans = String.valueOf(result.getInt(4));
        preparedStatementExt = connection.prepareStatement("SELECT * FROM Translations WHERE id = ?;");
        preparedStatementExt.setString(1, idTrans);


   /*         System.out.println("id_" + result.getInt(1) + "\n Word: " + result.getString(2) + "\n Перевод: \n"
                    + result.getString(3) + " ");*/
        String translate = result.getString(3);
        return translate;

//        System.out.println("\n расширенный перевод: \n" + resultTrans.getString(3) + " ");
    }

    public static void connect () throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        System.out.println("Connecting.....\n");
        connection = DriverManager.getConnection("jdbc:sqlite:en-ru.sqlite3");
        System.out.println("Connected... \n");
        statement = connection.createStatement();
    }

    public static void disconnect () {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

