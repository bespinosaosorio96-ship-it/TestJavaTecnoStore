package modelo;

public class Celular {
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private int stock;
    private String sistemaOperativo;
    private CategoriaGama gama;

    public Celular(int id, String marca, String modelo, double precio, int stock, String sistemaOperativo, CategoriaGama gama) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public CategoriaGama getGama() {
        return gama;
    }

    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser positivo");
        }
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock = stock;
    }

    @Override
    public String toString() {
        return id + " - " + marca + " " + modelo + " | $" + precio + " | Stock: " + stock;
    }
}
