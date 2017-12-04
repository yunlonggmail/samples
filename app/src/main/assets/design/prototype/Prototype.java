/**
 * Created by shiyunlong on 2017/12/4.
 * 原型接口，这个接口一般是不存在的，这个接口并没有实际意义。
 * 在正常开发中尽量不要有这个接口。
 */

public interface Prototype extends Cloneable {

    Prototype clone();
}
