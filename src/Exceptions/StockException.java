package Exceptions;

/**
 * Created by Myles on 4/12/17.
 */
public class StockException extends Exception {

    private int actualStock;
    private int orderQuantity;
    private int newStock;


    public StockException(int actualStock, int orderQuantity) {
        super("This item does not have enough stock of: " + actualStock + " to complete the order with this demand of: " + orderQuantity);
        this.actualStock = actualStock;
        this.orderQuantity = orderQuantity;
    }

    public boolean checkStock()
    {
        if(getActualStock() < getOrderQuantity()) {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void changeStock() throws StockException {
        boolean inStock = checkStock();
        if (inStock) {
            int stock = getActualStock() - getOrderQuantity();
            this.newStock = stock;
        }
        else
        {
            throw this;
        }
    }

    public int getActualStock() {
        return actualStock;
    }

    public void setActualStock(int actualStock) {
        this.actualStock = actualStock;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getNewStock() {
        return newStock;
    }
}
