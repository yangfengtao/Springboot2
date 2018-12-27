package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value="books")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	//定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(BookController.class);

	static Map<String, Book> books = Collections.synchronizedMap(new HashMap<String, Book>());
	
	@RequestMapping(value="displayHeaderInfo", method= RequestMethod.GET)  
	public void displayHeaderInfo(@RequestHeader("Accept-Encoding") String encoding,  
	                              @RequestHeader("Accept") String Accept,
	                              @RequestHeader("Connection") String Connection,
	                              @RequestHeader("User-Agent") String UserAgent
			)  {  
	  
	  System.out.println("class BookController.displayHeaderInfo" + 
			  "--Accept-Encoding:" + encoding + 
			  "--Accept:" + Accept + 
			  "--Connection:" + Connection + 
			  "User-Agent:" + UserAgent);  
	  
	} 

	
	
	@RequestMapping(value="displayHeaderInfo1", method= RequestMethod.GET)  
	public void displayHeaderInfo1(@RequestHeader HttpHeaders httpHeaders
			)  {  
		//TODO httpHeaders will have many methods
		Map<String,String> headerMap = httpHeaders.toSingleValueMap();
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			System.out.println("entry.getKey():" + entry.getKey() + ",entry.getValue():" + entry.getValue());
		}
	}
	
	
	@RequestMapping(value="getUserList", method= RequestMethod.GET)
	public List<Book> getUserList() {
		List<Book> r = new ArrayList<Book>(books.values());
		return r;
	}
	
	@RequestMapping(value="getBook1/{bookId}", method = RequestMethod.GET)
	public Long getBook1(@PathVariable Long bookId) {
		return bookId;
	}
	
	@RequestMapping(value="getBook", method = RequestMethod.GET)
	public Long getBook(@RequestParam("id") Long bookId, @RequestParam("name") String name){
		return bookId;
	}
	
	//raw:@RequestBody
	@RequestMapping(value="createBook", method= RequestMethod.POST,produces = "application/json")
	public String createBook(@RequestBody Book book) {
		bookRepository.saveAndFlush(book);
		logger.error("createBook.error");
		logger.warn("createBook.warn");
		logger.info("createBook.info");
		logger.debug("createBook.debug");
		return "success";
	}
	@RequestMapping(value="createBook2", method= RequestMethod.POST,produces = "text/xml")
	public String createBook2(@RequestBody String a) {
		System.out.println(a);
		return "success";
	}
	
	//key-value:@RequestParam
	@RequestMapping(value="createBook1", method= RequestMethod.POST,produces = "application/x-www-form-urlencoded")
	public String createBook1(@RequestParam("key1") String key1, @RequestParam("key2") String key2) {
		System.out.println(key1);
		System.out.println(key2);
		return "success";
	}
	@RequestMapping(value="createBook3", method= RequestMethod.POST,produces = "multipart/form-data")
	public String createBook3(@RequestParam("files") MultipartFile[] files,
			String text) {
		if(files != null && files.length > 0){
			for(int i = 0; i < files.length; i++){
				System.out.println("--file.getName():" + files[i].getName() + 
						"--file.getContentType():" + files[i].getContentType() + 
						"--file.getOriginalFilename():" + files[i].getOriginalFilename());
			}
		}
		System.out.println(text);
		return "success";
	}
	
	@RequestMapping(value="updateBook/{bookId}", method= RequestMethod.PUT)
	public String updateBook(@PathVariable Long bookId, @RequestBody Book book) {
		return bookId.toString();
	}
	
	
	@RequestMapping(value="delete/{bookId}", method= RequestMethod.DELETE)
	public String deleteBook(@PathVariable Long bookId){
		return bookId.toString();
	}
	
}
