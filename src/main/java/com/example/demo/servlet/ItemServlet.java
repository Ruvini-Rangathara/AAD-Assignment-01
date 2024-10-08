package com.example.demo.servlet;


import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.service.ServiceFactory;
import com.example.demo.service.ServiceType;
import com.example.demo.service.custom.ItemService;
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
import java.util.regex.Pattern;


@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    private ItemService itemService;

    @Override
    public void init() throws ServletException {
        itemService = ServiceFactory.getInstance().getService(ServiceType.ITEM_SERVICE);
    }

    // Validation method for ItemDTO
    public boolean isValid(ItemDTO itemDTO) {
        return isValidCode(itemDTO.getCode()) && itemDTO.getDescription() != null && itemDTO.getUnitPrice() > 0 && itemDTO.getQtyOnHand() >= 0;
    }

    // Validate the item code format (I001, I002, etc.)
    private boolean isValidCode(String code) {
        Pattern pattern = Pattern.compile("^I\\d{3}$");
        return pattern.matcher(code).matches();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Get method called in item");

        String itemCode = req.getParameter("code");
        if (itemCode != null && !itemCode.isEmpty()) {
            // Get specific item by code
            ItemDTO itemDTO = itemService.search(itemCode);

            // Convert the ItemDTO object to a JSON object
            JsonObject customerJson = Json.createObjectBuilder()
                    .add("code", itemDTO.getCode())
                    .add("description", itemDTO.getDescription())
                    .add("unitPrice", itemDTO.getUnitPrice())
                    .add("qtyOnHand", itemDTO.getQtyOnHand())
                    .build();

            // Set the content type to indicate JSON data
            resp.setContentType("application/json");

            // Write the JSON data to the response output stream
            try (PrintWriter out = resp.getWriter()) {
                out.print(customerJson.toString());
            }
        } else {
            // Get all items
            List<ItemDTO> allItems = itemService.getAll();
            Gson gson = new Gson();
            String json = gson.toJson(allItems);

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
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

        if(isValidCode(itemDTO.getCode())){
            if(isValid(itemDTO)){
                // If all validations pass, save the customer
                if (itemService.save(itemDTO)) {
                    System.out.println("Saved");
                } else {
                    System.out.println("Not Saved");
                }
            }else {
                System.out.println("Invalid item");
            }
        }else{
            System.out.println("Invalid code");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

        if(isValidCode(itemDTO.getCode())){
            if(isValid(itemDTO)){
                // If all validations pass, update the customer
                if (itemService.update(itemDTO)) {
                    System.out.println("Updated");
                } else {
                    System.out.println("Not Updated");
                }
            }else {
                System.out.println("Invalid item");
            }
        }else{
            System.out.println("Invalid code");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

        if(isValidCode(itemDTO.getCode())){
            if(isValid(itemDTO)){
                // If all validations pass, delete the customer
                if (itemService.delete(itemDTO)) {
                    System.out.println("Deleted");
                } else {
                    System.out.println("Not Deleted");
                }
            }else {
                System.out.println("Invalid item");
            }
        }else{
            System.out.println("Invalid code");
        }
    }

}
