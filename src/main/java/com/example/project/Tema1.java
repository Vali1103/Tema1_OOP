package com.example.project;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

class GetQuestion {
	String name, password, text;

	public GetQuestion(){
		this.name = null;
		this.password = null;
		this.text = null;
	}

	public void SetName(String name) {
		this.name = name;
	}

	public void SetPassword(String password) {
		this.password = password;
	}

	public void SetText(String text) {
		this.text = text;
	}

	public String ReadAuthentification(String File){
		try {
			/**
			 * functia mai exista doar ca aceasta este metoda a acestei clase pentru a nu ma incurca
			 * si realizeaza verificarea de utilizator in fiserul de utilizatori
			 * in caz contrar daca exista si parola este gresita esueaza autentificarea
			 */
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int ok = 0;
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String word = st.nextToken();
					if(ok == 0) {
						if (word.compareTo(this.name) == 0)
							ok = 1;
					}
					if(ok == 1)
						if(word.compareTo(this.password) == 0) {
							return this.name + "," + this.password;
							//continua
						}
					ok++;
				}
			}
			System.out.println("{'status':'error','message':'Login failed'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public int ReadQuestion(String File){
		/**
		 *  verifica daca in fisier mai exista aceasta intrebare
		 */
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				StringTokenizer st = new StringTokenizer(data, ",");
				String word = st.nextToken();
				int id = Integer.valueOf(word);
				word = st.nextToken();
				if(word.compareTo(this.text) == 0)
					return id;

			}
			System.out.println("{'status':'error','message':'Question does not " +
					"exist'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public String toString() {
		return this.name + "," + this.password;
	}
}
class GetAllQuestion{
	String name, password;
	GetAllQuestion(){
		this.name = null;
		this.password = null;
	}
	public void SetName(String name) {
		this.name = name;
	}
	public void SetPassword(String password) {
		this.password = password;
	}

	public String Search(String File) {
		/**
		 * verifica existenta utilizatorului sau a parolei corcte pentru utilizator
		 */
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int ok = 0;
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String word = st.nextToken();
					if(ok == 0) {
						if (word.compareTo(this.name) == 0)
							ok = 1;
					}
					if(ok == 1)
						if(word.compareTo(this.password) == 0) {
							return this.name + "," + this.password;
							//continua
						}
					ok++;
				}
			}
			System.out.println("{'status':'error','message':'Login failed'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public int GetSuccesfully(String File) {
		/**
		 * metoda creata pentru a parcurge acel fisier si printarea rezultatului corect pentru cerinta
		 */
		try {
			int first = 0;
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			System.out.print("{'status':'ok','message':'[");
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				//System.out.println(data);
				StringTokenizer st = new StringTokenizer(data, ",");
				String word = st.nextToken();
				int idQuestion = Integer.valueOf(word);
				word = st.nextToken();
				String Question = word, ant = word;
				while (st.hasMoreTokens()){
					ant = word;
					word = st.nextToken();
					if(!st.hasMoreTokens())
						if(ant.compareTo(this.name) == 0)
							if(word.compareTo(this.password) == 0)
								if(first == 0){
									first = 1;
									System.out.print("{\"question_id\" : \"" + idQuestion + "\", \"question_name\" : \"");
									System.out.print(Question + "\"}");
								}
								else {
									System.out.print(", {\"question_id\" : \"" + idQuestion + "\", \"question_name\" : \"");
									System.out.print(Question + "\"}");
								}
				}


			}
			System.out.println("]'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return 0;
	}
}

class Quizz{
	/**
	 * clasa folosita pentru crearea unui quiz
	 * si argumentele necesare aceste comenzi, initilizate cu null
	 * carora le sunt atribuite in functie de caz
	 */
	String nameUtilizator;
	String password;
	String nameQuizz;
	String []id;
	static int idx = 0;

	Quizz(){
		this.nameQuizz = null;
		this.nameUtilizator = null;
		this.password = null;
		this.id = new String[50];
	}
	public void SetUtilizator(String nameUtilizator){
		this.nameUtilizator = nameUtilizator;
	}
	public void SetPassword(String password){
		this.password = password;
	}
	public void SetQuizz(String nameQuizz){
		this.nameQuizz = nameQuizz;
	}
	public void AddId(String id) {
		this.id[idx] = id;
		idx++;
	}

	public void resetIDX(){
		this.idx = 0;
	}
	public String Search(String File) {
		/**
		 * verifica utilizator
		 */
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int ok = 0;
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String word = st.nextToken();
					if(ok == 0) {
						if (word.compareTo(this.nameUtilizator) == 0)
							ok = 1;
					}
					if(ok == 1)
						if(word.compareTo(this.password) == 0) {
							return this.nameUtilizator + "," + this.password;
							//continua
						}
					ok++;
				}
			}
			System.out.println("{'status':'error','message':'Login failed'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public int Retur(String File){
		/**
		 * verifica daca exista aceasta intrebare cu acest id,
		 * si in caz contrar returneaza id-ul
		 */
		try {
			int ok = 0;
			for(int k = 0; k < idx; k++) {
				ok = k;
				File myObj = new File(File);
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					StringTokenizer st = new StringTokenizer(data, ",");
					String word = st.nextToken();
					if(word.compareTo(id[k]) == 0)
						ok = 0;
				}
				myReader.close();
				if(ok != 0)
					return Integer.valueOf(id[ok]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return 0;
	}

	public String intrebari(String File){
		/**
		 * pentru usuritate metoda ce returneaza un string variabil in functie
		 * de nr de intrebari. stringul contine intrebare,tip,id (Ex: Cerul est albastru,single,1)
		 */
		try {
			int first = 0;
			String intrebari = new String();
			for(int k = 0; k < idx; k++) {
				File myObj = new File(File);
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					StringTokenizer st = new StringTokenizer(data, ",");
					String word = st.nextToken();
					if(word.compareTo(id[k]) == 0){
						word = st.nextToken();
						if(first == 0) {
							intrebari = intrebari + word;
							word = st.nextToken();
							intrebari = intrebari + "," + word + "," + id[k];
							first = 1;
						}
						else {
							intrebari = intrebari + "," + word;
							word = st.nextToken();
							intrebari = intrebari + "," + word + "," + id[k];
						}
					}


				}
				myReader.close();
			}
			return intrebari;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}
	public void AddQuiz(String File,String nameQuizz) {
		/**
		 *  Imi scrie in fisier mesajul dat de catre mine in nameQuizz
		 */
		try (FileWriter fw = new FileWriter(File, true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw)) {
			out.print(nameQuizz);
			out.println("");
			System.out.println("{'status':'ok','message':'Quizz added succesfully'}");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void TestQuizz(String File,String name ,String add, int idQuiz){
		/**
		 * Parcurg fisierul si verifica daca exista un quiz identic si in caz contrar adaug
		 * quiz-ul in fisierul respectiv
		 */
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				StringTokenizer st = new StringTokenizer(data, ",");
				if (st.hasMoreTokens()) {
					String word = st.nextToken();
					word = st.nextToken();
					if (word.compareTo(name) == 0){
						System.out.println("{'status':'error','message':'Quizz name already " +
								"exists'}");
						return;
					}
				}
			}
			AddQuiz(File, idQuiz + "," + add);
			// idQuiz++;
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
class GetAllQuiz{
	/**
	 * clasele si metodele sunt similare unele cu celalalte diferind putin
	 * in functie de cerinta si ce argumente mai primeste comanda respectiva
	 */
	String name;
	String password;
	String Quiz;

	GetAllQuiz(){
		this.name = null;
		this.password = null;
		this.Quiz = null;
	}

	public void SetName(String name) {
		this.name = name;
	}
	public void SetPassword(String password) {
		this.password = password;
	}
	public void SetQuiz(String Quiz) {
		this.Quiz = Quiz;
	}
	public String testUt(String File) {
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int ok = 0;
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String word = st.nextToken();
					if(ok == 0) {
						if (word.compareTo(this.name) == 0)
							ok = 1;
					}
					if(ok == 1)
						if(word.compareTo(this.password) == 0) {
							return this.name + "," + this.password;
							//continua
						}
					ok++;
				}
			}
			System.out.println("{'status':'error','message':'Login failed'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public int getQuiz(String File) {
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String ant ,word = st.nextToken();// id quiz
					ant = word;
					word = st.nextToken(); //nume quiz
					if(word.compareTo(this.Quiz) == 0)
						return Integer.valueOf(ant);

				}
			}
			System.out.println("{'status':'error','message':'Quizz does not exist'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return 0;
	}



}

class GetQuizzes{
	String name;
	String password;

	GetQuizzes(){
		this.name = null;
		this.password = null;
	}
	public void SetName(String name) {
		this.name = name;
	}
	public void SetPassword(String password) {
		this.password = password;
	}
	public String testUt(String File) {
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int ok = 0;
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String word = st.nextToken();
					if(ok == 0) {
						if (word.compareTo(this.name) == 0)
							ok = 1;
					}
					if(ok == 1)
						if(word.compareTo(this.password) == 0) {
							return this.name + "," + this.password;
							//continua
						}
					ok++;
				}
			}
			System.out.println("{'status':'error','message':'Login failed'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public int GetSuccesfully(String File) {
		/**
		 *  parcurge fisierul si afiseaza daca a fost adaugat in regula
		 *  informatiile necesare
		 */
		try {
			int first = 0;
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			System.out.print("{'status':'ok','message':'[");
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				StringTokenizer st = new StringTokenizer(data, ",");
				String word = st.nextToken();
				int idQuestion = Integer.valueOf(word);
				word = st.nextToken();// quiz name
				String name = st.nextToken();
				String password = st.nextToken();

				String state = st.nextToken();
				while (st.hasMoreTokens()) {
					state = st.nextToken();
				}

				if(name.compareTo(this.name) == 0)
					if(password.compareTo(this.password) == 0)
						if(first == 0){
							first = 1;
							System.out.print("{\"quizz_id\" : \"" + idQuestion + "\", \"quizz_name\" : \"");
							System.out.print(word + "\", \"is_completed\" : \"" + state + "\"}");
						}
						else {
							System.out.print(", {\"quizz_id\" : \"" + idQuestion + "\", \"quizz_name\" : \"");
							System.out.print(word + "\", \"is_completed\" : \"" + state + "\"}");
						}



			}
			System.out.println("]'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return 0;
	}

}
class GetQuizid{
	String name;
	String password;
	String id;

	GetQuizid(){
		this.name = null;
		this.password = null;
		this.id = null;
	}

	public void SetName(String name){
		this.name = name;
	}
	public void SetPassword(String password){
		this.password = password;
	}
	public void SetId(String id){
		this.id = id;
	}

	public String testUt(String File){
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int ok = 0;
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String word = st.nextToken();
					if(ok == 0) {
						if (word.compareTo(this.name) == 0)
							ok = 1;
					}
					if(ok == 1)
						if(word.compareTo(this.password) == 0) {
							return this.name + "," + this.password;
							//continua
						}
					ok++;
				}
			}
			System.out.println("{'status':'error','message':'Login failed'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public String raspunsuri(String File, int index, String name, String password){
		try {
			String rasp = new String();
			rasp = rasp + "\"[";
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			String word;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				StringTokenizer st = new StringTokenizer(data, ",");
				word = st.nextToken();
				if(Integer.valueOf(word) == index) {
					word = st.nextToken(); // intrebare
					word = st.nextToken(); // tip
					int f = 0;
					while (st.hasMoreTokens()) {
						String raspuns = st.nextToken();
						String id = st.nextToken();
						if(raspuns.compareTo(name) != 0) {
							if(f == 0) {
								rasp = rasp + "{\"answer_name\":\"" + raspuns + "\", \"answer_id\":\"" + (f+1) + "\"}";
								f = 1;
							}
							else {
								rasp = rasp + ", {\"answer_name\":\"" + raspuns + "\", \"answer_id\":\"" + (f+1) + "\"}";
								f++;
							}
						}
					}
				}
			}
			myReader.close();
			rasp = rasp + "]\"";
			return rasp;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public int GetSuccesfully(String File) {
		try {
			int first = 0;
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			System.out.print("{'status':'ok','message':'[");
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				StringTokenizer st = new StringTokenizer(data, ",");
				String word = st.nextToken();
				int idQuestion = Integer.valueOf(word);
				word = st.nextToken();// quiz name
				String name = st.nextToken();
				String password = st.nextToken();

				while (st.hasMoreTokens()) {
					String question = st.nextToken();
					if(st.hasMoreTokens()) {
						String type = st.nextToken();
						String indexQ = st.nextToken();
						if (name.compareTo(this.name) == 0)
							if (password.compareTo(this.password) == 0)
								if (first == 0) {
									String raspunsuri = raspunsuri("question.csv", idQuestion, name, password);
									first = 1;
									System.out.print("{\"question-name\":\"" + question + "\", \"question_index\":\"");
									System.out.print(idQuestion + "\", \"question_type\":\"" + type + "\", \"answers\":" + raspunsuri + "}");
								} else {
									String raspunsuri = raspunsuri("question.csv", idQuestion, name, password);
									System.out.print(", {\"question-name\":\"" + question + "\", \"question_index\":\"");
									System.out.print(idQuestion + "\", \"question_type\":\"" + type + "\", \"answers\":" + raspunsuri + "}");
								}
					}
				}
			}
			System.out.println("]'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return 0;
	}

}
class Submit{
	String name;
	String password;

	Submit(){
		this.name = null;
		this.password = null;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String testUt(String File){
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int ok = 0;
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String word = st.nextToken();
					if(ok == 0) {
						if (word.compareTo(this.name) == 0)
							ok = 1;
					}
					if(ok == 1)
						if(word.compareTo(this.password) == 0) {
							return this.name + "," + this.password;
							//continua
						}
					ok++;
				}
			}
			System.out.println("{'status':'error','message':'Login failed'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}
}




public class Tema1 {
	public static  int question_id = 1;
	static int idQuiz = 1;
	public static void Insert(String File, String name){
		/**
		 * functie de scriere in fisier a utilizatorului
		 * in fisierul primit ca parametru
		 */
		try (FileWriter fw = new FileWriter(File, true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw)) {
			out.print(name);
			out.println("");
			System.out.println("{'status':'ok','message':'User created successfully'}");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void InsertQuestion(String File, String name){
		try (FileWriter fw = new FileWriter(File, true);
			 BufferedWriter bw = new BufferedWriter(fw);
			 PrintWriter out = new PrintWriter(bw)) {
			out.print(name);
			out.println("");
			System.out.println("{'status':'ok','message':'Question added successfully'}");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public static String ReadAuthentification(String File, String name, String password){
		/**
		 * functie pentru verificare in fisierul dat ca parametru, respectiv "text.csv"
		 * daca exista utilizatorul si parola este corecta returnand din nou
		 * numele si parola
		 */
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int ok = 0;
				StringTokenizer st = new StringTokenizer(data, ",");
				while (st.hasMoreTokens()){
					String word = st.nextToken();
					if(ok == 0)
						if(word.compareTo(name) == 0)
							ok = 1;
					if(ok == 1)
						if(word.compareTo(password) == 0) {

							return name + "," + password;
							//continua
						}
					//System.out.println(word);
					ok++;
				}
			}
			System.out.println("{'status':'error','message':'Login failed'}");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}
	public static String Intrebari(String []rasp, int idx) {
		int counter = 1;
		String return1 = new String();
		for(int i = 0; i < idx; i = i + 4 ) {
			return1 = return1 + rasp[i+1] + "," + String.valueOf(counter) + ",";
			counter++;
		}
		return return1;
	}
	public static void IntroduceIntrebare(String File, String rezultatUt, String text, String type, String [] rasp, int idx) {
		/**
		 * Parcurge fisierul si daca exista deja aceeasi intrebare
		 * nu o adauga, in caz contrar apeleaza functia de adaugare
		 * in fiser a intrebari. functia fiind identica ca si la user diferind doar mesajul
		 */
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				StringTokenizer st = new StringTokenizer(data, ":,");
				if (st.hasMoreTokens()) {
					String word = st.nextToken();
					word = st.nextToken();
					if (word.compareTo(text) == 0){
						System.out.println("{'status':'error','message':'Question already exists'}");
						return;
					}
				}
			}
			String intrebari = Intrebari(rasp, idx);
			InsertQuestion(File, question_id + "," + text + "," + type + "," + intrebari + rezultatUt);
			question_id++;
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void Clean(String File){
		/**
		 * functie universala de stergere a continutului a unui fisier
		 * primit ca si argument
		 */
		try {
			question_id = 1;
			idQuiz = 1;
			PrintWriter writer = new PrintWriter(File);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void TestUser(String File, String name, String password){
		/**
		 *  Aceasta functie imi citeste fisierul in care scriu si in caz ca numele
		 *  deja exista nu mai adaug, in caz contrar apeleaza functia de scriere
		 *  intr-un fisier primit ca parametru
		 */
		try {
			File myObj = new File(File);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				StringTokenizer st = new StringTokenizer(data, ",");
				if (st.hasMoreTokens()) {
					String word = st.nextToken();
					if (word.compareTo(name) == 0){
						System.out.println("{'status':'error','message':'User already exists'}");
						return;
					}
				}
			}
			Insert(File, name + "," + password);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void CreateQuestion(String[] args, int i) {
		/**
		 * functie apelata pentru crearea unei intrebari pusa de un utilizator
		 * parcurg vectorul si preiau toate argumetele necesare nume, parola,
		 * intrebare, tip, raspunsuri, variabile
		 * si in caz ca una lipseste afisez cazul respectiv
		 */
		if((i+1) < args.length){
			i++;
			String arg = args[i];
			StringTokenizer st = new StringTokenizer(arg, " '");
			String name;
			String word = st.nextToken();
			if(word.compareTo("-u") == 0) {
				name = st.nextToken();
				if((i+1) < args.length){
					i++;
					String password;
					arg = args[i];
					st = new StringTokenizer(arg, " '");
					if(st.hasMoreTokens()) {
						word = st.nextToken();
						if (word.compareTo("-p") == 0) {
							String rezultatUt;
							password = st.nextToken();
							rezultatUt = ReadAuthentification("text.csv", name, password);
							if((i+1) < args.length){
								i++;
								arg = args[i];
								st = new StringTokenizer(arg, " '");
								StringTokenizer st2 = new StringTokenizer(arg, "'");
								if (st.hasMoreTokens()) {
									word = st.nextToken(); 					//am aflat textul
									if (word.compareTo("-text") == 0) {
										String text = st2.nextToken();
										text = st2.nextToken();
										if((i+1) < args.length) {
											i++;
											arg = args[i];
											st = new StringTokenizer(arg, " '");
											if(st.hasMoreTokens()){
												word = st.nextToken();
												if(word.compareTo("-type") == 0){
													String type = st.nextToken();
													int idx = 0;
													String []rasp = new String[40];
													/**
													 * preiau in vectorul rasp si argumelte -answer-1 si raspuntul si daca
													 * sunt corecte astfel incat pe un raspun sunt 4 argumente si maxim 5 raspunsuri
													 * astfel vectorul e de maxim 40
													 */
													if((i+1) < args.length) {
														while ((i + 1) < args.length) {
															i++;
															arg = args[i];
															st = new StringTokenizer(arg, " '");
															while (st.hasMoreTokens()) {
																rasp[idx] = st.nextToken();
																if(idx > 20)
																	System.out.println("{ 'status' : 'error', 'message' : 'More than 5 answers " +
																			"were submitted'}");
																idx++;
															}

														}
														if(idx == 4)
															System.out.println("{'status':'error','message':'Only one answer provided'}");
														else {
															int count1 = 0;
															for (int m = 0; m < idx; m++) {
																if (rasp[m].compareTo("1") == 0)
																	count1++;
															}
															if(count1 > 1)
																System.out.println("{ 'status' : 'error', 'message' : 'Single correct answer question has more than one correct answer'}");
															else {
																int answerDuplicated = 0;
																for(int m = 0; m < idx; m++)
																	for(int n = 0; n < idx; n++)
																		if(rasp[m].compareTo(rasp[n]) == 0)
																			if(m != n)
																				answerDuplicated = 1;
																if(answerDuplicated == 1)
																	System.out.println("{ 'status' : 'error', 'message' : 'Same answer provided " +
																			"more than once'}");
																else{
																	int description = 0;
																	description = Description(rasp, idx);
																	if(description != 0) {
																		if(description < 0)
																			System.out.println("{ 'status' : 'error', 'message' : 'Answer " + - description + " has no answer " +
																					"description'}");
																		else
																			System.out.println("{ 'status' : 'error', 'message' : 'Answer "+ description+" has no answer " +
																					"correct flag'}");
																	}
																	else {
																		// imi adauga intrebarea in fisier
																		IntroduceIntrebare("question.csv", rezultatUt, text, type, rasp, idx);
																	}
																}
															}
														}
													} else
														System.out.println("{ 'status' : 'error', 'message' : 'No answer provided'}");
												}
											}
										} else
											System.out.println("{ 'status' : 'error', 'message' : 'No answer provided'}");

									}else
										System.out.println("{ 'status' : 'error', 'message' : 'No question text " +
												"provided'}");
								}
							}
						}
					}
				} else
					System.out.println("{'status':'error','message':'You need to be authenticated'}");
			} else
				System.out.println("{'status':'error','message':'You need to be authenticated'}");
			//System.out.println(word);
		} else {
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		}
	}
	public static void CreateUser(String[] args, int i){
		if ((i + 1) < args.length) {
			/**
			 *  Prelucrez fiecare rand in parte din args in momentul acestei comenzi
			 * prin initializarea unui tokenizer(asemanator strtok in c) si imi impart
			 * fraza in functie de cuvintele si elementele necesare si compar in functie
			 * de primul element daca e nume sau parola primita
			 */
			i++;
			String arg = args[i];
			StringTokenizer st = new StringTokenizer(arg, " '");
			if (st.hasMoreTokens()) {
				String name;
				String word = st.nextToken();
				if (word.compareTo("-u") == 0) {
					name = st.nextToken();
					if ((i + 1) < args.length) {
						i++;
						arg = args[i];
						st = new StringTokenizer(arg, " '");
						if (st.hasMoreTokens()) {
							String password;
							word = st.nextToken();
							if (word.compareTo("-p") == 0) {
								password = st.nextToken();
								TestUser("text.csv", name, password);
							}
						}
					} else
						System.out.println("{'status':'error','message':'Please provide password'}");
				}
			}
		} else
			System.out.println("{'status':'error','message':'Please provide username'}");
	}

	public static int Description(String []rasp, int index){
		/**
		 * Aceasta functie imi parcurge vectorul rasp pana la index
		 * acesta variaza in functie de nr de raspunsuri la intrebare
		 *  Parcurg acest vector stiind cum este el construi si extrag fiecare cifra determinand daca este descriere
		 * sau correct flag
		 *  Apoi parcurg si creez un vector de vizitate
		 */
		String word, aux = rasp[index-2];
		StringTokenizer st = new StringTokenizer(aux, "-");
		int nr_descrieri = 0;
		if(st.hasMoreTokens()){
			word = st.nextToken();
			if(st.hasMoreTokens()){
				word = st.nextToken();					// avem in word cifra ultimului raspuns\
				nr_descrieri = Integer.valueOf(word);
			}
		}
		int []val = new int[2*nr_descrieri];
		int value;
		for ( int i = 0; i < index; i = i + 2){
			aux = rasp[i];
			st = new StringTokenizer(aux , "-");
			if(st.hasMoreTokens()){
				word = st.nextToken();
				if(st.hasMoreTokens()){
					word = st.nextToken();					// avem in word cifra ultimului raspuns\
					value = Integer.valueOf(word);
					if(!st.hasMoreTokens()){
						val[value - 1] = 1;
					}
					else
						val[value - 1 + nr_descrieri] = 1;
				}
			}
		}
		for(int i = 0; i < 2*nr_descrieri; i++){
			if(val[i] == 0)
				if(i < nr_descrieri)
					return -(i+1);
				else
					return (i-nr_descrieri+1);
		}

		return 0;
	}

	public static void GetQuestionCommand(String []args, int i) {
		/**
		 *   De acum in momentul in care se primeste ca argument comanda de get question
		 * aloc o variabila de clasa GetQuestion ce are aceeasi parametrii ca si comanda
		 * initializati cu null la inceput. Ulterior parcurg argumentele si le completezi
		 * cu ajutorul setterelor
		 * 	In cazul in care dupa parcurgere nu se seteaza ceva inseamna ca nu a fost primit
		 * acel parametru astfel, afisez pentru cazul respectiv
		 */
		GetQuestion intrebare = new GetQuestion();
		while((i+1) < args.length){
			i++;
			StringTokenizer st = new StringTokenizer(args[i], " '");
			String word = st.nextToken();
			if(word.compareTo("-u") == 0){
				word = st.nextToken();
				intrebare.SetName(word);
			}
			if(word.compareTo("-p") == 0) {
				word = st.nextToken();
				intrebare.SetPassword(word);
			}
			if(word.compareTo("-text") == 0) {
				st = new StringTokenizer(args[i], "'");
				word = st.nextToken();
				word = st.nextToken();
				intrebare.SetText(word);
			}
		}
		if(intrebare.name == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(intrebare.password == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(intrebare.ReadAuthentification("text.csv") != null) {
			/**
			 *  Verifica daca utilizatorul respectiv exista in fisier
			 */
			int idReturnat = intrebare.ReadQuestion("question.csv");
			if(idReturnat != 0)
				System.out.println("{'status':'ok','message':'" + idReturnat + "'}");
		}
	}

	public static void AllGetQuestion(String []args, int i) {
		/**
		 * parcurge parametrii si ii adauga structuri ca adineauri si apeleaza metodele necesare verificarilor si adaugarilor
		 */
		GetAllQuestion parameter = new GetAllQuestion();
		while ((i+1) < args.length){
			i++;
			StringTokenizer st = new StringTokenizer(args[i], " '");
			String word = st.nextToken();
			if(word.compareTo("-u") == 0){
				word = st.nextToken();
				parameter.SetName(word);
			}
			if(word.compareTo("-p") == 0) {
				word = st.nextToken();
				parameter.SetPassword(word);
			}
		}
		if(parameter.name == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(parameter.password == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(parameter.Search("text.csv") != null) {
			parameter.GetSuccesfully("question.csv");
		}
	}

	public static void CreateQuiz(String []args, int i){
		Quizz parameter = new Quizz();
		while ((i+1) < args.length){
			i++;
			StringTokenizer st = new StringTokenizer(args[i], " '");
			String word = st.nextToken();
			if(word.compareTo("-u") == 0){
				word = st.nextToken();
				parameter.SetUtilizator(word);
			} else
			if(word.compareTo("-p") == 0) {
				word = st.nextToken();
				parameter.SetPassword(word);
			} else
			if(word.compareTo("-name") == 0){
				st = new StringTokenizer(args[i], "'");
				word = st.nextToken();
				word = st.nextToken();
				parameter.SetQuizz(word);
			}
			else {
				parameter.resetIDX();
				while (i < args.length){
					st = new StringTokenizer(args[i], "'");
					word = st.nextToken();
					word = st.nextToken();
					parameter.AddId(word);
					i++;
				}
			}
		}
		if(parameter.nameUtilizator == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(parameter.password == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(parameter.idx > 10)
			System.out.println("{'status':'error','message':'Quizz has more than 10 " +
					"questions'}");
		else if(parameter.Search("text.csv") != null) {
			int y = parameter.Retur("question.csv");
			if(y != 0)
				System.out.println("{'status':'error','message':'Question ID for question " + y + " does not exist'}");
			else {
				String intrebari = parameter.intrebari("question.csv");
				parameter.TestQuizz("quiz.csv", parameter.nameQuizz, parameter.nameQuizz + "," + parameter.nameUtilizator + "," + parameter.password + "," + intrebari + "," + "False", idQuiz);
				idQuiz++;
			}
		}
	}
	public static void GetQuizName(String []args, int i) {
		GetAllQuiz parameter = new GetAllQuiz();
		while ((i+1) < args.length) {
			i++;
			StringTokenizer st = new StringTokenizer(args[i], " '");
			String word = st.nextToken();
			if(word.compareTo("-u") == 0){
				word = st.nextToken();
				parameter.SetName(word);
			} else
			if(word.compareTo("-p") == 0) {
				word = st.nextToken();
				parameter.SetPassword(word);
			} else
			if(word.compareTo("-name") == 0){
				st = new StringTokenizer(args[i], "'");
				word = st.nextToken();
				word = st.nextToken();
				parameter.SetQuiz(word);
			}
		}
		if(parameter.name == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(parameter.password == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if (parameter.testUt("text.csv") != null){
			if (parameter.getQuiz("quiz.csv") != 0 )
				System.out.println("{'status':'ok','message':'" + parameter.getQuiz("quiz.csv") + "'}");
		}
	}
	public static void GetAllQuizez(String []args, int i) {
		GetQuizzes parameter = new GetQuizzes();
		while ((i+1) < args.length) {
			i++;
			StringTokenizer st = new StringTokenizer(args[i], " '");
			String word = st.nextToken();
			if(word.compareTo("-u") == 0){
				word = st.nextToken();
				parameter.SetName(word);
			} else
			if(word.compareTo("-p") == 0) {
				word = st.nextToken();
				parameter.SetPassword(word);
			}
		}
		if(parameter.name == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(parameter.password == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if (parameter.testUt("text.csv") != null)
			parameter.GetSuccesfully("quiz.csv");
	}

	public static void GetQuizzid(String []args, int i) {
		GetQuizid parameter = new GetQuizid();
		while ((i+1) < args.length) {
			i++;
			StringTokenizer st = new StringTokenizer(args[i], " '");
			String word = st.nextToken();
			if(word.compareTo("-u") == 0){
				word = st.nextToken();
				parameter.SetName(word);
			} else
			if(word.compareTo("-p") == 0) {
				word = st.nextToken();
				parameter.SetPassword(word);
			} else
			if(word.compareTo("-id") == 0) {
				word = st.nextToken();
				parameter.SetId(word);
			}
		}
		if(parameter.name == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if(parameter.password == null)
			System.out.println("{'status':'error','message':'You need to be authenticated'}");
		else if (parameter.testUt("text.csv") != null) {
			parameter.GetSuccesfully("quiz.csv");
		}
	}
	public static void main(final String[] args) // throws IOException
	{
		if(args == null) {
			System.out.print("Hello world!");
			return;
		}
		/**
		 * 	In main am facut doar metodata de parcurgere a vectorului args, apoi in functie de comanda
		 * primita, am apelat metodele specifice pentru lizibilitate.
		 * 	La inceput, din pacate, nu am folosit clase diferite si doar metode creata in aceasta clasa
		 * astfel codul arata putin messy. Dar dupa ma indrept si o sa fie mult mai bine :)
		 */

		for(int i = 0; i < args.length; ++i){
			if(args[i].compareTo("-create-user") == 0) {
				CreateUser(args, i);
			} else if(args[i].compareTo("-cleanup-all") == 0) {
				Clean("text.csv");
				Clean("question.csv");
				Clean("quiz.csv");
			}
			else if (args[i].compareTo("-create-question") == 0)
				CreateQuestion(args, i);
			else if (args[i].compareTo("-get-question-id-by-text") == 0)
				GetQuestionCommand(args, i);
			else if (args[i].compareTo("-get-all-questions") == 0)
				AllGetQuestion(args, i);
			else if (args[i].compareTo("-create-quizz") == 0)
				CreateQuiz(args,i);
			else if (args[i].compareTo("-get-quizz-by-name") == 0)
				GetQuizName(args, i);
			else if (args[i].compareTo("-get-all-quizzes") == 0)
				GetAllQuizez(args, i);
			else if (args[i].compareTo("-get-quizz-details-by-id") == 0)
				GetQuizzid(args, i);
		}


	}
}
