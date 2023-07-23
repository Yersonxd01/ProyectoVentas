package view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import service.CustomerService;

import java.io.IOException;
import java.util.List;

import factory.DAOFactory;


public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	CustomerService cusService = daoFactory.getCustomer();
 
    public CustomerServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String type = request.getParameter("type");
	
	switch (type) {
	case "registrar":
		String codigo =request.getParameter("txtcode");
		if(codigo != null && !codigo.isEmpty()) {
			udpateCustomer(request,response);
		}else {
			addCustomer(request, response);
		}
		break;

	case"obtener":
		getCustomer(request, response);
		
		break;
	case"eliminar":
	    deleteCustomer(request,response);
	  break;
	case "listar":
		getcustomers(request,response);
		break;
	case"limpiar":
	   clean(request,response);
	   break;
	   
	  default:
		  break;
	}
	}


	private void udpateCustomer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String code = request.getParameter("");
		String name = request.getParameter("txtName");
		String documentType = request.getParameter("SelectDocument");
		String documentNumber = request.getParameter("txtDocument");
		String phone = request.getParameter("txtPhone");
		String address = request.getParameter("txtAddress");
		String business = request.getParameter("txtBusiness");
		
		Customer c = new Customer();
		c.setId(Integer.parseInt(code));
		c.setName(name);
		c.setDocumenType(documentType);
		c.setDocumentNumber(documentNumber);
		c.setPhone(phone);
        c.setAddress(address);
        c.setBusinessName(business);
        
        int value = cusService.updateCustomer(c);
        
        if(value == 1) {
			getCustomer(request, response);
		}else {
			request.setAttribute("mensaje","Error al Actulizar");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
	
	}

	private void clean(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
         Customer c = new Customer();
		
		request.setAttribute("customer", c);
		getCustomer(request, response);
	}

	private void getcustomers(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String cod = request.getParameter("txtcode");
		Customer p  = cusService.getCustomer(Integer.parseInt(cod));
		if (p != null) {
			request.setAttribute("Persona", p);
			getCustomer(request, response);
		}else {
			request.setAttribute("mensaje","Error al listar");
			request.getRequestDispatcher("Persona.jsp").forward(request, response);
		}
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("txtCode");
		int value = cusService.deleteCustomer(Integer.parseInt(code));
		
		if (value == 1) {
			getCustomer(request, response);
		}else {
			request.setAttribute("mensaje","Error al eliminar");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
	}

	private void getCustomer(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		List<Customer> customer = cusService.listCustomer();
		if (customer != null) {
			request.setAttribute("customer","customer");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
			
		}else {
			request.setAttribute("mensaje","Error al listar");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String name = request.getParameter("txtName");
		String documentType = request.getParameter("SelectDocument");
		String documentNumber = request.getParameter("txtDocument");
		String phone = request.getParameter("txtPhone");
		String address = request.getParameter("txtAddress");
		String business = request.getParameter("txtBusiness");
		
		Customer c = new Customer();
		c.setName(name);
		c.setDocumenType(documentType);
		c.setDocumentNumber(documentNumber);
		c.setPhone(phone);
        c.setAddress(address);
        c.setBusinessName(business);
        
        int value = cusService.addCustomer(c);
        
        if(value == 1) {
			getCustomer(request, response);
		}else {
			request.setAttribute("mensaje","Error al registrar");
			request.getRequestDispatcher("customer.jsp").forward(request, response);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
