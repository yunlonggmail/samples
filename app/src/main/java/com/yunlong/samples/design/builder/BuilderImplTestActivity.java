package com.yunlong.samples.design.builder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.builder.impl.Builder;
import com.yunlong.samples.design.builder.impl.ConcreteBuilder;
import com.yunlong.samples.design.builder.impl.Director;
import com.yunlong.samples.design.builder.impl.Product;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/10.
 * 简单工厂测试代码
 */

public class BuilderImplTestActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.builder.ImplTest";
    /**
     * 电视
     */
    Product mProduct;

    @Bind(R.id.tv_product)
    TextView tvProduct;

    @Bind(R.id.btn_builder_create_product)
    Button btnCreateProduct;

    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_builder_impl_test;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_builder_impl_test);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnCreateProduct.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_builder_create_product:
                Builder builder = new ConcreteBuilder();
                mProduct = new Director(builder).construct();
                refreshUI();
                break;
        }
    }

    private void refreshUI() {
        if (mProduct != null) {
            tvProduct.setText(mProduct.toString());
        } else {
            tvProduct.setText(R.string.a_design_pattern_builder_test_product_concrete_null);
        }
    }

}
