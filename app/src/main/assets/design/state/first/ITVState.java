
/**
 * Created by shiyunlong on 2017/8/10.
 * 电视的状态：
 * 每种状态都有相应的操作，也就是状态的主要操作方法
 * <p>
 * 相应的操作有
 * 调大声音
 * 调小声音
 * 上一节目
 * 下一节目
 */

public interface ITVState {

    /**
     * 调大声音
     */
    void turnUp();

    /**
     * 调小声音
     */
    void turnDown();

    /**
     * 上一节目
     */
    void preShow();

    /**
     * 下一节目
     */
    void nextShow();

}
