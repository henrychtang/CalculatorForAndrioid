package home.henry.calculator;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.KEYCODE_CLEAR;
import static android.view.KeyEvent.KEYCODE_NUMPAD_ADD;
import static android.view.KeyEvent.KEYCODE_NUMPAD_DIVIDE;
import static android.view.KeyEvent.KEYCODE_NUMPAD_DOT;
import static android.view.KeyEvent.KEYCODE_NUMPAD_ENTER;
import static android.view.KeyEvent.KEYCODE_NUMPAD_MULTIPLY;
import static android.view.KeyEvent.KEYCODE_NUMPAD_SUBTRACT;

public class MainActivity extends AppCompatActivity {

    protected KeyboardView keyboardView;
    protected TextView textView, textViewHistory;
    CalculationEngine calculationEngine = new CalculationEngine();
    History history=new History();

    protected String queryString = "8/2+5x2-8";
    boolean showingAnswer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView_input);
        textViewHistory = findViewById(R.id.textView_history);
        keyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
        keyboardView.setPreviewEnabled(false);
        Keyboard keyboard = new Keyboard(MainActivity.this, R.xml.number_pad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(keyboardActionListener);
        keyboardView.setVisibility(View.VISIBLE);
        keyboardView.setEnabled(true);


        textView.setText(queryString);
        calculationEngine.analyseQueryString(queryString);
        calculationEngine.calculate();
        textViewHistory.setText(textViewHistory.getText() + "\n" + calculationEngine.calculate());

    }

    public KeyboardView.OnKeyboardActionListener keyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            long eventTime = System.currentTimeMillis();
            KeyEvent keyEvent = new KeyEvent(eventTime, eventTime, ACTION_DOWN, primaryCode, 0, 0, 0, 0, KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);
            char pressedKey = (char) keyEvent.getUnicodeChar();
            Log.i("pressedKey", pressedKey + "");
            char keycode = (char) keyEvent.getKeyCode();

            if(showingAnswer==true){
                history.add("Ans: " +textView.getText().toString());
                textViewHistory.setText(history.toPrinttableString());
            }
            switch (keycode) {
                case KEYCODE_CLEAR:
                    textView.setText("");
                    showingAnswer = false;
                    break;
                case KEYCODE_NUMPAD_ENTER:
                    if (isValidQuery(textView.getText().toString())) {
                        history.add(textView.getText().toString());
                        calculationEngine.analyseQueryString(textView.getText().toString());
                        textView.setText(calculationEngine.calculate() + "");
                        showingAnswer = true;
                        textViewHistory.setText(history.toPrinttableString());
                    }else if(!textView.getText().toString().equals("")){

                    }
                    break;
                case KEYCODE_NUMPAD_DOT:
                    showingAnswer = false;
                    if (textView.getText().toString().indexOf('.') < 0) {
                        textView.setText(textView.getText().toString() + ".");
                    }
                    break;
                case KEYCODE_NUMPAD_MULTIPLY:
                    showingAnswer = false;
                    textView.setText(textView.getText().toString() + "x");
                    break;
                case KEYCODE_NUMPAD_DIVIDE:
                    showingAnswer = false;
                    textView.setText(textView.getText().toString() + "/");
                    break;
                case KEYCODE_NUMPAD_ADD:
                    showingAnswer = false;
                    textView.setText(textView.getText().toString() + "+");
                    break;
                case KEYCODE_NUMPAD_SUBTRACT:
                    showingAnswer = false;
                    textView.setText(textView.getText().toString() + "-");
                    break;
                default:
                    Log.i("Text (Default action)", pressedKey + "");
                    Log.i("Text (Default action)", textView.getText().toString());
                    if (showingAnswer == false)
                        textView.setText(textView.getText().toString() + pressedKey);
                    else {
                        textView.setText("" + pressedKey);
                        showingAnswer = false;
                    }

            }
            Log.i("keyinfo", "onKey: " + keyEvent.toString());

            if (primaryCode == KeyEvent.KEYCODE_NUMPAD_EQUALS) {
                // displayCalculatedResult();
            }
        }

        boolean isValidQuery(String queryString) {
            char lastChar = queryString.charAt(queryString.length() - 1);
            if (lastChar == '+' ||
                    lastChar == '-' ||
                    lastChar == 'x' ||
                    lastChar == '/')
                return false;
            else
                return true;

        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void swipeUp() {
        }
    };
}
