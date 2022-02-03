/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idat.ws;

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
import pe.idat.vo.CategoriaVo;

@Path("categoria")
public class CategoriaRest {
    
    @Context
    private UriInfo context;
    

    private CategoriaDao categoriaDao=new CategoriaDao();
    
    public CategoriaRest() {}
    
    
    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CategoriaVo> listar_GET() {
        return categoriaDao.findAll();
    }
    
    

    @Path("/agregar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(CategoriaVo categoria) 
    {
        categoriaDao.insert(categoria);
        return Response.ok().entity(categoria).build();
    }
    
    
    @Path("/editar/{categoriaId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editar_PUT(CategoriaVo vo,@PathParam("categoriaId") Integer categoriaId) {
        CategoriaVo categoriaDb=categoriaDao.findById(categoriaId);
        if(categoriaDb!=null)
        {
            categoriaDb.setDescripcion(vo.getDescripcion());
            categoriaDao.update(categoriaDb);
            
        }
    }
    
    
    
    
    @Path("/buscar/{categoriaId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CategoriaVo buscar_get(@PathParam("categoriaId") Integer categoriaId){
        
        CategoriaVo categoriaDb=categoriaDao.findById(categoriaId);
        if(categoriaDb!=null)
        {
           return categoriaDb;
        }
        
        return null;}
    
    @Path("/eliminar/{categoriaId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void eliminar_DELETE(CategoriaVo vo,@PathParam("categoriaId") Integer categoriaId) {
        CategoriaVo cocineroDb=categoriaDao.findById(categoriaId);
        
        
        if(cocineroDb!=null)
        {
            categoriaDao.delete(categoriaId);
            
        }
    }
    
}