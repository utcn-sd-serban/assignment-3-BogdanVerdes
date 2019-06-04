package ro.utcn.sd.vba.a1.dto;

import lombok.Data;
import ro.utcn.sd.vba.a1.entity.Tag;

@Data
public class TagDTO {

    private String name = "";

    public static TagDTO ofEntity(Tag tag){
        TagDTO tagDTO = new TagDTO();
        tagDTO.setName(tag.getName());

        return tagDTO;
    }
}
