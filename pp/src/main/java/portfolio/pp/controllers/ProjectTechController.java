package portfolio.pp.controllers;

import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.pp.repos.ProjectTechRepo;
import portfolio.pp.models.ProjectTech;
import java.util.List;

@RestController
@RequestMapping("/api/projecttech")
public class ProjectTechController {
    

    private final ProjectTechRepo projectTechRepo;

    
    public ProjectTechController(ProjectTechRepo projectTechRepo){
        this.projectTechRepo = projectTechRepo;
    }
    
    @GetMapping
    public List<ProjectTech> getAllProjectTechMetaData(){
        return projectTechRepo.findAll();
    }
}
