package Models.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Date;

public class code_sql {
	
	private int id;
	private Date dt;
	private int score;
	
	public code_sql(int id, int score, Date dt) {
		this.id = id;
		this.dt=dt;
		this.score=score;
	}
	
	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public static void createTableScores() {
		String sql = "CREATE TABLE IF NOT EXISTS scores ("
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " date_score DATETIME NOT NULL,"
                + "	score INT NOT NULL"
                + ");";
		
		try {
			Statement s = DataBase.getInstance().createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insert(code_sql score) {
		
		String sql = "INSERT INTO scores(score,date_score) VALUES(?,?)";
		try {
			PreparedStatement s = DataBase.getInstance().prepareStatement(sql);
			s.setInt(1, score.getScore());
			s.setDate(2, score.getDt());
			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String toString() {
		
		java.sql.Time time = new java.sql.Time(dt.getTime());
		
		if(score == 1)
			return score+" point\t"+ dt + "\t" + time;
		else
			return score+" points \t"+ dt + "\t" + time;
	}
	
	public static ArrayList<code_sql> listAll() {
		String sql = "SELECT * FROM  scores ORDER BY score DESC;";
		ArrayList<code_sql> list = new ArrayList<>();
		try {
			Statement s = DataBase.getInstance().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			//transformation in ArrayList
			while (r.next()) {
				list.add(new code_sql(r.getInt("id"),r.getInt("score"),r.getDate("date_score")));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static ArrayList<code_sql> list12() {
		String sql = "SELECT * FROM  scores ORDER BY score DESC LIMIT 12;";
		ArrayList<code_sql> list = new ArrayList<>();
		try {
			Statement s = DataBase.getInstance().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			//transformation in ArrayList
			while (r.next()) {
				list.add(new code_sql(r.getInt("id"),r.getInt("score"),r.getDate("date_score")));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static code_sql listMax() {
		
		java.util.Date date = new java.util.Date();
		long time = date.getTime();
		code_sql sm = new code_sql(0,1,new java.sql.Date(time));
		
		String sql = "SELECT score FROM scores ORDER BY score DESC LIMIT 1;";
		try {
			Statement s = DataBase.getInstance().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()){
				sm.setScore(r.getInt("score"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sm;
		
	}
	
	public static boolean isBestScore(int score) {
		boolean a = false;
		String sql = "SELECT MAX(score) FROM  scores;";
		
		try {
			Statement s = DataBase.getInstance().createStatement();
			ResultSet r = s.executeQuery(sql);
			r.next();
			if(r.getInt(1)<score)
				a=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}
	
}
