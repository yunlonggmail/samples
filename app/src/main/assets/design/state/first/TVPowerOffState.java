
/**
 * Created by shiyunlong on 2017/8/10.
 * 关机状态
 * 提示开机。。。。
 */

public class TVPowerOffState implements ITVState {
    @Override
    public void turnUp() {
        System.out.println("请先开机！");
    }

    @Override
    public void turnDown() {
        System.out.println("请先开机！");
    }

    @Override
    public void preShow() {
        System.out.println("请先开机！");
    }

    @Override
    public void nextShow() {
        System.out.println("请先开机！");
    }

    @Override
    public String toString() {
        return "关机状态";
    }
}
