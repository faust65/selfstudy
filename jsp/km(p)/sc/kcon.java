package kgide;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.qos.logback.core.model.Model;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/kgd")
public class kcon {
	final kdao dao;
	
	public kcon(kdao dao) {
		this.dao=dao;
	}
	
	@GetMapping("/abtkm")
	public String abtkm(Model m) {
		return "km/abtkm";
	}
	
	@GetMapping("/abtkm/bch")
	public String bch(Model m) {
		return "km/bchkm";
	}
	
	@PostMapping("/login")
	public String klog(@RequestBody km k) {
		try {
			if(dao.login(k)) {
				return "km/abtkm";
			}else {
				return "로그인 실패";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "로그인됨";
	}
	
	@GetMapping("/login")
	public String kloget(Model m) {
		return "km/login";
	}
	
	@PostMapping("/newac")
	public String knew(@RequestBody km k) {
		try {
			dao.newac(k);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "km/login";
	}
	
	@GetMapping("/newac")
	public String knewget(Model m) {
		return "km/newac";
	}
	
	
	@GetMapping("/kmunnity")
	public List<km> kmu(Model m) {
		List<km> kmlist= null;
		try {
			kmlist=dao.kmshow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kmlist;
	}
	
	@PostMapping
	public String addkmu(@RequestBody km k) {
		try {
			dao.addkmu(k);
		} catch (Exception e) {
			e.printStackTrace();
			return "등록 실패";
		}
		return "등록됨";
	}
	
	@DeleteMapping("{cid}")
	public String delkmu(@PathVariable("cid") int cid) {
		try {
			dao.delkmu(cid);
		} catch (Exception e) {
			e.printStackTrace();
			return "삭제 실패"+cid;
		}
		return "삭제됨"+cid;
	}
	
	
}
