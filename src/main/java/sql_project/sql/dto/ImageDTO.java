package sql_project.sql.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ImageDTO {

    private Long id;
    private String url;
    private List<Long> articlesIds;

}
