/**
 * Created by shiyunlong on 2017/10/24.
 * 具体建造者
 * 它实现了Builder接口，实现各个部件的具体构造和装配方法，定义并明确它所创建的复杂对象，也可以提供一个方法返回创建好的复杂产品对象。
 */

public class ConcreteBuilder implements Builder {
    /**
     * 创建了产品A
     */
    @Override
    public void buildPartA() {
        product.setPartA("设置了PartA");
    }

    /**
     * 创建了产品B
     */
    @Override
    public void buildPartB() {
        product.setPartB("设置了PartB");
    }

    /**
     * 创建了产品C
     */
    @Override
    public void buildPartC() {
        product.setPartC("设置了PartC");
    }

    /**
     * 获取创建的产品
     */
    @Override
    public Product getResult() {
        return product;
    }
}
