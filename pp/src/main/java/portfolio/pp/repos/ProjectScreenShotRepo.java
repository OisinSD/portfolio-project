package portfolio.pp.repos;

import org.springframework.stereotype.Repository;

import portfolio.pp.models.ProjectScreenShot;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectScreenShotRepo extends JpaRepository<ProjectScreenShot, Integer>{
    
}
