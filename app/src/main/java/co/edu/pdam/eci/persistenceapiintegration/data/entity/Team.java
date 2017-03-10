package co.edu.pdam.eci.persistenceapiintegration.data.entity;

import com.j256.ormlite.field.DatabaseField;

/**
 * @author Santiago Carrillo
 */

//TODO add database annotations and proper model structure
public class Team extends BaseEntity
{

    @DatabaseField
    String name;
    @DatabaseField
    String shortName;
    @DatabaseField
    String imgUrl;

    public Team(){
    }

    public Team (Long id, String name, String shortName, String imgUrl){
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
