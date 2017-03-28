# DrawableAnimation

## 1.帧动画

帧动画是一系列图片按照一定的顺序展示的过程，和放电影的机制相似我们称之为逐帧动画。

### 1.1 XML代码

说明：XML可以放置在res/drawable中或res/anim中。不过建议放置drawable中。

    <?xml version="1.0" encoding="utf-8"?>
    <animation-list xmlns:android="http://schemas.android.com/apk/res/android"
        android:oneshot="false"><!--oneshot表示是否执行一次-->
        <item
            android:drawable="@mipmap/d0"
            android:duration="100" /><!--该帧动画执行的时间-->
        .............
    </animation-list>

### 1.2 Java代码

     AnimationDrawable animationDrawable = new AnimationDrawable();
     animationDrawable.addFrame(getResources().getDrawable(R.mipmap.d0), 100);//添加一帧，参数1：帧，参数2：执行时间单位ms
     animationDrawable.setOneShot(false);//是否设置执行一次，默认执行一次。
     ivJava.setBackgroundDrawable(animationDrawable);
     animationDrawable.start();

### 1.3 API

|方法|说明|
|---|---|
|start()|开始播放|
|stop()|停止播放|
|addFrame(Drawable frame, int duration)|添加一帧，参数1：帧，参数2：执行时间|
|getDuration(int index)|获得指定帧index的持续时间|
|getFrame(int index)|获得指定帧index的帧 Drawable|
|getNumberOfFrames()|获得帧数量|
|isOneShot()|是否播放一次|
|isRunning()|是否正在播放|
|setOneShot(boolean onShot)|否：表示持续播放，是：表示重复播放|

### 1.4 说明

start()方法不能在Activity的onCreate方法中调用，因为那个时候AnimationDrawable还未完全附着在Window上，如果想要尽快运行，就在onWindowFocusChanged中调用。

## 2 animated-rotate 轻量级旋转动画

轻量级的旋转动画，用于实现简单的滚动动画。

但是需要在创建界面完成后调用ImageView的setResource方法，否则动画不运行。原因在于动画未能完全附着在Window上。直白说法就是UI还未完全创建完成。

在xml中设置，代码如下：

    <animated-rotate xmlns:android="http://schemas.android.com/apk/res/android"
       android:drawable="@mipmap/ball"
       android:pivotX="50%"
       android:pivotY="50%"
       android:visible="true" />
