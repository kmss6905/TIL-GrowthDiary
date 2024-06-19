package database.springfilterinterceptor;

import lombok.ToString.Exclude;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @ResponseBody
  @GetMapping("/authentication-filter")
  public ResponseEntity<?> requiredAuthenticationApi() {
    productService.decreaseStock();
    return ResponseEntity.ok().build();
  }


  @GetMapping("/forward")
  public String forward() {
    System.out.println("do forward");
    return "forward:/end-forward";
  }

  @GetMapping("/end-forward")
  public ModelAndView endForward() {
    System.out.println("end-forward");
    return new ModelAndView("index");
  }

  @GetMapping("/error")
  public void test() {
    throw new RuntimeException();
  }
}
