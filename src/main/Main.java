package main;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.helper.ViewTree;
public class Main {

	public static void main(String[] args) {
		// example
	       String url = "https://dhlottery.co.kr/gameResult.do?method=byWin";
	        String selector = ".logo";
	        Document doc = null;    
	        
	        try {
	            doc = Jsoup.connect(url).get(); // -- 1. get����� URL�� �����ؼ� ������ ���� doc�� ��´�.zz
//	            ViewTree.makeViewTreeFile(doc, "test.txt");
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        Elements titles = doc.select(selector); // -- 2. doc���� selector�� ������ ������ Elemntes Ŭ������ ��´�.
	        
	        for(Element element: titles) { // -- 3. Elemntes ���̸�ŭ �ݺ��Ѵ�.
	            System.out.println(element); // -- 4. ���ϴ� ��Ұ� ��µȴ�.
	        }
	    
	}

}
