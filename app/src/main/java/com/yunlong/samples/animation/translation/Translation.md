# Translation

场景切换动画框架

## 1、概念介绍

### 1.1 动画简介

4.4 开始引入，5.0进行了改进

抽象和封装了属性动画


### 1.2 概念简介

1. Scene:保存ViewGroup所有元素的状态，包括View和属性。需要传入父控件
2. Target: 在Scene里的View对象，可以在Scene移除或者添加Target
3. Transition：Scene切花所用的动画信息对象。
4. TransitionSet：动画集合
5. TransitionManager：在Scene间施加相应的Transition动画
6. TransitionManager.beginDelayedTransition：在一个Layout中可以设置两个场景
7. TransitionListener：类似Transition的生命周期回调

### 1.3 动画原理

## 2、自定义Transition综合举例

## 2.1 自定义Transition

1. 修改已有Transition子类的参数
2. 继承Transition类


## 3、Activity和Fragment切换方式介绍

### 3.1 切换方式

1. 非享元切换Transition
2. 享元切换Transition

### 3.2 Transition说明

1. exit transition：A start B,A中View的动画
2. enter transition: A start B，B中View的动画
3. return transition：B return A，B中View的动画
4. reenter transition：B return A，A中View的动画

说明：

如果想把某些部件设置为一个整体，使用 ViewGroup.setTransitionGroup(true);


## 4. 注意事项

1. 在SurfaceView上可能表现不正常。因为SurfaceView不是执行在主UI线程里的。
2. 在TextureView上施加某些Transition效果，可能达不到预期效果。
3. 继承自AdapterView的View，比如ListView上可能会出错。
4. 对TextView的Size施加Transition效果时，文字的位置会突变。
5. 不要通过scene的actions在scene间传递数据，用callback