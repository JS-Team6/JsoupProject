package org.jsoup.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/*** 기능 확장 ***/

public class ViewTree {
    private final static String _cross = " ├─";
    private final static String _corner = " └─";
    private final static String _vertical = " │ ";
    private final static String _space = "   ";
    
    private ViewTree() {}
    
    public static void makeViewTreeFile(Document doc, String fileName) {
        File file = new File(fileName);
        try {
        	
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            doc.traverse(new NodeVisitor() {
                public void head(Node node, int depth) {
                    System.out.println("Entering tag: " + node.nodeName());
                    try {
                    	for(int i=0; i<depth-1; i++) {
                    		bw.write(_space);
                    	}
                    	if(depth > 1) {
                    		bw.write(_cross);
                    	}
        				bw.write(node.nodeName()+"\n");
        			} catch (IOException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}

                }
                public void tail(Node node, int depth) {
//                    System.out.println("Exiting tag: " + node.nodeName());
                }
            });
            
            bw.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
}