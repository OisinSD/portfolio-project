package portfolio.pp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.pp.repos.SkillRepo;
import portfolio.pp.models.Skill;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    
    private final SkillRepo skillRepo;

    public SkillController(SkillRepo skillRepo){
        this.skillRepo = skillRepo;
    }

    @GetMapping("path")
    public List<Skill> getAllSkillMetaData() {
        return skillRepo.findAll();
    }
    
}
