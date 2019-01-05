package my.com.mimos.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class JDBC_Connection {
	
		public static  ResultSetMetaData metadata;
		public  static String JDBC_DRIVER ="oracle.jdbc.driver.OracleDriver";
		public  static  String DB_URL ="jdbc:oracle:thin:@//csbss51r-scan.homedepot.com:1521/dad12dcs_rw_9999_01";	
		public  static String USER="WMLMSB01";
		public  static String PASS="WMLMSB01";
		public  static Connection connection = null;
			
		public static ArrayList<String> get_DBdatas(String query)
		{
			ArrayList<String> Db_datas = new ArrayList<String>();
			try
			{
			String eventName = null;
			Class.forName(JDBC_DRIVER);	
			Connection conn = null;
			Statement stmt = null;
			String Result=null;
			
			// STEP 3: Open a connection

			conn = DriverManager.getConnection(DB_URL, USER, PASS); 
			if(conn!=null)
			{
		    System.out.println("Connected database successfully...");
			stmt = conn.createStatement();
			System.out.println("Fetching records with condition...");
			ResultSet rs = stmt.executeQuery(query);
			metadata = rs.getMetaData();
    		int count = metadata.getColumnCount();
    		System.out.println("Query Executed");
    		String SI="S";
    		int flag=7;
    		while(rs.next())
			{
    			
				for(int i=1;i<=count;i++)
				{
					if (rs.getString(i) == null && flag==i )
					{Db_datas.add("N");}
					else
					{Db_datas.add(rs.getString(i).trim());}
				}
			}
    		System.out.println(Db_datas);
    		System.out.println("Total Row Count : " +count);
    		rs.close();
			}
			else
			{
				System.out.println("Database Connection Failed ...");
			}
			stmt.close();
			conn.close();
			return Db_datas;
			}
			catch(Exception e)
			{
				Db_datas.add(null);
				System.out.println("Exception on Auto generated ID");
				e.printStackTrace();
				return null;
			}
		}
		
	public ArrayList<String> array_Database_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
			
			ArrayList<String> Db_datas = new ArrayList<String>();
		
			try
			{
				
			String eventName = null;
			Class.forName(JDBC_DRIVER);	
			Connection conn = null;
			Statement stmt = null;
			String Result=null;
			
			// STEP 3: Open a connection

			conn = DriverManager.getConnection(DB_URL, USER, PASS); 
			if(conn!=null)
			{
		    System.out.println("Connected database successfully...");
			stmt = conn.createStatement();
			System.out.println("Fetching records with condition...");
			ResultSet rs = stmt.executeQuery(query);
			metadata = rs.getMetaData();
    		int count = metadata.getColumnCount();
    		System.out.println("Query Executed");
			while(rs.next())
			{
				for(int i=1;i<=metadata.getColumnCount();i++)
				{
					
					if (rs.getString(i) == null)
					{Db_datas.add("N");}
					else
					{Db_datas.add(rs.getString(i).trim());}
					
					}
			System.out.println(Db_datas);	
				}
			  rs.close();
			}
			else
			{
				System.out.println("Database Connection Failed ...");
			} 	
			stmt.close();
			conn.close();
			
			return Db_datas;
			}
			catch(Exception e)
			{
				Db_datas.add(null);
				System.out.println("Exception on executing the query");
				e.printStackTrace();
				return null;
			}
		 
	}
		
		
public String str_Database_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
			
		   String Db_datas=null;
			try
			{
				
			Class.forName(JDBC_DRIVER);
			Connection conn = null;
			Statement stmt = null;
			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			System.out.println("Fetching records with condition...");
			String sql = query;	
			//stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			metadata = rs.getMetaData();
			while(rs.next()){
			Db_datas=rs.getString(1);			
			}
			System.out.println(Db_datas);					
			rs.close();
			stmt.close();
			conn.close();			
			return Db_datas;
			}
			catch(Exception e)
			{
			//	Db_datas.add(null);
				System.out.println("Exception on executing the query");
				e.printStackTrace();
				return null;
			}
		 
	}

public String str_Update_Database_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
	
	   String Db_datas=null;
		try
		{
			
		Class.forName(JDBC_DRIVER);
		Connection conn = null;
		Statement stmt = null;
		// STEP 3: Open a connection
		System.out.println("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connected database successfully...");
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		System.out.println("Fetching records with condition...");
		String sql = query;		
		stmt.executeUpdate(sql);		
		return Db_datas;
		}
		catch(Exception e)
		{
		//	Db_datas.add(null);
			System.out.println("Exception on executing the query");
			e.printStackTrace();
			return null;
		}
	 
}
public String str_Inster_Database_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
	
	   String Db_datas=null;
		try
		{
			
		Class.forName(JDBC_DRIVER);
		Connection conn = null;
		Statement stmt = null;
		// STEP 3: Open a connection
		System.out.println("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connected database successfully...");
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		System.out.println("Fetching records with condition...");
		String sql = query;		
		stmt.executeUpdate(sql);		
		return Db_datas;
		}
		catch(Exception e)
		{
		//	Db_datas.add(null);
			System.out.println("Exception on executing the query");
			e.printStackTrace();
			return null;
		}
	 
}
public String str_Select_Database_Connection(String query) throws InterruptedException,  IOException, ClassNotFoundException {
	
	   String Db_datas=null;
	   try {
			String MSG_TYPE=null;
		    Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con = DriverManager.getConnection("jdbc:oracle:thin:@cpliqa7n.homedepot.com:1521:dqa31dc","WMLMQA01","WMLMQA01");
           Statement stmt = con.createStatement();
           ResultSet rs=stmt.executeQuery(query);
           while(rs.next())
           {
           	//System.out.println(rs.getString(2));
        	   Db_datas=rs.getString(1);
           }

           rs.close();
           stmt.close();
           }
           catch (ClassNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
	return Db_datas;
			 
}
public Connection get_db2_connection() throws InterruptedException,  IOException, ClassNotFoundException {
	
	   String Db_datas=null;
	   String url="jdbc:db2://ibdynpx0.sysplex.homedepot.com:5122/NP1";
       Connection connection = null;
       try {
           //Load class into memory
           Class.forName("com.ibm.db2.jcc.DB2Driver");
           //Establish connection
           connection = DriverManager.getConnection(url, "cpsaa", "sam@002");
           
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }finally{
           if(connection!=null)
           {
               System.out.println("Connected successfully.");
           }
       }
	return connection;
}

@SuppressWarnings("unused")
public void db_close() throws Exception
{
	if(connection != null)
	{
	 try {
		 connection.commit();
		 connection.close();
     } catch (SQLException e) {
    	 System.out.println("Problem in closing DB2 connection: " + e.getMessage());
     }	 
	}
}

public int  DB_To_HashMap(String query,String Week,int i) throws SQLException {
	 
	HashMap<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
	Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
	String sql = query;
	Statement stmt = null;
	stmt = connection.createStatement();
	System.out.println("Fetching records with condition...");
  // Statement statement = connection.prepareStatement("");
   ResultSet resultset = stmt.executeQuery(sql);  /*to be checked  */
   while (resultset.next()) {
   valueMap.put(resultset.getInt("FIELDID"), resultset.getInt(Week));
   }
   resultset.close();
   stmt.close();
  connection.close();
   int w01 = valueMap.get(1010);
   int w03 = valueMap.get(1003);
   int w02 = valueMap.get(1001);
   int w04=valueMap.get(1013);
   int value = w01 + w03 - w02;
   if(i==0)
     return w04;
   else

   return value;

   
}


public String DB_To_HashMapping(String query) throws SQLException {
	String Db_datas=null;
	try{
	HashMap<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
	Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
	String sql = query;
	Statement stmt = null;
	stmt = connection.createStatement();
	System.out.println("Fetching records with condition...");
  // Statement statement = connection.prepareStatement("");
	ResultSet resultset = stmt.executeQuery(sql);
	  resultset.close();
	   stmt.close();
	  connection.close();
	return Db_datas;
	}
	catch(Exception e)
	{
	//	Db_datas.add(null);
		System.out.println("Exception on executing the query");
		e.printStackTrace();
		return null;
	}
	
}

public static ArrayList<Double> database_conn(String query) throws InterruptedException, IOException, ClassNotFoundException {
	
	ArrayList<Double> Db_datas = new ArrayList<Double>();

	try
	{
		
	Class.forName(JDBC_DRIVER);
	Connection conn = null;
	Statement stmt = null;
	// STEP 3: Open a connection
	System.out.println("Connecting to a selected database...");
	conn = DriverManager.getConnection(DB_URL, USER, PASS);
	System.out.println("Connected database successfully...");
	// STEP 4: Execute a query
	System.out.println("Creating statement...");
	stmt = conn.createStatement();
	System.out.println("Fetching records with condition...");
	String sql = query;		
	ResultSet rs = stmt.executeQuery(sql);
	metadata = rs.getMetaData();
	  while(rs.next())
	{
		for(int i=1;i<=metadata.getColumnCount();i++)
		{
			Db_datas.add(rs.getDouble(i));
			}
		System.out.println(Db_datas);
		}
	rs.close();
	stmt.close();
	conn.close();
	
	return Db_datas;
	}
	catch(Exception e)
	{
		Db_datas.add(null);
		System.out.println("Exception onAuto generated ID");
		e.printStackTrace();
		return null;
	}
}

public static ArrayList<String> database_conn_string(String query) throws InterruptedException, IOException, ClassNotFoundException {
		
		ArrayList<String> Db_datas = new ArrayList<String>();

		try
		{
			
		Class.forName(JDBC_DRIVER);
		Connection conn = null;
		Statement stmt = null;
		// STEP 3: Open a connection
		System.out.println("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connected database successfully...");
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		System.out.println("Fetching records with condition...");
		String sql = query;		
		ResultSet rs = stmt.executeQuery(sql);
		metadata = rs.getMetaData();
		  while(rs.next())
		{
			for(int i=1;i<=metadata.getColumnCount();i++)
			{
				Db_datas.add(rs.getString(i));
				}
			System.out.println(Db_datas);
			}
		rs.close();
		stmt.close();
		conn.close();
		
		return Db_datas;
		}
		catch(Exception e)
		{
			Db_datas.add(null);
			System.out.println("Exception onAuto generated ID");
			e.printStackTrace();
			return null;
		}		
	
}

}

