/**
 * Created by shiyunlong on 2017/8/1.
 * 环境类
 */

public class ConcreteStrategyContextB extends StrategyContext {

    public ConcreteStrategyContextB() {
        //调用父类构造方法注入策略
        super(new ConcreteStrategyB());
    }
}
