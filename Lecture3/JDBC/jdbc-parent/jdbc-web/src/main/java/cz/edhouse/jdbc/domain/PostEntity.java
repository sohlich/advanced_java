package cz.edhouse.jdbc.domain;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents model for post table.
 *
 * @author Frantisek Spacek
 */
public class PostEntity extends BaseEntity<Integer> {

    private String title;
    private String content;
    private boolean published;
    private long authorId;

    private Set<TagEntity> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Set<TagEntity> getTags() {
        if (tags == null) {
            this.tags = new LinkedHashSet<>();
        }
        return tags;
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
    }

}
