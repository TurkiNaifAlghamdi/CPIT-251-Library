import java.time.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * member
 */

public class member {

    private int id;
    private String name;
    private String email;
    private int mobile;
    
    LocalDate ld;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public LocalDate getExpire_date() {
        return ld;
    }
    public void setExprire_date(String st_date){
       String[] d = st_date.split("/");
        ld = LocalDate.of(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]));
    }

    public void renew_subscription(int months){
        ld = ld.plusMonths(months);
    }

}