package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    TextField inputText;
    @FXML
    TextArea result,resultWord;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        String[] pWords = {"home", "love", "word", "world"};
        //     DBWork arr = new DBWork();
        DBWork.getArr();

        TextFields.bindAutoCompletion(inputText,pWords);

    }

    public void addBtnClick(){
        String res = "";
        //System.out.println("Нажато"+ System.currentTimeMillis());
        String input1 = inputText.getText();
        inputText.clear();

        //  result.clear();
        DBWork base = new DBWork();

        //перенос строк
        String gett = base.dbWork(input1);
        String[] arr = gett.split("  ");

        for (int i = 0; i < arr.length; i++) {
            res = res + arr[i]+ "\n" ;
        }
        //   resultWord.setStyle("-fx-font-alignment: center");
        //
        resultWord.setPrefHeight(20);
        //    resultWord.setPrefColumnCount(1);
        resultWord.setText(input1);
        result.setText("\n"+res);
        //result.setText(input1 + "\n"+base.dbWork(input1));


    }




}
