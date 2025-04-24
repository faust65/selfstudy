package com.example.news;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/news")
public class newswebcon {
	newsdao dao = new newsdao();
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Value("${news.imgdir}")
	String fdir;
	
	@Autowired
	public newswebcon(newsdao dao) {
		this.dao=dao;
	}
	
	@PostMapping("/add")
	public String addnews(@ModelAttribute news news, Model m,@RequestParam("file") MultipartFile file) {
		try {
			File dest=new File(fdir+"/"+file.getOriginalFilename());
			file.transferTo(dest);
			news.setImg(dest.getName());
			dao.addnews(news);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("newsadde");
			m.addAttribute("error","newsadde");
		}
		return "redirect:/news/list";
	}
	
	@GetMapping("/delete/{aid}")
	public String deletenews(@PathVariable int aid,Model m) {
		try {
			dao.delnews(aid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("newsdele");
			m.addAttribute("error","newsdele");
		}
		return "redirect:/news/list";
	}
	
	@GetMapping("/list")
	public String listnews(Model m) {
		List<news> list;
		try {
			list=dao.getall();
			m.addAttribute("newslist",list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("newsliste");
			m.addAttribute("error","newsliste");
		}
		return "news/newslist";
	}
	
	@GetMapping("/{aid}")
	public String getnews(@PathVariable int aid,Model m) {
		try {
			news n=dao.getnews(aid);
			m.addAttribute("news",n);
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("newsgete");
			m.addAttribute("error","newsgete");
		}
		return "news/newsview";
	}
	
}
