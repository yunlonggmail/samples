import com.yunlong.samples.design.factory.method.impl.product.Product;

/**
 * Created by shiyunlong on 2017/10/10.
 * 抽象工厂角色
 * 在抽象工厂类中，声明了工厂方法（Factory Method），用于返回一个产品。
 * 抽象工厂是工厂方法模式的核心，所有创建对象的工厂都必须实现该接口。
 */

public interface Factory {
    /**
     * 创建产品
     * 可以有多个重载方法
     *
     * @return
     */
    Product createProduct();

    /**
     * 创建产品的重载方法
     *
     * @param i：根据i，设置产品的参数
     * @return
     */
    Product createProduct(int i);

    /**
     * 创建产品的重载方法
     *
     * @param str：根据字符串，设置产品的说明
     * @return
     */
    Product createProduct(String str);

    /**
     * 创建产品的重载方法
     *
     * @param i：根据i，设置产品的参数
     * @param str：根据字符串，设置产品的说明
     * @return
     */
    Product createProduct(int i, String str);
}
