package cz.fai.utb.spring.blog.dto;
import java.io.Serializable;

/**
 *
 * @author Frantisek Spacek
 * @param <PrimaryKeyType>
 */
public class BaseDto<PrimaryKeyType extends Integer> implements Serializable {

    private PrimaryKeyType id;

    public BaseDto() {
    }

    
    public BaseDto(PrimaryKeyType id) {
        this.id = id;
    }

    public PrimaryKeyType getId() {
        return id;
    }

    public void setId(PrimaryKeyType id) {
        this.id = id;
    }

}
