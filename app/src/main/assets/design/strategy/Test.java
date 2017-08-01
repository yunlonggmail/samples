/**
 * Created by shiyunlong on 2017/8/1.
 * 测试类
 */

public class Test {
    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {

        StrategyContext strategyContext = new StrategyContext();
        //设置具体的策略A
        strategyContext.setStrategy(new ConcreteStrategyA());
        strategyContext.algorithm();
        //设置具体的策略B
        strategyContext.setStrategy(new ConcreteStrategyB());
        strategyContext.algorithm();
        strategyContext.setStrategy(new Strategy() {
            @Override
            public void algorithm() {
                System.out.print("自定的匿名策略");
            }
        });


        //具体策略类A
        StrategyContext strategyContextA = new ConcreteStrategyContextA();
        strategyContextA.algorithm();
        //具体策略类B
        StrategyContext strategyContextB = new ConcreteStrategyContextB();
        strategyContextB.algorithm();
    }
}
