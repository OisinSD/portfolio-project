package portfolio.pp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portfolio.pp.models.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer>{
    
}
