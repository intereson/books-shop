package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.PartOfTheOrderListMapper;
import by.intereson.ebookservice.mappers.PartOfTheOrderMapper;
import by.intereson.ebookservice.repositories.PartOfTheOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartOfTheOrderServiceImpl implements PartOfTheOrderService {
    private final PartOfTheOrderMapper partOfTheOrderMapper;
    private final PartOfTheOrderListMapper partOfTheOrderListMapper;
    private final BookService bookService;
    private final PartOfTheOrderRepository partOfTheOrderRepository;

    @Override
    public PartResponse createPartOfTheOrder(CreatePartOfTheOrderRequest request) {
        PartOfTheOrder partOfTheOrder = partOfTheOrderMapper.mapToEntity(request);
        Book book = bookService.getBook(request.getIdBook());
        partOfTheOrder.setBook(book);
        Double price = book.getPrice();
        partOfTheOrder.setPrice(price);
        Integer quantity = request.getQuantity();
        Double sumPrise = price * quantity;
        partOfTheOrder.setSumPrice(sumPrise);
        partOfTheOrder.setQuantity(quantity);
        return partOfTheOrderMapper.mapToDTO(partOfTheOrder);
    }

    @Override
    public PartResponse getPartOfTheOrder(Long id) {
        PartOfTheOrder partOfTheOrder = partOfTheOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        return partOfTheOrderMapper.mapToDTO(partOfTheOrder);
    }

    @Override
    public List<PartResponse> getAllPartsOfTheOrder() {
        List<PartOfTheOrder> parts = partOfTheOrderRepository.findAll();
        return partOfTheOrderListMapper.toDTOList(parts);
    }

    @Override
    public PartResponse updatePartOfTheOrder(Long id, UpdatePartOfTheOrderRequest request) {
        Integer newQuantity = request.getQuantity();
        PartOfTheOrder oldPartOfTheOrder = partOfTheOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        Double price = oldPartOfTheOrder.getPrice();
        Double sumPrice=price*newQuantity;
        oldPartOfTheOrder.setSumPrice(sumPrice);
        oldPartOfTheOrder.setQuantity(newQuantity);
        partOfTheOrderRepository.save(oldPartOfTheOrder);
        return partOfTheOrderMapper.mapToDTO(oldPartOfTheOrder);
    }

    @Override
    public void deletePartOfTheOrder(Long id) {
        partOfTheOrderRepository.deleteById(id);
    }
}
