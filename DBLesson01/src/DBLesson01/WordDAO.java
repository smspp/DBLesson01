package DBLesson01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {

	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	static final String URL = "jdbc:mysql://localhost/testdb";
	static final String USER = "root";
	static final String PW = "";

	 //単語登録 insert
	public int registWords(List<Word> lists){

		int result = 0;

		try {
			 //ドライバーのロード
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useUnicode=true&characterEncoding=utf8", "root", "");

			 //DB接続
			if( con != null ){
				System.out.println("DB接続完了　\r\n---");
			}

			else{
					System.out.println("DB接続失敗\r\n---");
					return result;
			}

			for(int i = 0 ; i < lists.size()  ; i++){
				String SQL = "INSERT INTO dictionary(english, japanese) VALUES(?, ?)";
				Word wd = lists.get(i);
				st = con.prepareStatement(SQL);
				st.setString(1, wd.getEnglish());
				st.setString(2, wd.getJapanese());
				result = result + st.executeUpdate();
			}

				System.out.println(result + "確認");
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if ( st != null) {

				try {
					st.close();
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if ( con != null) {

				try {
					con.close();
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;	// 結果を返す
	}


	 //単語一覧 select
	public List<Word> getWords(){
		List<Word> lists = new ArrayList<Word>();

		try {
			 //ドライバーのロード
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testdb", "root", "");

			 //DB接続
			if( con != null ){
				System.out.println("登録済み単語一覧");
			}

			else{
					System.out.println("DB接続失敗\r\n---");
					return lists;
			}

			 // 単語の読み出し
			String SQL = "SELECT * FROM dictionary";
			st = con.prepareStatement(SQL);
			rs = st.executeQuery();

			while(rs.next()){
				Word wd = new Word(rs.getString("english"), rs.getString("japanese"));
				lists.add( wd );
			}

				System.out.println(lists);
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if ( st != null) {

				try {
					st.close();
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if ( con != null) {

				try {
					con.close();
				}

				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return lists;	// 結果を返す

	}


}