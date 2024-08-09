package dao;

import java.util.ArrayList;

import form.BookForm;
import form.CartForm;
import form.CartItemForm;
import form.OrderForm;
import form.PaymentForm;
import form.UserForm;


public interface DAO {
	
	UserForm login(long id, String password);
	
	long checkUsername(String username);
	
    ArrayList<BookForm> getAllBooks();
    
    ArrayList<BookForm> searchBooks(String searchString);
    
    BookForm findBookById(long bookId);
    
    int createCart(long userId);
    
    int addToCart(CartItemForm cartItem);
    
    ArrayList<CartItemForm> showCart(long cartId);
    
    CartForm findCartByUser(long userId);
    
    CartItemForm findCartItemByCart(long cartId, long bookId);
    
    int updateCartItem(long cartItemId, int quantity);
    
    int sendOrder(OrderForm order);
    
    long pay(PaymentForm paymentForm);
}
