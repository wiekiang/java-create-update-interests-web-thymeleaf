## Create & Update Interests with Thymeleaf

This code is a basic Spring Boot application using Thymeleaf for rendering HTML views and managing a simple entity, **Interest**, with CRUD operations. Here’s a breakdown of the different components:

### Entity Class

```
@Entity @Data 
class Interest {
    @Id @GeneratedValue 
    private Long id; 
    
    private String name;
}
```

* **@Entity**: Marks the **Interest** class as a JPA entity that will be mapped to a database table.
* **@Data**: Lombok annotation to automatically generate getters, setters, and toString() methods.
* **@Id**: Marks **id** as the primary key.
* **@GeneratedValue**: Specifies that the **id** will be automatically generated.

### Repository Interface

```
@RepositoryRestResource
interface InterestRepository extends JpaRepository<Interest, Long> {}
```

* **@RepositoryRestResource**: Exposes the repository as a RESTful resource. This means CRUD operations can be performed through HTTP methods (e.g., GET, POST, PUT, DELETE).

### Controller

```
@Controller @Data 
class InterestController {
    private final InterestRepository interestRepository;

    @GetMapping("/uiinterests/{id}")
    public String _retrieve(@PathVariable Long id, Model model) { 
        Interest i = interestRepository.findById(id).orElseThrow(); 
        model.addAttribute("interest", i);
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
```

* **@Controller**: Marks the class as a Spring MVC controller.
* **@GetMapping("/uiinterests/{id}")**: Handles GET requests to retrieve an **Interest** by its ID and display it using the **retrieve.html** view.
* **@PostMapping("/uiinterests/{id}")**: Handles POST requests to update an existing **Interest**.
* **@PostMapping("/uiinterests")**: Handles POST requests to create a new **Interes**t.
* **@ResponseBody**: Indicates that the return value of the method is a response body and not a view name.

### Main Application

```
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App, args);
    }
}
```

* **@SpringBootApplication**: Indicates that this class is the entry point of the Spring Boot application.
* **main** method: Launches the application.

### Dependencies

```
<!-- Spring Boot Starter Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Spring Boot Starter Thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- Spring Boot Starter Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- H2 Database (In-memory) -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>provided</scope>
</dependency>

<!-- Spring Boot Starter Data REST -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>
```
