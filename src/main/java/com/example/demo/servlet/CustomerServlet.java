package com.example.demo.servlet;


import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.ServiceFactory;
import com.example.demo.service.ServiceType;
import com.example.demo.service.custom.CustomerService;
import com.google.gson.Gson;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        this.customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER_SERVICE);
    }


    private boolean isValidCustomer(CustomerDTO customerDTO){
        // Perform validation checks
        if (!isValidCustomerId(customerDTO.getId())) {
            System.out.println("Invalid customer ID");
        } else if (customerDTO.getName() == null || customerDTO.getName().isEmpty()) {
            System.out.println("Name cannot be empty");
        } else if (customerDTO.getAddress() == null || customerDTO.getAddress().isEmpty()) {
            System.out.println("Address cannot be empty");
        } else if (customerDTO.getSalary() < 0) {
            System.out.println("Salary cannot be negative");
        } else {
            return true;
        }
        return false;
    }

    // Validate the customer ID
    private boolean isValidCustomerId(String id) {
        // Check if the ID is not null and has exactly 4 characters
        if (id == null || id.length() != 4) {
            return false;
        }

        // Check if the ID starts with a capital "C"
        if (!id.startsWith("C")) {
            return false;
        }

        // Check if the rest of the characters are numeric
        for (int i = 1; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get method called in customer");

        String customerId = req.getParameter("id");
        if (customerId != null && !customerId.isEmpty()) {
            // Get specific customer by ID
            CustomerDTO customerDTO = customerService.search(customerId);

            // Convert the CustomerDTO object to a JSON object
            JsonObject customerJson = Json.createObjectBuilder()
                    .add("id", customerDTO.getId())
                    .add("name", customerDTO.getName())
                    .add("address", customerDTO.getAddress())
                    .add("salary", customerDTO.getSalary())
                    .build();

            // Set the content type to indicate JSON data
            resp.setContentType("application/json");

            // Write the JSON data to the response output stream
            try (PrintWriter out = resp.getWriter()) {
                out.print(customerJson.toString());
            }
        } else {
            // Get all customers
            List<CustomerDTO> allCustomers = customerService.getAll();
            Gson gson = new Gson();
            String json = gson.toJson(allCustomers);

            // Set the content type to indicate JSON data
            resp.setContentType("application/json");

            // Write the JSON data to the response output stream
            try (PrintWriter out = resp.getWriter()) {
                out.print(json);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post method called in customer");
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        System.out.println(customerDTO.toString());

        if(isValidCustomer(customerDTO)){
            // If all validations pass, save the customer
            if (customerService.save(customerDTO)) {
                System.out.println("Saved");
            } else {
                System.out.println("Not Saved");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Put method called in customer");
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        System.out.println(customerDTO.toString());

        if(isValidCustomer(customerDTO)){
            // If all validations pass, update the customer
            if (customerService.update(customerDTO)) {
                System.out.println("Updated");
            } else {
                System.out.println("Not Updated");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Delete method called in customer");
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        System.out.println(customerDTO.toString());

        if(isValidCustomer(customerDTO)){
            // If all validations pass, delete the customer
            if (customerService.delete(customerDTO)) {
                System.out.println("Deleted");
            } else {
                System.out.println("Not Deleted");
            }
        }
    }
}
