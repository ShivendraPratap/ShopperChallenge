package shopper.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import shopper.model.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/** 
 * Entity to hold the application status
 * Created by ssingh9 on 8/2/15.
 */
@Entity
public class AppStatus implements Serializable {

    private static final long serialVersionUID = 2711961024108614560L;
    
    @Id
    @GeneratedValue
    private long id;

    @JsonProperty
    @Column(nullable = false)
    private long shopperId;

    @JsonProperty
    @Column(nullable = false)
    private Date date;

    @JsonProperty
    @Column(nullable = false)
    private StatusEnum status;

    protected AppStatus() {
    }

    public AppStatus(Long shopperId, Date date, StatusEnum status) {
        this.shopperId = shopperId;
        this.date = date;
        this.status = status;
    }

    public long getShopperId() {
        return shopperId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Appstatus: " + id + " " + shopperId + " " + date + " " + status);

    }

}
