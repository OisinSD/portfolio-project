package portfolio.pp.controllers;

import portfolio.pp.repos.ProjectRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.pp.models.Project;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    
    private final ProjectRepo projectRepo;

    // Link Controller to Databse
    public ProjectController(ProjectRepo projectRepo){
        this.projectRepo = projectRepo;
    }

    // Metadata endpoint
    @GetMapping
    public List<Project> getAllProjectMetaData(){
        return projectRepo.findAll(); //retrives project/s data -> JSON
    }
    @GetMapping("/test-403")
    public ResponseEntity<?> test403() {
        return ResponseEntity.status(403).build();
    }
}
