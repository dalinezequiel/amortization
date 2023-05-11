package property;

import com.simor.dao.ApplicationDAO;

public class Application extends ApplicationDAO {
	public Application() {
		// SERVER // DATABASE NAME // PORT // USER // PASSWORD
		super("localhost", "simor", 5432, "postgres", "hund,70");
	}
}
