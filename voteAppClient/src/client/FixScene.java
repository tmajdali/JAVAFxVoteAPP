package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;













import java.util.ArrayList;

import objects.VoteObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class FixScene extends Application  { 
	Label myLabel ;
	String username;
	String password;
	String code =" ";
	String code02 = code;
	boolean logout = false;
	boolean logout02 = logout;
	ArrayList<VoteObject> al;
	String selected;
	
    ObservableList<String> votesNamesOL = FXCollections.observableArrayList() ;
    
    
	
	 
   
    
    
	     
		  public static void main(String[] args) { 
		 
		    launch(args);   
		  } 
		 
		  
		  public void start(Stage myStage) { 
			  
			  
			  
			 
			  Button logoutBTN = new Button("Log out"); 
			  Button dataBTN = new Button("get Data"); 
			  Button likeBTN = new Button("like");
			  Button nlikeBTN = new Button("don't like"); 
			  Button sendBTN = new Button("Send Data");
			  
			  logoutBTN.setDisable(true);
			  //loginBTN.setVisible(false);
			 
			  
		    myStage.setTitle("Use a JavaFX label."); 
		 
		 
		    
		    FlowPane rootNode02 = new FlowPane(Orientation.VERTICAL,10, 30); 
		  // rootNode02.setStyle("-fx-background-color: #696969");
		   
		    rootNode02.setAlignment(Pos.CENTER);
		    
		     
	      ListView<String> lv = new ListView<String>(votesNamesOL); 
    	  lv.setPrefSize(100, 70);
          MultipleSelectionModel<String> lvSEL = lv.getSelectionModel(); 
//		   
		   // lvSEL = lv.getSelectionModel();     
		    
		    
		    
		   
		    Scene myScene02 = new Scene(rootNode02, 300, 300); 
		    
		   
		    myStage.setScene(myScene02); 
		 
		   //===================
		    lv.setPrefSize(200, 150); 
		    dataBTN.setTranslateX(220);
		    dataBTN.setTranslateY(-120);
		    
		    logoutBTN.setTranslateX(10);
		    logoutBTN.setTranslateY(10);
		    
		    nlikeBTN.setTranslateX(-200);
		    nlikeBTN.setTranslateY(10);
		    
		    likeBTN.setTranslateX(-90);
		    likeBTN.setTranslateY(65);
		    
		    sendBTN.setTranslateX(2);
		    sendBTN.setTranslateY(180);
		   //=================================  
		     logoutBTN.setOnAction((ae) -> {
		    	logout = true;
		    	 
		     }
             ); 
		     
		     likeBTN.setOnAction((ae) -> {
			    	for(VoteObject vo : al)
			    	{
			    		if(vo.name.equals(selected))
			    		{
			    			vo.p = 1;
			    			vo.n=0;
			    		}
			    		 likeBTN.setDisable(true);
			    		 nlikeBTN.setDisable(false);
			    	}
			    	//test
			    	for(VoteObject vo : al){
			    		System.out.println(vo.name+ " "+ vo.p + " "+ vo.n);
			    	}
			     }
	             ); 
		     
		     nlikeBTN.setOnAction((ae) -> {
			    	for(VoteObject vo : al)
			    	{
			    		if(vo.name.equals(selected))
			    		{
			    			vo.p = 0;
			    			vo.n=1;
			    		}
			    		 nlikeBTN.setDisable(true);
			    		 likeBTN.setDisable(false);
			    	}
			    	//test
			    	for(VoteObject vo : al){
			    		System.out.println(vo.name+ " "+ vo.p + " "+ vo.n);
			    	}
			     }
	             ); 

		    dataBTN.setOnAction(new EventHandler<ActionEvent>() {
		    	    @Override 
		    	    public void handle(ActionEvent o) {
		    	       
		    	    	code = "data";
		    	    	try {
							Thread.sleep(5000);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	    	

		    		    
		    		    
				    	//votesNamesOL=FXCollections.observableList(votesNameAL);
				    	
				    	//rootNode02.getChildren().add(lv);
		    	    	 lv.setDisable(false);
				    	 
		    	    }
		    	});
		   
		     
		     
		    
		    
		     lvSEL.selectedItemProperty().addListener( 
		    		 new ChangeListener<String>() { 
		    			 public void changed(ObservableValue<? extends String> changed, 
		    					 String oldVal, String newVal) { 

		    				 // Display the selection. 
		    				 //System.out.println(newVal);
		    				 selected = newVal;
		    				 Platform.runLater(new Runnable() {
				    			 @Override
				    			 public void run() {
				    				 
				    				 for(VoteObject vo : al){
				    					 if(vo.name.equals(newVal)){
				    						 if(vo.p == 1){
				    							 likeBTN.setDisable(true);
				    						 }else{
				    							 likeBTN.setDisable(false);
				    						 }
				    						 
				    						 if(vo.n == 1){
				    							 nlikeBTN.setDisable(true);
				    						 }else{
				    							 nlikeBTN.setDisable(false);
				    						 }
				    						 
				    						 
				    						 
				    					 }
				    				 }
				    					 


				    				 
				    			 }
				    		 });
				    		 try {
				    			 Thread.sleep(1000);
				    		 } catch (InterruptedException e) {

				    			 e.printStackTrace();
				    		 }
		    				 
		    				 
		    				// 
		    			 } 
		    		 }); 



		     
		     rootNode02.getChildren().addAll(lv,dataBTN,logoutBTN,sendBTN,likeBTN,nlikeBTN);
             lv.setDisable(true);

		     myStage.show(); 
		  }
}
