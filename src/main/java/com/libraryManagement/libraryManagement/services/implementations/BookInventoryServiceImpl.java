package com.libraryManagement.libraryManagement.services.implementations;

import com.libraryManagement.libraryManagement.Entities.BookInventory;
import com.libraryManagement.libraryManagement.model.UpdateBookQuantity;
import com.libraryManagement.libraryManagement.repository.BookInventoryRepository;
import com.libraryManagement.libraryManagement.services.BookInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookInventoryServiceImpl implements BookInventoryService{

    private final BookInventoryRepository bookInventoryRepository;

    @Autowired
    public BookInventoryServiceImpl(BookInventoryRepository bookInventoryRepository){
        this.bookInventoryRepository = bookInventoryRepository;
    }


    @Override
    public BookInventory save(BookInventory bookInventory) {
        try {
            return bookInventoryRepository.save(bookInventory);
        }catch (Exception e){
            throw new RuntimeException("Data was not saved properly");
        }

    }

    @Override
    public List<BookInventory> findAll(){
        try {
            return (List<BookInventory>) bookInventoryRepository.findAll();

        }catch (Exception e){
            throw new RuntimeException("Data was not fetched properly");
        }

    }
    @Override
    public BookInventory findById(Long id ){

        try {
            Optional<BookInventory> bookInv = bookInventoryRepository.findById(id);
            if(bookInv.isPresent()){
                return bookInv.get();
            }else{
                throw new RuntimeException("your data is empty");
            }

        }catch (Exception e){
            throw new RuntimeException("Data was not saved properly");
        }



    }
    @Override
    public BookInventory update( BookInventory bookInventory){
        try {
        return bookInventoryRepository.save(bookInventory);

        }catch (Exception e){
            throw new RuntimeException("Data was updated successfully");
        }
    }


    @Override
    public void deleteById(Long id){
        try {
        bookInventoryRepository.deleteById(id);

        }catch (Exception e){
            throw new RuntimeException("Deletion not completed successfully");
        }
    }
    @Override
    public void updateBookQuantityService(UpdateBookQuantity updateBookQuantity){
        try {
            Optional<BookInventory> optionalBooks = bookInventoryRepository.findById(updateBookQuantity.getBookId());
            BookInventory book = optionalBooks.orElseThrow(() -> new RuntimeException("no book found with this id" + updateBookQuantity.getBookId()));
            book.setTotalQuantity(book.getTotalQuantity() + updateBookQuantity.getQuantity());
            book.setAvailableQuantity(book.getAvailableQuantity() + updateBookQuantity.getQuantity());
            bookInventoryRepository.save(book);
        }catch (Exception e){
            log.error("book quantity could not be updated ",e);
        }

    }




}
// List<BookInventory> bookList = StreamSupport.stream(iterable.spliterator(), false)
//                                                     .collect(Collectors.toList());
