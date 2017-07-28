
import java.io.ObjectStreamException;

/**
 * Created by shiyunlong on 2017/7/19.
 * 饿汉式
 * <p>
 * 优点：
 * 在类加载的时候就创建了实例初始化
 * <p>
 * 缺点：
 * 如果该类在实例化时需要较多的资源，那么无论这个类是否被使用都会占用资源
 */

public class SingletonHungry {

    private static SingletonHungry singletonHungry = new SingletonHungry();

    /**
     * 私有化构造函数
     */
    private SingletonHungry() {
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static SingletonHungry getInstance() {
        return singletonHungry;
    }

    /**
     * 方法
     */
    public void doSomething() {

    }

    /**
     * 反序列化使用
     * 为了避免在反序列化可能会出现创建新的实例对象的情况。
     *
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return singletonHungry;
    }
}
