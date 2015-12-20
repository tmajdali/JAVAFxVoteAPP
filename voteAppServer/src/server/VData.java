package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import objects.VoteObject;

public class VData {

	 private static final VData inst= new VData();

	    private VData() {
	        super();
	    }
	    
	    public ArrayList<VoteObject> getArrayList()
	     {
	    	  ArrayList<VoteObject> al = new ArrayList<VoteObject>();
	    	  try(BufferedReader br = new BufferedReader(new FileReader("VData.txt"))) {
	    		    
	    		    String line = br.readLine();

	    		    while (line != null) {
	    		      
	    		    	al.add(new VoteObject(line.substring(0, line.indexOf("+")),line.substring(line.indexOf("+"), line.lastIndexOf("+")),line.substring(line.lastIndexOf("+"), line.length())));
	    		        line = br.readLine();
	    		    }
	    		   
	    		} catch (FileNotFoundException e) {
	    			
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			
	    			e.printStackTrace();
	    		}

	    		    return al;
	    	 
	     }

	    public synchronized void sendData() {
	    	
	    	try{
	    	ServerSocketChannel ssChannel = ServerSocketChannel.open();
	        ssChannel.configureBlocking(true);
	        int port = 4004 ;
	        ssChannel.socket().bind(new InetSocketAddress(port));

	        ArrayList<VoteObject> al = getArrayList();
	        
	       
	        while (true) {
	            SocketChannel sChannel = ssChannel.accept();

	            ObjectOutputStream  oos = new 
	                      ObjectOutputStream(sChannel.socket().getOutputStream());
	            oos.writeObject(al);
	            oos.close();
	            ssChannel.close();
	            //sChannel.close();
	            break;
	            
	        }
	    	}catch(Exception e){
	    		System.out.println("Error from VData ");
	    		e.printStackTrace();
	    	}
	    

	    }
	    

	    public static VData getInstance() {
	        return inst;
	    }

	    public synchronized ArrayList<VoteObject> receiveData(){
	    	ArrayList<VoteObject> mainDataAl = getMainArrayList();
	    	ArrayList<VoteObject> ial = null;
	    	
	    	try{
		    	ServerSocketChannel ssChannel = ServerSocketChannel.open();
		        ssChannel.configureBlocking(true);
		        int port = 4004 ;
		        ssChannel.socket().bind(new InetSocketAddress(port));

		        //ArrayList<VoteObject> al = getArrayList();
		        //VoteObject vo = new VoteObject("lolo","1","2");
		       // al.add(new VoteObject("lolo","1","2"));
		       
		        while (true) {
		            SocketChannel sChannel = ssChannel.accept();

		            ObjectInputStream ois = 
		                     new ObjectInputStream(sChannel.socket().getInputStream());
		            
		             ial = (ArrayList<VoteObject>) ois.readObject();
		            
		          

		            //oos.writeObject(al);
		             ois.close();
		             ssChannel.close();
			       // sChannel.close();
		           break;
		        }
		    	}catch(Exception e){
		    		System.out.println("Error from VData ");
		    		e.printStackTrace();
		    	}
	    	System.out.println("i am in");
	    	int a;
	    	int b;
	    	try(FileWriter fw = new FileWriter("mainData.txt")){
	    		
	    		for(int i = 0 ; i < mainDataAl.size() ; i++){
	    			 a = mainDataAl.get(i).p+ial.get(i).p;
	    			 b = mainDataAl.get(i).n+ial.get(i).n;
	    			fw.write(mainDataAl.get(i).name+"+"+a+"+"+b+"\n");
	    		}
	    			
	    		
	    	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ial;
		    
	    	
	    }
	    
	    public ArrayList<VoteObject> getMainArrayList()
	     {
	    	  ArrayList<VoteObject> al = new ArrayList<VoteObject>();
	    	  try(BufferedReader br = new BufferedReader(new FileReader("mainData.txt"))) {
	    		    
	    		    String line = br.readLine();

	    		    while (line != null) {
	    		       //map.put(line.substring(0, line.lastIndexOf("-")).replaceAll("\\s",""), line.substring(5, line.length()));
	    		    	al.add(new VoteObject(line.substring(0, line.indexOf("+")),line.substring(line.indexOf("+"), line.lastIndexOf("+")),line.substring(line.lastIndexOf("+"), line.length())));
	    		        line = br.readLine();
	    		    }
	    		   
	    		} catch (FileNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
//	    		 Iterator it = map.entrySet().iterator();
//	    		    while (it.hasNext()) {
//	    		        Map.Entry pair = (Map.Entry)it.next();
//	    		        System.out.println(pair.getKey() + " = " + pair.getValue());
//	    		        it.remove(); // avoids a ConcurrentModificationException
//	    		    }
	    		    return al;
	    	 
	     }
}
