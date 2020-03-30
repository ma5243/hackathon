import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DictionaryMaker {
	private String filename;
	
	public DictionaryMaker(String filename) {
		this.filename = filename;
	}
	
	public ArrayList<String> getDictOfWords() {
	    ArrayList<String> dictWords = new ArrayList<>();

	    try {
			BufferedReader bufReader = new BufferedReader(new FileReader(this.filename));
		    String line = bufReader.readLine();
		    while (line != null) {
		    	dictWords.add(line);
		      line = bufReader.readLine();
		    }

		    bufReader.close();
		} catch (Exception e) {
			System.out.println("this file is not found in the given location");
		}
	    return dictWords;
	}
	
	public ArrayList<String> listMaker(String letter){
		ArrayList<String> li = this.getDictOfWords();
		ArrayList<String> AList = new ArrayList<String>();
		for(String word : li) {
			if (word.length() >= 5) {
				if(word.substring(0, 1).equalsIgnoreCase(letter)) {
					AList.add(word);
				}
			}
		}
		return AList;
	}
	@SuppressWarnings("rawtypes")
	public HashMap<String, ArrayList> alphaList(){
		HashMap<String, ArrayList> map = new HashMap<String, ArrayList>();
		ArrayList<String> a = this.listMaker("a");
		ArrayList<String> b = this.listMaker("b");
		ArrayList<String> c = this.listMaker("c");
		ArrayList<String> d = this.listMaker("d");
		ArrayList<String> e = this.listMaker("e");
		ArrayList<String> f = this.listMaker("f");
		ArrayList<String> g = this.listMaker("g");
		ArrayList<String> h = this.listMaker("h");
		ArrayList<String> i = this.listMaker("i");
		ArrayList<String> j = this.listMaker("j");
		ArrayList<String> k = this.listMaker("k");
		ArrayList<String> l = this.listMaker("l");
		ArrayList<String> m = this.listMaker("m");
		ArrayList<String> n = this.listMaker("n");
		ArrayList<String> o = this.listMaker("o");
		ArrayList<String> p = this.listMaker("p");
		ArrayList<String> q = this.listMaker("q");
		ArrayList<String> r = this.listMaker("r");
		ArrayList<String> s = this.listMaker("s");
		ArrayList<String> t = this.listMaker("t");
		ArrayList<String> u = this.listMaker("u");
		ArrayList<String> v = this.listMaker("v");
		ArrayList<String> w = this.listMaker("w");
		ArrayList<String> x = this.listMaker("x");
		ArrayList<String> y = this.listMaker("y");
		ArrayList<String> z = this.listMaker("z");
		map.put("a", a);
		map.put("b", b);
		map.put("c", c);
		map.put("d", d);
		map.put("e", e);
		map.put("f", f);
		map.put("g", g);
		map.put("h", h);
		map.put("i", i);
		map.put("j", j);
		map.put("k", k);
		map.put("l", l);
		map.put("m", m);
		map.put("n", n);
		map.put("o", o);
		map.put("p", p);
		map.put("q", q);
		map.put("r", r);
		map.put("s", s);
		map.put("t", t);
		map.put("u", u);
		map.put("v", v);
		map.put("w", w);
		map.put("x", x);
		map.put("y", y);
		map.put("z", z);

		return map;
	}
	
  public static void main(String[] args) throws Exception {
	  DictionaryMaker ma = new DictionaryMaker("hardWords.txt");
	  
	  System.out.println("Content of MAP:");
//	  System.out.println(yo);
	  System.out.println(ma.alphaList());
  }

}