package com.example.root.drawerlayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    //botão back listener
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //drawer menu item listener click
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_textview) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(150,150);
            layoutParams.gravity = Gravity.CENTER;
            TextView texto = new TextView(this);


            texto.setLayoutParams(layoutParams);
            texto.setText("Texto ");texto.setTextColor(Color.WHITE);
            Log.d("SUCESSO","texto Adicionado");

            FrameLayout container = (FrameLayout) findViewById(R.id.container);
            container.addView(texto);

            texto.setOnTouchListener(new ChoiceTouchListener());

        } else if (id == R.id.nav_editText) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            EditText textbox = new EditText(this);

            textbox.setLayoutParams(layoutParams);
            textbox.setInputType(InputType.TYPE_CLASS_TEXT);
            textbox.setText("Text Box");textbox.setActivated(false);textbox.setBackgroundColor(Color.WHITE);
            Log.d("SUCESSO","edit text Adicionado");

            FrameLayout container = (FrameLayout) findViewById(R.id.container);
            container.addView(textbox);
            textbox.setOnTouchListener(new ChoiceTouchListener());


        } else if (id == R.id.nav_button) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            Button button = new Button(this);

            button.setLayoutParams(layoutParams);
            button.setText("BUTTON");button.setActivated(false);
            Log.d("SUCESSO","button Adicionado");

            FrameLayout container = (FrameLayout) findViewById(R.id.container);
            container.addView(button);

            button.setOnTouchListener(new ChoiceTouchListener());

        } else if (id == R.id.nav_radio){
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            RadioButton radio = new RadioButton(this);

            radio.setLayoutParams(layoutParams);
            radio.setText("Radio");radio.setActivated(false);

            FrameLayout container = (FrameLayout)findViewById(R.id.container);
            container.addView(radio);

            radio.setOnTouchListener(new ChoiceTouchListener());

        } else if(id == R.id.nav_checkBox){
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            CheckBox checkBox = new CheckBox(this);

            checkBox.setLayoutParams(layoutParams);
            checkBox.setText("Check Box");checkBox.setActivated(false);

            FrameLayout container = (FrameLayout) findViewById(R.id.container);
            container.addView(checkBox);

            checkBox.setOnTouchListener(new ChoiceTouchListener());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    final class ChoiceTouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    FrameLayout.LayoutParams Params = (FrameLayout.LayoutParams)view.getLayoutParams();
                    xDelta = X - Params.leftMargin;
                    yDelta = Y - Params.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)view.getLayoutParams();
                    layoutParams.leftMargin = X - xDelta;
                    layoutParams.topMargin = Y - yDelta;
                    layoutParams.rightMargin =  -250;
                    layoutParams.bottomMargin = -250;

                    view.setLayoutParams(layoutParams);
                    break;

            }

            return true;
        }
    }
}

