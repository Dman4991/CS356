import java.util.ArrayList;

public interface ComponentElementVisitor {
	
	/**Observer pattern to count totals*/
	public ArrayList<Integer> visitComponent(UserGroupComponent component);
}
