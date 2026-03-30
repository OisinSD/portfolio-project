package portfolio.pp.controllers;

import portfolio.pp.repos.ProjectRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
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
public void view403(HttpServletResponse response) throws java.io.IOException {
    // This tells the container "an error happened," 
    // which triggers the automatic search for your error page.
    response.sendError(HttpServletResponse.SC_FORBIDDEN);
}
}

