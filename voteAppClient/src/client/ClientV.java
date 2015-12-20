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
 
public class ClientV extends Application  { 
	Label myLabel ;
	String username;
	String password;
	String code =" ";
	String code02 = code;
	boolean logout = false;
	boolean logout02 = logout;
    static ArrayList<VoteObject> al;
	String selected;
	 
    ObservableList<String> votesNamesOL = FXCollections.observableArrayList() ;
    
    
	String getCode()
	{
		return code;
	}
	
	boolean getlogout()
	{
		return logout;
	}
	 
    private BooleanProperty booleanProperty = new SimpleBooleanProperty(); 
    private StringProperty stringProperty02 = new SimpleStringProperty(" ");
    private StringProperty stringProperty03 = new SimpleStringProperty(" ");
    Server02 myObject = new Server02();
    
    
	     
		  public static void main(String[] args) { 
		 
		    launch(args);   
		  } 
		 
		  
		  public void start(Stage myStage) { 
			  
			  Label status = new Label("---");
			 
			  TextField userTF = new TextField();  
			  TextField passTF = new TextField();  
			  
			  Button loginBTN = new Button("Log in"); 
			  Button logoutBTN = new Button("Log Out"); 
			  Button dataBTN = new Button("Get Data"); 
			  Button likeBTN = new Button("Like");
			  Button nlikeBTN = new Button("Don't Like"); 
			  Button sendBTN = new Button("Send Data"); 
			  
			  logoutBTN.setDisable(true);
			
			  userTF.setPromptText("Enter Username");
			  passTF.setPromptText("Enter Password");
			  
		    myStage.setTitle("Use a JavaFX label."); 
		 
		 
		    FlowPane rootNode = new FlowPane(Orientation.VERTICAL,10, 30); 
		    FlowPane rootNode02 = new FlowPane(Orientation.VERTICAL,10, 30); 
		   // rootNode.setStyle("-fx-background-color: #000000");
		    rootNode.setAlignment(Pos.CENTER); 
		    rootNode02.setAlignment(Pos.CENTER);
		    
		     
	      ListView<String> lv = new ListView<String>(votesNamesOL); 
    	  lv.setPrefSize(100, 70);
          MultipleSelectionModel<String> lvSEL = lv.getSelectionModel(); 
		   
		        
		    
		    
		    
		    Scene myScene = new Scene(rootNode, 300, 300); 
		    Scene myScene02 = new Scene(rootNode02, 300, 300); 
		    
		   
		    myStage.setScene(myScene); 
		 
		   
		    
		     myLabel = new Label("No Connection"); 
		    // myLabel.setTextFill(Color.web("#00ff00"));
		    
		    //myLabel.setTranslateY(250);
		    // myLabel.setTranslateX(100);
		     loginBTN.setOnAction((ae) -> {
		    	 username = userTF.getText();
		    	 password = passTF.getText();
		    	 code= username+"-"+password;
		    	 System.out.println(code);
		    	 stringProperty03.set("disable");
		    	 
		     }
             ); 
		     
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
							Thread.sleep(2000);
						} catch (Exception e) {
							
							e.printStackTrace();
						}
		    	    	

		    		    
		    		    
				    	
		    	    	 lv.setDisable(false);
				    	 
		    	    }
		    	});
		    
		    sendBTN.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override 
	    	    public void handle(ActionEvent o) {
	    	       
	    	    	code = "recdata";
	    	    	try {
						Thread.sleep(5000);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
	    	    	
	    	    
	   			    
	   			  dataBTN.setDisable(true); 
	   			  likeBTN.setDisable(true); 
	   			  nlikeBTN.setDisable(true);  
	   			  sendBTN.setDisable(true);
	   			  lv.setDisable(true);
	    		    
	    		    
			    	
			    	 
	    	    }
	    	});
		   
		     booleanProperty.addListener(new ChangeListener<Boolean>() {

		    	 @Override
		    	 public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		    		 System.out.println("changed " + oldValue + "->" + newValue);

		    		 Platform.runLater(new Runnable() {
		    			 @Override
		    			 public void run() {
		    				 if(newValue.booleanValue() == true){
		    					
		    					 myLabel.setText("Connected to Server");
		    				 }else{
		    				 
		    					 myLabel.setText("Lost Connecttion To Server");
		    				 }
		    				 
		    					 


		    				 
		    			 }
		    		 });
		    		 try {
		    			 Thread.sleep(1000);
		    		 } catch (InterruptedException e) {

		    			 e.printStackTrace();
		    		 }

		    	 }
		     });
		     
		     stringProperty02.addListener(new ChangeListener<String>() {

		    	 @Override
		    	 public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    		 System.out.println("changed " + oldValue + "->" + newValue);

		    		 Platform.runLater(new Runnable() {
		    			 @Override
		    			 public void run() {
		    				 if(newValue.equals("login")){
		    					
		    					 status.setText("logged in");
		    					 try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		    					 myStage.setScene(myScene02);
		    					 logoutBTN.setDisable(false);
		    				 }else if(newValue.equals("loggedout")){
		    					 status.setText("logged out");
		    					 logoutBTN.setDisable(true);
		    					 userTF.setDisable(true);
		    					 passTF.setDisable(true);
		    					 
		    				 }else{
		    				     System.out.println("error here");
		    					 status.setText("username or/and password is incorrect");
		    				 }
		    				 
		    					 


		    				 
		    			 }
		    		 });
		    		 try {
		    			 Thread.sleep(1000);
		    		 } catch (InterruptedException e) {

		    			 e.printStackTrace();
		    		 }

		    	 }
		     });

		     stringProperty03.addListener(new ChangeListener<String>() {

		    	 @Override
		    	 public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    		 System.out.println("changed " + oldValue + "->" + newValue);

		    		 Platform.runLater(new Runnable() {
		    			 @Override
		    			 public void run() {
		    				 if(newValue.equals("disable")){
		    					
		    					 loginBTN.setDisable(true);
		    					 userTF.setDisable(true);
		    					 passTF.setDisable(true);
		    				 }else{
		    					 loginBTN.setDisable(false);
		    					 userTF.setDisable(false);
		    					 passTF.setDisable(false);
		    				 }
		    				 
		    					 


		    				 
		    			 }
		    		 });
		    		 try {
		    			 Thread.sleep(1000);
		    		 } catch (InterruptedException e) {

		    			 e.printStackTrace();
		    		 }

		    	 }
		     });
		     
		     lvSEL.selectedItemProperty().addListener( 
		    		 new ChangeListener<String>() { 
		    			 public void changed(ObservableValue<? extends String> changed, 
		    					 String oldVal, String newVal) { 

		    				 
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

		     rootNode.getChildren().addAll(userTF,passTF,status,loginBTN,myLabel ); 
		     rootNode02.getChildren().addAll(lv,dataBTN,logoutBTN,sendBTN,likeBTN,nlikeBTN);
             lv.setDisable(true);

		     myStage.show(); 
		  }
		  public class Server02 implements Runnable{
				Thread th;
				
				//Client cl ;
				Server02()
				{
					th = new Thread(this);
					th.start();
				}

				  

				@Override
				public void run() {
    	
					
        String hostName = "x.x.x.x";
        int portNumber = xxxx;
       
        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in))
        ) {
        	if(echoSocket.isConnected()){
        		 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
        		booleanProperty.set(true);
        		
        	}
            String userInput;
            while (true) {
            	code = " ";
            	code02 = " ";
            	while(code02.equals(" ")){
            		code02 = getCode();
            		try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
            	}
            	
            	System.out.println("inside while");
                out.println(code);
                
                if(in.readLine().equals("correct")){
                	System.out.println("correct");
                	stringProperty02.set("login");
                	break;
                }else{
                	
                	try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
                	stringProperty03.set("enable");
                	System.out.println("not correct");
                	stringProperty02.set("!login");
                	
                	
                	
                }
                
            }
            code = " ";
        	code02 = " ";
            while(!logout ){
            	logout02 = getlogout();
            	code02 = getCode();
            	if(code02.equals("data")){
            	out.println("data");	
            	VDC vdc = VDC.getInstance();
            	al = vdc.getData();
            	
            	 for(VoteObject vo : al)
		            {
            		 
            		 votesNamesOL.add(vo.name);
		            }
            	 
            	code = " ";
            	code02 = " ";
          
            	}
            	
            	if(code02.equals("recdata")){
            		try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
                	out.println("recdata");	
                	VDC vdc = VDC.getInstance();
                	 vdc.sendData();
                	System.out.println("recdata");
                	
                	 
                	code = " ";
                	code02 = " ";
              
                	}
            	
            	try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
            	
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
        stringProperty02.set("loggedout");
        booleanProperty.set(false);
    }
}
}

