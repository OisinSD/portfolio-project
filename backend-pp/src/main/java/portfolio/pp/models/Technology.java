package portfolio.pp.models;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Technologies")
public class Technology {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "icon_url", nullable = false, columnDefinition = "TEXT")
    private String iconUrl;

    @Column(name = "tech_category", length = 300)
    private String techCategory;

    @OneToMany(mappedBy = "technology", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectTech> projectTech = new ArrayList<>();
}
