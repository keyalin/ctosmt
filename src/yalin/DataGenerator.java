package yalin;

import java.io.File;

import db.Saver;

public class DataGenerator {
	public static void generateDatabase(String fold){
		File dir = new File(fold);
		for(String file : dir.list()){
			String path = fold + "/" + file;
			EntryTranslator tran = new EntryTranslator(path);
			System.out.println("--------------------------------------");
			tran.getObject().print();
			Saver.save(tran.getObject());
		}
	}
	
	public static void main(String[] args){
		generateDatabase("entries");
	}
}
