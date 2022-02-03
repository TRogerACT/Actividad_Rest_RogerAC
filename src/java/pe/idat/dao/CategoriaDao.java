/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pe.idat.dbase.ConexionDb;
import pe.idat.vo.CategoriaVo;
import pe.idat.vo.ProductoVo;

/**
 *
 * @author User
 */
public class CategoriaDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public CategoriaDao() {}
    public void insert(CategoriaVo categoria)
    {
        try
        {
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("insert into categorias(descripcion) values(?)");
            
            ps.setString(1,categoria.getDescripcion());
            
            int rows=ps.executeUpdate();
            if(rows!=1)
                throw new Exception("Error!");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Collection<CategoriaVo> findAll() 
    {
        List<CategoriaVo> list=new ArrayList<>();

        try
        {
            conn=ConexionDb.MySQL();
            
            ps=conn.prepareStatement("select * from categorias");
            rs=ps.executeQuery();
            
            while(rs.next())
            {
                CategoriaVo producto=new CategoriaVo();
                
                producto.setId_categoria(rs.getInt("id_Categoria"));
                producto.setDescripcion(rs.getString("descripcion"));

                
                
                list.add(producto);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return list;
    }
    
    public void update(CategoriaVo vo) {
        try{
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("update categorias set descripcion=? where id_Categoria =?");
            ps.setString(1,vo.getDescripcion());

            ps.setInt(2,vo.getId_categoria());
            
            int rows = ps.executeUpdate();

            if(rows!=1){
                System.out.println("Error al Actualizar");
            }
        }
        catch(Exception ex){
        }
    }
    
    
    public CategoriaVo findById(int id_categoria) {
        CategoriaVo categoria=null;

        try {
            conn=ConexionDb.MySQL();            
            ps=conn.prepareStatement("select * from categorias where id_categoria=?");
            ps.setInt(1,id_categoria);            
            rs=ps.executeQuery();
            
            if(rs.next())
            {
                categoria=new CategoriaVo();                
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return categoria;
    }
    
    public void delete(Integer id_categoria) {
       try{
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("delete from categorias where id_categoria =?");
            ps.setInt(1,id_categoria);
            
            int rows =ps.executeUpdate();
            
            if (rows!=1){
            System.out.println("Error al eliminar");
                       
            }
                
        }
        catch(Exception ex){
        }
    }

}
