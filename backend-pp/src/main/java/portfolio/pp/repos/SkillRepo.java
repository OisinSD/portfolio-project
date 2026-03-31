package portfolio.pp.repos;

import org.springframework.stereotype.Repository;

import portfolio.pp.models.Skill;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer>{
    
}
