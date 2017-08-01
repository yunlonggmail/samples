/**
 * Created by shiyunlong on 2017/8/1.
 * 环境类
 * <p>
 * 可以根据需要采用具体环境类的方式或者注入的方式使用策略
 */

public class StrategyContext {
    /**
     * 持有一个策略类
     */
    private Strategy mStrategy;

    /**
     * 空构造函数
     */
    public StrategyContext() {
    }

    /**
     * 通过构造函数注入策略类
     *
     * @param strategy
     */
    public StrategyContext(Strategy strategy) {
        this.mStrategy = strategy;
    }

    /**
     * 具体的算法，该算法执行的是策略类的算法
     */
    public void algorithm() {
        if (mStrategy != null)
            mStrategy.algorithm();
    }

    /**
     * 注入策略
     *
     * @param strategy：策略类
     */
    public void setStrategy(Strategy strategy) {
        this.mStrategy = strategy;
    }

}
