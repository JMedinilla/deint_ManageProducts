package com.jmedinilla.manageproducts;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jmedinilla.manageproducts.adapter.SpinnerAdapter;
import com.jmedinilla.manageproducts.interfaces.IProductMvp;
import com.jmedinilla.manageproducts.model.Product;
import com.jmedinilla.manageproducts.presenter.ProductPresenter;

/**
 * Class created by JMedinilla on 2016-10-20
 *
 * View for the Products MVP
 */
public class ProductForm_Activity extends AppCompatActivity implements IProductMvp.View {

    //Const
    private static final int MAXIMUM_POSIBLE_VALUE = 9999;
    //Objects
    private IProductMvp.Presenter productMvp;
    //Components
    private TextInputLayout tilName;
    private TextInputLayout tilDescription;
    private TextInputLayout tilConcentration;
    private TextInputLayout tilBrand;
    private TextInputLayout tilPrice;
    private TextInputLayout tilStock;
    private EditText edtName;
    private EditText edtDescription;
    private EditText edtConcentration;
    private EditText edtBrand;
    private EditText edtPrice;
    private EditText edtStock;
    private Spinner product_spinner;

    //Spinner lists
    String[] spinnerText = {
            "Undefined", "Drop", "Syrup", "Pill",
            "Capsule", "Granulated", "Injection", "Powder",
            "Suppository", "Ointment", "Patch", "Inhaler" };
    Integer[] spinnerImage = {
            R.drawable.placehgolder, R.drawable.drop_1, R.drawable.syrup_2, R.drawable.pill_3,
            R.drawable.capsule_4, R.drawable.granulated_5, R.drawable.injection_6, R.drawable.powder_11,
            R.drawable.suppository_7, R.drawable.ointment_8, R.drawable.patch_9, R.drawable.inhaler_10 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_form_activity);

        //Objects
        productMvp = new ProductPresenter(this);

        //Components
        tilName = (TextInputLayout)findViewById(R.id.til_edtProductName);
        tilDescription = (TextInputLayout)findViewById(R.id.til_edtProductDescription);
        tilConcentration = (TextInputLayout)findViewById(R.id.til_edtProductConcentration);
        tilBrand = (TextInputLayout)findViewById(R.id.til_edtProductBrand);
        tilPrice = (TextInputLayout)findViewById(R.id.til_edtProductPrice);
        tilStock = (TextInputLayout)findViewById(R.id.til_edtProductStock);
        edtName = (EditText) findViewById(R.id.edtProductName);
        edtDescription = (EditText)findViewById(R.id.edtProductDescription);
        edtConcentration = (EditText)findViewById(R.id.edtProductConcentration);
        edtBrand = (EditText)findViewById(R.id.edtProductBrand);
        edtPrice = (EditText)findViewById(R.id.edtProductPrice);
        edtStock = (EditText)findViewById(R.id.edtProductStock);
        product_spinner = (Spinner) findViewById(R.id.product_spinner);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, spinnerText, spinnerImage);
        if (product_spinner != null) {
            product_spinner.setAdapter(spinnerAdapter);
        }

        setEditTextOnTextWatcher();
    }

    /**
     * Method that implements the TextChanged listeners on every EditText of the screen
     */
    private void setEditTextOnTextWatcher() {
        //NAME
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user changes the text, the error must be deleted
                tilName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        //DESCRIPTION
        edtDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user changes the text, the error must be deleted
                tilDescription.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        //CONCENTRATION
        edtConcentration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user changes the text, the error must be deleted
                tilConcentration.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        //BRAND
        edtBrand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user changes the text, the error must be deleted
                tilBrand.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        //PRICE
        edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user changes the text, the error must be deleted
                tilPrice.setError(null);
                /**
                 * If we want to read as an Integer, we have to make sure that the EditText
                 * will never have a long number inside when we press the add button and
                 * 7 characters are more than enough for a price
                 */
                if (s.length() > 8) {
                    edtPrice.setText(String.valueOf(MAXIMUM_POSIBLE_VALUE));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        //STOCK
        edtStock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //If the user changes the text, the error must be deleted
                tilStock.setError(null);
                /**
                 * If we want to read as an Integer, we have to make sure that the EditText
                 * will never have a long number inside when we press the add button and
                 * 5 characters are enough for the stock
                 */
                if (s.length() > 5) {
                    edtStock.setText(String.valueOf(MAXIMUM_POSIBLE_VALUE));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });
    }

    /**
     * OnClickListener method for 'product_form_activity.xml' buttons
     * @param view Clicked button
     */
    public void getOnClickProductForm(View view) {
        if (edtPrice.getText().toString().length() == 0) {
            edtPrice.setText("0");
        }
        if (edtStock.getText().toString().length() == 0) {
            edtStock.setText("0");
        }

        switch (view.getId()) {
            case R.id.btnAddProduct:
                //We ask the Presenter ro validate the Product
                if (productMvp.validateProduct(
                        edtName.getText().toString(),
                        edtDescription.getText().toString(),
                        edtConcentration.getText().toString(),
                        edtBrand.getText().toString(),
                        Double.parseDouble(edtPrice.getText().toString()),
                        Integer.parseInt(edtStock.getText().toString()),
                        spinnerImage[product_spinner.getSelectedItemPosition()]
                )) {
                    //If the Product is OK, we create it
                    Product prd = new Product(edtName.getText().toString(),
                            edtDescription.getText().toString(),
                            edtConcentration.getText().toString(),
                            edtBrand.getText().toString(),
                            Double.parseDouble(edtPrice.getText().toString()),
                            Integer.parseInt(edtStock.getText().toString()),
                            spinnerImage[product_spinner.getSelectedItemPosition()]);

                    //And then we save it if it's not already in the list
                    if (!((ProductApplication)getApplication()).getProducts().contains(prd)) {
                        ((ProductApplication) getApplication()).saveProducts(prd);
                        Toast.makeText(ProductForm_Activity.this, "Product saved", Toast.LENGTH_SHORT).show();
                        //After that, we finish the Activity
                        finish();
                    }
                    else {
                        Toast.makeText(ProductForm_Activity.this, "This product already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    /**
     * Method that sends the error so the user can see them
     * @param message Error to show
     * @param idView The component with the error
     */
    @Override
    public void setProductMessageError(String message, int idView) {
        switch (idView) {
            case R.id.edtProductName:
                tilName.setError(message);
                break;
            case R.id.edtProductDescription:
                tilDescription.setError(message);
                break;
            case R.id.edtProductConcentration:
                tilConcentration.setError(message);
                break;
            case R.id.edtProductBrand:
                tilBrand.setError(message);
                break;
            case R.id.edtProductPrice:
                tilPrice.setError(message);
                break;
            case R.id.edtProductStock:
                tilStock.setError(message);
                break;
        }
    }
}