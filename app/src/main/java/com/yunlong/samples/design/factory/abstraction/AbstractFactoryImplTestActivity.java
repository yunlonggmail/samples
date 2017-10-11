package com.yunlong.samples.design.factory.abstraction;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.factory.abstraction.impl.factory.ConcreteFactory1;
import com.yunlong.samples.design.factory.abstraction.impl.factory.ConcreteFactory2;
import com.yunlong.samples.design.factory.abstraction.impl.factory.Factory;
import com.yunlong.samples.design.factory.abstraction.impl.product.AbstractProductA;
import com.yunlong.samples.design.factory.abstraction.impl.product.AbstractProductB;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/10.
 * 简单工厂测试代码
 */

public class AbstractFactoryImplTestActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.factory.abstraction.ImplTest";
    /**
     * 工厂
     */
    Factory factory;
    /**
     * 产品A
     */
    AbstractProductA productA;
    /**
     * 产品B
     */
    AbstractProductB productB;

    @Bind(R.id.tv_product_a)
    TextView tvProductA;

    @Bind(R.id.tv_product_b)
    TextView tvProductB;

    @Bind(R.id.btn_factory_method_produce_product_1)
    Button btnProductProduct1;

    @Bind(R.id.btn_factory_method_produce_product_2)
    Button btnProductProduct2;


    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_factory_abstract_impl_test;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_factory_abstract_impl_test);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnProductProduct1.setOnClickListener(this);
        btnProductProduct2.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_factory_method_produce_product_1:
                factory = new ConcreteFactory1();
                produceProduct();
                break;
            case R.id.btn_factory_method_produce_product_2:
                factory = new ConcreteFactory2();
                produceProduct();
                break;
        }
    }

    private void produceProduct() {
        if (factory != null) {
            productA = factory.createProductA();
            productB = factory.createProductB();
            if (productA != null) {
                tvProductA.setText(productA.doSthA());
            }else{
                tvProductA.setText(R.string.a_design_pattern_factory_abstract_test_product_concrete_null);
            }
            if (productB != null) {
                tvProductB.setText(productB.doSthB());
            } else {
                tvProductB.setText(R.string.a_design_pattern_factory_abstract_test_product_concrete_null);
            }
        } else {
            tvProductA.setText(R.string.a_design_pattern_factory_abstract_test_product_concrete_null);
            tvProductB.setText(R.string.a_design_pattern_factory_abstract_test_product_concrete_null);
        }
    }

}
