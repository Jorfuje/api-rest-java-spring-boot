package com.jorfuje.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jorfuje.apirest.apirest.Entities.Producto;
import com.jorfuje.apirest.apirest.Repositories.ProductoRepository;

// Decorador para indicar que la clase es un controlador
@RestController

// Decorador para indicar la ruta base de la clase
@RequestMapping("/productos")
public class ProductoController {

    // Decorador para inyectar la dependencia o repositorio
    @Autowired
    // Repositorio de la entidad Producto
    private ProductoRepository productoRepository;

    // Decorador para indicar que el método es un endpoint de tipo GET
    @GetMapping
    public List<Producto> getAllProductos() {
        // Retorna todos los productos
        return productoRepository.findAll();
    }

    // Decorador para indicar que el método es un endpoint de tipo GET para un producto específico, con un parámetro en la URL
    @GetMapping("/{id}")
    // El decorador @PathVariable indica que el método recibe un parámetro de la URL
    public Producto getProductoById(@PathVariable Long id) {
        // Retorna un producto por su id
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con el id: " + id));
    }

    // Decorador para indicar que el método es un endpoint de tipo POST
    @PostMapping
    // El decorador @RequestBody indica que el método recibe un objeto JSON
    public Producto createProducto(@RequestBody Producto producto) {
        // Guarda un producto
        return productoRepository.save(producto);
    }

    // Decorador para indicar que el método es un endpoint de tipo PUT
    @PutMapping("/{id}")
    // El decorador @PathVariable indica que el método recibe un parámetro de la URL
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        // Retorna un producto por su id, sino lo encuentra lanza una excepción
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con el id: " + id));

        // Actualiza los datos del producto
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        // Guarda el producto actualizado
        return productoRepository.save(producto);
    }

    // Decorador para indicar que el método es un endpoint de tipo DELETE
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        // Retorna un producto por su id, sino lo encuentra lanza una excepción
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con el id: " + id));

        // Elimina el producto
        productoRepository.delete(producto);

        return "El producto con el id: " + id + " ha sido eliminado";
    }

}
