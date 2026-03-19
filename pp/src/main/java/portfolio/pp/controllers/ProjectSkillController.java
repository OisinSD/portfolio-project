package portfolio.pp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.pp.models.ProjectSkill;
import portfolio.pp.repos.ProjectSkillRepo;
import java.util.List;



@RestController
@RequestMapping
public class ProjectSkillController {

    private final ProjectSkillRepo projectSkillRepo;

    public ProjectSkillController(ProjectSkillRepo projectSkillRepo){
        this.projectSkillRepo = projectSkillRepo;
    }

    @GetMapping
    public List<ProjectSkill> getAllProjectSkillMetaData(){
        return projectSkillRepo.findAll();
    }
}
