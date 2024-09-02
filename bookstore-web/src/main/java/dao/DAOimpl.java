package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectDB;
import form.BookForm;
import form.CartForm;
import form.CartItemForm;
import form.OrderForm;
import form.PaymentForm;
import form.UserForm;

public class DAOimpl implements DAO {

	private static final String CHECK_USERNAME = "SELECT id FROM User WHERE username = ? ;";

	private static final String LOGIN_SQL = "SELECT id, username FROM User WHERE id = ? AND password = ?;";

	// Book queries
	private static final String SELECT_ALL_BOOKS = "SELECT id, title, author, category, price, availability FROM book;";
	private static final String SEARCH_BOOKS = "SELECT id, title, author, category, price, availability FROM book WHERE title LIKE ? OR author LIKE ? OR category LIKE ?;";
	private static final String SELECT_BOOK_BY_ID = "SELECT id, title, author, category, price, availability FROM book WHERE id = ?;";

	// Cart queries
	private static final String SELECT_CART = "SELECT id, userId FROM cart_detail WHERE userId = ?;";
	private static final String INSERT_CART = "INSERT INTO cart_detail (userId) VALUES (?)";
	private static final String INSERT_CART_ITEM = "INSERT INTO cartitem (cartId, bookId, quantity) VALUES (?, ?, ?);";
	private static final String SELECT_CART_ITEMS = "SELECT id, cartId, bookId, quantity FROM cartitem WHERE cartId = ?;";
	private static final String SELECT_CART_ITEM_PRODUCT = "SELECT id, cartId, bookId, quantity FROM cartitem WHERE cartId = ? AND bookId = ?;";
	private static final String UPDATE_QUANTITY = "UPDATE cartitem SET quantity = ? WHERE id = ?;";


	// Order queries
	private static final String INSERT_ORDER_DETAILS = "INSERT INTO order_detail (shippingAddress, userId, paymentId, cartId) VALUES (?, ?, ?, ?);";

	// Payment queries
	private static final String INSERT_PAYMENT = "INSERT INTO payment (iban, cvvCode, exDate) VALUES (?, ?, ?);";

	protected ConnectDB connectDB = new ConnectDB();

	@Override
	public UserForm login(long id, String password) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(LOGIN_SQL)) {
			pr.setLong(1, id);
			pr.setString(2, password);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					return new UserForm(Long.parseLong(rs.getString("id")), rs.getString("username"));
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long checkUsername(String username) {
		long userId = 0;
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(CHECK_USERNAME)) {
			pr.setString(1, username);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					return userId = rs.getLong("id");
				} else {
					return userId;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<BookForm> getAllBooks() {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_ALL_BOOKS);
				ResultSet rs = pr.executeQuery()) {

			ArrayList<BookForm> books = new ArrayList<>();
			while (rs.next()) {
				books.add(new BookForm(rs.getLong("id"), rs.getString("title"), rs.getString("author"),
						rs.getString("category"), rs.getDouble("price"), rs.getBoolean("availability")));
			}
			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ArrayList<BookForm> searchBooks(String searchString) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SEARCH_BOOKS)) {

			pr.setString(1, "%" + searchString + "%");
			pr.setString(2, "%" + searchString + "%");
			pr.setString(3, "%" + searchString + "%");

			try (ResultSet rs = pr.executeQuery()) {
				ArrayList<BookForm> books = new ArrayList<>();
				while (rs.next()) {
					books.add(new BookForm(rs.getLong("id"), rs.getString("title"), rs.getString("author"),
							rs.getString("category"), rs.getDouble("price"), rs.getBoolean("availability")));
				}
				return books;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public BookForm findBookById(long bookId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
			pr.setLong(1, bookId);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					// public BookForm(long id, String title, String author, String category, double
					// price, boolean availability)
					return new BookForm(rs.getLong("id"), rs.getString("title"), rs.getString("author"),
							rs.getString("category"), rs.getDouble("price"), rs.getBoolean("availability"));
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int addToCart(CartItemForm cartItem) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_CART_ITEM)) {
			pr.setLong(1, cartItem.getCartId());
			pr.setLong(2, cartItem.getBookId());
			pr.setInt(3, cartItem.getQuantity());
			return pr.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public int updateCartItem(long cartItemId, int quantity) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(UPDATE_QUANTITY )) {
			pr.setLong(1, quantity);
			pr.setLong(2, cartItemId);
			return pr.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public int createCart(long userId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_CART)) {
			pr.setLong(1, userId);
			int rowsAffected = pr.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CartForm findCartByUser(long userId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_CART)) {

			pr.setLong(1, userId);

			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) { // Move cursor to the first row
					// Retrieve data and create CartForm
					long id = rs.getLong("id");
					long retrievedUserId = rs.getLong("userId");
					return new CartForm(id, retrievedUserId);
				} else {
					// Handle the case where no data is found, if necessary
					return null; // or throw an exception, or return an empty CartForm
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CartItemForm findCartItemByCart(long cartId, long bookId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_CART_ITEM_PRODUCT)) {
			pr.setLong(1, cartId);
			pr.setLong(2, bookId);
			try (ResultSet rs = pr.executeQuery()) {
				if (rs.next()) {
					return new CartItemForm(rs.getLong("id"), rs.getLong("cartId"), rs.getLong("bookId"),
							rs.getInt("quantity"));
				}
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ArrayList<CartItemForm> showCart(long cartId) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(SELECT_CART_ITEMS)) {
			pr.setLong(1, cartId);
			try (ResultSet rs = pr.executeQuery()) {
				ArrayList<CartItemForm> cartItems = new ArrayList<>();
				while (rs.next()) {
					cartItems.add(new CartItemForm(rs.getLong("id"), rs.getLong("cartId"), rs.getLong("bookId"),
							rs.getInt("quantity")));
				}
				return cartItems;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int sendOrder(OrderForm order) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_ORDER_DETAILS)) {

			pr.setString(1, order.getShippingAddress());
			pr.setLong(2, order.getUserId());
			pr.setLong(3, order.getPaymentId());
			pr.setLong(4, order.getCartId());
			return pr.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long pay(PaymentForm paymentForm) {
		try (Connection connection = connectDB.getConnection();
				PreparedStatement pr = connection.prepareStatement(INSERT_PAYMENT,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

			pr.setString(1, paymentForm.getIban());
			pr.setString(2, paymentForm.getCvvCode());
			pr.setString(3, paymentForm.getExDate());

			int affectedRows = pr.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating payment failed, no rows affected.");
			}

			try (ResultSet generatedKeys = pr.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating payment failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
