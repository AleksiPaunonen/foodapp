package fi.hh.foodapp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.foodapp.domain.CategoryRepository;
import fi.hh.foodapp.domain.Food;
import fi.hh.foodapp.domain.FoodRepository;

@CrossOrigin
@Controller
public class FoodController {
	
	@Autowired
	private FoodRepository frepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Näytä kaikki ruoat
	@RequestMapping(value = "/ruokalista")
	public String foodList(Model model) {
		model.addAttribute("foods", frepository.findAll());
		return "foodlist";
	}
	
	// Ruoan lisäyslomake
	@RequestMapping(value = "/uusiruoka")
	public String addFood(Model model) {
		model.addAttribute("food", new Food());
		model.addAttribute("categories", crepository.findAll());
		return "addfood";
	}
	
	// Ruoan tallentaminen
	@RequestMapping(value = "/tallenna", method = RequestMethod.POST)
	public String save(Food food) {
		frepository.save(food);
		return "redirect:ruokalista";
	}
	
	// Ruoan poistaminen
	@RequestMapping(value = "/poista/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteFood(@PathVariable("id") Long foodId, Model model) {
		frepository.deleteById(foodId);
		return "redirect:../ruokalista";
	}
	
	// Ruoan muokkaaminen
	@RequestMapping(value = "/muokkaa/{id}")
	public String editFood(@PathVariable("id") Long foodId, Model model) {
		model.addAttribute("food", frepository.findById(foodId));
		model.addAttribute("categories", crepository.findAll());
		return "editfood";
	}
	
	// RESTful näytä kaikki ruoat
	@RequestMapping(value="/ruoat", method = RequestMethod.GET)
	public @ResponseBody List<Food> foodListRest() {
		return (List<Food>) frepository.findAll();
	}
	
	// RESTful näytä ruoka id:n perusteella
	@RequestMapping(value="/ruoat/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Food> findFoodRest(@PathVariable("id") Long foodId) {
		return frepository.findById(foodId);
	}
	
	// RESTful ruoan tallentaminen
	@RequestMapping(value="/ruoat", method = RequestMethod.POST)
	public @ResponseBody Food saveFoodRest(@RequestBody Food food) {
		return frepository.save(food);
	}
}
