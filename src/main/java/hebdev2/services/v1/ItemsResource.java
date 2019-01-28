package hebdev2.services.v1;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import hebdev2.BusinessManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Path("/v1/Items")
@Api(value = "/Items", description = "Manage Items")

public class ItemsResource {
	
	private static final Logger log = Logger.getLogger(ItemsResource.class.getName());
	
	@Context
    private HttpServletResponse servletResponse;

    private void allowCrossDomainAccess() {
        if (servletResponse != null){
            servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        }
    }
	
	@GET
	@Path("/FindItemById/{ItemId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find Item",
			notes = "This API retrieves the public information for the Item (Private info is returned if this is the auth Item "
					+ "<p><u>Input Parameters</u><ul><li><b>ItemId</b> is required</li></ul>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucess: { Item profile }"),
			@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")
	})
	public Response getItemById(@ApiParam(value = "ItemId", required = true, defaultValue = "23456", allowableValues = "", allowMultiple = false)
			@PathParam("ItemId") String ItemId) {
		
		log.info("ItemsResource::getItemById started ItemId=" + ItemId);
		
		allowCrossDomainAccess();

		if (ItemId == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\":\"Empty ItemId\", \"status\":\"FAIL\"}")
					.build();
		}
		
		try {
			Item item = BusinessManager.getInstance().findItem(ItemId);
			
			return Response.status(Response.Status.OK).entity(item).build();
		}
		catch (Exception e) {
			
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could not find Item\", \"status\":\"FAIL\"}")
				.build();		
	}
	
	@GET
	@Path("/FindItemsByString/{string}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Finds all Items matching string",
			notes = "This API retrieves the public information for the Item (Private info is returned if this is the auth Item "
					+ "<p><u>Input Parameters</u><ul><li><b>ItemId</b> is required</li></ul>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucess: { Item profile }"),
			@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")
	})
	public Response getItemsByString(@ApiParam(value = "string", required = true, defaultValue = "23456", allowableValues = "", allowMultiple = false)
			@PathParam("string") String string) {
		
		log.info("ItemsResource::getItemsByString started string=" + string);
		
		allowCrossDomainAccess();

		
		if (string == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\":\"Empty ItemId\", \"status\":\"FAIL\"}")
					.build();
		}
		
		try {
			List<Item> items = BusinessManager.getInstance().findItemsByString(string);
			return Response.status(Response.Status.OK).entity(items).build();
		}
		catch (Exception e) {
			
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could not find Item\", \"status\":\"FAIL\"}")
				.build();		
	}
	
	@GET
	@Path("/FindAllItems")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Items",
			notes = "This API retrieves all Items")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucess: { Items : [] }"),
			@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")
	})
	public Response getItem() {
		
		log.info("ItemsResource::findAllItems started");
		allowCrossDomainAccess();
		try {
			List<Item> items = BusinessManager.getInstance().findItems();
			return Response.status(Response.Status.OK).entity(items).build();
		}
		catch (Exception e) {
			System.err.print(e);
			
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could not find Item\", \"status\":\"FAIL\"}")
				.build();		
	}
	
	@POST
	@Path("/CreateItem")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Create a new Item",
	notes = "This API creates a new Item if the Itemdescription does not exist"
			+ "<p><u>Input Parameters</u><ul><li><b>new Item object</b> is required</li></ul>")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Sucess: { Item profile }"),
			@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")
	})
	public Response createItem(@ApiParam(value = "New Item", required = true, defaultValue = "\"{\"description\":\"Ted Nanney\"}\"", allowableValues = "", allowMultiple = false)
		Item item) {
		
		log.info("ItemsResource::createItem started");
		try {
			Item newItem = BusinessManager.getInstance().addItem(item);
			return Response.status(Response.Status.CREATED).entity(newItem).build();
		}
		catch (Exception e) {
			
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could not create Item\", \"status\":\"FAIL\"}")
				.build();		
	}
	
	@POST
	@Path("/CreateItems")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Create a new Items from List",
	notes = "This API creates a new Item if the Itemdescription does not exist"
			+ "<p><u>Input Parameters</u><ul><li><b>new Item object</b> is required</li></ul>")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Sucess: { Item profile }"),
			@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")
	})
	public Response createItems(@ApiParam(value = "New Item", required = true, defaultValue = "\"{\"description\":\"Ted Nanney\"}\"", allowableValues = "", allowMultiple = false)
		List<Item> items) {
		
		log.info("ItemsResource::createItems started");
		try {
			List<Item> itemsToReturn = BusinessManager.getInstance().addItems(items);
			return Response.status(Response.Status.CREATED).entity(itemsToReturn).build();
		}
		catch (Exception e) {
			
		}
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could not create Item\", \"status\":\"FAIL\"}")
				.build();		
	}
	
	//toDo
	
	@PUT
	@Path("/UpdateItem/{ItemId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update Item", 
    notes = "This API updates the Item")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success: { Item profile }"),
    @ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}") })
	public Response updateItem(@PathParam("ItemId") String itemId, String jsonString) {
		
		String description;
		
		try {
			Object obj = JSONValue.parse(jsonString);

			JSONObject jsonObject = (JSONObject) obj;
			description = (String) jsonObject.get("description");

		}
		 catch (Exception e) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity("{\"error\":\"Invalid or Missing fields error\", \"status\":\"FAIL\"}")
						.build();
			}
		
		try {
			Item updatedItem = BusinessManager.getInstance().updateItemAttribute(
				itemId, "description", description);
		
			return Response.status(Response.Status.OK).entity(updatedItem).build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could Not Update Item\", \"status\":\"FAIL\"}")
				.build();


		
	}

	
	//todo
	@DELETE
	@Path("/DeleteItem/{ItemId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update Item", 
    notes = "This API deletes the Item")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success: {  }"),
    @ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}") })
	public Response deleteItem(@PathParam("ItemId") String itemId) {
		
		log.info("ItemsResource::deleteItem started ItemId: " + itemId);

		try {
			BusinessManager.getInstance().deleteItem(itemId);
		
			return Response.status(Response.Status.OK).entity("{}").build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could Not Update Item\", \"status\":\"FAIL\"}")
				.build();
		
	}
}
