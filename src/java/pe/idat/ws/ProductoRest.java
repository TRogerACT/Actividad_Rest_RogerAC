/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idat.ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author User
 */
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pe.idat.dao.CategoriaDao;
import pe.idat.dao.ProductoDao;
import pe.idat.vo.ProductoVo;

@Path("producto")
public class ProductoRest 
{
    @Context
    private UriInfo context;
    
    //instancia al dao
    private ProductoDao productoDao=new ProductoDao();
    private CategoriaDao categoriaDao=new CategoriaDao();
    
    public ProductoRest() {}

    @Path("/agregar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(ProductoVo producto) 
    {
        productoDao.insert(producto);
        return Response.ok().entity(producto).build();
    }
    
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductoVo> listar_GET() 
    {
        return productoDao.findAll();
    }
    
    
    @Path("/editar/{productoId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editar_PUT(ProductoVo vo,@PathParam("productoId") Integer productoId) {
        ProductoVo productoDb=productoDao.findById(productoId);
        if(productoDb!=null)
        {
            productoDb.setMarca(vo.getMarca());
            productoDb.setNombre(vo.getNombre());
            productoDb.setPrecio(vo.getPrecio());
            productoDb.setStock(vo.getStock());
            productoDb.setCategoria(vo.getCategoria());
            productoDao.update(productoDb);
            
        }
    }
    
    
       
    
    
    @Path("/buscar/{productoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductoVo buscar_get(@PathParam("productoId") Integer productoId){
        
        ProductoVo productoDb=productoDao.findById(productoId);
        if(productoDb!=null)
        {
           return productoDb;
        }
        
        return null;}
    
    @Path("/eliminar/{productoId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void eliminar_DELETE(ProductoVo vo,@PathParam("productoId") Integer productoId) {
        ProductoVo productoDb=productoDao.findById(productoId);
        
        
        if(productoDb!=null)
        {
            productoDao.delete(productoId);
            
        }
    }
    
    
}

