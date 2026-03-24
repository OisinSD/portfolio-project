package portfolio.pp.models;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "ProjectTech")
public class ProjectTech {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "tech_id", nullable = false)
    private Technology technology;
}
