package com.example.news;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/news")
public class newsapicon {
	final newsdao dao;
	
	@Autowired
	public newsapicon(newsdao dao) {
		this.dao=dao;
	}
	
	@PostMapping
	public String addnews(@RequestBody news news) {
		try {
			dao.addnews(news);
		}catch(Exception e) {
			e.printStackTrace();
			return "newsadde";
		}
		return "newsadd";
	}
	
	@DeleteMapping("{aid}")
	public String delnews(@PathVariable("aid") int aid) {
		try {
			dao.delnews(aid);
		}catch(Exception e) {
			e.printStackTrace();
			return "newsdele";
		}
		return "newsdel";
	}
	
	@GetMapping
	public List<news> getnewslist() {
		List<news> newslist=null;
		try {
			newslist=dao.getall();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newslist;
	}
	
	@GetMapping("{aid}")
	public news getnews(@PathVariable("{aid}") int aid) {
		news news=null;
		try {
			news=dao.getnews(aid);
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return news;
	}
}
