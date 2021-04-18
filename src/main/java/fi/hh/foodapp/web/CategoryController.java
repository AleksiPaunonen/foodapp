package fi.hh.foodapp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.foodapp.domain.Category;
import fi.hh.foodapp.domain.CategoryRepository;

@CrossOrigin
@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository crepository;
	
	// RESTful näytä kaikki kategoriat
	@RequestMapping(value="/kategoriat", method = RequestMethod.GET)
	public @ResponseBody List<Category> getCategoriesRest() {
		return (List<Category>) crepository.findAll();
	}
	
	// RESTful näytä kategoria id:n perusteella
	@RequestMapping(value="/kategoriat/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long cId) {
		return crepository.findById(cId);
	}
	
	// RESTful tallenna uusi kategoria
	@RequestMapping(value="/kategoriat", method = RequestMethod.POST)
	public @ResponseBody Category saveCategoryRest(@RequestBody Category category) {
		return crepository.save(category);
	}
}
