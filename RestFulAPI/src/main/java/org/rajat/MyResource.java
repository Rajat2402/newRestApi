package org.rajat;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.rajat.model.BrandEntity;
import org.rajat.model.Links;
import org.rajat.model.ProductEntity;
import org.rajat.service.BrandService;
import org.rajat.service.ProductService;

@Path("showroom/brands")
public class MyResource {

	BrandService service = new BrandService();
	ProductService pservice = new ProductService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BrandEntity> getBrands() {
		return service.getBrands();
	}

	@GET
	@Path("/{brandId}")
	@Produces(MediaType.APPLICATION_JSON)
	public BrandEntity getBrandWithId(@PathParam("brandId") int brandId , @Context UriInfo uri) {
		Links link=new Links(uri.getAbsolutePath().toString(), "Self");
		Links link2= new Links(uri.getAbsolutePathBuilder()+"/products".toString() , "products");
		BrandEntity brand= service.getBrandWithId(brandId);
		List<Links> list= new ArrayList<Links>();
		list.add(link);
		list.add(link2);
		brand.setLinks(list);
		return brand;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{brandId}/products")
	public List<ProductEntity> getProductWithBrandId(@PathParam("brandId") int brandId,
			@QueryParam("category") String category) {
		if (category == null) {
			return pservice.getProductWithBrandId(brandId);
		} else {
			return pservice.getProductWithBrandIdAndCategory(brandId, category);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBrand(BrandEntity entity, @Context UriInfo uriInfo) {
		service.createBrand(entity);
		//String location = uriInfo.getAbsolutePathBuilder().toString() + "/" + entity.getBrandId();
		URI location = uriInfo.getAbsolutePathBuilder().path(Integer.toBinaryString(entity.getBrandId())).build();
		//URI url = URI.create(location);
		return Response.created(location).entity(entity).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{brandId}")
	public void updateBrand(@PathParam("brandId") int brandId, BrandEntity brandEntity) {
		brandEntity.setBrandId(brandId);
		service.updateBrand(brandId, brandEntity);
	}

	@DELETE
	@Path("/{brandId}")
	public void deleteBrand(@PathParam("brandId") int brandId) {
		service.deleteBrand(brandId);
	}
}
