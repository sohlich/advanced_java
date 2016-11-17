package cz.fai.blog.dto;

import java.io.Serializable;

/**
 *
 * @author Frantisek Spacek
 * @param <PrimaryKeyType>
 */
public class BaseDto<PrimaryKeyType extends Integer> implements Serializable {

    private PrimaryKeyType id;

<<<<<<< HEAD
    public BaseDto() {
    }

    
=======
>>>>>>> 6f6ecc94441eedad94a281af9787ea4dfe0f84ef
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
