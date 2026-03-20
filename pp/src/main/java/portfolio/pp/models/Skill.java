package portfolio.pp.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "skills")
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_name", length = 100, nullable = false)
    private String name;

    @Column(name = "proficiency_level", length = 200, nullable = false)
    private String proficiencyLevel;


    @Column(name = "narrative", length = 300, nullable = false)
    private String narrative;

    @Column(name = "category", length = 200)
    private String category;

    // 2. For ProjectSkills (The Joiner Table)
    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectSkill> projectSkills = new ArrayList<>();

}
