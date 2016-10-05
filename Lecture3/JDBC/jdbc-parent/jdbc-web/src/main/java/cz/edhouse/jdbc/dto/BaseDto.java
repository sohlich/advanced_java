package cz.edhouse.jdbc.dto;

import java.io.Serializable;

/**
 *
 * @author Frantisek Spacek
 * @param <PrimaryKeyType>
 */
public class BaseDto<PrimaryKeyType extends Number> implements Serializable {

    private PrimaryKeyType id;

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
