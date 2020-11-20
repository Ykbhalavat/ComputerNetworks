import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class Info_Client {
    String name;
    
    Date logindate = new Date();
    
    public Info_Client(String name, Date login )
    {
        this.name = name;
        this.logindate = login;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}
    
}
