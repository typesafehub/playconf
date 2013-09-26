package models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.JsonNode;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class RegisteredUser extends Model {

    @Id
    public Long id;
    
    @Required
    public String name;
    
    @MaxLength(value = 200)
    public String description;
    
    @Required
    public String pictureUrl;
    
    @Required
    public String twitterId;
    
    @Required
    public Date registrationDate = new Date(System.currentTimeMillis());

    public static RegisteredUser fromJson(JsonNode twitterJson) {
        RegisteredUser u = new RegisteredUser();
        u.name = twitterJson.findPath("name").asText();
        u.twitterId = twitterJson.findPath("screen_name").asText();
        u.description = twitterJson.findPath("description").asText();
        u.pictureUrl = twitterJson.findPath("profile_image_url").asText();
        return u;        
    }
}
