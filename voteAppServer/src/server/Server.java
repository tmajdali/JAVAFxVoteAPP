package server;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import objects.VoteObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Server extends Application  { 
	Label myLabel ;
    private BooleanProperty booleanProperty = new SimpleBooleanProperty(); 
    Server02 myObject = new Server02();
    VData vd = VData.getInstance();
    ArrayList<VoteObject> al;
    
	     public Map<String,String> getHashMap()
	     {
	    	  Map<String,String> map = new HashMap<String,String>();
	    	  try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
	    		    
	    		    String line = br.readLine();

	    		    while (line != null) {
	    		       map.put(line.substring(0, line.lastIndexOf("-")).replaceAll("\\s",""), line.substring(5, line.length()));
	    		        line = br.readLine();
	    		    }
	    		   
	    		} catch (FileNotFoundException e) {
	    			
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			
	    			e.printStackTrace();
	    		}

	    		    return map;
	    	 
	     }
		  public static void main(String[] args) { 
		 
		    launch(args);   
		  } 
		 
		  
		  public void start(Stage myStage) { 
			 
			  
		    
		    myStage.setTitle("Use a JavaFX label."); 
		 
		 
		    FlowPane rootNode = new FlowPane(10, 10); 
		 
		    
		    Scene myScene = new Scene(rootNode, 300, 200); 
		 
		   
		    myStage.setScene(myScene); 
		 
		   
		    
		     myLabel = new Label("No Connection"); 
		     
		    
		     myLabel.setTranslateY(180);
		     myLabel.setTranslateX(210);
		 
		   
		   booleanProperty.addListener(new ChangeListener<Boolean>() {

	            @Override
	            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	                System.out.println("changed " + oldValue + "->" + newValue);
	               
	                Platform.runLater(new Runnable() {
	                    @Override
	                    public void run() {
	                    	if(newValue.booleanValue() == true){
	                    	 myLabel.setText("Connected");
	                    }
	                   else
	                    {
	                    	 myLabel.setText("Lost Connecttion");
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
           
                  
		   
		    rootNode.getChildren().addAll(myLabel); 
		 
		    
		    myStage.show(); 
		  }
		  public class Server02 implements Runnable{
				Thread th;
				Map<String,String> mapDB;
				Server cl ;
				Server02()
				{
					
					th = new Thread(this);
					th.start();
				}

				  

				@Override
				public void run() {
					
					
					System.out.println("Sender Start");
					mapDB = getHashMap();
					int portNumber = xxxx;
			         
			        try (
			            ServerSocket serverSocket =
			                new ServerSocket(portNumber);
			            Socket clientSocket = serverSocket.accept(); 
			        		
			            PrintWriter out =
			                new PrintWriter(clientSocket.getOutputStream(), true);                   
			            BufferedReader in = new BufferedReader(
			                new InputStreamReader(clientSocket.getInputStream()));
			        		
			        ) {
			        	if(clientSocket.isConnected())
		        		{
		            		booleanProperty.set(true);
		        		}
			        							
			            String inputLine;
			            while ((!(inputLine = in.readLine()).equals("done"))) {
			            	
			            		if(mapDB.containsKey(inputLine.substring(0, inputLine.lastIndexOf("-")))){
			            			String user = inputLine.substring(0, inputLine.lastIndexOf("-"));
			            			if(mapDB.get(user).equals(inputLine.substring(5, inputLine.length()))){
			            				out.println("correct");
			            				break;
			            			}
			            			
			            		}
			            	
			            		out.println("!correct");
			            	
			               
			            }
			            String inputLine02;
			            while(true){
			            	System.out.println("before input02");
			            	inputLine02 = in.readLine();
			            	System.out.println(inputLine02);
			            	if(inputLine02.equals("data")){
			            		vd.sendData();
			            		inputLine02 = " ";
			            	}
			            	
			            	if(inputLine02.equals("recdata")){
			            		System.out.println("got recdata ");
			            	
			            		al =vd.receiveData();
			            		
			            		  for(VoteObject vo: al){
			  		            	System.out.println(vo.name+" "+vo.p+" "+vo.n);
			  		            	inputLine02 = " ";
			  		            }
			            	}
			            }
			        } catch (IOException e) {
			            System.out.println("Exception caught when trying to listen on port "
			                + portNumber + " or listening for a connection");
			            System.out.println(e.getMessage());
			        }
			            booleanProperty.set(false);
			            System.out.println("Connection ended");
				}
//			  
		  }

		
		
		
}
