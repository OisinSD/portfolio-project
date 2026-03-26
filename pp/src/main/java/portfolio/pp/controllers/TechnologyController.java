package portfolio.pp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.pp.repos.TechnologyRepo;
import portfolio.pp.models.Technology;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {
    
    private final TechnologyRepo technologyRepo;

    public TechnologyController(TechnologyRepo technologyRepo){
        this.technologyRepo = technologyRepo;
    }

    @GetMapping
    public List<Technology> getAllTechnologysMetaData() {
        return technologyRepo.findAll();
    }
    
}
