package fi.hh.foodapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.hh.foodapp.domain.FoodRepository;
import fi.hh.foodapp.domain.CategoryRepository;
import fi.hh.foodapp.domain.Food;

@Controller
public class FoodController {
	
	@Autowired
	private FoodRepository frepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// näytä kaikki ruoat
	@RequestMapping(value = "/index")
	public String foodList(Model model) {
		model.addAttribute("foods", frepository.findAll());
		return "foodlist";
	}
	
	// lisää uusi ruoka
	@RequestMapping(value = "/uusiruoka")
	public String addFood(Model model) {
		model.addAttribute("food", new Food());
		model.addAttribute("category", crepository.findAll());
		return "addfood";
	}

}
