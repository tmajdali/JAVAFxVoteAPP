package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;


import objects.VoteObject;

public class VDC {

	 private static final VDC inst= new VDC();

	    private VDC() {
	        super();
	    }

	    public synchronized ArrayList<VoteObject> getData() {
//	    	try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
	    	ArrayList<VoteObject> al = null;
	    	try{
	    		 SocketChannel sChannel = SocketChannel.open();
			        sChannel.configureBlocking(true);
			        if (sChannel.connect(new InetSocketAddress("205.178.20.186", 4004))) {

			            ObjectInputStream ois = 
			                     new ObjectInputStream(sChannel.socket().getInputStream());

			           
			            al = (ArrayList<VoteObject>) ois.readObject();
			            Thread.sleep(1000);
			            ois.close();
			            sChannel.close();
			            
			        }
	        
	    	}catch(Exception e){
	    		System.out.println("Error from VData ");
	    	}
			return al;
	    

	    }
	    

	    public static VDC getInstance() {
	        return inst;
	    }
	    
	    public synchronized void sendData(){
	    	
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
	    	ArrayList<VoteObject> oal = ClientV.al;
	    	try{
	    		 SocketChannel sChannel = SocketChannel.open();
			        sChannel.configureBlocking(true);
			        if (sChannel.connect(new InetSocketAddress("205.178.20.186", 4004))) {
			        	 
			        	 ObjectOutputStream  oos = new 
			                      ObjectOutputStream(sChannel.socket().getOutputStream());
                         
			        	 oos.writeObject(oal);
			        	 oos.close();
				         sChannel.close();
			           
			        }
	        
	    	}catch(Exception e){
	    		System.out.println("Error from VData ");
	    	}
	    	System.out.println("iam in");
	    }

}
