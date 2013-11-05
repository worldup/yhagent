package com.nangua.yhagent.billing.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {
	 public static void main(String[] args) {
	        try {
	            HttpServer hs = HttpServer.create(new InetSocketAddress(8888),0);//设置HttpServer的端口为8888
	            hs.createContext("/test", new MyHandler());//用MyHandler类内处理到/chinajash的请求
	            hs.setExecutor(null); // creates a default executor
	            hs.start();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
}
class MyHandler implements HttpHandler {
    
    /* (non-Javadoc)
     * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
     */
    public void handle(HttpExchange httpexchnge) throws IOException {
    	
    	BufferedReader br=new BufferedReader(new InputStreamReader(   httpexchnge.getRequestBody()));
        String a=null;
    	do{
        	  a=br.readLine();
        	if(a!=null) System.out.println(a);
        }while(a!=null);
        String response = "<h3>Happy New Year 2007!--Soddabao</h3>";
        httpexchnge.sendResponseHeaders(200, response.length());
        OutputStream os = httpexchnge.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
 }
