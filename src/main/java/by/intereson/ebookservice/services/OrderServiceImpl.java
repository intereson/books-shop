package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.enums.OrderStatus;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.OrderListMapper;
import by.intereson.ebookservice.mappers.OrderMapper;
import by.intereson.ebookservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderListMapper orderListMapper;
    private final ShoppingCartService shoppingCartService;


    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        return orderMapper.mapToDTO(order);
    }

    @Override
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderListMapper.mapToDTO(orders);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<Order> ordersByUserId = orderRepository.getOrdersByUserId(userId);
        return orderListMapper.mapToDTO(ordersByUserId);
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request.getIdUser());
        Order order = new Order();
        order.setOrderStatus(OrderStatus.NEW);
        List<PartOfTheOrder> parts = shoppingCart.getParts();
        double sum = 0;
        for (PartOfTheOrder varPart : parts) {
            sum = varPart.getSumPrice() + sum;
            varPart.setOrder(order);
            varPart.setShoppingCart(null);
        }
        order.setSumPrice(sum);
        order.setUser(shoppingCart.getUser());
        shoppingCart.setSumPrice(0.0);
        shoppingCartService.cleanSumPriceInShoppingCart(request.getIdUser());
        orderRepository.save(order);
        return orderMapper.mapToDTO(order);
    }

    @Override
    public OrderResponse patchOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        order.setOrderStatus(status);
        orderRepository.save(order);
        return orderMapper.mapToDTO(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
