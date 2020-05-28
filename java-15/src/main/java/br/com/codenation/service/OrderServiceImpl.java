package br.com.codenation.service;

import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {

	  Double percentualDesconto = 0.2;
	  Double valorProdutosSemdesconto = somaValorTotalProdutos(items, false);
	  Double valorProdutosComdesconto = somaValorTotalProdutos(items, true);

    return valorProdutosSemdesconto + valorProdutosComdesconto -
      (valorProdutosComdesconto * percentualDesconto);
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream()
      .map(this.productRepository::findById)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderItem>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
	  List<Double> valorOrdens = orders.stream().map(this::calculateOrderValue).collect(Collectors.toList());
		return valorOrdens.stream().reduce(0.0, Double::sum);
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		List<Product> produtos = productIds.stream().map(id-> productRepository.findById(id).get()).collect(Collectors.toList());
		Map<Boolean, List<Product>> group = new HashMap<>();
		List<Product> isSaleList = produtos.stream().filter(Product::getIsSale).collect(Collectors.toList());
		List<Product> isNotSaleList = produtos.stream().filter(produto -> !produto.getIsSale()).collect(Collectors.toList());
		group.put(true, isSaleList );
		group.put(false, isNotSaleList );
		return group;
	}

  private Double somaValorTotalProdutos(List<OrderItem> items, boolean comDesconto) {
    return items.stream()
      .filter(product -> productRepository.findById(product.getProductId()).get().getIsSale() == comDesconto)
      .mapToDouble(product -> product.getQuantity() *
        productRepository.findById(product.getProductId()).get().getValue()).sum();
  }

}
