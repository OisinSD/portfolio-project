package portfolio.pp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.pp.models.ProjectScreenShot;
import portfolio.pp.repos.ProjectScreenShotRepo;
import java.util.List;


@RestController
@RequestMapping("/api/screenshots")
public class ProjectScreenShotController {
    
    private final ProjectScreenShotRepo projectScreenShotRepo;

    public ProjectScreenShotController(ProjectScreenShotRepo projectScreenShotRepo){
        this.projectScreenShotRepo = projectScreenShotRepo;
    }

    @GetMapping
    public List<ProjectScreenShot> getAllProjectScreenShotMetaData(){
        return projectScreenShotRepo.findAll();
    }
}
