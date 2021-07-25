package model;


import dto.ProjectDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "test")
public class Project {

    @Id
    private String id;

    private String projectName;

    public ProjectDTO convertToDTO() {
        return new ProjectDTO(this.id, this.projectName);
    }
}
