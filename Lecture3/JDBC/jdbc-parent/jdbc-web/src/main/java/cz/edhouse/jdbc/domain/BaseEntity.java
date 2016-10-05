package cz.edhouse.jdbc.domain;

import java.util.Objects;

/**
 * Base entity.
 *
 * @author Frantisek Spacek
 * @param <PrimaryKeyType> type of primary key
 */
public class BaseEntity<PrimaryKeyType extends Number> {

    private PrimaryKeyType id;

    public PrimaryKeyType getId() {
        return id;
    }

    public void setId(PrimaryKeyType id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseEntity<?> other = (BaseEntity<?>) obj;
        return Objects.equals(this.id, other.id);
    }
}
