package com.jmedinilla.manageproducts;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jmedinilla.manageproducts.adapter.SpinnerAdapter;
import com.jmedinilla.manageproducts.application.ProductApplication;
import com.jmedinilla.manageproducts.interfaces.IProductMvp;
import com.jmedinilla.manageproducts.model.Product;
import com.jmedinilla.manageproducts.presenter.ProductPresenter;

/**
 * Class created by JMedinilla on 2016-10-20
 * <p>
 * Product view
 */
public class ProductForm_Activity extends AppCompatActivity implements IProductMvp.View {
    private static final int MAXIMUM_POSIBLE_VALUE = 9999;

    private ProductPresenter presenter;

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

    String[] spinnerText = {
            "Undefined", "Drop", "Syrup", "Pill",
            "Capsule", "Granulated", "Injection", "Powder",
            "Suppository", "Ointment", "Patch", "Inhaler"};
    Integer[] spinnerImage = {
            R.drawable.placehgolder, R.drawable.drop_1, R.drawable.syrup_2, R.drawable.pill_3,
            R.drawable.capsule_4, R.drawable.granulated_5, R.drawable.injection_6, R.drawable.powder_11,
            R.drawable.suppository_7, R.drawable.ointment_8, R.drawable.patch_9, R.drawable.inhaler_10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_form_activity);

        presenter = new ProductPresenter(this);

        tilName = (TextInputLayout) findViewById(R.id.til_edtProductName);
        tilDescription = (TextInputLayout) findViewById(R.id.til_edtProductDescription);
        tilConcentration = (TextInputLayout) findViewById(R.id.til_edtProductConcentration);
        tilBrand = (TextInputLayout) findViewById(R.id.til_edtProductBrand);
        tilPrice = (TextInputLayout) findViewById(R.id.til_edtProductPrice);
        tilStock = (TextInputLayout) findViewById(R.id.til_edtProductStock);
        edtName = (EditText) findViewById(R.id.edtProductName);
        edtDescription = (EditText) findViewById(R.id.edtProductDescription);
        edtConcentration = (EditText) findViewById(R.id.edtProductConcentration);
        edtBrand = (EditText) findViewById(R.id.edtProductBrand);
        edtPrice = (EditText) findViewById(R.id.edtProductPrice);
        edtStock = (EditText) findViewById(R.id.edtProductStock);
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
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilDescription.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtConcentration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilConcentration.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtBrand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilBrand.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPrice.setError(null);

                if (s.length() > 8) {
                    edtPrice.setText(String.valueOf(MAXIMUM_POSIBLE_VALUE));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtStock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilStock.setError(null);

                if (s.length() > 5) {
                    edtStock.setText(String.valueOf(MAXIMUM_POSIBLE_VALUE));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_productform, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_ok_form:
                if (edtPrice.getText().toString().length() == 0) {
                    edtPrice.setText("0");
                }
                if (edtStock.getText().toString().length() == 0) {
                    edtStock.setText("0");
                }

                if (presenter.validateProduct(
                        edtName.getText().toString(),
                        edtDescription.getText().toString(),
                        edtConcentration.getText().toString(),
                        edtBrand.getText().toString(),
                        Double.parseDouble(edtPrice.getText().toString()),
                        Integer.parseInt(edtStock.getText().toString()),
                        spinnerImage[product_spinner.getSelectedItemPosition()])) {

                    Product prd = new Product(edtName.getText().toString(),
                            edtDescription.getText().toString(),
                            edtConcentration.getText().toString(),
                            edtBrand.getText().toString(),
                            Double.parseDouble(edtPrice.getText().toString()),
                            Integer.parseInt(edtStock.getText().toString()),
                            spinnerImage[product_spinner.getSelectedItemPosition()]);

                    if (!((ProductApplication) getApplication()).getProducts().contains(prd)) {
                        ((ProductApplication) getApplication()).saveProducts(prd);

                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(ProductForm_Activity.this, "This product already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method that sends the error so the user can see them
     *
     * @param message Error to show
     * @param idView  The component with the error
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