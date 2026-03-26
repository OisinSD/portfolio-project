package portfolio.pp.repos;

import org.springframework.stereotype.Repository;

import portfolio.pp.models.ProjectTech;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectTechRepo extends JpaRepository<ProjectTech, Integer>{
    
}
