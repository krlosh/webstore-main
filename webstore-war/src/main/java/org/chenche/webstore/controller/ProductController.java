package org.chenche.webstore.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.chenche.webstore.domain.Product;
import org.chenche.webstore.exception.NoProductsFoundUnderCategoryException;
import org.chenche.webstore.exception.ProductNotFoundException;
import org.chenche.webstore.service.ProductService;
import org.chenche.webstore.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductValidator productValidator;
	
	//Spring MVC form whitelisting: Indicar que atributos se pueden inicializar desde el formulario
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		//binder.setDisallowedFields("unitsInOrder", "discontinued");//Estos campos no se inicializan desde el formulario
		binder.setAllowedFields("productId","name","unitPrice","description","manufacturer","category","unitsInStock", "productImage","condition","language");
		//Los custom editors permiten hacer mapeos mucho más avanzados, por ejemplo para fechas,... 
		DateFormat dateFormat = new SimpleDateFormat("MMM d, YYYY");   
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);  
		binder.registerCustomEditor(Date.class, orderDateEditor);
		binder.setValidator(productValidator);
	}
	
	
	@RequestMapping
	public String list(Model model){				
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@RequestMapping("/all")
	public ModelAndView allProducts(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products",productService.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	}
	
	@RequestMapping("/{category}")
	public String productsByCategory(Model model,@PathVariable("category")String category){
		List<Product> products = this.productService.getProductsByCategory(category);
		if(products==null||products.isEmpty()){
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products",products);
		return "products";
	}
	
	@RequestMapping("/filter/{ByCriteria}")
	public String filterByCategory(@MatrixVariable(pathVar= "ByCriteria") Map<String,List<String>> filterParams,Model model) {
	    model.addAttribute("products", productService.getProductsByFilter(filterParams));
	    return "products";
	 }
	
	@RequestMapping("/product")
	public String productById(@RequestParam("id") String id, Model model){
		model.addAttribute("product", this.productService.getProductById(id));
		
		return "product";
		
	}
	
	@RequestMapping("{category}/{price}")
	public String productByManufacturer(Model model,
			@MatrixVariable(pathVar = "price") Map<String, List<String>> filterParams,
			@PathVariable("category") String category, @RequestParam("manufacturer") String manufacturer) {
		
		List<Product> productsByCategory = this.productService.getProductsByCategory(category);
		List<Product> productByManufacturer = this.productService.getProductsByManufacturer(manufacturer);
		Set<Product> productsByPrice = this.productService.getProductsByPriceFilter(filterParams);
		productsByPrice.retainAll(productByManufacturer);
		productsByPrice.retainAll(productsByCategory);
		model.addAttribute("products", productsByPrice);
		return "products";
	}
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showAddProductForm(Model model){
		model.addAttribute("newProduct", new Product());
		//No sería necesario crear esta instancia de Product si en la firma del metodo incluyeramos: 
		//@ModelAttribute("newProduct") Product newProduct 
		return "addProduct";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String processAddProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result,HttpServletRequest request){
		
		if(result.hasErrors()) {
		  return "addProduct";
		}
		
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));

		}
		
		MultipartFile productImage =newProduct.getProductImage();
		String rootDirectory =request.getSession().getServletContext().getRealPath("/");

		if (productImage!=null && !productImage.isEmpty()) {
		  try {
		    productImage.transferTo(new File(rootDirectory+"resources\\images\\"+newProduct.getProductId() + ".png"));
		  } catch (Exception e) {
		    throw new RuntimeException("Product Image saving failed",e);
		  }
		}
		
		MultipartFile productManual =newProduct.getProductImage();

		if (productManual!=null && !productManual.isEmpty()) {
		  try {
		    productImage.transferTo(new File(rootDirectory+"resources\\pdf\\"+newProduct.getProductId() + ".pdf"));
		  } catch (Exception e) {
		    throw new RuntimeException("Product Manual saving failed",e);
		  }
		}
		
		
		this.productService.addProduct(newProduct);
		return "redirect:/products"; //Patron: redirect after post-> Permite evitar dobles submits
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, ProductNotFoundException exception){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("invalidProductId",exception.getProductId());
		modelAndView.addObject("exception",exception);
		modelAndView.addObject("url",request.getRequestURL()+"?"+exception.getProductId());
		modelAndView.setViewName("productNotFound");
		return modelAndView;
	}
	
	@RequestMapping("/invalidPromoCode")
	public String invalidPromoCode() {
	  return "invalidPromoCode";
	}
}
