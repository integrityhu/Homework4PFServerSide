/**
 * 
 */
package hu.infokristaly.archiwar.back.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_join_group")
public class UserJoinGroup implements Serializable {

    private static final long serialVersionUID = -448692172698838315L;

    @EmbeddedId
    private UserJoinGroupId userJoinGroupId;

    public UserJoinGroupId getUserJoinGroupId() {
        return userJoinGroupId;
    }

    public void setUserJoinGroupId(UserJoinGroupId userJoinGroup) {
        this.userJoinGroupId = userJoinGroup;
    }
    
}
