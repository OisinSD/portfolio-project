package portfolio.pp.repos;

import org.springframework.stereotype.Repository;

import portfolio.pp.models.ProjectSkill;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProjectSkillRepo extends JpaRepository<ProjectSkill, Integer>{
    
}
