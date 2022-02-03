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
import pe.idat.vo.ProductoVo;
import pe.idat.dbase.ConexionDb;
import pe.idat.vo.CategoriaVo;
/**
 *
 * @author User
 */
public class ProductoDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;    
    private CategoriaDao categoriaDao=new CategoriaDao();

    public ProductoDao() {}
    
    public void insert(ProductoVo producto)
    {
        try
        {
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("insert into productos(nombre,marca,precio,stock,id_categoria) values(?,?,?,?,?)");
            
            ps.setString(1,producto.getNombre());
            ps.setString(2,producto.getMarca());
            ps.setDouble(3,producto.getPrecio());
            ps.setInt(4,producto.getStock());
            ps.setInt(5,producto.getCategoria().getId_categoria());
            
            int rows=ps.executeUpdate();
            if(rows!=1)
                throw new Exception("Error!");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Collection<ProductoVo> findAll() 
    {
        List<ProductoVo> list=new ArrayList<>();

        try
        {
            conn=ConexionDb.MySQL();
            
            ps=conn.prepareStatement("select * from productos");
            rs=ps.executeQuery();
            
            while(rs.next())
            {
                ProductoVo producto=new ProductoVo();
                
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setMarca(rs.getString("marca"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                
                CategoriaVo categoria=categoriaDao.findById(rs.getInt("id_categoria"));
                producto.setCategoria(categoria);
                
                list.add(producto);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return list;
    }
    
    public void update(ProductoVo vo) {
        try{
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("update productos set nombre=?,marca=?,precio=?,stock=?,id_categoria=? where id_producto =?");
            ps.setString(1,vo.getNombre());
            ps.setString(2,vo.getMarca());          
            ps.setDouble(3,vo.getPrecio());
            ps.setInt(4,vo.getStock());
            ps.setInt(5,vo.getCategoria().getId_categoria());
            ps.setInt(6,vo.getId_producto());
            
            int rows = ps.executeUpdate();

            if(rows!=1){
                System.out.println("Error al Actualizar");
            }
        }
        catch(Exception ex){
        }
    }
    
    
    public ProductoVo findById(Integer productoId) {
       ProductoVo vo=null;
        
        try{
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("select * from productos where id_producto=?");
            ps.setInt(1,productoId);
            rs=ps.executeQuery();
            
            while(rs.next())
            {
                
                vo =new ProductoVo();
                vo.setId_producto(rs.getInt("id_producto"));
                vo.setNombre(rs.getString("nombre"));
                vo.setMarca(rs.getString("marca"));
                vo.setPrecio(rs.getDouble("precio"));
                vo.setStock(rs.getInt("stock"));
                CategoriaVo categoriaVo=categoriaDao.findById(rs.getInt("id_categoria"));                
                vo.setCategoria(categoriaVo);
                
            }
        
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return vo;
    }
    
    
    public void delete(Integer productoId) {
       try{
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("delete from productos where id_producto =?");
            ps.setInt(1,productoId);
            
            int rows =ps.executeUpdate();
            
            if (rows!=1){
            System.out.println("Error al eliminar");
                       
            }
                
        }
        catch(Exception ex){
        }
    }
    
}
