package team.globaloptima;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("suppliers")
public class SupplierResource {

    @Inject
    SupplierService supplierBean;

    @GET
    @Operation(summary = "Get supplier list", description = "Returns a list of all suppliers.")
    @APIResponses({
            @APIResponse(description = "List of suppliers", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Supplier.class,
                            type = SchemaType.ARRAY)))
    })
    public Response getAllSuppliers() {
        List<Supplier> suppliers = supplierBean.getSuppliers();
        return Response.ok(suppliers).build();
    }

    @GET
    @Operation(summary = "Get supplier details", description = "Return details of one supplier with selected id")
    @APIResponses({
            @APIResponse(description = "Supplier", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Supplier.class)))
    })
    @Path("{supplierId}")
    public Response getSupplier(@PathParam("supplierId") Integer supplierId) {
        Supplier supplier = supplierBean.getSupplier(supplierId);
        return supplier != null
                ? Response.ok(supplier).build()
                : Response.ok(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Operation(summary = "Create new supplier", description = "Create new supplier")
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "New supplier created"
            )
    })
    public Response addNewSupplier(@RequestBody(
            description = "JSON object with Supplier data",
            required = true, content = @Content(
            schema = @Schema(implementation = Supplier.class))) Supplier supplier) {
        supplierBean.saveSupplier(supplier);
        return Response.noContent().build();
    }

    @DELETE
    @Operation(description = "Delete Supplier", summary = "Delete supplier")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Supplier successfully deleted."
            )
    })
    @Path("{supplierId}")
    public Response deleteSupplier(@Parameter(description = "Supplier ID.", required = true)
                                   @PathParam("supplierId") Integer supplierId) {
        supplierBean.deleteSupplier(supplierId);
        return Response.noContent().build();
    }

    @POST
    @Operation(summary = "Create new food", description = "Create new food")
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "New food created"
            )
    })
    @Path("menu/{supplierId}")
    public Response addNewFood(@RequestBody(
            description = "JSON object with Food data",
            required = true, content = @Content(
            schema = @Schema(implementation = Food.class))) Food food) {
        supplierBean.saveFood(food);
        return Response.noContent().build();
    }

    @GET
    @Operation(summary = "Get supplier menu", description = "Returns suppliers menu - list of food.")
    @APIResponses({
            @APIResponse(description = "Suppliers menu", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Food.class,
                            type = SchemaType.ARRAY)))
    })
    @Path("menu/{supplierId}")
    public Response getMenu(@PathParam("supplierId") Integer supplierId) {
        List<Food> menu = supplierBean.getMenu(supplierId);
        return Response.ok(menu).build();
    }
}
