package prod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class prodsv {
	Map<String,prod> prods=new HashMap<>();
	
	public prodsv(){
		prod p=new prod("101","prod1","a",120000,"2020.1.22");
		prods.put("101", p);
		p=new prod("102", "prod2", "b", 1562000, "2024.2.5");
		prods.put("102", p);
		p=new prod("103", "prod3", "c", 1960000, "2021.6.18");
		prods.put("103", p);
	}
	
	public List<prod> findall() {
		return new ArrayList<>(prods.values());
	}
	
	public prod find(String id) {
		return prods.get(id);
	}
}
