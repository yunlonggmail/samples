package com.yunlong.samples.design.factory.simple;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.factory.simple.impl.SimpleFactory;
import com.yunlong.samples.design.factory.simple.impl.SimpleProduct;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/10.
 * 简单工厂测试代码
 */

public class SimpleFactoryImplTestActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.factory.simple.ImplTest";
    /**
     * 电视
     */
    SimpleProduct mProduct;

    @Bind(R.id.tv_product)
    TextView tvProduct;

    @Bind(R.id.btn_simple_factory_product_concrete_a)
    Button btnConcreteA;

    @Bind(R.id.btn_simple_factory_product_concrete_b)
    Button btnConcreteB;


    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_factory_simple_impl_test;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_factory_simple_impl_test);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnConcreteA.setOnClickListener(this);
        btnConcreteB.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simple_factory_product_concrete_a:
                mProduct = SimpleFactory.factoryMethod(SimpleFactory.SIMPLE_CONCRETE_PRODUCT_A);
                refreshUI();
                break;
            case R.id.btn_simple_factory_product_concrete_b:
                mProduct = SimpleFactory.factoryMethod(SimpleFactory.SIMPLE_CONCRETE_PRODUCT_B);
                refreshUI();
                break;
        }
    }

    private void refreshUI() {
        if (mProduct != null) {
            tvProduct.setText(mProduct.methodDiff());
        } else {
            tvProduct.setText(R.string.a_design_pattern_factory_simple_test_product_concrete_null);
        }
    }

}
