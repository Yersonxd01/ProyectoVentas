package factory;

import service.CustomerService;
import service.impl.CustomerServicempl;

public class MysqlDAOFactory extends DAOFactory{

	@Override
	public CustomerService getCustomer() {
		return new CustomerServicempl();
	}

}
