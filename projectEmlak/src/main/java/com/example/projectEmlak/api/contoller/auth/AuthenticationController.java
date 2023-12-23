package com.example.projectEmlak.api.contoller.auth;

import com.example.projectEmlak.api.model.*;
import com.example.projectEmlak.exception.AdAlreadyExistsException;
import com.example.projectEmlak.model.CustomerEstateAd;
import com.example.projectEmlak.service.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.projectEmlak.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@RestController
//@RequestMapping("/auth")
@Controller
public class AuthenticationController {

    private CustomerService customerService;

    private EstateCompanyService estateCompanyService;
    private CustomerEstateAdService customerEstateAdService;
    private CompanyEstateAdService companyEstateAdService;
    private SearchService searchService;

    @Autowired
    public AuthenticationController(CustomerService customerService, EstateCompanyService estateCompanyService, CustomerEstateAdService customerEstateAdService, CompanyEstateAdService companyEstateAdService, SearchService searchService) {
        this.customerService = customerService;
        this.estateCompanyService = estateCompanyService;
        this.customerEstateAdService = customerEstateAdService;
        this.companyEstateAdService = companyEstateAdService;
        this.searchService = searchService;
    }


    @GetMapping("/registerCustomerPage")
    public String getRegPage(@ModelAttribute("customer") CustomerRegistrationBody customerRegistrationBody) {
        return "registerCustomerPage";
    }

    @GetMapping("/registerCompanyPage")
    public String getCompPage(@ModelAttribute("company") EstateCompRegistrationBody estateCompRegistrationBody) {
        return "registerCompanyPage";
    }





   @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("Page Title", "Home Page");
        return "home";
    }
    @GetMapping("/userOptions")
    public String showUserOptions(Model model) {
        model.addAttribute("Page Title", "Options");
        return "userOptions";
    }

    @GetMapping("/loginOptions")
    public String showLoginUserOptions(Model model) {
        model.addAttribute("Page Title", "Options");
        return "loginOptions";
    }

    @GetMapping("/homeCustomerLoggedin")
    public String showLoggedInCustomerPage(Model model) {
        model.addAttribute("Page Title", "LoggeninCustomer");
        return "homeCustomerLoggedin";
    }

    @GetMapping("/homeCompanyLoggedin")
    public String showLoggedInCompanyPage(Model model) {
        model.addAttribute("Page Title", "loggedinCompany");
        return "homeCompanyLoggedin";
    }

    @PostMapping("/registerCustomerPage")
    public String/*ResponseEntity*/ registerCustomer(@Valid /*@RequestBody*/@ModelAttribute("customer") CustomerRegistrationBody customerRegistrationBody, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/registration?failed";
        }
        try {
            customerService.registerCustomer(customerRegistrationBody);
            //return ResponseEntity.ok().build();  //eğer bir sorun yoksa, register edildiyse user, HTTP 200 OK command'ini build edip gönderiyo dışarıya
            model.addAttribute("message", "Registration Successful");
            return "redirect:/homeCustomerLoggedin"/*"registerCustomerPage"*//*"redirect:/registration?success"*/;
        } catch (UserAlreadyExistsException ex) {
            //return ResponseEntity.status(HttpStatus.CONFLICT).build();  //eğer bi sorun çıktıysa conflict command'ini build edip gönderiyo, bir conflict oluştu diye
            return "not registered"/*"redirect:/registration?failed"*/;
        }

    }

    @PostMapping("/registerCompanyPage")
    public String /*ResponseEntity*/ registerEstateCompany(@Valid /*@RequestBody*/@ModelAttribute("company") EstateCompRegistrationBody estateCompRegistrationBody, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/registration?failed";
        }
        try {
            estateCompanyService.registerEstateCompany(estateCompRegistrationBody);
            //return ResponseEntity.ok().build();  //eğer bir sorun yoksa, register edildiyse user, HTTP 200 OK command'ini build edip gönderiyo dışarıya
            model.addAttribute("message", "Registration Successful");
            return "redirect:/homeCompanyLoggedin"/*"registerCustomerPage"*//*"redirect:/registration?success"*/;
        } catch (UserAlreadyExistsException ex) {
            //return ResponseEntity.status(HttpStatus.CONFLICT).build();  //eğer bi sorun çıktıysa conflict command'ini build edip gönderiyo, bir conflict oluştu diye
            return "not registered"/*"redirect:/registration?failed"*/;
        }
    }

    @GetMapping("/loginCustomer")
    public String getLoginCustomerPage(@ModelAttribute("login") LoginBody loginBody) {
        return "loginCustomer";
    }
    @PostMapping("/loginCustomer")
    public String/*ResponseEntity<LoginResponse>*/ loginCustomer(@Valid /*@RequestBody*/@ModelAttribute("login") LoginBody loginBody,
                                                                 Model  model/*,
                                                                 RedirectAttributes attributes*/) {
        String jwt = customerService.loginCustomer(loginBody);
        if (jwt == null) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Invalid username or password";
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            model.addAttribute("message", "Login Successful");
            //return ResponseEntity.ok(response);

            return "redirect:/homeCustomerLoggedin";

            //return "loginCustomer";
        }
    }

    @GetMapping("/loginCompany")
    public String getLoginCompanyPage(@ModelAttribute("login") LoginBody loginBody) {
        return "loginCompany";
    }
    @PostMapping("/loginCompany")
    public String/*ResponseEntity<LoginResponse>*/ loginCompany(@Valid /*@RequestBody*/@ModelAttribute("login") LoginBody loginBody,
                                                                Model  model/*,
                                                                 RedirectAttributes attributes*/) {
        String jwt = estateCompanyService.loginCompany(loginBody);
        if (jwt == null) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return "Invalid username or password";
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            model.addAttribute("message", "Login Successful");
            //return ResponseEntity.ok(response);

            return "redirect:/homeCompanyLoggedin";

            //return "loginCustomer";
        }
    }

    /*@PostMapping("/register1/customer")
    public String registration(@Valid @ModelAttribute("user") CustomerRegistrationBody customerRegistrationBody,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }*/


   @GetMapping("/searchEstateAds")  //sonra if ekle addAd'den sonra çalışıyosa o ve bu
    public String getEstateAds(Model model, /*@RequestParam("keyword") String keyword*/@ModelAttribute SearchForm searchForm ) {
       String keyword = searchForm.getKeyword();
       List<Object> searchedResults = searchService.searchEntitiesByKeyword(keyword);
        model.addAttribute("searchedResults", searchedResults);
        model.addAttribute("keyword", keyword);
        return "searchEstateAds";
    }

    @GetMapping("/loggedinCustomerSearch")
    public String getLoggedinCustomerSearchEstateAds(Model model, /*@RequestParam("keyword") String keyword*/@ModelAttribute SearchForm searchForm ) {
        String keyword = searchForm.getKeyword();
        List<Object> searchedResults = searchService.searchEntitiesByKeyword(keyword);
        model.addAttribute("searchedResults", searchedResults);
        model.addAttribute("keyword", keyword);
        return "loggedinCustomerSearch";
    }

    @GetMapping("/loggedinCompanySearch")
    public String getLoggedinCompanySearchEstateAds(Model model, /*@RequestParam("keyword") String keyword*/@ModelAttribute SearchForm searchForm ) {
        String keyword = searchForm.getKeyword();
        List<Object> searchedResults = searchService.searchEntitiesByKeyword(keyword);
        model.addAttribute("searchedResults", searchedResults);
        model.addAttribute("keyword", keyword);
        return "loggedinCompanySearch";
    }


    @GetMapping("/createCustomerEstateAdPage")
    public String getCreateCustomerAdPage(@ModelAttribute("customerEstateAd") CustomerEstateAdRegistrationBody customerEstateAdRegistrationBody) {
        return "createCustomerEstateAdPage";
    }
    @PostMapping("/createCustomerEstateAdPage")
    public String/*ResponseEntity*/ createCustomerEstateAd(@Valid /*@RequestBody*/@ModelAttribute("customerEstateAd") CustomerEstateAdRegistrationBody customerEstateAdRegistrationBody, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/registration?failed";
        }
        try {
            customerEstateAdService.addCustomerEstateAd(customerEstateAdRegistrationBody);
            //return ResponseEntity.ok().build();  //eğer bir sorun yoksa, register edildiyse user, HTTP 200 OK command'ini build edip gönderiyo dışarıya
            model.addAttribute("message", "İlan Yapıldı!");
            return "createCustomerEstateAdPage"/*"redirect:/registration?success"*/;
        } catch (AdAlreadyExistsException ex) {
            //return ResponseEntity.status(HttpStatus.CONFLICT).build();  //eğer bi sorun çıktıysa conflict command'ini build edip gönderiyo, bir conflict oluştu diye
            return "cannot be created"/*"redirect:/registration?failed"*/;
        }

    }



    @GetMapping("/createCompanyEstateAdPage")
    public String getCreateCompanyAdPage(@ModelAttribute("companyEstateAd") CompanyEstateAdRegistrationBody companyEstateAdRegistrationBody) {
        return "createCompanyEstateAdPage";
    }
    @PostMapping("/createCompanyEstateAdPage")
    public String/*ResponseEntity*/ createCompanyEstateAd(@Valid /*@RequestBody*/@ModelAttribute("companyEstateAd") CompanyEstateAdRegistrationBody companyEstateAdRegistrationBody, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/registration?failed";
        }
        try {
            companyEstateAdService.addCompanyEstateAd(companyEstateAdRegistrationBody);
            //return ResponseEntity.ok().build();  //eğer bir sorun yoksa, register edildiyse user, HTTP 200 OK command'ini build edip gönderiyo dışarıya
            model.addAttribute("message", "İlan Yapıldı!");
            return "createCompanyEstateAdPage"/*"redirect:/registration?success"*/;
        } catch (AdAlreadyExistsException ex) {
            //return ResponseEntity.status(HttpStatus.CONFLICT).build();  //eğer bi sorun çıktıysa conflict command'ini build edip gönderiyo, bir conflict oluştu diye
            return "cannot be created"/*"redirect:/registration?failed"*/;
        }

    }

}
