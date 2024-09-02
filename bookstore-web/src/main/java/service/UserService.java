package service;

import java.util.ArrayList;

import dao.DAOimpl;
import form.BookForm;
import form.CartForm;
import form.CartItemForm;
import form.OrderForm;
import form.PaymentForm;
import form.UserForm;

public class UserService {
	
	DAOimpl daoImpl = new DAOimpl();
	
	public long authenticate(String username, String password) {
		long userId = daoImpl.checkUsername(username);
		if (userId != 0) {
			daoImpl.login(userId, password);
		}
		return userId;
	}
	
    public ArrayList<BookForm> getAllBooks() {
    	return daoImpl.getAllBooks();
    }
    
    public ArrayList<BookForm> searchBooks(String searchString){
    	return daoImpl.searchBooks(searchString);
    }
    
    public BookForm findBookById(long bookId) {
    	return daoImpl.findBookById(bookId);
    }
    
    public int addToCart(CartItemForm cartItem) {
    	return daoImpl.addToCart(cartItem);
    }
    
    public int updateCartItem(long cartItemId, int quantity) {
    	return daoImpl.updateCartItem(cartItemId, quantity);
    }
    
    public ArrayList<CartItemForm> showCart(long cartId) {
    	return daoImpl.showCart(cartId);
    }
    
    public CartItemForm findCartItemByCart(long cartId, long bookId) {
    	return daoImpl.findCartItemByCart(cartId, bookId);
    }
    
    public int sendOrder(OrderForm order) {
    	return daoImpl.sendOrder(order);
    }
    
    public long pay(PaymentForm paymentForm) {
    	return daoImpl.pay(paymentForm);
    }

}
