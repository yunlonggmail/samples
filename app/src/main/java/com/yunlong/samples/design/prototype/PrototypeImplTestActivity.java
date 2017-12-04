package com.yunlong.samples.design.prototype;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.prototype.impl.ConcretePrototype;
import com.yunlong.samples.design.prototype.impl.Prototype;
import com.yunlong.samples.design.prototype.impl.PrototypePart;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/10.
 * 原型模式测试代码
 */

public class PrototypeImplTestActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.prototype.ImplTest";
    /**
     * 原型
     */
    ConcretePrototype concretePrototype;
    /**
     * 拷贝的原型
     */
    Prototype copyPrototype;

    @Bind(R.id.tv_product)
    TextView tvProduct;

    @Bind(R.id.btn_prototype_copy)
    Button btnPrototypeCopy;

    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_prototype_impl_test;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_prototype_impl_test);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnPrototypeCopy.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        concretePrototype = new ConcretePrototype();
        concretePrototype.name = "new name";
        concretePrototype.num = 1;
        concretePrototype.what = true;
        concretePrototype.prototypePart = new PrototypePart();
        concretePrototype.prototypePart.part = "new part ";
        refreshUI(concretePrototype);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_prototype_copy:
                copyPrototype = concretePrototype.clone();
                refreshUI(copyPrototype);
                break;
        }
    }

    private void refreshUI(Prototype prototype) {
        if (prototype != null) {
            String text = concretePrototype.toString() + prototype.toString();
            tvProduct.setText(text);
        } else {
            tvProduct.setText(R.string.a_design_pattern_prototype_test_copy_error);
        }
    }

}
