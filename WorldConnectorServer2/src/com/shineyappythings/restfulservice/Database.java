package com.shineyappythings.restfulservice;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Database
{

	public static final String _ID = "id";

	public static final String COMM_TABLE_NAME = "Comments";
	public static final String COMM_WHEN = "whenx";
	public static final String COMM_WHAT = "what";
	public static final String COMM_WHO = "who";

	private static String connectionString = null;

	private static Connection conn = null;

	private static Database  _instance;

	synchronized public static Database getInstance()
	{
		if (_instance == null)
		{
			_instance = new Database();
			
		}
		return _instance;
	}
	
	private Database()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");

			// Poke class to be sure we can
			connectionString = "jdbc:sqlite:"
					+ Configuration.getInstance().getDatabaseFile();
			createDB();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			System.err.println("Class file missing try again");
			System.exit(-1);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.err.println("Cant create schema");
			System.exit(-1);
		}

	}

	private Connection getConnection() throws SQLException
	{
		if (conn == null)
			conn = DriverManager.getConnection(connectionString);

		return conn;
	}

	private void closeConnection() throws SQLException
	{
		conn.close();
		conn = null;

	}

	private Statement createStatement() throws SQLException
	{
		return getConnection().createStatement();
	}

	private PreparedStatement createPreparedStatement(String sql)
			throws SQLException
	{
		return getConnection().prepareStatement(sql);
	}

	@SuppressWarnings("unused")
	private void onUpgrade()
	{

	}

	private void createDB() throws SQLException
	{
		String dbFileName = Configuration.getInstance().getDatabaseFile();
		System.out.println("DB file name: " + dbFileName);
		File file = new File(dbFileName);

		if (!file.exists()) // here's how to check
		{
			Statement stat = createStatement();
			stat.executeUpdate("drop table if exists messages;");

			StringBuilder sb_messages = new StringBuilder();
			sb_messages.append("CREATE TABLE ");
			sb_messages.append(COMM_TABLE_NAME);
			sb_messages.append(" (");
			sb_messages.append(_ID);
			sb_messages.append(" INTEGER PRIMARY KEY, ");
			sb_messages.append(COMM_WHEN);
			sb_messages.append(" INTEGER NOT NULL, ");
			sb_messages.append(COMM_WHO);
			sb_messages.append(" TEXT NOT NULL, ");
			sb_messages.append(COMM_WHAT);
			sb_messages.append(" TEXT NOT NULL);");
			stat.executeUpdate(sb_messages.toString());
			stat.close();
			closeConnection();
		}
		else
		{
			System.out.println("This database name already exists");

		}
	}

	public void createMessage(Message message)
	{

		PreparedStatement stmt;
		try
		{
			stmt = createPreparedStatement("INSERT INTO " + COMM_TABLE_NAME
					+ " VALUES(?,?,?,?)");
			System.out.println("Inserting " + message);
			stmt.setLong(1, message.getMessageID());
			stmt.setLong(2, message.getWhen());
			stmt.setString(3, message.getUserName());
			stmt.setString(4, message.getComment());

			stmt.execute();
			stmt.close();
			closeConnection();

		}
		catch (SQLException e)
		{
			System.err
					.println("Failed to update a message this is probably bad!");
			e.printStackTrace();
		}

	}

	public Message getLatestMessage()
	{
		
		Statement stat;
		Message message = null;
		
		try
		{
			stat = createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM comments where id = (select MAX(id) from comments)");

			// Kludgy, there has to be a quikcer way to get  single result!
			while (rs.next())
			{

				String what = rs.getString(COMM_WHAT);
				String who = rs.getString(COMM_WHO);
				Long when = rs.getLong(COMM_WHEN);
				Long id = rs.getLong(this._ID);

				message = new Message(what, who, when, id);
			}
			rs.close();
			stat.close();
			closeConnection();
		}
		catch (SQLException e)
		{
			System.err
					.println("Failed to gett all messages this is probably bad!");
			e.printStackTrace();
		}

		return message;
		
	}
	public void updateMessage(Message message)
	{

	}

	public void deleteMessage(Message message)
	{

	}

	public int getNextID()
	{
		Statement stat;
		int id = 0;
		try
		{
			stat = createStatement();
			ResultSet rs = stat.executeQuery("SELECT MAX(id) from comments;");

			while (rs.next())
			{

				id = rs.getInt(1);

				System.out.println(" Next ID is " + id);
			}
			rs.close();
			stat.close();
			closeConnection();
		}
		catch (SQLException e)
		{
			System.err.println("Failed to get id this is probably bad!");
			e.printStackTrace();
		}
		return id;
	}

	public ArrayList<Message> loadAllMessages()
	{
		Statement stat;
		ArrayList<Message> messageList = new ArrayList<Message>();
		try
		{
			stat = createStatement();
			ResultSet rs = stat.executeQuery("select * from " + COMM_TABLE_NAME
					+ ";");

			while (rs.next())
			{

				String what = rs.getString(COMM_WHAT);
				String who = rs.getString(COMM_WHO);
				Long when = rs.getLong(COMM_WHEN);
				Long id = rs.getLong(this._ID);

				messageList.add(new Message(what, who, when, id));
			}
			rs.close();
			stat.close();
			closeConnection();
		}
		catch (SQLException e)
		{
			System.err
					.println("Failed to gett all messages this is probably bad!");
			e.printStackTrace();
		}

		return messageList;
	}

	

}