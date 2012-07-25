package TopoClass;

import java.io.File;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class DrawClass {

	/**
	 * @param args
	 */
	public static String strPath=null,pic_pathString=null,exe_pathString=null;
	public static String[] outStrings;
	public static String[] colorStrings;
	 private void start(String PicturePath,String exeString,String Str_Path)
	   {
		 String type = "gif";
//	      String type = "dot";
//	      String type = "fig";    // open with xfig
//	      String type = "pdf";
//	      String type = "ps";
//	      String type = "svg";    // open with inkscape
//	      String type = "png";
//	      String type = "plain";
	    //  File out = new File("/tmp/out." + type);   // Linux
		  File file=new File(PicturePath+"/out." + type);
		  if(file.exists()){
			  file.delete();
		  }
	      GraphViz gv = new GraphViz(PicturePath,exeString);
	      gv.addln(gv.start_graph());
	     // File file=new File("C:/test.txt");
	    /*  try{
		      BufferedReader reader=new BufferedReader(new FileReader(file));
		      String tempString=null;
		      while((tempString=reader.readLine())!=null)
		      {*/
		    	  gv.addln(Str_Path);
	    /*  for(int m=0;m<strings.length;m++){
	    	  
	          gv.addln("subgraph cluster"+m+"{");
	          //gv.addln("style=filled;");
	          gv.addln(Str_Path[m]);
	          gv.addln("label=\""+strings[m]+" Package\"; fontcolor=blue;fontsize=40;");
	          gv.addln("}");
	      }*/
		    	  
		     /* }
		      reader.close();*/
		     gv.addln(gv.end_graph());
		      System.out.println(gv.getDotSource());
	     
	     
	    File out = new File(PicturePath+"/out." + type);    // Windows
	      gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	   }
	
   private void config(){
	   File inputXml = null;
		SAXBuilder saxBuilder = null;
		Document document = null;	
		try {
			inputXml = new File("src/Config.xml");
			saxBuilder = new SAXBuilder();
			document=saxBuilder.build(inputXml);
			Element root=document.getRootElement();  
			Element wpathElement=root.getChild("workspacepath");
			strPath=wpathElement.getValue().toString();
			Element ppathElement=root.getChild("picturepath");
			pic_pathString=ppathElement.getValue().toString();
			Element tpathElement=root.getChild("toolpath");
			exe_pathString=tpathElement.getValue().toString();
		    Element outelement=root.getChild("packagename-config");
		    outStrings=outelement.getText().split(",");
		    Element colorelement=root.getChild("color-config");
		    colorStrings=colorelement.getText().split(",");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   }
	public static void main(String[] args) {
		
		DrawClass ds=new DrawClass();
		ds.config();
		ReadXML rXml=new ReadXML();
		String str_topo=rXml.ReadOutXML(strPath,outStrings,colorStrings);
		ds.start(pic_pathString,exe_pathString,str_topo);	
	}

}
