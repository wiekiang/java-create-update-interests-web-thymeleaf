/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package au.edu.cqu.App;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.rest.core.annotation.RepositoryRestResource; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author wiekiang
 */

@Entity @Data 
class Interest {
    @Id @GeneratedValue 
    private Long id; 
    
    private String name;
}

@RepositoryRestResource
interface InterestRepository extends JpaRepository<Interest, Long> {}

@Controller @Data 
class InterestController {
    private final InterestRepository interestRepository;

    @GetMapping("/uiinterests/{id}")
    public String _retrieve(@PathVariable Long id, Model model) { 
        Interest i=interestRepository.findById(id).orElseThrow(); 
        model.addAttribute("interest",i);
        return "retrieve.html";
    }

    @PostMapping("/uiinterests/{id}") 
    @ResponseBody
    public String _update(@PathVariable Long id, @ModelAttribute Interest interest) {
        interestRepository.save(interest); 
        return "Record Updated!<br><a href='http://localhost:8080/'>Home</a>";
    }

    @PostMapping("/uiinterests") 
    @ResponseBody
    public String _create(@ModelAttribute Interest interest) { 
        interestRepository.save(interest);
        return "Record Added!<br><a href='http://localhost:8080/'>Home</a>";
    }
}

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
