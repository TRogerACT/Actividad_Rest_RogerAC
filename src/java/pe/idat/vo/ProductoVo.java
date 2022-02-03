/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idat.vo;

public class ProductoVo 
{
    private int id_producto;
    private String nombre;
    private String marca;
    private double precio;
    private int stock;    
    private CategoriaVo categoria;

    public ProductoVo() {}

    public ProductoVo(int id_producto, String nombre, String marca, 
                      double precio, int stock, CategoriaVo categoria) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ProductoVo{" + "id_producto=" + id_producto + ", nombre=" + nombre + 
                    ", marca=" + marca + ", precio=" + precio + 
                    ", stock=" + stock + ", categoria=" + categoria.getDescripcion() + '}';
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CategoriaVo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVo categoria) {
        this.categoria = categoria;
    }

    
}

