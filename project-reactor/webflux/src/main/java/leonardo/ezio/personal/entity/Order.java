package leonardo.ezio.personal.entity;

/**
 * @Description : 订单实体
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 16:01
 */
public class Order {

    private String id;

    private String orderName;

    public Order() {
    }

    public Order(String id, String orderName) {
        this.id = id;
        this.orderName = orderName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}
