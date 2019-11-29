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
	            doc = Jsoup.connect(url).get(); // -- 1. get방식의 URL에 연결해서 가져온 값을 doc에 담는다.zz
//	            ViewTree.makeViewTreeFile(doc, "test.txt");
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        Elements titles = doc.select(selector); // -- 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
	        
	        for(Element element: titles) { // -- 3. Elemntes 길이만큼 반복한다.
	            System.out.println(element); // -- 4. 원하는 요소가 출력된다.
	        }
	    
	}

}
