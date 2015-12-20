package objects;

import java.io.Serializable;

public class VoteObject implements Serializable {
	 public String name;
	 public int p;
	 public int n;
	
	public VoteObject(String name, String p, String n)
	{
		this.name = name;
		this.p = Integer.parseInt(p);
		this.n= Integer.parseInt(n);
		
	}
	public String getName()
	{
		return name;
		
	}

}
