package DBLesson01;


//英語と日本語をセットするためのクラス
public class Word{
	private String english;		//英語
	private String japanese;	//日本語

//何もセットされていないコンストラクタ　何も処理しないコンストラクタ
public Word(){

}

//初期値セットされたコンストラクタ
public Word(String english, String japanese){
	this.english = english;
	this.japanese = japanese;
}

//ゲッターとセッター
	public String getEnglish(){
		return english;
	}
	public void setEnglish(String english){
		this.english = english;
	}

	public String getJapanese(){
		return japanese;
	}
	public void setJapanese(String japanese){
		this.japanese = japanese;
	}

//↑一応問題1はこれだけで足りる

//toStringメソッドを書いて、Wordで出力するようにする
public String toString(){
	return("英単語:"
			+ this.english
			+ "　日本語:"
			+ this.japanese);

	}

}