package DBLesson01;

import java.util.ArrayList; //ArrayListを使うために必要
import java.util.List;
import java.util.Scanner;	//Scannerクラスを使用するために必要なコード。　Scannerクラスがあることで、プログラムとして(?)テキスト入力を扱える。

public class DBLesson01{

	public static void main(String[] args){		//メインメソッド(関数)。メソッド名：main、引数String型の配列、static修飾子が付与されているメソッド。argはargumentの略で引数という意味。
		 //ArrayListのインスタンス配列
		List<Word> lists = new ArrayList<Word>();
		 //インスタンス作成
		WordDAO dao = new WordDAO();

		 //コマンドラインから入力
		System.out.println("英単語と日本語をスペースで区切って入力してください");
		 //入力文字受け付け
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		 //カウント用
		int num = 0;

		 //正常の処理。(10回以内の英単語・日本語入力)
		try{
			 //ぐるぐる回る　e以外だったら処理を繰り返す
			while(!input.equals("e")){

				 //変数inputの中に、入力された英単語スペース日本語を変数tmpの中にいれる。配列も作る。
				String[] tmp = new String[2];
				tmp = input.split(" ");
				 //Wordクラスのインスタンスを作りつつ、while外側にあるarraylistに実体をいれる。(そうするとwordが保存される)
				lists.add( new Word(tmp[0], tmp[1]) );
				 //実体を入れたら、1カウントする。
				num++;
				 //次の入力を促す文
				System.out.println("次の英単語と日本語を入力してください。\"e\"で終了します");
				 //入力を収めるinput
				input = sc.nextLine();
			}
		}
		 //英単語・日本語を10回以上入力しようとした時のエラー　
		catch(Exception e){
			e.printStackTrace();
			System.out.println("登録制限を超えました。登録済みのデータは以下の通りです");
			 //入力したデータを5個までリスト表示する。
			for( int i = 0 ; i < 5 ; i++){
				System.out.println( lists.get(i).toString());
			}
			System.out.println("5件、登録しました");
		}
		System.out.println("プログラムを終了します");


		 //eが入力されたあとの処理。WordDAOクラスに渡す
		int result = dao.registWords(lists);

		System.out.println(result + "件のデータを登録しました。");
		sc.close();


	}
}