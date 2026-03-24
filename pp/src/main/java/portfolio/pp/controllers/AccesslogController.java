package portfolio.pp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.pp.repos.AccessLogRepo;
import portfolio.pp.models.AccessLog;
import java.util.List;

@RestController
@RequestMapping("/api/accessLogs")
public class AccesslogController {
    private final AccessLogRepo accessLogRepo;

    public AccesslogController(AccessLogRepo accessLogRepo){
        this.accessLogRepo = accessLogRepo;
    }
    @GetMapping
    public List<AccessLog> getAllAccessLogsMetaData(){
        return accessLogRepo.findAll();
    }
}
