package hr.fer.opp.exceptions;

public abstract class ExceptionMessages {

	public static final String EXCEPTION_MESSAGE_USER_EXISTS = "User with given e-mail already exists.";

	public static final String EXCEPTION_MESSAGE_EMPLOYEE_NOT_EXIST = "Employee with given id does not exist";

	public static final String EXCEPTION_MESSAGE_EMPLOYEE_EMAIL_NOT_EXIST = "Employee with given email does not exist";

	public static final String EXCEPTION_MESSAGE_EMPLOYEE_OIB_NOT_EXIST = "Employee with given oib does not exist";

	public static final String EXCEPTION_MESSAGE_EMPLOYEE_EMAIL_EXISTS = "Employee with given e-mail already exists.";

	public static final String EXCEPTION_MESSAGE_EMPLOYEE_OIB_EXISTS = "Employee with given oib already exists.";

	public static final String EXCEPTION_MESSAGE_EMPLOYEE_CAN_NOT_REGISTER = "Can't register given employee in requested neighborhoodt";

	public static final String EXCEPTION_MESSAGE_EMPLOYEE_CAN_NOT_UPDATE = "Can't update given employee in requested neighborhood";

	public static final String EXCEPTION_MESSAGE_NEIGHBORHOOD_NOT_EXIST = "Neighborhood with given id does not exist";

	public static final String EXCEPTION_MESSAGE_NEIGHBORHOOD_NAME_NOT_EXIST = "Neighborhood with given name does not exist.";

	public static final String EXCEPTION_MESSAGE_NEIGHBORHOOD_NAME_EXISTS = "Neighborhood with given name already exists.";

	public static final String EXCEPTION_MESSAGE_CONTAINER_NOT_EXIST = "Container with given id does not exist";

	public static final String EXCEPTION_MESSAGE_CONTAINER_CAN_NOT_REGISTER = "Can't register given container in requested neighborhood.";

	public static final String EXCEPTION_MESSAGE_CONTAINER_CAN_NOT_UPDATE = "Can't update given container in requested neighborhood.";

	public static final String EXCEPTION_MESSAGE_CONTAINER_CAN_NOT_REGISTER_FAVORITE = "Can't register given container as a favorite";

	public static final String EXCEPTION_MESSAGE_FAVORITE_NOT_EXIST = "Favorite does not exist.";
}
