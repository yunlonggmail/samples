package com.yunlong.samples.design.factory.method;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.samples.R;
import com.yunlong.samples.design.factory.method.impl.factory.ConcreteFactoryA;
import com.yunlong.samples.design.factory.method.impl.factory.ConcreteFactoryB;
import com.yunlong.samples.design.factory.method.impl.factory.Factory;
import com.yunlong.samples.design.factory.method.impl.product.Product;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/10.
 * 简单工厂测试代码
 */

public class FactoryMethodImplTestActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.factory.method.ImplTest";
    /**
     * 工厂
     */
    Factory factory;
    /**
     * 产品
     */
    Product product;

    @Bind(R.id.tv_product)
    TextView tvProduct;

    @Bind(R.id.rb_product_a)
    RadioButton rbProductA;

    @Bind(R.id.rb_product_b)
    RadioButton rbProductB;

    @Bind(R.id.et_product_model)
    EditText etProductModel;

    @Bind(R.id.et_product_desc)
    EditText etProductDesc;

    @Bind(R.id.btn_factory_method_produce_product)
    Button btnProductProduct;


    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_factory_method_impl_test;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_factory_method_impl_test_desc);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnProductProduct.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_factory_method_produce_product:
                produceProduct();
                break;
        }
    }

    private void produceProduct() {
        int model = -1;
        if (rbProductA.isChecked()) {
            factory = new ConcreteFactoryA();
        } else if (rbProductB.isChecked()) {
            factory = new ConcreteFactoryB();
        } else {
            tvProduct.setText(R.string.a_design_pattern_factory_method_test_product_concrete_null);
        }
        if (factory != null) {
            try {
                model = Integer.parseInt(etProductModel.getText().toString());
            } catch (Exception e) {
                model = -1;
            }
            String desc = etProductDesc.getText().toString();
            if (model > 0 && !StringsUtils.isEmpty(desc)) {
                product = factory.createProduct(model, desc);
            } else if (model > 0) {
                product = factory.createProduct(model);
            } else if (!StringsUtils.isEmpty(desc)) {
                product = factory.createProduct(desc);
            } else {
                product = factory.createProduct();
            }
        }
        if (product != null) {
            tvProduct.setText(product.doSomething());
        }
    }

}
