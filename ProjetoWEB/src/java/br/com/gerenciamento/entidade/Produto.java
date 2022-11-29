/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gerenciamento.entidade;

public class Produto {
    private Integer  codigo;
    private String nome_prod;
    private String valor;
    private String descricao;
    private String categoria;
    private Long estoque;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getEstoque() {
        return estoque;
    }

    public void setEstoque(Long estoque) {
        this.estoque = estoque;
    }

   
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

   
    
    //inserindo hash code para diferenciar os objeto pelo campo codigo
    //separando os dados por codigo que estao no array
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    
}
