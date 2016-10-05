package cz.edhouse.jdbc.domain;

import java.util.Objects;

/**
 * Represents model for tag table.
 *
 * @author Frantisek Spacek
 */
public class TagEntity extends BaseEntity<Integer> {

    private String name;
    private boolean visible;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 79 * hash + Objects.hashCode(this.name);
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
        final TagEntity other = (TagEntity) obj;
        return super.equals(obj) && Objects.equals(this.name, other.name);
    }
}
