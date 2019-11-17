package persistence;

import logic.Product;
import exception.CommandException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

/**
 * @author Nina Lisakowski, Allan
 */
public class ProductMapper implements IProductMapper {

    @Override
    public void create(Product product) throws CommandException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = PersistenceFacadeDB.getConnection();
            String insertSql = "INSERT INTO products "
                    + "(id, name, description, category_name) VALUES"
                    + "(?, ?, ?, ?)";

            pstmt = connection.prepareStatement(insertSql);
            pstmt.setInt(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getDescription());
            pstmt.setString(4, product.getCategoryname());

            pstmt.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            throw new CommandException("Could not create product. Try again!");
        } finally {
            DbUtils.closeQuietly(pstmt);
            DbUtils.closeQuietly(connection);

        }
    }

    @Override
    public Product getProduct(String name) throws CommandException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        Product product = null;
        try {
            connection = PersistenceFacadeDB.getConnection();
            String selectSql = "SELECT * FROM products WHERE name LIKE ?";
            pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, '%' + name + '%');

            result = pstmt.executeQuery();

            while (result.next()) {
                int id = result.getInt(1);
                String description = result.getString(3);
                String categoryname = result.getString(4);

                product = new Product(id, name, description, categoryname);
            }

            if (product == null) {
                throw new SQLException();
            }

        } catch (SQLException | NullPointerException ex) {
            throw new CommandException("Could not find any product with that name" );
        } finally {
            DbUtils.closeQuietly(connection, pstmt, result);
        }

        return product;
    }

    @Override
    public Product getProduct(int id) throws CommandException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        Product product = null;
        try {
            connection = PersistenceFacadeDB.getConnection();
            String selectSql = "SELECT * FROM products WHERE id = ?";
            pstmt = connection.prepareStatement(selectSql);
            pstmt.setInt(1, id);

            result = pstmt.executeQuery();

            while (result.next()) {
                String name = result.getString(2);
                String description = result.getString(3);
                String categoryname = result.getString(4);

                product = new Product(id, name, description, categoryname);
            }

            if (product == null) {
                throw new SQLException();
            }

        } catch (SQLException | NullPointerException ex) {
            throw new CommandException("Could not find any product with that id");
        } finally {
            DbUtils.closeQuietly(connection, pstmt, result);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryname) throws CommandException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Product> products = new ArrayList();
        try {
            connection = PersistenceFacadeDB.getConnection();
            String selectSql = "SELECT * FROM products WHERE category_name LIKE ?";
            pstmt = connection.prepareStatement(selectSql);
            pstmt.setString(1, '%' + categoryname + '%');

            result = pstmt.executeQuery();

            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String description = result.getString(3);

                products.add(new Product(id, name, description, categoryname));
            }
            
            if (products.size() < 1) {
                throw new SQLException();
            }
        } catch (SQLException | NullPointerException ex) {
            throw new CommandException("Could not find the products with the chosen name");
        } finally {
            DbUtils.closeQuietly(connection, pstmt, result);
        }
        return products;
    }

    @Override
    public List<Product> getAllProducts() throws CommandException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Product> products = new ArrayList();
        try {
            connection = PersistenceFacadeDB.getConnection();
            String selectSql = "SELECT * FROM products";
            pstmt = connection.prepareStatement(selectSql);

            result = pstmt.executeQuery(selectSql);

            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String description = result.getString(3);
                String categoryname = result.getString(4);

                products.add(new Product(id, name, description, categoryname));
            }

        } catch (SQLException | NullPointerException ex) {
            throw new CommandException("Could not find any products");
        } finally {
            DbUtils.closeQuietly(connection, pstmt, result);
        }
        return products;
    }

    @Override
    public void update(Product product) throws CommandException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = PersistenceFacadeDB.getConnection();
            String updateSql = "UPDATE products SET name = ?, description = ?, "
                    + "category_name = ? WHERE id = ?";
            pstmt = connection.prepareStatement(updateSql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setString(3, product.getCategoryname());
            pstmt.setInt(4, product.getId());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No rows updated");
            }
        } catch (SQLException | NullPointerException ex) {
            throw new CommandException("Could not find a product with the given ID" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(pstmt);
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public void delete(Product product) throws CommandException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = PersistenceFacadeDB.getConnection();
            String deleteSql = "DELETE FROM products WHERE id = ?";
            pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, product.getId());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No rows updated");
            }
        } catch (SQLException | NullPointerException ex) {
            throw new CommandException("Could not find the product to be deleted");
        } finally {
            DbUtils.closeQuietly(pstmt);
            DbUtils.closeQuietly(connection);
        }
    }
}
